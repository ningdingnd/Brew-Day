package brewDay;
//This class is implemented by Chris and Jason
public class Recipe {
	private static String name;
	private static float quantity;
	private static String unit;
	private static RecipeIngredient[] recipeIngredient;

	public Recipe(String name, float quantity, String unit, RecipeIngredient[] recipeIngredient) {
		this.unit = unit;
		this.quantity = quantity;
		this.name = name;
		this.recipeIngredient = recipeIngredient;
	}

	public String getName() {
		return this.name;
	}

	public float getQuantity() {
		return this.quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public RecipeIngredient[] viewRecipe() {
		return this.recipeIngredient;
	}

	// update the recipe ingredient
	public boolean update(String name, float newQuantity) {

		// get the recipe ingredient list, find out the correct one
		for (int i = 0; i < recipeIngredient.length; i++) {
			if (recipeIngredient[i].getName().equals(name) == true) {
				recipeIngredient[i].setAmount(newQuantity);
				return true;
			}
		}

		return false;
	}

	public boolean update(String name, String newUnit) {

		// get the recipe ingredient list, find out the correct one
		for (int i = 0; i < recipeIngredient.length; i++) {
			if (recipeIngredient[i].getName().equals(newUnit) == true) {
				recipeIngredient[i].setUnit(newUnit);
				return true;
			}
		}

		return false;
	}

	public RecipeIngredient[] convertValue(int batchSize) {
		// get the recipe ingredient list, find out the correct one
		for (int i = 0; i < recipeIngredient.length; i++) {
			recipeIngredient[i].setAmount((batchSize / quantity) * recipeIngredient[i].getAmount());
		}
		return recipeIngredient;
	}
/*
	public ShoppingList produceShoppingList(){ 
		
		for (int i = 0; i < recipeIngredient.length; i++) {
			if (storageIngredient[i].amount >= recipeIngredient[i].amount) {};
				
			else 
				shoppingList.Ingredient[i] = storageIngredient[i].amount - recipeIngredient[i].amount;
		}
		
		return ShoppingList;
	}
*/
	/*
	 * public double convertUnit(double originalQuantity, String originalUnit,
	 * String targetUnit) { quantity = originalQuantity; unit = targetUnit; for (int
	 * i = 0; i < recipeIngredient.length; i++) { if (recipeIngredient[i].getUnit()
	 * == "gallons"){ quantity *= 3.7; }
	 * 
	 * } if (originalUnit == "gallons" && targetUnit == "liters") { quantity *= 3.7;
	 * // originalQuantity *= 3.7; // return originalQuantity; }
	 * 
	 * if (originalUnit == "liters" && targetUnit == "gallons") { quantity /= 3.7;
	 * // originalQuantity /= 3.7; // return originalQuantity; } return quantity; }
	 * 
	 * public double convertUnit(String targetUnit) { float quantity,
	 * originalQuantity; for (int i = 0; i < recipeIngredient.length; i++) { if
	 * (recipeIngredient[i].getUnit() == targetUnit){
	 * 
	 * } else if (recipeIngredient[i].getUnit() == "gallons" && targetUnit
	 * =="liters"){ recipeIngredient[i].amount *= 3.7; } else if
	 * (recipeIngredient[i].getUnit() == "liters" && targetUnit =="gallons") {
	 * recipeIngredient[i].amount /= 3.7; } } return recipeIngredient; }
	 */
}
