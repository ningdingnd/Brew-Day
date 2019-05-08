package brewDay;
import java.util.ArrayList;
public class Workbench {

	private ArrayList<ModelListener> listeners;
	
	public Workbench() {
		this.listeners = new ArrayList<ModelListener>();
	}
	
	public void addListener(ModelListener l) {
		this.listeners.add(l);
	}
	
	
	private void notifyListeners() {	//	notify all its listeners
		for(ModelListener l: listeners) {
			l.update();
		}
	}
	
	
	
}
