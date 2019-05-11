package brewDay;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		return result;
	}

	// this method check whether brew is available according to batch size the user
	// input
	public boolean checkBrewAvailable(double batchSize) {
		Connection connection = null;
		System.out.println("Start to check whether brew is available.");
		boolean result = false;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			//	count the number of recipes
			ResultSet countNum = statement.executeQuery("select COUNT(DISTINCT rID) from RecipeAndIngredients");
			int recipeNum = countNum.getInt(1);

			System.out.println("recipeNum = " + recipeNum);
			int curr = -1;
			int rid;
			int ingreNum;
			
			// for every recipe, check whether it is available one by one
			while(curr++ < recipeNum) {
			//while (allrecipes.next()) {	//	why not?????????????????????
				int i = 0;
				ResultSet currRecipe = statement.executeQuery("select DISTINCT rID from RecipeAndIngredients");
				RecipeIngredient[] recipeIngredient;
				int k = -1;
				Recipe recipe;
				
				
				while(k++ < curr) {
					currRecipe.next();
				}
				rid = currRecipe.getInt("rID");
				ResultSet countIngre = statement.executeQuery("select COUNT(DISTINCT ingredientID) from RecipeAndIngredients WHERE rID = \'" + rid + "\'");
				ingreNum = countIngre.getInt(1);
				
				System.out.println("Recipe " + rid + " has " + ingreNum + " ingredients.");

				System.out.println("Call check recipe " + rid + " in checkBrew method");

				ResultSet recipeWIngre = statement.executeQuery("SELECT * FROM RecipeAndIngredients, RecipeIngredient"
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
				ResultSet recipeInfo = statement
						.executeQuery("SELECT * " + "FROM Recipe " + "WHERE ID = \'" + rid + "\'");

				String recipeName = recipeInfo.getString("name");
				double recipeQuantity = recipeInfo.getDouble("quantity");
				String recipeUnit = recipeInfo.getString("unit");

				i = 0;
				while (i < ingreNum) {
					System.out.println(recipeIngredient[i].getName());
					i++;
				}

				// construct the recipe instance
				recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
				System.out.println("construct recipe " + rid + "finished.");
				System.out.println("Construct recipe finished.");

				// get the absolute converted ingredients information
				boolean converR = recipe.convertValue(batchSize, "L");
				if (!converR) {
					System.out.println("Convert ingredients value to absolute value failed.");
					continue;	//	this recipe is not available
				}

				// compare the recipe ingredients amount with corresponding storage ingredient
				// one by one
				System.out.println("Convert absolute value of ingredient finished.");

				System.out.println("Convert recipe unit finished, start to prepare the ingredient one by one.");


				// compare the amount of ingredient one by one
				for (int i1 = 0; i1 < ingreNum; i1++) {
					ResultSet rs1 = statement.executeQuery("SELECT * " + " FROM StorageIngredient, RecipeIngredient"
							+ " WHERE StorageIngredient.name = \'" + recipe.getIngredients()[i1].getName() + "\'"
							+ " AND RecipeIngredient.name = StorageIngredient.name");
					// if units of the recipe ingredient and storage ingredient not same
					// convert recipe ingredient unit
					if (!recipe.getIngredients()[i1].getUnit().equals(rs1.getString(3))) {
						recipe.getIngredients()[i1].convertUnit(rs1.getString(3));
					}
					// compare the amount
					//	the recipe ingredient amount is larger than storage ingredient quantity, so this recipe is not available, break directly
					//	if all recipe ingredient amounts is smaller, then this recipe is available, result is true;
					if (recipe.getIngredients()[i1].getAmount() > rs1.getDouble(2)) {
						System.out.println(recipe.getIngredients()[i1].getName() + " not enough");
						break;	
					}else if((i1  + 1) == ingreNum){
						result = true;
					}
					System.out.println(recipe.getIngredients()[i1].getName() + " enough");
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
		return result;
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



	// test function
	public static void testWorkbench() {
		Workbench w = new Workbench();

		// w.subtractAmount(1, 5);

	}

}
