package uk.co.lukestevens.challenges.nanofactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Nanofactory {
	
	private final List<ChemicalReaction> reactions;
	private Map<String, Long> resourcesNeeded = new HashMap<>(); 

	public Nanofactory(List<ChemicalReaction> reactions) {
		this.reactions = reactions;
	}
	
	public long getOreNeededForOneFuel() {
		return this.getOreNeededForFuel(1L);
	}
	
	public long getOreNeededForFuel(long fuel) {
		this.resourcesNeeded = new HashMap<>();
		this.resourcesNeeded.put("FUEL", fuel);
		this.processResourceMap();
		return this.resourcesNeeded.get("ORE");
	}
	
	void processResourceMap() {
		Optional<ResourceChemical> nextNeeded = this.resourcesNeeded
				.entrySet()
				.stream()
				.map(e -> new ResourceChemical(e.getKey(), e.getValue()))
				.filter(rc -> !rc.getName().equals("ORE"))
				.filter(rc -> rc.getQuantity() > 0)
				.findFirst();
		
		if(!nextNeeded.isPresent()) {
			return;
		}
		
		ResourceChemical chemical = nextNeeded.get();
		ChemicalReaction reaction = this.getReactionForResource(chemical.getName());
		long reactionsNeeded = (int) Math.ceil(((double)chemical.getQuantity() / (double)reaction.getOutput().getQuantity()));
		long resourcesProduced = reaction.getOutput().getQuantity() * reactionsNeeded;
		
		for(ResourceChemical input : reaction.getInput()) {
			long currentNeeded = this.resourcesNeeded.computeIfAbsent(input.getName(), k -> 0L);
			currentNeeded += reactionsNeeded * input.getQuantity();
			this.resourcesNeeded.put(input.getName(), currentNeeded);
		}
		
		long excess = chemical.getQuantity() - resourcesProduced;
		this.resourcesNeeded.put(chemical.getName(), excess);
		
		this.processResourceMap();
		
	}
	
	public long getFuelProducedFromOre(long oreAmount) {
		long lowest = oreAmount/this.getOreNeededForOneFuel();
		long highest = lowest*2L;
		System.out.println("Start with range: " + lowest + " - " + highest);
		
		long midpoint = 0;
		while(highest-lowest > 1) {
			midpoint = lowest+((highest-lowest)/2);
			System.out.println(midpoint);
			long oreNeeded = this.getOreNeededForFuel(midpoint);
			if(oreNeeded > oreAmount) {
				highest = midpoint;
			}
			else if(oreNeeded < oreAmount) {
				lowest = midpoint;
			}
			else {
				return midpoint;
			}
		}
		
		long oreNeeded = this.getOreNeededForFuel(midpoint);
		if(oreNeeded > oreAmount) {
			return midpoint - 1;
		}
		else{
			return midpoint;
		}
	}
	
	
	ChemicalReaction getReactionForResource(String resource){
		return this.reactions
				.stream()
				.filter(cr -> cr.getOutput().getName().equals(resource))
				.findFirst()
				.get();
	}
	

}
