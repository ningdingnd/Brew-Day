package brewDay;



public class Ingredient {
	private int id;
	private String name;
	private double amount;
	private String unit;
	
	
	public Ingredient(String name, float amount, String unit, int id) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAmount(double d) {
		this.amount = d;
	}
	
	public void setUnit(String newUnit) {
		this.unit = newUnit;
	}
	
	public boolean convertUnit(Ingredient ingredient, String targetUnit) {
		if(ingredient.getUnit().equals("g") && targetUnit.equals("kg")) {
			ingredient.setAmount(ingredient.getAmount() * 1000);
		}else if(ingredient.getUnit().equals("kg") && targetUnit.equals("g")){
			ingredient.setAmount(ingredient.getAmount() * 0.001);
		}
		else {
			return false;
		}
		
		return true;
	}
}


