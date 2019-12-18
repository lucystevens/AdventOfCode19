package uk.co.lukestevens.challenges.tunnels;

import java.awt.Point;
import java.util.List;

public class TunnelKey {
	
	Point point;
	String key;
	List<String> prerequesites;
	
	public TunnelKey(String key, Point point, List<String> prerequesites) {
		this.point = point;
		this.key = key;
		this.prerequesites = prerequesites;
	}

	public String getKey() {
		return key;
	}

	public List<String> getPrerequesites() {
		return prerequesites;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public boolean hasPrerequisiteKeys(List<String> keysCollected) {
		for(String prerequesite : prerequesites) {
			if(!keysCollected.contains(prerequesite)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TunnelKey other = (TunnelKey) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return key + " needs " + String.join(", ", prerequesites);
	}

}
