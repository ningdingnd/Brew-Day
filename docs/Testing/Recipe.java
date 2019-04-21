
public class Recipe {
	private static String name;
	private static double quantity;
	private static String unit;
	// private static RecipeIngredient recipeIngredient[];
	private static double recipeIngredient;

	public double convertValue(int batchSize) {
		quantity = 500.0;
		recipeIngredient = 15.0;

		return batchSize / quantity * recipeIngredient;
	}

	/*
	 * public ShoppingList produceShoppingList(){ //will be implemented by my Zeyu
	 * Wang. }
	 */

	public double convertUnit(double originalQuantity, String originalUnit, String targetUnit) {
		quantity = originalQuantity;
		unit = targetUnit;
		if (originalUnit == "gallons" && targetUnit == "liters") {
			quantity *= 3.7;
			// originalQuantity *= 3.7;
			// return originalQuantity;
		}

		if (originalUnit == "liters" && targetUnit == "gallons") {
			quantity /= 3.7;
			// originalQuantity /= 3.7;
			// return originalQuantity;
		}
		return quantity;
	}

}
