package brewDay;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ShoppingList {
	private static Ingredient[] toBuy;
	
	public ShoppingList(Ingredient[] toBuy) {
	this.toBuy=toBuy;
	}
	
	public boolean save() {
		try {
			FileOutputStream f=new FileOutputStream("C:\\Users\\jay chou\\Desktop\\test.txt");
			ObjectOutputStream s=new ObjectOutputStream(f);
			s.writeObject(toBuy);
			s.flush();
			s.close();
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\jay chou\\Desktop\\test.txt"),"UTF-8"));
			System.out.println("Hello bug");
			} catch (Exception e) {				
				System.out.println(e);			
				}	
		return true;
		}	
	public static void main(String[] args){		
		Ingredient[] toBuy= {new Ingredient("d",10,"gallon",1)};
		ShoppingList test=new ShoppingList(toBuy);		
		test.save();
	}
}
