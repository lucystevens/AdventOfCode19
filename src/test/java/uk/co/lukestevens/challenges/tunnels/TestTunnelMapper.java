package uk.co.lukestevens.challenges.tunnels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestTunnelMapper {
	
	
	static List<String> input;
	static List<String> input2;

	@BeforeAll
	public static void setup() throws IOException {
		InputFileReader reader = new InputFileReader("Day18");
		input = reader.readFileAsListOfStrings();
		
		reader = new InputFileReader("Day18-2");
		input2 = reader.readFileAsListOfStrings();
	}
	
	@Test
	public void testFindShortestPath() {
		List<String> testMap = Arrays.asList(
			"#########",
			"#...#...#",
			"#.#.#.#.#",
			"#.#.#...#",
			"#.#...#.#",
			"#.#####.#",
			"#.......#",
			"#########"
		);
		
		{
			SingleTunnelMapper tunnels = new SingleTunnelMapper(testMap);
			List<Point> route = tunnels.findShortestRoute(new Point(1, 1), new Point(7,6));
			assertEquals(new Point(1, 1), route.get(0));
			assertEquals(new Point(1, 2), route.get(1));
			assertEquals(new Point(1, 3), route.get(2));
			assertEquals(new Point(1, 4), route.get(3));
			assertEquals(new Point(1, 5), route.get(4));
			assertEquals(new Point(1, 6), route.get(5));
			assertEquals(new Point(2, 6), route.get(6));
			assertEquals(new Point(3, 6), route.get(7));
			assertEquals(new Point(4, 6), route.get(8));
			assertEquals(new Point(5, 6), route.get(9));
			assertEquals(new Point(6, 6), route.get(10));
			assertEquals(new Point(7, 6), route.get(11));
		}
		
		{
			// Block off shortest route
			testMap.set(6, "##......#");
			SingleTunnelMapper tunnels = new SingleTunnelMapper(testMap);
			List<Point> route = tunnels.findShortestRoute(new Point(1, 1), new Point(7,6));
			assertEquals(new Point(1, 1), route.get(0));
			assertEquals(new Point(2, 1), route.get(1));
			assertEquals(new Point(3, 1), route.get(2));
			assertEquals(new Point(3, 2), route.get(3));
			assertEquals(new Point(3, 3), route.get(4));
			assertEquals(new Point(3, 4), route.get(5));
			assertEquals(new Point(4, 4), route.get(6));
			assertEquals(new Point(5, 4), route.get(7));
			assertEquals(new Point(5, 3), route.get(8));
			assertEquals(new Point(6, 3), route.get(9));
			assertEquals(new Point(7, 3), route.get(10));
			assertEquals(new Point(7, 4), route.get(11));
			assertEquals(new Point(7, 5), route.get(12));
			assertEquals(new Point(7, 6), route.get(13));
		}
	}

	
	@Test
	public void testMapHasOnlySingleRoutes() {
		SingleTunnelMapper tunnel = new SingleTunnelMapper(input);
		Point start = tunnel.findStart();
		
		{
			Point zDoor = tunnel.findDoor("z");
			List<Point> route = tunnel.findShortestRoute(start, zDoor);
			assertEquals(42, route.size());
			
			// Block route to Z door
			tunnel.getMap().set(49, 43, "#");
			assertNull(tunnel.findShortestRoute(start, zDoor));
		}
	}
	
	@Test
	public void testFindAllKeys_small() {
		List<String> map = Arrays.asList(
			"#########",
			"#b.A.@.a#", 
			"#########"
		);
		
		SingleTunnelMapper tunnel = new SingleTunnelMapper(map);
		assertEquals(8, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testFindAllKeys_medium() {
		List<String> map = Arrays.asList(
			"########################", 
			"#f.D.E.e.C.b.A.@.a.B.c.#",
			"######################.#",
			"#d.....................#",
			"########################"
		);
		
		SingleTunnelMapper tunnel = new SingleTunnelMapper(map);
		assertEquals(86, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testFindAllKeys_medium2() {
		List<String> map = Arrays.asList(
			"########################", 
			"#...............b.C.D.f#",
			"#.######################", 
			"#.....@.a.B.c.d.A.e.F.g#",
			"########################"
		);
		
		SingleTunnelMapper tunnel = new SingleTunnelMapper(map);
		assertEquals(132, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testFindAllKeys_medium3() {
		List<String> map = Arrays.asList(
			"#################", 
			"#i.G..c...e..H.p#", 
			"########.########", 
			"#j.A..b...f..D.o#", 
			"########@########", 
			"#k.E..a...g..B.n#", 
			"########.########", 
			"#l.F..d...h..C.m#", 
			"#################"
		);
		
		SingleTunnelMapper tunnel = new SingleTunnelMapper(map);
		assertEquals(136, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testFindAllKeys_medium4() {
		List<String> map = Arrays.asList(
			"########################", 
			"#@..............ac.GI.b#", 
			"###d#e#f################", 
			"###A#B#C################", 
			"###g#h#i################", 
			"########################"
		);
		
		SingleTunnelMapper tunnel = new SingleTunnelMapper(map);
		assertEquals(81, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testTaskOne() {
		SingleTunnelMapper tunnel = new SingleTunnelMapper(input);
		assertEquals(4868, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testQuadTunnelMapper_small() {
		List<String> map = Arrays.asList(
				"#######", 
				"#a.#Cd#",
				"##@#@##",
				"###*###",
				"##@#@##",
				"#cB#Ab#",
				"#######"
			);
			
			QuadTunnelMapper tunnel = new QuadTunnelMapper(map);
			assertEquals(8, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testQuadTunnelMapper_medium() {
		List<String> map = Arrays.asList(
				"###############",
				"#d.ABC.#.....a#",
				"######@#@######",
				"#######*#######",
				"######@#@######",
				"#b.....#.....c#",
				"###############"
			);
			
			QuadTunnelMapper tunnel = new QuadTunnelMapper(map);
			assertEquals(24, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	@Test
	public void testQuadTunnelMapper_complex() {
		List<String> map = Arrays.asList(
				"#############",
				"#g#f.D#..h#l#",
				"#F###e#E###.#",
				"#dCba@#@BcIJ#",
				"######*######",
				"#nK.L@#@G...#",
				"#M###N#H###.#",
				"#o#m..#i#jk.#",
				"#############"
			);
			
			QuadTunnelMapper tunnel = new QuadTunnelMapper(map);
			assertEquals(72, tunnel.findQuickestRouteToCollectAllKeys());
	}
	
	//@Test Takes ~ 3 minutes
	public void testTaskTwo() {
		QuadTunnelMapper tunnel = new QuadTunnelMapper(input2);
		assertEquals(1984, tunnel.findQuickestRouteToCollectAllKeys());
	}
}
