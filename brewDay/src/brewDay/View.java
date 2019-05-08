package brewDay;
import javax.swing.JFrame;
public abstract class View extends JFrame implements ModelListener{
	protected Workbench w;
	protected StorageIngredientController sc;
	
	public View(Workbench w) {
		this.w = w;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// for the InputNameQuantity view
	public View(Workbench w, StorageIngredientController sc) {
		this.w = w;
		this.sc = sc;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public abstract void update() ;
}
