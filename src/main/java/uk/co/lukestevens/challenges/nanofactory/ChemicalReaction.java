package uk.co.lukestevens.challenges.nanofactory;

import java.util.ArrayList;
import java.util.List;

public class ChemicalReaction {
	
	public static ChemicalReaction parseReaction(String reaction) {
		String[] parts = reaction.split("[ ,=>]+");
		
		List<ResourceChemical> input = new ArrayList<>();
		for(int i = 0; i<parts.length-2; i+=2) {
			input.add(new ResourceChemical(parts[i+1], Long.parseLong(parts[i])));
		}
		
		ResourceChemical output = new ResourceChemical(parts[parts.length-1], Long.parseLong(parts[parts.length -2]));
		return new ChemicalReaction(input, output);
	}
	
	private final List<ResourceChemical> input;
	private final ResourceChemical output;
	
	public ChemicalReaction(List<ResourceChemical> input, ResourceChemical output) {
		this.input = input;
		this.output = output;
	}

	public List<ResourceChemical> getInput() {
		return input;
	}

	public ResourceChemical getOutput() {
		return output;
	}

}
