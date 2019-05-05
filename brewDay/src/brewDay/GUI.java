package brewDay;

public class GUI {
	public static void main(String[] args) {
		
		/* ADD YOUR TEST HERE*/
		StorageIngredientController.testStorageIngredientController();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			
			public void run() {
				Workbench w = new Workbench();

				RecipeIngredientController c1 = new RecipeIngredientController(w);

				
			}

		});
	}
}
