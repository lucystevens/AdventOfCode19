package uk.co.lukestevens.challenges.nanofactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import uk.co.lukestevens.utils.InputFileReader;

public class TestNanofactory {
	
	@Test
	public void testParseReaction() {
		ChemicalReaction reaction1 = ChemicalReaction.parseReaction("7 A, 1 B => 1 C");
		assertEquals(7, reaction1.getInput().get(0).getQuantity());
		assertEquals("A", reaction1.getInput().get(0).getName());
		assertEquals(1, reaction1.getInput().get(1).getQuantity());
		assertEquals("B", reaction1.getInput().get(1).getName());
		assertEquals(1, reaction1.getOutput().getQuantity());
		assertEquals("C", reaction1.getOutput().getName());
		
		ChemicalReaction reaction2 = ChemicalReaction.parseReaction("44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL");
		assertEquals(44, reaction2.getInput().get(0).getQuantity());
		assertEquals("XJWVT", reaction2.getInput().get(0).getName());
		assertEquals(5, reaction2.getInput().get(1).getQuantity());
		assertEquals("KHKGT", reaction2.getInput().get(1).getName());
		assertEquals(1, reaction2.getInput().get(2).getQuantity());
		assertEquals("QDVJ", reaction2.getInput().get(2).getName());
		assertEquals(29, reaction2.getInput().get(3).getQuantity());
		assertEquals("NZVS", reaction2.getInput().get(3).getName());
		assertEquals(9, reaction2.getInput().get(4).getQuantity());
		assertEquals("GPVTF", reaction2.getInput().get(4).getName());
		assertEquals(48, reaction2.getInput().get(5).getQuantity());
		assertEquals("HKGWZ", reaction2.getInput().get(5).getName());
		assertEquals(1, reaction2.getOutput().getQuantity());
		assertEquals("FUEL", reaction2.getOutput().getName());
		
		ChemicalReaction reaction3 = ChemicalReaction.parseReaction("8 ORE => 3 B");
		assertEquals(8, reaction3.getInput().get(0).getQuantity());
		assertEquals("ORE", reaction3.getInput().get(0).getName());
		assertEquals(3, reaction3.getOutput().getQuantity());
		assertEquals("B", reaction3.getOutput().getName());
		
	}

	// This means there is only ever one way to create a resource
	@Test
	public void testWaysToCreateResource() throws IOException {
		InputFileReader reader = new InputFileReader("Day14");
		List<ChemicalReaction> input = reader
				.readFileAsListOfStrings()
				.stream()
				.map(ChemicalReaction::parseReaction)
				.collect(Collectors.toList());
		
		assertEquals(
				input.size(),
				input.stream().map(ChemicalReaction::getOutput).distinct().count());
	}
	
