package uk.co.lukestevens.utils;

import java.util.Objects;

public class Pair<A, B> {
	
	private A a;
	private B b;
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair) {
			Pair<?,?> pair = (Pair<?,?>) obj;
			return Objects.equals(pair.getA(), a) && Objects.equals(pair.getB(), b);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Pair [a=" + a + ", b=" + b + "]";
	}
	
	

}
