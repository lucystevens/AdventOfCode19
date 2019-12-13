package uk.co.lukestevens.challenges.moons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TestMoonMapper {
	
	@Test
	public void testSimulateTimeSteps() {
		Moon m1 = new Moon(-1, 0, 2);
		Moon m2 = new Moon(2, -10, -7);
		Moon m3 = new Moon(4, -8, 8);
		Moon m4 = new Moon(3, 5, -1);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		
		//After 0 steps:
		assertEquals("Point3D(-1,0,2)", m1.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m1.getVelocity().toString());

		assertEquals("Point3D(2,-10,-7)", m2.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m2.getVelocity().toString());

		assertEquals("Point3D(4,-8,8)", m3.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m3.getVelocity().toString());

		assertEquals("Point3D(3,5,-1)", m4.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m4.getVelocity().toString());


		//After 1 step:
		mapper.simulateTimeStep();
		assertEquals("Point3D(2,-1,1)", m1.getLocation().toString());
		assertEquals("Point3D(3,-1,-1)", m1.getVelocity().toString());

		assertEquals("Point3D(3,-7,-4)", m2.getLocation().toString());
		assertEquals("Point3D(1,3,3)", m2.getVelocity().toString());

		assertEquals("Point3D(1,-7,5)", m3.getLocation().toString());
		assertEquals("Point3D(-3,1,-3)", m3.getVelocity().toString());

		assertEquals("Point3D(2,2,0)", m4.getLocation().toString());
		assertEquals("Point3D(-1,-3,1)", m4.getVelocity().toString());


		//After 2 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(5,-3,-1)", m1.getLocation().toString());
		assertEquals("Point3D(3,-2,-2)", m1.getVelocity().toString());

		assertEquals("Point3D(1,-2,2)", m2.getLocation().toString());
		assertEquals("Point3D(-2,5,6)", m2.getVelocity().toString());

		assertEquals("Point3D(1,-4,-1)", m3.getLocation().toString());
		assertEquals("Point3D(0,3,-6)", m3.getVelocity().toString());

		assertEquals("Point3D(1,-4,2)", m4.getLocation().toString());
		assertEquals("Point3D(-1,-6,2)", m4.getVelocity().toString());


		//After 3 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(5,-6,-1)", m1.getLocation().toString());
		assertEquals("Point3D(0,-3,0)", m1.getVelocity().toString());

		assertEquals("Point3D(0,0,6)", m2.getLocation().toString());
		assertEquals("Point3D(-1,2,4)", m2.getVelocity().toString());

		assertEquals("Point3D(2,1,-5)", m3.getLocation().toString());
		assertEquals("Point3D(1,5,-4)", m3.getVelocity().toString());

		assertEquals("Point3D(1,-8,2)", m4.getLocation().toString());
		assertEquals("Point3D(0,-4,0)", m4.getVelocity().toString());


		//After 4 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(2,-8,0)", m1.getLocation().toString());
		assertEquals("Point3D(-3,-2,1)", m1.getVelocity().toString());

		assertEquals("Point3D(2,1,7)", m2.getLocation().toString());
		assertEquals("Point3D(2,1,1)", m2.getVelocity().toString());

		assertEquals("Point3D(2,3,-6)", m3.getLocation().toString());
		assertEquals("Point3D(0,2,-1)", m3.getVelocity().toString());

		assertEquals("Point3D(2,-9,1)", m4.getLocation().toString());
		assertEquals("Point3D(1,-1,-1)", m4.getVelocity().toString());


		//After 5 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(-1,-9,2)", m1.getLocation().toString());
		assertEquals("Point3D(-3,-1,2)", m1.getVelocity().toString());

		assertEquals("Point3D(4,1,5)", m2.getLocation().toString());
		assertEquals("Point3D(2,0,-2)", m2.getVelocity().toString());

		assertEquals("Point3D(2,2,-4)", m3.getLocation().toString());
		assertEquals("Point3D(0,-1,2)", m3.getVelocity().toString());

		assertEquals("Point3D(3,-7,-1)", m4.getLocation().toString());
		assertEquals("Point3D(1,2,-2)", m4.getVelocity().toString());


		//After 6 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(-1,-7,3)", m1.getLocation().toString());
		assertEquals("Point3D(0,2,1)", m1.getVelocity().toString());

		assertEquals("Point3D(3,0,0)", m2.getLocation().toString());
		assertEquals("Point3D(-1,-1,-5)", m2.getVelocity().toString());

		assertEquals("Point3D(3,-2,1)", m3.getLocation().toString());
		assertEquals("Point3D(1,-4,5)", m3.getVelocity().toString());

		assertEquals("Point3D(3,-4,-2)", m4.getLocation().toString());
		assertEquals("Point3D(0,3,-1)", m4.getVelocity().toString());


		//After 7 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(2,-2,1)", m1.getLocation().toString());
		assertEquals("Point3D(3,5,-2)", m1.getVelocity().toString());

		assertEquals("Point3D(1,-4,-4)", m2.getLocation().toString());
		assertEquals("Point3D(-2,-4,-4)", m2.getVelocity().toString());

		assertEquals("Point3D(3,-7,5)", m3.getLocation().toString());
		assertEquals("Point3D(0,-5,4)", m3.getVelocity().toString());

		assertEquals("Point3D(2,0,0)", m4.getLocation().toString());
		assertEquals("Point3D(-1,4,2)", m4.getVelocity().toString());


		//After 8 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(5,2,-2)", m1.getLocation().toString());
		assertEquals("Point3D(3,4,-3)", m1.getVelocity().toString());

		assertEquals("Point3D(2,-7,-5)", m2.getLocation().toString());
		assertEquals("Point3D(1,-3,-1)", m2.getVelocity().toString());

		assertEquals("Point3D(0,-9,6)", m3.getLocation().toString());
		assertEquals("Point3D(-3,-2,1)", m3.getVelocity().toString());

		assertEquals("Point3D(1,1,3)", m4.getLocation().toString());
		assertEquals("Point3D(-1,1,3)", m4.getVelocity().toString());


		//After 9 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(5,3,-4)", m1.getLocation().toString());
		assertEquals("Point3D(0,1,-2)", m1.getVelocity().toString());

		assertEquals("Point3D(2,-9,-3)", m2.getLocation().toString());
		assertEquals("Point3D(0,-2,2)", m2.getVelocity().toString());

		assertEquals("Point3D(0,-8,4)", m3.getLocation().toString());
		assertEquals("Point3D(0,1,-2)", m3.getVelocity().toString());

		assertEquals("Point3D(1,1,5)", m4.getLocation().toString());
		assertEquals("Point3D(0,0,2)", m4.getVelocity().toString());


		//After 10 steps:
		mapper.simulateTimeStep();
		assertEquals("Point3D(2,1,-3)", m1.getLocation().toString());
		assertEquals("Point3D(-3,-2,1)", m1.getVelocity().toString());

		assertEquals("Point3D(1,-8,0)", m2.getLocation().toString());
		assertEquals("Point3D(-1,1,3)", m2.getVelocity().toString());

		assertEquals("Point3D(3,-6,1)", m3.getLocation().toString());
		assertEquals("Point3D(3,2,-3)", m3.getVelocity().toString());

		assertEquals("Point3D(2,0,4)", m4.getLocation().toString());
		assertEquals("Point3D(1,-1,-1)", m4.getVelocity().toString());
	}
	
	@Test
	public void testSimulateTimeStepsWithEnergy() {
		Moon m1 = new Moon(-8, -10, 0);
		Moon m2 = new Moon(5, 5, 10);
		Moon m3 = new Moon(2, -7, 3);
		Moon m4 = new Moon(9, -8, -3);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		
		//After 0 steps:
		assertEquals("Point3D(-8,-10,0)", m1.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m1.getVelocity().toString());

		assertEquals("Point3D(5,5,10)", m2.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m2.getVelocity().toString());

		assertEquals("Point3D(2,-7,3)", m3.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m3.getVelocity().toString());

		assertEquals("Point3D(9,-8,-3)", m4.getLocation().toString());
		assertEquals("Point3D(0,0,0)", m4.getVelocity().toString());


		//After 10 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(-9,-10,1)", m1.getLocation().toString());
		assertEquals("Point3D(-2,-2,-1)", m1.getVelocity().toString());

		assertEquals("Point3D(4,10,9)", m2.getLocation().toString());
		assertEquals("Point3D(-3,7,-2)", m2.getVelocity().toString());

		assertEquals("Point3D(8,-10,-3)", m3.getLocation().toString());
		assertEquals("Point3D(5,-1,-2)", m3.getVelocity().toString());

		assertEquals("Point3D(5,-10,3)", m4.getLocation().toString());
		assertEquals("Point3D(0,-4,5)", m4.getVelocity().toString());


		//After 20 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(-10,3,-4)", m1.getLocation().toString());
		assertEquals("Point3D(-5,2,0)", m1.getVelocity().toString());

		assertEquals("Point3D(5,-25,6)", m2.getLocation().toString());
		assertEquals("Point3D(1,1,-4)", m2.getVelocity().toString());

		assertEquals("Point3D(13,1,1)", m3.getLocation().toString());
		assertEquals("Point3D(5,-2,2)", m3.getVelocity().toString());

		assertEquals("Point3D(0,1,7)", m4.getLocation().toString());
		assertEquals("Point3D(-1,-1,2)", m4.getVelocity().toString());


		//After 30 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(15,-6,-9)", m1.getLocation().toString());
		assertEquals("Point3D(-5,4,0)", m1.getVelocity().toString());

		assertEquals("Point3D(-4,-11,3)", m2.getLocation().toString());
		assertEquals("Point3D(-3,-10,0)", m2.getVelocity().toString());

		assertEquals("Point3D(0,-1,11)", m3.getLocation().toString());
		assertEquals("Point3D(7,4,3)", m3.getVelocity().toString());

		assertEquals("Point3D(-3,-2,5)", m4.getLocation().toString());
		assertEquals("Point3D(1,2,-3)", m4.getVelocity().toString());


		//After 40 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(14,-12,-4)", m1.getLocation().toString());
		assertEquals("Point3D(11,3,0)", m1.getVelocity().toString());

		assertEquals("Point3D(-1,18,8)", m2.getLocation().toString());
		assertEquals("Point3D(-5,2,3)", m2.getVelocity().toString());

		assertEquals("Point3D(-5,-14,8)", m3.getLocation().toString());
		assertEquals("Point3D(1,-2,0)", m3.getVelocity().toString());

		assertEquals("Point3D(0,-12,-2)", m4.getLocation().toString());
		assertEquals("Point3D(-7,-3,-3)", m4.getVelocity().toString());


		//After 50 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(-23,4,1)", m1.getLocation().toString());
		assertEquals("Point3D(-7,-1,2)", m1.getVelocity().toString());

		assertEquals("Point3D(20,-31,13)", m2.getLocation().toString());
		assertEquals("Point3D(5,3,4)", m2.getVelocity().toString());

		assertEquals("Point3D(-4,6,1)", m3.getLocation().toString());
		assertEquals("Point3D(-1,1,-3)", m3.getVelocity().toString());

		assertEquals("Point3D(15,1,-5)", m4.getLocation().toString());
		assertEquals("Point3D(3,-3,-3)", m4.getVelocity().toString());


		//After 60 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(36,-10,6)", m1.getLocation().toString());
		assertEquals("Point3D(5,0,3)", m1.getVelocity().toString());

		assertEquals("Point3D(-18,10,9)", m2.getLocation().toString());
		assertEquals("Point3D(-3,-7,5)", m2.getVelocity().toString());

		assertEquals("Point3D(8,-12,-3)", m3.getLocation().toString());
		assertEquals("Point3D(-2,1,-7)", m3.getVelocity().toString());

		assertEquals("Point3D(-18,-8,-2)", m4.getLocation().toString());
		assertEquals("Point3D(0,6,-1)", m4.getVelocity().toString());


		//After 70 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(-33,-6,5)", m1.getLocation().toString());
		assertEquals("Point3D(-5,-4,7)", m1.getVelocity().toString());

		assertEquals("Point3D(13,-9,2)", m2.getLocation().toString());
		assertEquals("Point3D(-2,11,3)", m2.getVelocity().toString());

		assertEquals("Point3D(11,-8,2)", m3.getLocation().toString());
		assertEquals("Point3D(8,-6,-7)", m3.getVelocity().toString());

		assertEquals("Point3D(17,3,1)", m4.getLocation().toString());
		assertEquals("Point3D(-1,-1,-3)", m4.getVelocity().toString());


		//After 80 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(30,-8,3)", m1.getLocation().toString());
		assertEquals("Point3D(3,3,0)", m1.getVelocity().toString());

		assertEquals("Point3D(-2,-4,0)", m2.getLocation().toString());
		assertEquals("Point3D(4,-13,2)", m2.getVelocity().toString());

		assertEquals("Point3D(-18,-7,15)", m3.getLocation().toString());
		assertEquals("Point3D(-8,2,-2)", m3.getVelocity().toString());

		assertEquals("Point3D(-2,-1,-8)", m4.getLocation().toString());
		assertEquals("Point3D(1,8,0)", m4.getVelocity().toString());


		//After 90 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(-25,-1,4)", m1.getLocation().toString());
		assertEquals("Point3D(1,-3,4)", m1.getVelocity().toString());

		assertEquals("Point3D(2,-9,0)", m2.getLocation().toString());
		assertEquals("Point3D(-3,13,-1)", m2.getVelocity().toString());

		assertEquals("Point3D(32,-8,14)", m3.getLocation().toString());
		assertEquals("Point3D(5,-4,6)", m3.getVelocity().toString());

		assertEquals("Point3D(-1,-2,-8)", m4.getLocation().toString());
		assertEquals("Point3D(-3,-6,-9)", m4.getVelocity().toString());


		//After 100 steps:
		mapper.simulateTimeSteps(10);
		assertEquals("Point3D(8,-12,-9)", m1.getLocation().toString());
		assertEquals("Point3D(-7,3,0)", m1.getVelocity().toString());

		assertEquals("Point3D(13,16,-3)", m2.getLocation().toString());
		assertEquals("Point3D(3,-11,-5)", m2.getVelocity().toString());

		assertEquals("Point3D(-29,-11,-1)", m3.getLocation().toString());
		assertEquals("Point3D(-3,7,4)", m3.getVelocity().toString());

		assertEquals("Point3D(16,-13,23)", m4.getLocation().toString());
		assertEquals("Point3D(7,1,1)", m4.getVelocity().toString());
		
		// Check total energy
		assertEquals(1940, mapper.getTotalEnergy());
	}
	
	@Test 
	public void testTaskOne() {
		Moon m1 = new Moon(17, -12, 13);
		Moon m2 = new Moon(2, 1, 1);
		Moon m3 = new Moon(-1, -17, 7);
		Moon m4 = new Moon(12, -14, 18);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		mapper.simulateTimeSteps(1000);
		assertEquals(8960, mapper.getTotalEnergy());
	}

	@Test
	public void findStepsToMatchPrevious() {
		Moon m1 = new Moon(-1, 0, 2);
		Moon m2 = new Moon(2, -10, -7);
		Moon m3 = new Moon(4, -8, 8);
		Moon m4 = new Moon(3, 5, -1);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		assertEquals(2772, mapper.findStepsToMatchPreviousPoint());
	}
	
	@Test
	public void findStepsToMatchPreviousLong() {
		Moon m1 = new Moon(-8, -10, 0);
		Moon m2 = new Moon(5, 5, 10);
		Moon m3 = new Moon(2, -7, 3);
		Moon m4 = new Moon(9, -8, -3);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		assertEquals(4686774924L, mapper.findStepsToMatchPreviousPoint());
	}
	
	@Test
	public void testTaskTwo() {
		Moon m1 = new Moon(17, -12, 13);
		Moon m2 = new Moon(2, 1, 1);
		Moon m3 = new Moon(-1, -17, 7);
		Moon m4 = new Moon(12, -14, 18);
		
		MoonMapper mapper = new MoonMapper(Arrays.asList(m1, m2, m3, m4));
		assertEquals(314917503970904L, mapper.findStepsToMatchPreviousPoint());
	}
	
}
