package brewDay;
import java.util.ArrayList;

import javax.swing.JFrame;
public abstract class View extends JFrame implements ModelListener{
	protected Workbench w;
	protected Controller c;
	protected ArrayList sr;
	
	public View(Workbench w) {
		this.w = w;
	}

	public abstract void update() ;
}
