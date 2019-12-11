package uk.co.lukestevens.challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.challenges.asteroids.Asteroid;
import uk.co.lukestevens.challenges.asteroids.AsteroidField;
import uk.co.lukestevens.utils.InputFileReader;

public class TestAsteroidField {

	
	@Test
	public void testFindBlockingPoints() {
		Asteroid start = new Asteroid(7, 9, true);
		Asteroid end = new Asteroid(1, 6, true);
		List<Point> points = start.getPointsBetweenAsteroids(end);
		assertEquals(2, points.size());
		assertEquals(new Point(5, 8), points.get(0));
		assertEquals(new Point(3, 7), points.get(1));
	}
	
	@Test
	public void testFindBlockingPoints2() {
		Asteroid start = new Asteroid(0, 2, true);
		Asteroid end = new Asteroid(4, 0, true);
		List<Point> points = start.getPointsBetweenAsteroids(end);
		assertEquals(1, points.size());
		assertEquals(new Point(2,1), points.get(0));
	}
	
	@Test
	public void getBestMonitoringStation1() {
		List<String> map = Arrays.asList(
			"......#.#.",
			"#..#.#....",
			"..#######.",
			".#.#.###..",
			".#..#.....",
			"..#....#.#",
			"#..#....#.",
			".##.#..###",
			"##...#..#.",
			".#....####"
		);
		
		AsteroidField field = new AsteroidField(map);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		assertEquals(new Point(5, 8), bestLocation.getPoint());
		assertEquals(33, bestLocation.getAsteroidsInSight());
	}
	
	@Test
	public void getBestMonitoringStation2() {
		List<String> map = Arrays.asList(
			"#.#...#.#.",
			".###....#.",
			".#....#...",
			"##.#.#.#.#",
			"....#.#.#.",
			".##..###.#",
			"..#...##..",
			"..##....##",
			"......#...",
			".####.###."
		);
		
		AsteroidField field = new AsteroidField(map);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		assertEquals(new Point(1, 2), bestLocation.getPoint());
		assertEquals(35, bestLocation.getAsteroidsInSight());
	}
	
	@Test
	public void getBestMonitoringStation3() {
		List<String> map = Arrays.asList(
			".#..#..###",
			"####.###.#",
			"....###.#.",
			"..###.##.#",
			"##.##.#.#.",
			"....###..#",
			"..#.#..#.#",
			"#..#.#.###",
			".##...##.#",
			".....#.#.."
		);
		
		AsteroidField field = new AsteroidField(map);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		assertEquals(new Point(6, 3), bestLocation.getPoint());
		assertEquals(41, bestLocation.getAsteroidsInSight());
	}
	
	@Test
	public void getBestMonitoringStation4() {
		List<String> map = Arrays.asList(
			".#..##.###...#######",
			"##.############..##.",
			".#.######.########.#",
			".###.#######.####.#.",
			"#####.##.#.##.###.##",
			"..#####..#.#########",
			"####################",
			"#.####....###.#.#.##",
			"##.#################",
			"#####.##.###..####..",
			"..######..##.#######",
			"####.##.####...##..#",
			".#####..#.######.###",
			"##...#.##########...",
			"#.##########.#######",
			".####.#.###.###.#.##",
			"....##.##.###..#####",
			".#.#.###########.###",
			"#.#.#.#####.####.###",
			"###.##.####.##.#..##"
		);
		
		AsteroidField field = new AsteroidField(map);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		assertEquals(new Point(11, 13), bestLocation.getPoint());
		assertEquals(210, bestLocation.getAsteroidsInSight());
	}
	
	@Test
	public void testTaskOne() throws IOException {
		InputFileReader reader = new InputFileReader("Day10");
		List<String> input = reader.readFileAsListOfStrings();
		
		AsteroidField field = new AsteroidField(input);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		assertEquals(247, bestLocation.getAsteroidsInSight());
	}
	
	@Test
	public void testFindAsteroidAngle() {
		Asteroid laser = new Asteroid(2, 2, true);
		
		Asteroid ast1 = new Asteroid(4,  0, true);
		double angle1 = laser.getAngleToLocation(ast1);
		assertEquals(45.0, angle1);
		
		Asteroid ast2 = new Asteroid(4,  4, true);
		double angle2 = laser.getAngleToLocation(ast2);
		assertEquals(135.0, angle2);
		
		Asteroid ast3 = new Asteroid(0,  4, true);
		double angle3 = laser.getAngleToLocation(ast3);
		assertEquals(225.0, angle3);
		
		Asteroid ast4 = new Asteroid(0,  0, true);
		double angle4 = laser.getAngleToLocation(ast4);
		assertEquals(315.0, angle4);
		
		Asteroid ast5 = new Asteroid(3,  0, true);
		double angle5 = laser.getAngleToLocation(ast5);
		assertEquals("26.57", String.format("%.2f", angle5));
		
		Asteroid ast6 = new Asteroid(0,  3, true);
		double angle6 = laser.getAngleToLocation(ast6);
		assertEquals("243.43", String.format("%.2f", angle6));
	}
	
	@Test
	public void testBlastASteroids() {
		List<String> map = Arrays.asList(
			".#..##.###...#######",
			"##.############..##.",
			".#.######.########.#",
			".###.#######.####.#.",
			"#####.##.#.##.###.##",
			"..#####..#.#########",
			"####################",
			"#.####....###.#.#.##",
			"##.#################",
			"#####.##.###..####..",
			"..######..##.#######",
			"####.##.####...##..#",
			".#####..#.######.###",
			"##...#.##########...",
			"#.##########.#######",
			".####.#.###.###.#.##",
			"....##.##.###..#####",
			".#.#.###########.###",
			"#.#.#.#####.####.###",
			"###.##.####.##.#..##"
		);
		
		AsteroidField field = new AsteroidField(map);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		assertEquals(new Point(11, 13), bestLocation.getPoint());
		assertEquals(210, bestLocation.getAsteroidsInSight());
		
		List<Asteroid> blastOrder = field.blastAsteroids(bestLocation);
		assertEquals(new Point(11, 12), blastOrder.get(0).getPoint());
		assertEquals(new Point(12, 1), blastOrder.get(1).getPoint());
		assertEquals(new Point(12, 2), blastOrder.get(2).getPoint());
		assertEquals(new Point(12, 8), blastOrder.get(9).getPoint());
		assertEquals(new Point(16, 0), blastOrder.get(19).getPoint());
		assertEquals(new Point(16, 9), blastOrder.get(49).getPoint());
		assertEquals(new Point(10, 16), blastOrder.get(99).getPoint());
		assertEquals(new Point(9, 6), blastOrder.get(198).getPoint());
		assertEquals(new Point(8, 2), blastOrder.get(199).getPoint());
		assertEquals(new Point(10, 9), blastOrder.get(200).getPoint());
		assertEquals(new Point(11, 1), blastOrder.get(298).getPoint());
	}
	
	@Test
	public void testTask2() throws IOException {
		InputFileReader reader = new InputFileReader("Day10");
		List<String> input = reader.readFileAsListOfStrings();
		
		AsteroidField field = new AsteroidField(input);
		field.mapPossibleMonitoringStations();
		
		Asteroid bestLocation = field.getBestMonitoringStationLocation();
		List<Asteroid> blastOrder = field.blastAsteroids(bestLocation);
		
		Point point200 = blastOrder.get(199).getPoint();
		assertEquals(1919, (point200.getX()*100) + point200.getY());
	}
}
