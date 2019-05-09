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

	//	this method check whether the batch size user input is larger than the capacity of equipments
	public boolean checkBatchSize(int batchSize) {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rs = statement.executeQuery("select * from Equipment");
			while (rs.next()) {
				// read the result set
				if(rs.getInt("capacity") > batchSize)
					return true;
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
		return false;
	}
	
	
	//	this method check whether brew is available according to batch size the user input
	public boolean checkBrewAvailable(int batchSize) {
		Connection connection = null;
		try {
			//ArrayList<RecipeIngredient> ri = new ArrayList<RecipeIngredient>();
			
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			
			//	get all available recipe id in database
			ResultSet rs = statement.executeQuery("select DISTINCT rID from RecipeAndIngredients");
			
			
			// for every recipe, check whether it is available one by one
			while (rs.next()) {
				int rid = rs.getInt("rID");
				boolean availableRecipe = this.checkRecipeAvailable(rid, batchSize);
				if(availableRecipe == true)	//	if one of the recipe is available, then available
					return true;
			}
			return false;	//	if none available, then false
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
	
	//	this method check whether one specific recipe is available according to batch size 
	public boolean checkRecipeAvailable(int rid, int batchSize) {
		Recipe recipe = constructRecipe(rid);	//	construct the recipe instance
		
		//	get the converted ingredients information
		RecipeIngredient[] conIngre = new RecipeIngredient[recipe.getIngredients().length];
		conIngre = recipe.convertValue(batchSize);
		
		//	compare the recipe ingredients amount with corresponding storage ingredient one by one
		Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * from StorageIngredient"
	      		+ "WHERE StorageIngredient.name = RecipeIngredient.Name");
	      while(rs.next())
	      {
	        if (!rs.getString("StorageIngredient.unit").equals(rs.getString("RecipeIngredient.unit"))){
	        	//	convert unit, unfinished!
	        }
	        
	        System.out.println("StorageIngredient unit is: " + rs.getString("StorageIngredient.unit"));
	        System.out.println("Recipe ingredient unit is: " + rs.getString("RecipeIngredient.unit"));

	        //	compare the recipe ingredient amount with storage ingredient amount
	        //	if the storage ingredient is larger or equal, go to next iteration
	        //	if the storage ingredient is smaller, return false directly
	        if(rs.getFloat("StorageIngredient.amount") < rs.getFloat("RecipeIngredient.amount")) {
	        	return false;
	        }
	      }
	    }
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory",
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e.getMessage());
	      }
	    }
		
		return true;
	}
	
	
	
	//	this method construct a recipe instance according to its id in database
	public Recipe constructRecipe(int rid) {
		Connection connection = null;
		try {
			
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			//	get the recipe ingredients in database
			ResultSet rs = statement.executeQuery("select * " + 
					"FROM RecipeAndIngredients, ReicpeIngredient" + 
					"WHERE RecipeAndIngredients = \' " + rid + '\''+
					", RecipeAndIngredients.ingredientID = RecipeIngredient.ID");
			
			
			//	start to construct the recipe instance
			int ingreNumber = rs.getFetchSize();
			
			//	the ingredient array part
			RecipeIngredient[] recipeIngredient = new RecipeIngredient[ingreNumber];
			
			int i = 0;
			while (rs.next()) {
				recipeIngredient[i].setName(rs.getString("name"));
				recipeIngredient[i].setAmount(rs.getFloat("amount"));
				recipeIngredient[i].setUnit(rs.getString("unit"));
				i++;
			}
			
			//	the recipe information part
			ResultSet recipeInfo = statement.executeQuery("SELECT * "
					+ "FROM Recipe"
					+ "WHERE ID = \'" + rid + "\'");
			
			String recipeName = recipeInfo.getString("name");
			float recipeQuantity = recipeInfo.getFloat("quantity");
			String recipeUnit = recipeInfo.getString("unit");
			
			
			//	construct the recipe instance
			Recipe recipe = new Recipe(recipeName, recipeQuantity, recipeUnit, recipeIngredient);
			return recipe;	//	if none available, then false
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

		//w.subtractAmount(1, 5);

	}

}
