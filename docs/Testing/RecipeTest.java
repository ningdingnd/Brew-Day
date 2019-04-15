import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RecipeTest {
	private static String name;
	private static float quantity;
	private static String unit;
	private static RecipeIngredient[] recipeIngredient;
	
	@Before
	public RecipeIngredient[] convertValue (int batchSize) {
		quantity = 500.0;
		batchSize = 1000;
		RecipeIngredient[0] = 15;
		assertEquals(30, Recipe.convertValue (batchSize) / quantity * RecipeIngredient[0]);
	}
	
	public static void setQuantity(float quantity) {
		RecipeTest.quantity = quantity;
	}

	public ShoppingList produceShoppingList() {
		
	}
	
	public float converUnit (float originalQuantity, String originalUnit, String targetUnit) {
		if (originalUnit == "gallons" && targetUnit == "liters") {
			originalQuantity *= 3.7;
		}
		
		
		if (originalUnit == "liters" && targetUnit == "gallons") {
			originalQuantity /= 3.7;
		}
		assertEquals(37, Recipe.converUnit(10.0, "gallons", "liters")); // expect answer should be 37
		assertEquals(20, Recipe.converUnit(74.0, "liters", "gallons")); // expect answer should be 20
	}
	
}