	@Test
	public void testFindOreForFuel1() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("9 ORE => 2 A"),
			ChemicalReaction.parseReaction("8 ORE => 3 B"),
			ChemicalReaction.parseReaction("7 ORE => 5 C"),
			ChemicalReaction.parseReaction("3 A, 4 B => 1 AB"),
			ChemicalReaction.parseReaction("5 B, 7 C => 1 BC"),
			ChemicalReaction.parseReaction("4 C, 1 A => 1 CA"),
			ChemicalReaction.parseReaction("2 AB, 3 BC, 4 CA => 1 FUEL")
		));
		
		assertEquals(165, factory.getOreNeededForOneFuel());
	}
	
	@Test
	public void testFindOreForFuel2() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("157 ORE => 5 NZVS"),
			ChemicalReaction.parseReaction("165 ORE => 6 DCFZ"),
			ChemicalReaction.parseReaction("44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL"),
			ChemicalReaction.parseReaction("12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ"),
			ChemicalReaction.parseReaction("179 ORE => 7 PSHF"),
			ChemicalReaction.parseReaction("177 ORE => 5 HKGWZ"),
			ChemicalReaction.parseReaction("7 DCFZ, 7 PSHF => 2 XJWVT"),
			ChemicalReaction.parseReaction("165 ORE => 2 GPVTF"),
			ChemicalReaction.parseReaction("3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT")
		));
		
		assertEquals(13312, factory.getOreNeededForOneFuel());
	}
	
	@Test
	public void testFindOreForFuel3() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG"),
			ChemicalReaction.parseReaction("17 NVRVD, 3 JNWZP => 8 VPVL"),
			ChemicalReaction.parseReaction("53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL"),
			ChemicalReaction.parseReaction("22 VJHF, 37 MNCFX => 5 FWMGM"),
			ChemicalReaction.parseReaction("139 ORE => 4 NVRVD"),
			ChemicalReaction.parseReaction("144 ORE => 7 JNWZP"),
			ChemicalReaction.parseReaction("5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC"),
			ChemicalReaction.parseReaction("5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV"),
			ChemicalReaction.parseReaction("145 ORE => 6 MNCFX"),
			ChemicalReaction.parseReaction("1 NVRVD => 8 CXFTF"),
			ChemicalReaction.parseReaction("1 VJHF, 6 MNCFX => 4 RFSQX"),
			ChemicalReaction.parseReaction("176 ORE => 6 VJHF")
		));
		
		assertEquals(180697, factory.getOreNeededForOneFuel());
	}
	
	@Test
	public void testFindOreForFuel4() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("171 ORE => 8 CNZTR"),
			ChemicalReaction.parseReaction("7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL"),
			ChemicalReaction.parseReaction("114 ORE => 4 BHXH"),
			ChemicalReaction.parseReaction("14 VRPVC => 6 BMBT"),
			ChemicalReaction.parseReaction("6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL"),
			ChemicalReaction.parseReaction("6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT"),
			ChemicalReaction.parseReaction("15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW"),
			ChemicalReaction.parseReaction("13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW"),
			ChemicalReaction.parseReaction("5 BMBT => 4 WPTQ"),
			ChemicalReaction.parseReaction("189 ORE => 9 KTJDG"),
			ChemicalReaction.parseReaction("1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP"),
			ChemicalReaction.parseReaction("12 VRPVC, 27 CNZTR => 2 XDBXC"),
			ChemicalReaction.parseReaction("15 KTJDG, 12 BHXH => 5 XCVML"),
			ChemicalReaction.parseReaction("3 BHXH, 2 VRPVC => 7 MZWV"),
			ChemicalReaction.parseReaction("121 ORE => 7 VRPVC"),
			ChemicalReaction.parseReaction("7 XCVML => 6 RJRHP"),
			ChemicalReaction.parseReaction("5 BHXH, 4 VRPVC => 5 LTCX")
		));
		
		assertEquals(2210736, factory.getOreNeededForOneFuel());
	}
	
	@Test
	public void testTaskOne() throws IOException {
		InputFileReader reader = new InputFileReader("Day14");
		List<ChemicalReaction> input = reader
				.readFileAsListOfStrings()
				.stream()
				.map(ChemicalReaction::parseReaction)
				.collect(Collectors.toList());
		
		Nanofactory factory = new Nanofactory(input);
		assertEquals(337862, factory.getOreNeededForOneFuel());
	}
	
	@Test
	public void findFuelForOre1() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("157 ORE => 5 NZVS"),
			ChemicalReaction.parseReaction("165 ORE => 6 DCFZ"),
			ChemicalReaction.parseReaction("44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL"),
			ChemicalReaction.parseReaction("12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ"),
			ChemicalReaction.parseReaction("179 ORE => 7 PSHF"),
			ChemicalReaction.parseReaction("177 ORE => 5 HKGWZ"),
			ChemicalReaction.parseReaction("7 DCFZ, 7 PSHF => 2 XJWVT"),
			ChemicalReaction.parseReaction("165 ORE => 2 GPVTF"),
			ChemicalReaction.parseReaction("3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT")
		));
		
		assertEquals(82892753, factory.getFuelProducedFromOre(1000000000000L));
	}
	
	@Test
	public void findFuelForOre2() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG"),
			ChemicalReaction.parseReaction("17 NVRVD, 3 JNWZP => 8 VPVL"),
			ChemicalReaction.parseReaction("53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL"),
			ChemicalReaction.parseReaction("22 VJHF, 37 MNCFX => 5 FWMGM"),
			ChemicalReaction.parseReaction("139 ORE => 4 NVRVD"),
			ChemicalReaction.parseReaction("144 ORE => 7 JNWZP"),
			ChemicalReaction.parseReaction("5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC"),
			ChemicalReaction.parseReaction("5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV"),
			ChemicalReaction.parseReaction("145 ORE => 6 MNCFX"),
			ChemicalReaction.parseReaction("1 NVRVD => 8 CXFTF"),
			ChemicalReaction.parseReaction("1 VJHF, 6 MNCFX => 4 RFSQX"),
			ChemicalReaction.parseReaction("176 ORE => 6 VJHF")
		));
		
		assertEquals(5586022 , factory.getFuelProducedFromOre(1000000000000L));
	}
	
	@Test
	public void findFuelForOre3() {
		Nanofactory factory = new Nanofactory(Arrays.asList(
			ChemicalReaction.parseReaction("171 ORE => 8 CNZTR"),
			ChemicalReaction.parseReaction("7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL"),
			ChemicalReaction.parseReaction("114 ORE => 4 BHXH"),
			ChemicalReaction.parseReaction("14 VRPVC => 6 BMBT"),
			ChemicalReaction.parseReaction("6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL"),
			ChemicalReaction.parseReaction("6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT"),
			ChemicalReaction.parseReaction("15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW"),
			ChemicalReaction.parseReaction("13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW"),
			ChemicalReaction.parseReaction("5 BMBT => 4 WPTQ"),
			ChemicalReaction.parseReaction("189 ORE => 9 KTJDG"),
			ChemicalReaction.parseReaction("1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP"),
			ChemicalReaction.parseReaction("12 VRPVC, 27 CNZTR => 2 XDBXC"),
			ChemicalReaction.parseReaction("15 KTJDG, 12 BHXH => 5 XCVML"),
			ChemicalReaction.parseReaction("3 BHXH, 2 VRPVC => 7 MZWV"),
			ChemicalReaction.parseReaction("121 ORE => 7 VRPVC"),
			ChemicalReaction.parseReaction("7 XCVML => 6 RJRHP"),
			ChemicalReaction.parseReaction("5 BHXH, 4 VRPVC => 5 LTCX")
		));
		
		assertEquals(460664, factory.getFuelProducedFromOre(1000000000000L));
	}
}
