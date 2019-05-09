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

	public boolean checkBatchSize(float batchSize) {
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
	
	
	public boolean checkRecipeAvailable(int rid, int batchSize) {
		Connection connection = null;
		try {
			
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			
			//	get all available recipe id in database
			ResultSet rs = statement.executeQuery("select * " + 
					"FROM RecipeAndIngredients, ReicpeIngredient" + 
					"WHERE RecipeAndIngredients = \' " + rid + '\''+
					", RecipeAndIngredients.rID = RecipeIngredient.ID");
			
			
			// for every recipe, check whether it is available one by one
			while (rs.next()) {
				
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
		return false;
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
