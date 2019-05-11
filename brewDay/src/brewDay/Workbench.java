package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
						return true;
					}
				} else {
					// test if there is any capacity available
					if (rs.getInt("capacity") > batchSize) {
						System.out.println("Batch size available.");
						return true;
					}
				}

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
		System.out.println("Batch size unavailable.");
		return false;
	}

	// this method check whether brew is available according to batch size the user
	// input
	public boolean checkBrewAvailable(double batchSize) {
		Connection connection = null;
		System.out.println("Start to check whether brew is available.");
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get all available recipe id in database
			ResultSet recipes = statement.executeQuery("select DISTINCT rID from RecipeAndIngredients");

			// for every recipe, check whether it is available one by one
			while (recipes.next()) {
				int rid = recipes.getInt("rID");
				System.out.println("Call check recipe " + rid + " in checkBrew method");

				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				ResultSet recipeWIngre = statement
						.executeQuery("SELECT * FROM RecipeAndIngredients, RecipeIngredient, Recipe"
								+ "WHERE RecipeAndIngredients.rID = Recipe.ID"
								+ "AND RecipeIngredient.ID = RecipeAndIngredients.ingredientID"
								+ "AND RecipeAndIngredients.rID = \'" + rid + "\'");

				// start to construct the recipe instance
				int ingreNumber = 0;
				while (recipeWIngre.next()) {
					ingreNumber++;
					if (recipeWIngre.isLast())
						break;
				}
				System.out.println("Recipe " + rid + "has " + ingreNumber + "ingredients.");

				// the ingredient array part
				RecipeIngredient[] recipeIngredient = new RecipeIngredient[ingreNumber];

				int i = 0;
				while (recipeWIngre.next()) {
					recipeIngredient[i].setName(recipeWIngre.getString("RecipeIngredient.name"));
					recipeIngredient[i].setAmount(recipeWIngre.getFloat("RecipeIngredient.amount"));
					recipeIngredient[i].setUnit(recipeWIngre.getString("RecipeIngredient.unit"));
					i++;
				}

				System.out.println("construct ingredient list finished.");

				// the recipe information part
				ResultSet recipeInfo = statement
						.executeQuery("SELECT *" + "FROM Recipe" + "WHERE ID = \'" + rid + "\'");

				String recipeName = recipeInfo.getString("name");
				double recipeQuantity = recipeInfo.getDouble("quantity");
				String recipeUnit = recipeInfo.getString("unit");

				// construct the recipe instance
				Recipe recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
				System.out.println("construct recipe " + rid + "finished.");
				System.out.println("Construct recipe finished.");

				// get the absolute converted ingredients information
				boolean converR = recipe.convertValue(batchSize, "L");
				if (!converR) {
					System.out.println("Convert ingredients value to absolute value failed.");
					return false;
				}

				// compare the recipe ingredients amount with corresponding storage ingredient
				// one by one
				System.out.println("Convert absolute value of ingredient finished.");

				System.out.println("Convert recipe unit finished, start to prepare the ingredient one by one.");

				// compare the amount of ingredient one by one
				for (int i1 = 0; i1 < recipe.getIngredients().length; i1++) {
					ResultSet rs1 = statement.executeQuery("SELECT *" + "FROM StorageIngredient, RecipeIngredient"
							+ "WHERE StorageIngredient.name = \'" + recipe.getIngredients()[i1].getName() + "\'"
							+ " AND RecipeIngredient.name = StorageIngredient.name");
					// if units of the recipe ingredient and storage ingredient not same
					// convert recipe ingredient unit
					if (!recipe.getIngredients()[i1].getUnit().equals(rs1.getString("StorageIngredient.unit"))) {
						recipe.getIngredients()[i1].convertUnit(rs1.getString("StorageIngredient.unit"));
					}
					// compare the amount
					if (recipe.getIngredients()[i1].getAmount() > rs1.getDouble("StorageIngredient.amount")) {
						return false;
					}
				}

			}
			// }
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
		return true;
	}

	// this method check whether one specific recipe is available according to batch
	// size
	public boolean checkRecipeAvailable(int rid, double batchSize, Connection connection) {

		System.out.println("Start to select recipe ingredient of recipe " + rid);
		try {

			// construct the recipe instance
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM RecipeAndIngredients, RecipeIngredient, Recipe"
					+ "WHERE RecipeAndIngredients.rID = Recipe.ID"
					+ "AND RecipeIngredient.ID = RecipeAndIngredients.ingredientID"
					+ "AND RecipeAndIngredients.rID = \'" + rid + "\'");

			// start to construct the recipe instance
			int ingreNumber = rs.getFetchSize();
			System.out.println("Recipe " + rid + "has " + ingreNumber + "ingredients.");

			// the ingredient array part
			RecipeIngredient[] recipeIngredient = new RecipeIngredient[ingreNumber];

			int i = 0;
			while (rs.next()) {
				recipeIngredient[i].setName(rs.getString("name"));
				recipeIngredient[i].setAmount(rs.getFloat("amount"));
				recipeIngredient[i].setUnit(rs.getString("unit"));
				i++;
			}

			System.out.println("construct ingredient list finished.");

			// the recipe information part
			ResultSet recipeInfo = statement.executeQuery("SELECT *" + "FROM Recipe" + "WHERE ID = \'" + rid + "\'");

			String recipeName = recipeInfo.getString("name");
			double recipeQuantity = recipeInfo.getDouble("quantity");
			String recipeUnit = recipeInfo.getString("unit");

			// construct the recipe instance
			Recipe recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
			System.out.println("construct recipe " + rid + "finished.");
			System.out.println("Construct recipe finished.");

			// get the absolute converted ingredients information
			boolean converR = recipe.convertValue(batchSize, "L");
			if (!converR) {
				System.out.println("Convert ingredients value to absolute value failed.");
				return false;
			}

			// compare the recipe ingredients amount with corresponding storage ingredient
			// one by one
			System.out.println("Convert absolute value of ingredient finished.");

			System.out.println("Convert recipe unit finished, start to prepare the ingredient one by one.");

			// compare the amount of ingredient one by one
			for (int i1 = 0; i1 < recipe.getIngredients().length; i1++) {
				ResultSet rs1 = statement.executeQuery("SELECT *" + "FROM StorageIngredient, RecipeIngredient"
						+ "WHERE StorageIngredient.name = \'" + recipe.getIngredients()[i1].getName() + "\'"
						+ " AND RecipeIngredient.name = StorageIngredient.name");
				// if units of the recipe ingredient and storage ingredient not same
				// convert recipe ingredient unit
				if (!recipe.getIngredients()[i1].getUnit().equals(rs1.getString("StorageIngredient.unit"))) {
					recipe.getIngredients()[i1].convertUnit(rs1.getString("StorageIngredient.unit"));
				}
				// compare the amount
				if (recipe.getIngredients()[i1].getAmount() > rs1.getDouble("StorageIngredient.amount")) {
					return false;
				}
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
		// if none of the ingredients are unavailable, the recipe is available
		return true;
	}

	// this method construct a recipe instance according to its id in database
	public Recipe constructRecipe(int rid, Connection connection) {
		// Connection connection = null;
		System.out.println("Start to construct recipe.");
		try {

			// create a database connection
			// connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get the recipe ingredients in database
			ResultSet rs = statement.executeQuery("SELECT *" + "FROM RecipeAndIngredients, RecipeIngredient, Recipe"
					+ "WHERE RecipeAndIngredients.rID = Recipe.ID"
					+ "AND RecipeIngredient.ID = RecipeAndIngredients.ingredientID"
					+ "AND RecipeAndIngredients.rID = \'" + rid + "\'");

			// start to construct the recipe instance
			int ingreNumber = rs.getFetchSize();
			System.out.println("Recipe " + rid + "has " + ingreNumber + "ingredients.");
			// the ingredient array part
			RecipeIngredient[] recipeIngredient = new RecipeIngredient[ingreNumber];

			int i = 0;
			while (rs.next()) {
				recipeIngredient[i].setName(rs.getString("name"));
				recipeIngredient[i].setAmount(rs.getFloat("amount"));
				recipeIngredient[i].setUnit(rs.getString("unit"));
				i++;
			}

			System.out.println("construct ingredient list finished.");

			// the recipe information part
			ResultSet recipeInfo = statement.executeQuery("SELECT *" + "FROM Recipe" + "WHERE ID = \'" + rid + "\'");

			String recipeName = recipeInfo.getString("name");
			double recipeQuantity = recipeInfo.getDouble("quantity");
			String recipeUnit = recipeInfo.getString("unit");

			// construct the recipe instance
			Recipe recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
			System.out.println("construct recipe " + rid + "finished.");
			return recipe; // if none available, then false
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
		return null;
	}

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
			ResultSet rsName = statement.executeQuery("select distinct name,unit from RecipeIngredient");
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

	public void insertRecipe(int loopNum, ArrayList textfieled, ArrayList availableIngredient, ArrayList currentUnit) {
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
			ResultSet RecipeNum = statement.executeQuery("SELECT ID FROM Recipe ORDER BY ID DESC");
			for (int i = 0; i < loopNum; i++) {
				if (Float.parseFloat(((JTextArea) textfieled.get(i + 2)).getText()) != 0) {
					statement.executeUpdate(
							"INSERT INTO RecipeIngredient(name,amount,unit) VALUES ('" + availableIngredient.get(i)
									+ "','" + Float.parseFloat(((JTextArea) textfieled.get(i + 2)).getText()) + "','"
									+ currentUnit.get(i) + "');");
					ResultSet ingNum = statement.executeQuery("SELECT ID FROM RecipeIngredient ORDER BY ID DESC");
					statement.executeUpdate("INSERT INTO RecipeAndIngredients VALUES ('" + ingNum.getInt(1) + "','"
							+ RecipeNum.getInt(1) + "')");
				}
			}

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection1 != null)
					connection1.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
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
				//add name
				String name = rsRecipe.getString("name");
				recipeAndIngr.add(name);
				//add ingredient info
				String ID = rsRecipe.getString("ID");
				ResultSet rsIngrID = statement.executeQuery("select ingredientID from RecipeAndIngredients WHERE rID = " + ID);
				ResultSet rsIngResultSet = statement.executeQuery("select * from RecipeIngredient where ID = " + rsIngrID.getInt("ingredientID"));
				String ingredientInfoString = rsIngResultSet.getString("name")+": "+ rsIngResultSet.getString("amount") +  rsIngResultSet.getString("unit") + ";";
				recipeAndIngr.add(ingredientInfoString);
				
				recipeAndIngrPack.add(recipeAndIngr);
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
		return recipeAndIngrPack;
	}
	
	

	/*
	 * public boolean subtractAmount(int ID, float amount) { Connection connection =
	 * null; try { // create a database connection connection =
	 * DriverManager.getConnection("jdbc:sqlite:data.db"); Statement statement =
	 * connection.createStatement(); statement.setQueryTimeout(30); // set timeout
	 * to 30 sec.
	 * 
	 * float updateAmount = 0; // get the correct record from table ResultSet rs =
	 * statement.executeQuery("select * from StorageIngredient"); while (rs.next())
	 * { if (rs.getInt("ID") == ID) { updateAmount = rs.getFloat("amount") - amount;
	 * }
	 * 
	 * } statement.executeUpdate("UPDATE StorageIngredient SET amount = " +
	 * updateAmount + " WHERE ID = " + ID);
	 * 
	 * } catch (SQLException e) { // if the error message is "out of memory", // it
	 * probably means no database file is found System.err.println(e.getMessage());
	 * } finally { try { if (connection != null) connection.close(); } catch
	 * (SQLException e) { // connection close failed.
	 * System.err.println(e.getMessage()); } } return true; }
	 * 
	 */

	// test function
	public static void testWorkbench() {
		Workbench w = new Workbench();

		// w.subtractAmount(1, 5);

	}

}
