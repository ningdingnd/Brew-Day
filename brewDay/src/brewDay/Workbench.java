package brewDay;

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
			ResultSet rs = statement.executeQuery("select DISTINCT rID from RecipeAndIngredients");

			// for every recipe, check whether it is available one by one
			while (rs.next()) {
				int rid = rs.getInt("rID");
				System.out.println("Call check recipe " + rid + " in checkBrew method");;
				boolean availableRecipe = this.checkRecipeAvailable(rid, batchSize);
				if (availableRecipe == true) // if one of the recipe is available, then available
					return true;
			}
			return false; // if none available, then false
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
	public boolean checkRecipeAvailable(int rid, double batchSize) {
		System.out.println("Start to select recipe ingredient of recipe " + rid);
		
		
		// construct the recipe instance
		Recipe recipe = constructRecipe(rid); 
		
		System.out.println("construct recipe finished.");
		
		// get the converted ingredients information
		RecipeIngredient[] conIngre = new RecipeIngredient[recipe.getIngredients().length];
		conIngre = recipe.convertValue(batchSize);

		// compare the recipe ingredients amount with corresponding storage ingredient
		// one by one
		Connection connection = null;
		
		try {
			connection = null;
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("Start to execute SQL.");
			//	get the same ingredient from storage ingredient
			ResultSet rs = statement.executeQuery(
					"select *" + 
					"from StorageIngredient, RecipeIngredient, RecipeAndIngredients" + 
					"WHERE StorageIngredient.name = RecipeIngredient.name" + 
					"AND RecipeIngredient.ID = RecipeAndIngredients.rID" + 
					"AND RecipeIngredient.ID = \'" + rid + "\'");
			System.out.println("Select recipe ingredient success.");
			while (rs.next()) {
				// if the current ingredient unit is not the same as storage ingredient unit
				// make them unit the same to compare
				if (!rs.getString("StorageIngredient.unit").equals(rs.getString("RecipeIngredient.unit"))) {
					// construct the recipe ingredient
					String name = rs.getString("RecipeIngredient.name");
					String unit = rs.getString("RecipeIngredient.name");
					double amount = rs.getDouble("RecipeIngredient.amount");
					int id = rs.getInt("RecipeIngredient.ID");

					RecipeIngredient ingredient = new RecipeIngredient(name, amount, unit, id);

					// convert the unit to storage ingredient unit
					boolean convertR = ingredient.convertUnit(rs.getString("StorageIngredient.unit"));

					// compare them
					if (convertR == false) {
						// raise an exception that the unit cannot be identified
						// convert failed
					} else {
						if (rs.getDouble("StorageIngredient.amount") < ingredient.getAmount()) {
							return false;
						}
					}
				}

				System.out.println("StorageIngredient unit is: " + rs.getString("StorageIngredient.unit"));
				System.out.println("Recipe ingredient unit is: " + rs.getString("RecipeIngredient.unit"));

				// compare the recipe ingredient amount with storage ingredient amount
				// if the storage ingredient is larger or equal, go to next iteration
				// if the storage ingredient is smaller, return false directly
				if (rs.getFloat("StorageIngredient.amount") < rs.getFloat("RecipeIngredient.amount")) {
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

		return true;
	}

	// this method construct a recipe instance according to its id in database
	public Recipe constructRecipe(int rid) {
		Connection connection = null;
		try {

			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get the recipe ingredients in database
			ResultSet rs = statement.executeQuery(
					"select * " + "FROM RecipeAndIngredients, ReicpeIngredient" + "WHERE RecipeAndIngredients = \' "
							+ rid + '\'' + ", RecipeAndIngredients.ingredientID = RecipeIngredient.ID");

			// start to construct the recipe instance
			int ingreNumber = rs.getFetchSize();

			// the ingredient array part
			RecipeIngredient[] recipeIngredient = new RecipeIngredient[ingreNumber];

			int i = 0;
			while (rs.next()) {
				recipeIngredient[i].setName(rs.getString("name"));
				recipeIngredient[i].setAmount(rs.getFloat("amount"));
				recipeIngredient[i].setUnit(rs.getString("unit"));
				i++;
			}

			// the recipe information part
			ResultSet recipeInfo = statement.executeQuery("SELECT * " + "FROM Recipe" + "WHERE ID = \'" + rid + "\'");

			String recipeName = recipeInfo.getString("name");
			float recipeQuantity = recipeInfo.getFloat("quantity");
			String recipeUnit = recipeInfo.getString("unit");

			// construct the recipe instance
			Recipe recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
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
