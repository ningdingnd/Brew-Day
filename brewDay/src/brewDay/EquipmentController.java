package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class EquipmentController extends Controller {

	public EquipmentController(Workbench w) {
		super(w);
		// TODO Auto-generated constructor stub
	}
	
	public boolean addEquipment(double quantity, String unit) {
		Connection connection = null;
		boolean result;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("quantity = " + quantity + " unit = " + unit);
			// execute SQL
			statement.executeUpdate("INSERT INTO StorageIngredient (quantity, unit) VALUES ('" + quantity + "', "
					 + unit + "')");
			System.out.println("Equipment added.");
			result = true;
		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			result = false;
			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				result = false;
				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}

		return result;

	}
	
	public boolean deleteEquipment() {
		
		return true;
	}

}
