package uk.co.lukestevens.challenges.nanofactory;

public class ResourceChemical {
	
	private String name;
	private long quantity;
	
	
	public ResourceChemical(String name, long quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public long getQuantity() {
		return quantity;
	}

}
