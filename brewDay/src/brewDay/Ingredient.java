package brewDay;

<<<<<<< HEAD
import java.io.Serializable;

public class Ingredient implements Serializable{
=======
public class Ingredient {
>>>>>>> branch 'master' of https://github.com/comp3053/Babbage.git
	private int id;
	private String name;
	private double amount;
	private String unit;

	public Ingredient(String name, double amount, String unit, int id) {
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

	// this method convert the ingredient amount and unit to equivalence amount
	// of
	// target unit
	public boolean convertUnit(String targetUnit) {
		if (this.getUnit().equals("g") && targetUnit.equals("kg")) {
			this.setAmount(this.getAmount() * 0.001);
			this.setUnit(targetUnit);
		} else if (this.getUnit().equals("kg") && targetUnit.equals("g")) {
			this.setAmount(this.getAmount() * 1000);
			this.setUnit(targetUnit);
		} else {
			return false;
		}
		return true;
	}
}
