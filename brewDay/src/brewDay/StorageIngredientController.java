package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StorageIngredientController extends Controller{

	public StorageIngredientController(Workbench w) {
		super(w);
	}
	
	public boolean addAmount(int ID, float amount) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      float updateAmount = 0;
	      //	get the correct record from table
	      ResultSet rs = statement.executeQuery("select * from StorageIngredient");
	      while(rs.next())
	      {
	        if(rs.getInt("ID") == ID) {
	        	updateAmount = rs.getFloat("amount") + amount;
	        }
	        
	      }
	      statement.executeUpdate("UPDATE StorageIngredient SET amount = " + updateAmount 
  	    		  + "WHERE ID = " + ID);
	      
	      
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
	
	public static void testStorageIngredientController() {
		Workbench w = new Workbench();
		StorageIngredientController s = new StorageIngredientController(w);
		
		s.addAmount(1, 5);
	}
}
