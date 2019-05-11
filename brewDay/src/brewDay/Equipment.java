package brewDay;

public class Equipment {
	private double capacity;
	private int id;
	private String unit;

	//	the constructor
	public Equipment(int id, double capacity, String unit) {
		this.id = id;
		this.capacity = capacity;
		this.unit = unit;
	}

	public boolean updateCapacity(double newCapacity) {
		if (newCapacity <= 0) {
			System.out.println("Capacity must be a non-negtive value!");
			return false;
		} else {
			this.capacity = newCapacity;
			System.out.println("Updated successfully!");
			return true;
		}
	}

	public double getCapacity() {
		return this.capacity;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setCapacity(double nCapacity) {
		this.capacity = nCapacity;
	}
	
	
	//	this method convert the equipment capacity to equivalent capacity of target unit
	public boolean convertUnit(String targetUnit) {

		if (this.getUnit().equals("galon") && targetUnit.equals("L")) {
			this.setCapacity(this.getCapacity() / 0.26417);
		} else if (this.getUnit().equals("L") && targetUnit.equals("galon")) {
			this.setCapacity(this.getCapacity() * 3.78541178);
		} else if (this.getUnit().equals("mL") && targetUnit.equals("L")) {
			this.setCapacity(this.getCapacity() * 0.001);
		} else if (this.getUnit().equals("L") && targetUnit.equals("mL")) {
			this.setCapacity(this.getCapacity() * 1000);
		} else if (this.getUnit().equals("galon") && targetUnit.equals("mL")) {
			this.setCapacity(this.getCapacity() / 0.26417 * 1000);
		} else if (this.getUnit().equals("mL") && targetUnit.equals("galon")) {
			this.setCapacity(this.getCapacity() * 0.001 * 3.78541178);
		} else {
			return false;
		}

		return true;
	}
}
