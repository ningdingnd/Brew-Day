package brewDay;

public class GUI {
	public static void main(String[] args) {
		
		/* ADD YOUR TEST HERE*/
		//StorageIngredientController.testStorageIngredientController();
		//RecipeIngredientController.testRecipeIngredientController();
		RecipeController.testRecipeController();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			
			public void run() {
				Workbench w = new Workbench();

				
				RecipeIngredientController c1 = new RecipeIngredientController(w);
				MainPageView mv = new MainPageView(w, c1);
				
				StorageIngredientController sc = new StorageIngredientController(w);
				
				
				w.addListener(mv);
			}

		});
	}
}
