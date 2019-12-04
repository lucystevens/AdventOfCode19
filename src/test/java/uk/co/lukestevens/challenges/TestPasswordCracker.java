package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestPasswordCracker {
	
	@Test
	public void testValidPassword() {
		PasswordCracker cracker = new PasswordCracker();
		assertTrue(cracker.isValidPassword("111111"));
	}
	
	@Test
	public void testInvalidPassword1() {
		PasswordCracker cracker = new PasswordCracker();
		assertFalse(cracker.isValidPassword("223450"));
	}
	
	@Test
	public void testInvalidPassword2() {
		PasswordCracker cracker = new PasswordCracker();
		assertFalse(cracker.isValidPassword("123789"));
	}
	
	@Test
	public void testGetPasswordsInRange() {
		PasswordCracker cracker = new PasswordCracker();
		assertEquals(1246, cracker.getPasswordsInRange(234208, 765869).size());
	}
	
	@Test
	public void testValidPasswordExt1() {
		PasswordCracker cracker = new PasswordCracker();
		assertTrue(cracker.isValidPasswordExt("112233"));
	}
	
	@Test
	public void testValidPasswordExt2() {
		PasswordCracker cracker = new PasswordCracker();
		assertTrue(cracker.isValidPasswordExt("111122"));
	}
	
	@Test
	public void testInvalidPasswordExt() {
		PasswordCracker cracker = new PasswordCracker();
		assertFalse(cracker.isValidPasswordExt("123444"));
	}

	@Test
	public void testGetPasswordsInRangeExt() {
		PasswordCracker cracker = new PasswordCracker();
		assertEquals(814, cracker.getPasswordsInRangeExt(234208, 765869).size());
	}
}
