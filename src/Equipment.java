package brewDay;

public class Equipment {
	private int capacity;
	private int id;

	public Equipment(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;
	}

	public boolean updateCpacity(int newCapacity) {
		if (newCapacity <= 0) {
			System.out.println("Capacity must be a non-negtive value!");
			return false;
		} else {
			this.capacity = newCapacity;
			System.out.println("Updated successfully!");
			return true;
		}
	}

	public int getCapacity() {
		return this.capacity;
	}
}
