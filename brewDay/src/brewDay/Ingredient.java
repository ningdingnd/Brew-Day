package brewDay;



public class Ingredient {
	private String name;
	private float amount;
	private String unit;
	
	
	public Ingredient(String name, float amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void setUnit(String newUnit) {
		this.unit = newUnit;
	}
}


