
//This is to test methods "convertValue", "produceShoppingList" and "converUnit".
//Author: Jason 1630003004

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RecipeTest {
	private static Recipe recipe = new Recipe();

	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@Test
	public void testproduceShoppingList() {
		// can not be tested yet.
	}

	@Test
	public void testconvertValue() {
		assertEquals(30.0, recipe.convertValue(1000), 0.1);
	}

	@Test
	public void testconvertUnit() {
		assertEquals(37.0, recipe.convertUnit(10.0, "gallons", "liters"), 0.1);
		assertEquals(20.0, recipe.convertUnit(74.0, "liters", "gallons"), 0.1);
	}

}
