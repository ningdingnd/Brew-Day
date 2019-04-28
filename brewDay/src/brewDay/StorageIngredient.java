package brewDay;
//This class is implemented by Jason
public class StorageIngredient extends Ingredient{
	private int id;
	public StorageIngredient(String name, float amount, String unit) {
		super(name, amount, unit);
	}
	
	public boolean addAmount(float amount) {
		this.setAmount(this.getAmount() + amount);
		return true;
	}
	
	public boolean subtractAmount(float amount) {
		this.setAmount(this.getAmount() - amount);
		return true;
	}
	
}
