package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeController extends Controller {
	public RecipeController(Workbench w) { // constructor
		super(w);
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
			/*
			 * while (rs.next()) { System.out.println("ingredient id  = " +
			 * rs.getInt("ingredientID")); //int rid = rs.getInt("ingredientID");
			 * 
			 * ResultSet rs1 =
			 * statement.executeQuery("select * from RecipeIngredient WHERE ID = " +
			 * rs.getInt("ingredientID"));
			 * 
			 * // get the result set //System.out.println("ingredient = " +
			 * rs1.getString("name")); //System.out.println("amount = " +
			 * rs1.getInt("amount")); //System.out.println("unit = " +
			 * rs1.getString("unit"));
			 * 
			 * }
			 */

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
	
	//public boolean update(int ID, String name, float newQuantity) {	//	update ingredient quantity of recipe
		
	//}

	public static void testRecipeController() {
		Workbench w = new Workbench();
		RecipeController r = new RecipeController(w);

		r.viewRecipe(1);
	}
}
