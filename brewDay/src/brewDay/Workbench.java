package brewDay;

import static org.junit.Assert.assertFalse;

import java.awt.ScrollPane;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Workbench {

	private ArrayList<ModelListener> listeners;

	public Workbench() {
		this.listeners = new ArrayList<ModelListener>();
	}

	public void addListener(ModelListener l) {
		this.listeners.add(l);
	}

	private void notifyListeners() { // notify all its listeners
		for (ModelListener l : listeners) {
			l.update();
		}
	}
	
	

	// this method check whether the batch size user input is larger than the
	// capacity of equipments
	public boolean checkBatchSize(double batchSize) {
		Connection connection = null;
		boolean result = false;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get all equipment information
			ResultSet rs = statement.executeQuery("select * from Equipment");
			while (rs.next()) {

				// test if the unit is the same as L
				if (!rs.getString("unit").equals("L")) {

					// the unit is not L, so convert it to L
					double capacity = rs.getDouble("capacity");
					String unit = rs.getString("unit");
					int id = rs.getInt("ID");

					Equipment equipment = new Equipment(id, capacity, unit);
					equipment.convertUnit("L");

					if (equipment.getCapacity() > batchSize) {
						System.out.println("Batch size available.");
						result = true;
					}
				} else {
					// test if there is any capacity available
					if (rs.getInt("capacity") > batchSize) {
						System.out.println("Batch size available.");
						result = true;
					}
				}

			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		System.out.println("Batch size unavailable.");
		if(result == false)
			JOptionPane.showMessageDialog(null, "Batch size cannot be larger than capacity of equipment.");
		return result;
	}

	// this method check whether brew is available according to batch size the user
	// input
	public ArrayList<ArrayList> checkBrewAvailable(double batchSize) {
		Connection connection = null;
		System.out.println("Start to check whether brew is available.");
		ArrayList<ArrayList> result = new ArrayList<ArrayList>();
		
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			//	count the number of recipes
			ResultSet countNum = statement.executeQuery("select COUNT(DISTINCT rID) from RecipeAndIngredients");
			int recipeNum = countNum.getInt(1);

			System.out.println("recipeNum = " + recipeNum);
			int curr = 0;
			int rid;
			int ingreNum;

			//array list to store available recipe and shopping list
//			ArrayList availableRecipe = new ArrayList();
//			availableRecipe.add("recipe");
//			ArrayList shoppList = new ArrayList();
//			shoppList.add("shoppingList");
			ArrayList<Recipe> recipes = new ArrayList<Recipe>(); // Object is Recipe
			boolean available = true;
			ArrayList<Boolean> condition = new ArrayList<Boolean>(); // Object is Boolean
			
			// for every recipe, check whether it is available one by one
			while(curr < recipeNum) {
				available = true;
				System.out.println("\n\ncurr: " + curr + " recipeNum : " + recipeNum);
			//while (allrecipes.next()) {	//	why not?????????????????????
				int i = 0;
				Statement statement2 = connection.createStatement();
				ResultSet currRecipe = statement2.executeQuery("select DISTINCT rID from RecipeAndIngredients");
				RecipeIngredient[] recipeIngredient;
				int k = -1;
				
				
				while(k++ < curr) {
					currRecipe.next();
				}
				rid = currRecipe.getInt("rID");
				Statement statement3 = connection.createStatement();
				ResultSet countIngre = statement3.executeQuery("select COUNT(DISTINCT ingredientID) from RecipeAndIngredients WHERE rID = \'" + rid + "\'");
				ingreNum = countIngre.getInt(1);
				
				System.out.println("Recipe " + rid + " has " + ingreNum + " ingredients.");

				System.out.println("Call check recipe " + rid + " in checkBrew method");

				Statement statement4 = connection.createStatement();
				ResultSet recipeWIngre = statement4.executeQuery("SELECT * FROM RecipeAndIngredients, RecipeIngredient"
						+ " WHERE " + " RecipeIngredient.ID = RecipeAndIngredients.ingredientID"
						+ " AND RecipeAndIngredients.rID = \'" + rid + "\'");

				System.out.println("Select all ingredients of recipe " + rid + " finished.");

				
				// the ingredient array part
				recipeIngredient = new RecipeIngredient[ingreNum];
				
				i = 0;
				while (recipeWIngre.next()) {
					System.out.println(recipeWIngre.getString(3));
					recipeIngredient[i++] = new RecipeIngredient(recipeWIngre.getString("name"),
							recipeWIngre.getDouble("amount"), recipeWIngre.getString("unit"),
							recipeWIngre.getInt("ID"));
				}

				System.out.println("construct ingredient list finished.");

				// the recipe information part
				Statement statement5 = connection.createStatement();
				ResultSet recipeInfo = statement5
						.executeQuery("SELECT * FROM Recipe WHERE ID = '" + rid+"'");

				String recipeName = recipeInfo.getString("name");
				double recipeQuantity = recipeInfo.getDouble("quantity");
				String recipeUnit = recipeInfo.getString("unit");

				i = 0;
				while (i < ingreNum) {
					System.out.println(recipeIngredient[i].getName());
					i++;
				}

				System.out.println("recipe name: " + recipeName);
				System.out.println("recipe quantity: " + recipeQuantity);
				System.out.println("recipe unit: " + recipeUnit);
				// construct the recipe instance
				//recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
				recipes.add(new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient));//////////////////////////////
				
				System.out.println("construct recipe " + rid + " finished.");
				System.out.println("Construct recipe finished.");

				// get the absolute converted ingredients information
				boolean converR = ((Recipe)recipes.get(curr)).convertValue(batchSize, "L");
				if (!converR) {
					System.out.println("Convert ingredients value to absolute value failed.");
					continue;	//	this recipe is not available
				}

				// compare the recipe ingredients amount with corresponding storage ingredient
				// one by one
				System.out.println("Convert absolute value of ingredient finished.");

				System.out.println("Convert recipe unit finished, start to prepare the ingredient one by one.");

				//single shopping list for one recipe
				ArrayList shoppListSingle = new ArrayList();
				// compare the amount of ingredient one by one
				for (int i1 = 0; i1 < ingreNum; i1++) {
					Statement statement6 = connection.createStatement();
					ResultSet rs1 = statement6.executeQuery("SELECT * " + " FROM StorageIngredient, RecipeIngredient"
							+ " WHERE StorageIngredient.name = \'" + ((Recipe)recipes.get(curr)).getIngredients()[i1].getName() + "\'"
							+ " AND RecipeIngredient.name = StorageIngredient.name");
					// if units of the recipe ingredient and storage ingredient not same
					// convert recipe ingredient unit
					if (!((Recipe)recipes.get(curr)).getIngredients()[i1].getUnit().equals(rs1.getString(3))) {
						((Recipe)recipes.get(curr)).getIngredients()[i1].convertUnit(rs1.getString(3));
					}
					// compare the amount
					//	the recipe ingredient amount is larger than storage ingredient quantity, so this recipe is not available, break directly
					//	if all recipe ingredient amounts is smaller, then this recipe is available, result is true;
					if (((Recipe)recipes.get(curr)).getIngredients()[i1].getAmount() > rs1.getDouble(2)) {
						System.out.println(((Recipe)recipes.get(curr)).getIngredients()[i1].getName() + " not enough");
						if (available) {
							available = false;
//							shoppListSingle.add(((Recipe)recipes.get(curr)).getName());
//							System.out.println("false");
						}
						((Recipe)recipes.get(curr)).update(((Recipe)recipes.get(curr)).getIngredients()[i1].getName(),rs1.getDouble(2) - ((Recipe)recipes.get(curr)).getIngredients()[i1].getAmount());
					}
//					else if((i1  + 1) == ingreNum){
//						availableRecipe.add(recipe);
//					}
//						System.out.println(recipe.getIngredients()[i1].getName() + " enough");
//					
					
				}
				
				if (available == true) {
					condition.add(true);
				} else {
					condition.add(false);
				}
//					System.out.print(((Recipe) (availableRecipe.get(1))).getName()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//				} else {
//					condition.add(false);
//					shoppList.add(shoppListSingle);
//				}
				curr++;
			}
			//System.out.println(((Recipe)result.get(1)).getName());
			result.add(recipes);
			result.add(condition);
//			System.out.println(recipes.size()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//			System.out.println(condition.size()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!");

//			//to judge if there is available recipe
//			if (availableRecipe.size() <= 1) {
//				result = shoppList;
//			}
//			else {
//				result = availableRecipe;
//			}
			
			
			// return false; // if none available, then false
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		System.out.print("result is "+result.size());
		return result;
	}



	////////////from Eunice
	public ArrayList getRecipe() {
		// connect to database and get available ingredient from recipe
		Connection connection = null;
		ArrayList availableIngredient = new ArrayList();
		ArrayList currentUnit = new ArrayList();
		ArrayList pack = new ArrayList();
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get all available recipe id in database
			ResultSet rsName = statement.executeQuery("select distinct name,unit from StorageIngredient");
			// for every recipe, check whether it is available one by one
			while (rsName.next()) {
				String name = rsName.getString(1);
				availableIngredient.add(name);
				String unit = rsName.getString(2);
				currentUnit.add(unit);
			}

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		pack.add(availableIngredient);
		pack.add(currentUnit);
		return pack;
	}

	public boolean insertRecipe(int loopNum, ArrayList textfieled, ArrayList availableIngredient, ArrayList currentUnit) {
		boolean result = false;
		Connection connection1 = null;
		try {
			// create a database connection
			connection1 = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection1.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// add the recipe to database
			statement.executeUpdate(
					"INSERT INTO Recipe(name,quantity,unit) VALUES ('" + ((JTextArea) textfieled.get(0)).getText()
							+ "','" + ((JTextArea) textfieled.get(1)).getText() + "','L')");
			ResultSet RecipeNum = statement.executeQuery("SELECT MAX(ID) FROM Recipe");
			RecipeNum.next();
			int rID = RecipeNum.getInt(1);
			for (int i = 0; i < loopNum; i++) {
				if (Float.parseFloat(((JTextArea) textfieled.get(i + 2)).getText()) != 0) {
					Statement statement1 = connection1.createStatement();
					statement1.setQueryTimeout(30); // set timeout to 30 sec.
					statement1.executeUpdate(
							"INSERT INTO RecipeIngredient(name,amount,unit) VALUES ('" + availableIngredient.get(i)
									+ "','" + Float.parseFloat(((JTextArea) textfieled.get(i + 2)).getText()) + "','"
									+ currentUnit.get(i) + "');");
					ResultSet ingNum = statement1.executeQuery("SELECT MAX(ID) FROM RecipeIngredient");
					
					Statement statement2 = connection1.createStatement();
					statement2.setQueryTimeout(30); // set timeout to 30 sec.
					
					statement2.executeUpdate("INSERT INTO RecipeAndIngredients VALUES ('" + ingNum.getInt(1) + "','"
							+ RecipeNum.getInt(1) + "')");
				}
			}
			
			
			result = true;
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			try {
				if (connection1 != null)
					connection1.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		if(result == true)
			JOptionPane.showMessageDialog(null, "Add successfully!");
		return result;
	}

	public void deleteRecipe() {
		/////////////////// w.
		// connect to database and get available recipe
		Connection connection = null;
		int sizeAvailableIngredient = 0;
		ArrayList recipe = new ArrayList();
		ArrayList recipeAndIngr = new ArrayList();
		ArrayList ingrID = new ArrayList();
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get all available recipe name in database
			ResultSet rsRecipe = statement.executeQuery("select distinct name,ID from Recipe");
			// add the result to arraylist
			String rID = null;
			String IDSQL = "";
			int first = 0;
			while (rsRecipe.next()) {
				String name = rsRecipe.getString("name");
				recipeAndIngr.add(name);
				recipe.add(recipeAndIngr);
				String ID = rsRecipe.getString("ID");
				if (first == 0) {
					IDSQL = "ID = " + ID;
					rID = "rID = " + ID;
					first = 1;
				} else {
					rID += " OR rID = " + ID;
					IDSQL += " OR ID = " + ID;
				}
			}

			// get ingredient according to recipe ID
			ResultSet rsIngrID = statement.executeQuery("select ingredientID from RecipeAndIngredients WHERE " + rID);
			// select from recipe ingredient from ID
			ResultSet rsIngResultSet = statement.executeQuery("select * from RecipeIngredient where " + IDSQL);

			// statement.executeQuery("delete from RecipeAndIngredient where " + rID);
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
	
	
	//get all recipe and ingredient info
	public ArrayList getRecipeIngredient() {
		// connect to database and get available recipe
		Connection connection = null;
		int sizeAvailableIngredient = 0;
		ArrayList recipeAndIngrPack = new ArrayList();
		//ArrayList recipeAndIngr = new ArrayList();
		ArrayList ingrID = new ArrayList();
		
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> ids = new ArrayList<>();
		
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
	
			// get all available recipe name in database
			ResultSet rsRecipe = statement.executeQuery("SELECT * FROM Recipe");
			
			// add the result to arraylist
			String rID = null;
			String IDSQL = "";
			int first = 0;
			
			while (rsRecipe.next()) {
				//add name
				String name = rsRecipe.getString("name");
				int id = rsRecipe.getInt("ID");
				names.add(name);
				//add ingredient info
				String ID = rsRecipe.getString("ID");
				ids.add(ID);
			}
			
			for(int i = 0; i < ids.size(); i++) {
				ArrayList recipeAndIngr = new ArrayList();
				recipeAndIngr.add(names.get(i));

				Statement statement2 = connection.createStatement();
				ResultSet rsIngrID = statement2.executeQuery("select ingredientID from RecipeAndIngredients WHERE rID = '" + ids.get(i) + "'");
				while(rsIngrID.next() ) {
					Statement statement3 = connection.createStatement();
					ResultSet rsIngResultSet = statement3.executeQuery("select * from RecipeIngredient where ID = " + rsIngrID.getInt("ingredientID"));
						while(rsIngResultSet.next()) {
						String ingredientInfoString = rsIngResultSet.getString("name")+": "+ rsIngResultSet.getString("amount") +  rsIngResultSet.getString("unit") + ";";
						recipeAndIngr.add(ingredientInfoString);
					}
				}
				recipeAndIngrPack.add(recipeAndIngr);
			}
			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		
		return recipeAndIngrPack;
	}


	
	//get notes for recipe
	public ArrayList<Note> getNote(Recipe recipe) {
		// connect to database and get available recipe
		Connection connection = null;
		ArrayList<Note> arrayNote = new ArrayList<Note>();
		
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
	
			// get note from recipe ID
			Statement statement1 = connection.createStatement();
			ResultSet rsRID = statement1.executeQuery("select ID from Recipe where name = \"" + recipe.getName()+"\"");
			Statement statement2 = connection.createStatement();
			ResultSet rsNID = statement2.executeQuery("select nID from Brew where rID = "+rsRID.getInt(1));
			while (rsNID.next()) {
				Statement statement3 = connection.createStatement();
				ResultSet rsNote = statement3.executeQuery("select content, createDate from Note where ID = "+ rsNID.getInt(1));
				Note note = new Note(rsNote.getString("content"), rsNote.getString("createDate"));
				arrayNote.add(note);
			}
			
		} catch (SQLException e) {	
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		return arrayNote;
	}
	
	public boolean brew(Recipe recipe, Brew brew, int nID) {
		// connect to database and get available recipe
		Connection connection = null;
		
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement1 = connection.createStatement();
			statement1.setQueryTimeout(30); // set timeout to 30 sec.
	
			// get note from recipe ID
			for (int i = 0; i < recipe.getIngredients().length; i++) {
				Statement statement2 = connection.createStatement();
				ResultSet rsAmount = statement2.executeQuery("SELECT amount FROM StorageIngredient WHERE name = \"" + recipe.getIngredients()[i].getName()+"\"");
				Statement statement3 = connection.createStatement();
				System.out.print(rsAmount.getDouble(1) +" : "+ recipe.getIngredients()[i].getAmount());
				statement3.executeUpdate("UPDATE StorageIngredient SET amount = " + (rsAmount.getDouble(1) - recipe.getIngredients()[i].getAmount()) + " WHERE name = \"" + recipe.getIngredients()[i].getName()+"\"");
				
			}
			//	write record in brew database
			Statement statement5 = connection.createStatement();
			ResultSet rsRID = statement5.executeQuery("SELECT ID FROM Recipe WHERE name = \"" +recipe.getName() +"\"");
			Statement statement4 = connection.createStatement();
			statement4.executeUpdate("INSERT INTO Brew (batchSize, date, time, rID, nID) VALUES (" + brew.getBatchSize()+ ",\""+brew.getDate()+"\",\""+brew.getTime()+"\","+rsRID.getInt(1)+"," + nID + ")");
			
		} catch (SQLException e) {	
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
			return false;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
				return false;
			}
		}
		return true;		
	}
	
	/************** note *****************/
	//	this method add note to database, then return the nID
	public int addNote(String content, String date) {
		int nID = -1;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			
			//	insert into database
			statement.executeUpdate("INSERT INTO Note (content, createDate) values ('" + content + "','" + date + "')");
			
			//	order the id of content desc to get the largest id in the first row
			//	which is the latest note
			ResultSet rs = statement.executeQuery("SELECT ID FROM Note ORDER BY ID DESC");
			rs.next();
				// read the result set
				System.out.println("id = " + rs.getInt("id"));
				nID = rs.getInt(1);
			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		
		return nID;
	}
	
	//	this method get note from database according to the recipe name
	public String[] getNote(String rName) {

		String[] notes = null;
		int noteNum;
		
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// execute SQL
			ResultSet noteNums = statement.executeQuery("select COUNT(DISTINCT Brew.nID) from Brew, Recipe WHERE Brew.rID = Recipe.ID AND Recipe.name = '" + rName + "'");
			
			Statement statement1 = connection.createStatement();
			ResultSet updateNote = statement1.executeQuery("SELECT content FROM Note, Brew WHERE Brew.id =(SELECT MAX(ID) FROM Brew) AND Brew.nID = Note.ID");
			noteNum = noteNums.getInt(1);
			
			notes = new String[noteNum];

			System.out.println("note number: " + noteNum);
			
			
			Statement s1 = connection.createStatement();
			s1.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet noteContent = s1.executeQuery("SELECT content FROM Note, Brew, Recipe WHERE Brew.rID = Recipe.ID AND Note.ID = Brew.nID AND Recipe.name = '" + rName + "'");

			int i = 0;
			while(noteContent.next() && i < noteNum) {
				notes[i++] = noteContent.getString(1);
			}
			

		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found

			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {

				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}
		return notes;
	}
	
	
	/**************** Equipment *******************/
	
	public String[] getEquipColNames() {
		String[] columnNames = { "ID", "Capacity", "Unit" };
		return columnNames;
	}
	
//	this method get all equipment data from database
	public Object[][] getEquipData() {

		Object[][] data = null;
		int equipNum;
		int colNum = this.getEquipColNames().length;
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// execute SQL
			ResultSet countIngre = statement.executeQuery("select COUNT(DISTINCT ID) from Equipment");
			equipNum = countIngre.getInt(1);

			System.out.println("equipment number: " + equipNum);

			ResultSet ingreInfo = statement.executeQuery("SELECT * FROM Equipment");

			// get the data array ready
			data = new Object[equipNum][colNum];
			for (int i = 0; i < equipNum; i++) {
				ingreInfo.next();
				for (int j = 0; j < colNum; j++) {
					if (j == 0) {
						data[i][j] = ingreInfo.getInt("ID");
					} else if (j == 1) {
						data[i][j] = ingreInfo.getDouble("capacity");
					} else if (j == 2) {
						data[i][j] = ingreInfo.getString("unit");
					} 
				}
			}

		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found

			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {

				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}
		return data;
	}
	
	public boolean deleteEquipment(int id, ScrollPane equipPanel) {

		boolean result = false;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DELETE FROM Equipment WHERE id = '" + id + "' ");
			
//			update the table in main page
					Object[][] equipData = this.getEquipData();
					String[] equipColNames = this.getEquipColNames();
					TablePane equipInfoPane = new TablePane(equipData, equipColNames);
					equipPanel.add(equipInfoPane);
					equipInfoPane.setVisible(true);
			result = true;
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}

		return result;
	}
	
	//	this method update equipment information
	public boolean updateEquipment(int id, double nCapacity, String nUnit, ScrollPane equipPanel) {

		boolean result = false;
		Connection connection = null;
		
		System.out.println("You selected : " + id);
		System.out.println("You input capacity: " + nCapacity);
		System.out.println("You input unit: " + nUnit);
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			statement.executeUpdate("UPDATE Equipment SET capacity = '" + nCapacity + "', unit = '" + nUnit
					+ "' WHERE ID = '" + id + "'");
			
			//			update the table in main page
					Object[][] equipData = this.getEquipData();
					String[] equipColNames = this.getEquipColNames();
					TablePane equipInfoPane = new TablePane( equipData, equipColNames);
					equipPanel.add(equipInfoPane);
					equipInfoPane.setVisible(true);
			result = true;
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}

		return result;
	}
	
	public boolean addEquipment(double quantity, String unit, ScrollPane equipPanel) {
		Connection connection = null;
		boolean result;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("quantity = " + quantity + " unit = " + unit);
			// execute SQL
			statement.executeUpdate("INSERT INTO Equipment (capacity, unit) VALUES ('" + quantity + "', '"
					 + unit + "')");
			System.out.println("Equipment added.");
			
			
			//	update the table in main page
			Object[][] equipData = this.getEquipData();
			String[] equipColNames = this.getEquipColNames();
			TablePane equipInfoPane = new TablePane(equipData, equipColNames);
			equipPanel.add(equipInfoPane);
			equipInfoPane.setVisible(true);
			result = true;
		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			result = false;
			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				result = false;
				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}

		return result;

	}
	
	
	/**************storage ingredient  *************/
	public String[] getStroIngreColNames() {
		String[] columnNames = { "ID", "Name", "Amount", "Unit" };
		return columnNames;
	}
	
	public Object[][] getStorIngreData() {

		Object[][] data = null;
		int ingreNum;
		int colNum = this.getStroIngreColNames().length;
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// execute SQL
			ResultSet countIngre = statement.executeQuery("select COUNT(DISTINCT ID) from StorageIngredient");
			ingreNum = countIngre.getInt(1);

			System.out.println("storage ingredient number: " + ingreNum);

			ResultSet ingreInfo = statement.executeQuery("SELECT * FROM StorageIngredient");

			// get the data array ready
			data = new Object[ingreNum][colNum];
			for (int i = 0; i < ingreNum; i++) {
				ingreInfo.next();
				for (int j = 0; j < colNum; j++) {
					if (j == 0) {
						data[i][j] = ingreInfo.getInt("ID");
					} else if (j == 1) {
						data[i][j] = ingreInfo.getString("name");
					} else if (j == 2) {
						data[i][j] = ingreInfo.getDouble("amount");
					} else if (j == 3) {
						data[i][j] = ingreInfo.getString("unit");
					}
				}
			}

		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found

			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {

				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}
		return data;
	}
	
	public boolean addIngredient(String name, double amount, String unit, ScrollPane storageScroll) {
		Connection connection = null;
		boolean result;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("name = " + name + " amount = " + amount + " unit = " + unit);
			// execute SQL
			statement.executeUpdate("INSERT INTO StorageIngredient (name, amount, unit) VALUES ('" + name + "', "
					+ amount + ", '" + unit + "')");
			System.out.println("ingredient 1 added.");
			
			String[] colNames = this.getStroIngreColNames();
			Object[][] ingreData = this.getStorIngreData();
			TablePane ingreInfoTable = new TablePane(ingreData, colNames);
		
			storageScroll.add(ingreInfoTable);
			result = true;
		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			result = false;
			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				result = false;
				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}

		return result;
	}
	
	public boolean updateIngre(String name, double nAmount, String nUnit, ScrollPane storageScroll) {
		// construct the storage ingredient instance
		boolean result = false;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("UPDATE StorageIngredient SET amount = '" + nAmount + "', unit = \'" + nUnit
					+ "\' WHERE name = \'" + name + "\'");
			
			String[] colNames = this.getStroIngreColNames();
			Object[][] ingreData = this.getStorIngreData();
			TablePane ingreInfoTable = new TablePane(ingreData, colNames);
		
			storageScroll.add(ingreInfoTable);
			result = true;
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}

		return result;
	}
	
	
	public boolean deleteIngre(String name, ScrollPane storageScroll) {
		// construct the storage ingredient instance
		boolean result = false;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DELETE FROM StorageIngredient WHERE name = '" + name + "' ");
			
			String[] colNames = this.getStroIngreColNames();
			Object[][] ingreData = this.getStorIngreData();
			TablePane ingreInfoTable = new TablePane(ingreData, colNames);
		
			storageScroll.add(ingreInfoTable);
			result = true;
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}

		return result;
	}


	// test function
	public static void testWorkbench() {
		Workbench w = new Workbench();

		// w.subtractAmount(1, 5);

	}

}
