package brewDay;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EquipmentTest {
	private static Equipment equipment = new Equipment(001, 500);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpdateCpacity() {
		// we use white box path testing strategy
		// test case 1
		equipment.updateCpacity(-200);
		assertEquals(500, equipment.getCapacity());
		
		// test case 2
		equipment.updateCpacity(0);
		assertEquals(500, equipment.getCapacity());
		
		// test case 3
		equipment.updateCpacity(200);
		assertEquals(200, equipment.getCapacity());
	}
	
	@Test
	public void testGetCapacity() {
		assertEquals(500, equipment.getCapacity());
	}
}
