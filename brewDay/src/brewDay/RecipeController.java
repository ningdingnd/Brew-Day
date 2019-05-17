package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RecipeController extends Controller {
	public RecipeController(Workbench w) { // constructor
		super(w);
	}

	public String[] getRecipeNames() {
		String[] names = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("select COUNT(DISTINCT ID) from Recipe");
			int recipeNum = rs.getInt(1);
			System.out.println("recipeNum: " + recipeNum);
			
			//	construct the names string list
			names = new String[recipeNum];
			int i = 0;
			
			Statement s1 = connection.createStatement();
			s1.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet rs1 = s1.executeQuery("select name from Recipe");
			
			
			while (rs1.next()) {
				// read the result set
				names[i++] = rs1.getString(1);
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
		return names;
	}

	public String[] getRecipeIngreColNames() {
		String[] columnNames = { "ID", "Name", "Amount", "Unit" };
		return columnNames;
	}

	public Object[][] getRecipeIngreData(String name) {

		Object[][] data = null;
		int ingreNum;
		int colNum = this.getRecipeIngreColNames().length;
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// get number of recipe ingredients of this recipe
			ResultSet countIngre = statement.executeQuery(
					"select COUNT(DISTINCT ingredientID) from RecipeAndIngredients, Recipe WHERE name = '" + name + "' AND RecipeAndIngredients.rID = Recipe.ID");
			ingreNum = countIngre.getInt(1);

			System.out.println("ingredient number: " + ingreNum);

			ResultSet ingreInfo = statement.executeQuery(
					"SELECT * FROM RecipeIngredient, RecipeAndIngredients, Recipe WHERE RecipeIngredient.ID = RecipeAndIngredients.ingredientID AND RecipeAndIngredients.ingredientID = RecipeIngredient.ID AND Recipe.name = '" + name + "'");

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

	public boolean viewRecipe(int ID) {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("recipe id  = " + ID);
			// get the recipe with corresponding ingredient table
			ResultSet rs = statement.executeQuery("select ingredientID from RecipeAndIngredients WHERE rID = " + ID);

			// search for the recipe ingredients

			while (rs.next()) {
				System.out.println("ingredient id  = " + rs.getInt("ingredientID")); // int rid =
																						// rs.getInt("ingredientID");

				ResultSet rs1 = statement
						.executeQuery("select * from RecipeIngredient WHERE ID = " + rs.getInt("ingredientID"));

				// get the result set //System.out.println("ingredient = " +
				// rs1.getString("name")); //System.out.println("amount = " +
				// rs1.getInt("amount")); //System.out.println("unit = " +
				// rs1.getString("unit"));

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
		return true;
	}

	// public boolean update(int ID, String name, float newQuantity) { // update
	// ingredient quantity of recipe

	// }

	public static void testRecipeController() {
		Workbench w = new Workbench();
		RecipeController r = new RecipeController(w);

		r.viewRecipe(2);
	}
}
