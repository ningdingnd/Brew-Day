package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteNoteController extends Controller{

	public WriteNoteController(Workbench w) {
		super(w);
	}
	
	public boolean editNote(int ID, String content) {
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      String updateContent="";
	      //	get the correct record from table
	      ResultSet rs = statement.executeQuery("select * from Note");
	      
	      if(rs.getInt("ID") == ID) {
	    	  updateContent = rs.getString("content") + content;
	      }
	        
	      statement.executeUpdate("UPDATE Note SET content = " + updateContent 
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
		WriteNoteController s = new WriteNoteController(w);
		
		s.editNote(1, "Nothing");
	}
}
