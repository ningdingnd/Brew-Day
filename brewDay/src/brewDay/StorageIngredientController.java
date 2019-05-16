package brewDay;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StorageIngredientController extends Controller {

	private Workbench w;

	public StorageIngredientController(Workbench w) {
		super(w);
		this.w = w;
	}

	public String[] getColNames() {
		String[] columnNames = { "ID", "Name", "Amount", "Unit" };
		return columnNames;
	}

	public Workbench getWorkbench() {
		return this.w;
	}

	public boolean deleteIngre(String name) {
		// construct the storage ingredient instance
		boolean result = false;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DELETE FROM StorageIngredient WHERE name = '" + name + "' ");
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

	public boolean updateIngre(String name, double nAmount, String nUnit) {
		// construct the storage ingredient instance
		boolean result = false;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("UPDATE StorageIngredient SET amount = '" + nAmount + "', unit = \'" + nUnit
					+ "\' WHERE name = \'" + name + "\'");
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

	public Object[][] getData() {

		Object[][] data = null;
		int ingreNum;
		int colNum = this.getColNames().length;
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// execute SQL
			ResultSet countIngre = statement.executeQuery("select COUNT(DISTINCT ID) from StorageIngredient");
			ingreNum = countIngre.getInt(1);

			System.out.println("storage ingredient number: " + ingreNum);

			ResultSet ingreInfo = statement.executeQuery("SELECT * FROM StorageIngredient");

			// get the data array ready
			data = new Object[ingreNum][colNum];
			for (int i = 0; i < ingreNum; i++) {
				ingreInfo.next();
				for (int j = 0; j < colNum; j++) {
					if (j == 0) {
						data[i][j] = ingreInfo.getInt("ID");
					} else if (j == 1) {
						data[i][j] = ingreInfo.getString("name");
					} else if (j == 2) {
						data[i][j] = ingreInfo.getDouble("amount");
					} else if (j == 3) {
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

	public boolean addIngredient(String name, double amount, String unit) {
		Connection connection = null;
		boolean result;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("name = " + name + " amount = " + amount + " unit = " + unit);
			// execute SQL
			statement.executeUpdate("INSERT INTO StorageIngredient (name, amount, unit) VALUES ('" + name + "', "
					+ amount + ", '" + unit + "')");
			System.out.println("ingredient 1 added.");
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

	

	public boolean addAmount(int ID, float amount) {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			float updateAmount = 0;
			// get the correct record from table
			ResultSet rs = statement.executeQuery("select * from StorageIngredient");
			while (rs.next()) {
				if (rs.getInt("ID") == ID) {
					updateAmount = rs.getFloat("amount") + amount;
				}

			}
			statement.executeUpdate("UPDATE StorageIngredient SET amount = " + updateAmount + " WHERE ID = " + ID);

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

	public boolean subtractAmount(int ID, float amount) {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			float updateAmount = 0;
			// get the correct record from table
			ResultSet rs = statement.executeQuery("select * from StorageIngredient");
			while (rs.next()) {
				if (rs.getInt("ID") == ID) {
					updateAmount = rs.getFloat("amount") - amount;
				}

			}
			statement.executeUpdate("UPDATE StorageIngredient SET amount = " + updateAmount + " WHERE ID = " + ID);

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

	// test function
	public static void testStorageIngredientController() {
		Workbench w = new Workbench();
		StorageIngredientController s = new StorageIngredientController(w);

		s.addAmount(1, 5);
		s.subtractAmount(1, 6);
	}
}
