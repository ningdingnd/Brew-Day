package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class EquipmentController extends Controller {

	public EquipmentController(Workbench w) {
		super(w);
		// TODO Auto-generated constructor stub
	}
	
	public String[] getColNames() {
		String[] columnNames = { "ID", "Capacity", "Unit" };
		return columnNames;
	}
	
	//	this method delete equipment information
	public boolean deleteEquipment(int id) {

		boolean result = false;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DELETE FROM Equipment WHERE id = '" + id + "' ");
			result = true;
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

		return result;
	}

	//	this method update equipment information
	public boolean updateEquipment(int id, double nCapacity, String nUnit) {

		boolean result = false;
		Connection connection = null;
		
		System.out.println("You selected : " + id);
		System.out.println("You input capacity: " + nCapacity);
		System.out.println("You input unit: " + nUnit);
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
			statement.executeUpdate("UPDATE Equipment SET capacity = '" + nCapacity + "', unit = '" + nUnit
					+ "' WHERE ID = '" + id + "'");
			result = true;
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

		return result;
	}

	//	this method get all equipment data from database
	public Object[][] getData() {

		Object[][] data = null;
		int equipNum;
		int colNum = this.getColNames().length;
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// execute SQL
			ResultSet countIngre = statement.executeQuery("select COUNT(DISTINCT ID) from Equipment");
			equipNum = countIngre.getInt(1);

			System.out.println("equipment number: " + equipNum);

			ResultSet ingreInfo = statement.executeQuery("SELECT * FROM Equipment");

			// get the data array ready
			data = new Object[equipNum][colNum];
			for (int i = 0; i < equipNum; i++) {
				ingreInfo.next();
				for (int j = 0; j < colNum; j++) {
					if (j == 0) {
						data[i][j] = ingreInfo.getInt("ID");
					} else if (j == 1) {
						data[i][j] = ingreInfo.getDouble("capacity");
					} else if (j == 2) {
						data[i][j] = ingreInfo.getString("unit");
					} 
				}
			}

		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found

			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {

				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}
		return data;
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
			statement.executeUpdate("INSERT INTO Equipment (capacity, unit) VALUES ('" + quantity + "', '"
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
