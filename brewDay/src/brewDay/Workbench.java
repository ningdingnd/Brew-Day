package brewDay;
import java.sql.Connection;
import java.sql.DriverManager;
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
	
	
	private void notifyListeners() {	//	notify all its listeners
		for(ModelListener l: listeners) {
			l.update();
		}
	}
	
	public String createIngredient(String name, float amount, String unit) {
		Connection connection = null;
		System.out.println("Before try and catch name: " + name + "amount" + amount + "unit" + unit);
		try {
			System.out.println("name: " + name + "amount" + amount + "unit" + unit);
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("Start to execute sql.");
			// get the correct record from table
			statement.executeUpdate("insert into StorageIngredient (name, amount, unit) values (\'" + name + "\',\'"
					+ amount + "\',\'" + unit + "\')");
		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			// System.err.println(e1.getMessage());
			System.out.println("Failed because of no database file found!");
			return e1.getMessage();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				// connection close failed.
				// System.err.println(e1.getMessage());
				System.out.println("Close connection to database failed.");
				return "Close connection to database failed.";
			}
		}
		return "Add new ingredient success.";
	}
	
	
	
}
