package brewDay;

import java.util.ArrayList;

public class GUI {
	public static void main(String[] args) {
		
		/* ADD YOUR TEST HERE*/
		//StorageIngredientController.testStorageIngredientController();
		//RecipeIngredientController.testRecipeIngredientController();
		//RecipeController.testRecipeController();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			
			public void run() {
				Workbench w = new Workbench();
				ArrayList<Controller> controller = new ArrayList<Controller>();
				
				//w.testWorkbench();
				
				
				//MainPageController mc = new MainPageController(w);
				
				StorageIngredientController sc = new StorageIngredientController(w);
				controller.add(sc);
				
				EquipmentController ec = new EquipmentController(w);
				controller.add(ec);
				
				MainPageView mv = new MainPageView(w, controller);
				
				
				
				w.addListener(mv);
			}

		});
	}
}
