package team.scaucs1.utils;

public class Pair<E1,E2> {
	private E1 x;
	private E2 y;
	
	public Pair(E1 x, E2 y) {
		this.x = x;
		this.y = y;
	}
	
	public E1 getKey() {
		return x;
	}
	
	public E2 getValue() {
		return y;
	}
	
}
