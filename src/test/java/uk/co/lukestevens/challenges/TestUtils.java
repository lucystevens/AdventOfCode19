package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.Utils;

public class TestUtils {
	
	@Test
	public void testFindLowestCommonMultiple() {
		long result1 = Utils.getLowestCommonMultiple(2, 3, 4);
		assertEquals(12, result1);
		
		long result2 = Utils.getLowestCommonMultiple(18, 28, 44);
		assertEquals(2772, result2);
	}
	
	@Test
	public void testIsDivisible() {
		assertTrue(Utils.isDivisible(24, 2, 3, 4, 6, 8));
		assertFalse(Utils.isDivisible(25, 2, 5));		
	}

}