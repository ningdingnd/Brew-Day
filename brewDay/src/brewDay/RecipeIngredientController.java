package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeIngredientController extends Controller {
	public RecipeIngredientController(Workbench w) {
		super(w);
	}
	
	public boolean updateAmount(int ID, float amount) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      statement.executeUpdate("UPDATE RecipeIngredient SET amount = " + amount 
	    		  + " WHERE ID  = " + ID);
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
	
	public static void testRecipeIngredientController() {
		Workbench w = new Workbench();
		RecipeIngredientController r = new RecipeIngredientController(w);
		
		r.updateAmount(1, 5);
	}
}
