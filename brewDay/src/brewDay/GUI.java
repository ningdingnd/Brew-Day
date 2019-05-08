package brewDay;

public class GUI {
	public static void main(String[] args) {
		
		/* ADD YOUR TEST HERE*/
		//StorageIngredientController.testStorageIngredientController();
		//RecipeIngredientController.testRecipeIngredientController();
		//RecipeController.testRecipeController();
		
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			
			public void run() {
				Workbench w = new Workbench();

				//w.testWorkbench();
				
				
				//MainPageController mc = new MainPageController(w);
				StorageIngredientController sc = new StorageIngredientController(w);
				MainPageView mv = new MainPageView(w, sc);
				
				
				
				w.addListener(mv);
			}

		});
	}
}
