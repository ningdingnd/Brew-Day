package brewDay;
import javax.swing.JFrame;
public abstract class View extends JFrame implements ModelListener{
	protected Workbench w;
	
	public View(Workbench w) {
		this.w = w;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public abstract void update() ;
}
