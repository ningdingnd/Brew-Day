package brewDay;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RecipeTest {
	private static Recipe recipe = new Recipe("Draft Beer", 500);
	@Before
	public void setUp() throws Exception {
	}

	@Ignore("viewRecipe() Not yet implemented")
	@Test
	public void testViewRecipe() {
		
	}
	
	@Test
	public void testUpdateName() {
		recipe.update("Puree beer");
		assertEquals("Puree beer", recipe.getName());	
	}
	
	@Test
	public void testUpdateQuantity() {
		recipe.update(400);
		assertEquals(400, recipe.getQuantity());			
	}	
	
	@Ignore("checkAvailable() Not yet implemented")
	@Test
	public void checkAvailable() {
		
	}
}
