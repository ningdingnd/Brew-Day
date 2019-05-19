package brewDay;
//This class is implemented by Chris and Jason
public class Recipe implements Comparable<Recipe> {
	private String name;
	private double quantity;
	private String unit;
	private RecipeIngredient[] recipeIngredient;

	public Recipe(String name, double recipeQuantity, String unit, RecipeIngredient[] recipeIngredient) {
		this.unit = unit;
		this.quantity = recipeQuantity;
		this.name = name;
		this.recipeIngredient = recipeIngredient;
	}

	public String getName() {	//	get name attribute of recipe
		return this.name;
	}

	public double getQuantity() {	//	get quantity attribute of recipe
		return this.quantity;	
	}
	
	public void setQuantity(double nQuantity) {	//	set quantity to a new value
		this.quantity = nQuantity;
	}

	public String getUnit() {	//	get unit attribute of recipe
		return this.unit;
	}
	
	public void setUnit(String nUnit) {	//	set unit to a new value
		this.unit = nUnit;
	}
	

	public RecipeIngredient[] getIngredients() {	//	get ingredients of recipe
		return this.recipeIngredient;
	}

	// update the recipe ingredient quantity only
	public boolean update(String name, double newQuantity) {

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

	
	//	this method convert the recipe ingredient value to the batch size user specified
	public boolean convertValue(double batchSize, String b_unit) {
		
		System.out.println("start to convert quantity unit of recipe.");
		//	test whether the recipe unit and target unit same or not
		//	if not, convert unit first
		if(!this.getUnit().equals(b_unit)) {
			boolean result = this.convertQuantityUnit(b_unit);
			if(!result) {
				System.out.println("convert recipe unit failed.");
				return false;
			}
		}
		
		System.out.println("convert unit of recipe finished, start to convert ingredient amount.");
		double originalAmount;
		//	convert the recipe ingredient value to absolute value with batch size
		for (int i = 0; i < this.getIngredients().length; i++) {
			//	convert the amount
			
			originalAmount = this.getIngredients()[i].getAmount();
			System.out.println("Get ingredient amount done.");
			this.getIngredients()[i].setAmount((batchSize / this.getQuantity()) * originalAmount);
		}
		System.out.println("convert all ingredient value to absolute value finishied.");
		return true;
	}
	
	
	//	this method convert recipe quantity unit in this instance to value of target unit
	//	the value of recipe ingredient will be changed too
	public boolean convertQuantityUnit(String targetUnit) {
		if (this.getUnit().equals("galon") && targetUnit.equals("L")) {
			this.setQuantity(this.getQuantity() / 0.26417);
		} else if (this.getUnit().equals("L") && targetUnit.equals("galon")) {
			this.setQuantity(this.getQuantity() * 3.78541178);
		} else if (this.getUnit().equals("mL") && targetUnit.equals("L")) {
			this.setQuantity(this.getQuantity() * 0.001);
		} else if (this.getUnit().equals("L") && targetUnit.equals("mL")) {
			this.setQuantity(this.getQuantity() * 1000);
		} else if (this.getUnit().equals("galon") && targetUnit.equals("mL")) {
			this.setQuantity(this.getQuantity() / 0.26417 * 1000);
		} else if (this.getUnit().equals("mL") && targetUnit.equals("galon")) {
			this.setQuantity(this.getQuantity() * 0.001 * 3.78541178);
		} else {
			return false;
		}
		this.setUnit(targetUnit);
		return true;
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
	
	@Override
	public int compareTo(Recipe o) {
		double thisSum = 0;
		double oSum = 0;
		for (Ingredient i : this.getIngredients()) {
			i.convertUnit("g");
			thisSum += i.getAmount();
		}
		for (Ingredient i : o.getIngredients()) {
			i.convertUnit("g");
			oSum += i.getAmount();
		}

		if (thisSum < oSum)
			return 1;
		else if (oSum < thisSum) 
			return -1;
		else 
			return 0;
	}
	
}
