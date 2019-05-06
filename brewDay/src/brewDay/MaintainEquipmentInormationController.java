package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaintainEquipmentInormationController extends Controller{

	public MaintainEquipmentInormationController(Workbench w) {
		super(w);
	}
	
	public boolean addCapacity(int ID, int capacity) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      int updateCapacity = 0;
	      //	get the correct record from table
	      ResultSet rs = statement.executeQuery("select * from Equipment");
	      
	      if(rs.getInt("ID") == ID) {
	    	  updateCapacity = rs.getInt("capacity") + capacity;
	      }
	        
	      statement.executeUpdate("UPDATE Equipment SET capacity = " + updateCapacity 
  	    		  + " WHERE ID = " + ID);
	      
	      
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
	public boolean subtractCapacity(int ID, int capacity) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      int updateCapacity = 0;
	      //	get the correct record from table
	      ResultSet rs = statement.executeQuery("select * from Equipment");
	      if(rs.getInt("ID") == ID) {
	    	  updateCapacity = rs.getInt("capacity") - capacity;
	      }
	        
	      statement.executeUpdate("UPDATE Equipment SET capacity = " + updateCapacity 
  	    		  + " WHERE ID = " + ID);
	      
	      
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
	
	//	test function
	public static void testStorageIngredientController() {
		Workbench w = new Workbench();
		MaintainEquipmentInormationController s = new MaintainEquipmentInormationController(w);
		
		s.addCapacity(1, 5);
		s.subtractCapacity(1, 6);
	}
}
