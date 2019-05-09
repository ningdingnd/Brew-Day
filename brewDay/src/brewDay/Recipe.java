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

	public String getName() {	//	get name attribute of recipe
		return this.name;
	}

	public float getQuantity() {	//	get quantity attribute of recipe
		return this.quantity;	
	}

	public String getUnit() {	//	get unit attribute of recipe
		return this.unit;
	}
	

	public RecipeIngredient[] getIngredients() {	//	get ingredients of recipe
		return this.recipeIngredient;
	}

	// update the recipe ingredient quantity only
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

	//	update the ingredient unit only
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
		RecipeIngredient[] conIngre= new RecipeIngredient[recipeIngredient.length];
		for (int i = 0; i < recipeIngredient.length; i++) {
		
			conIngre[i].setName(recipeIngredient[i].getName());
			//	convert the amount
			conIngre[i].setAmount((batchSize / quantity) * recipeIngredient[i].getAmount());
			
		}
		return conIngre;
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
	
}
