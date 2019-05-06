package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BrewController extends Controller {
	public BrewController(Workbench w) {
		super(w);
	}
	
	public boolean implement(Recipe recipe) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec
	      ResultSet rs = statement.executeQuery("select amount from StorageIngredient");
	      //statement.executeUpdate("Impliment Brew = " + r.
	    	//	  + " WHERE ID  = " + recipe.hashCode());
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
	
	public boolean verifyBatchSize(int Batchsize) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      ResultSet rs = statement.executeQuery("select amount from StorageIngredient");
	      if (Batchsize > rs.getFloat("amount")) {
	    	  System.err.println("You do not have enough storage for your requirement!");
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
	
	public static void testBrewController() {
		Workbench w = new Workbench();
		BrewController b = new BrewController(w);
		//b.implement();
	}
}
