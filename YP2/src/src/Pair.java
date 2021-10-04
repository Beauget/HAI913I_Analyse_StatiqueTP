package src;

import java.util.Comparator;

public class Pair implements Comparable<Pair>{
	String key;
	int value;
	
	public Pair(String k, int v) {
		this.key=k;
		this.value=v;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public int getValue(){
		return this.value;
	}

	 public int compareTo(Pair p) {
	        return (this.value - p.getValue());
	 }

	 public String toString() {
		 return(this.getKey()+" : "+this.getValue());
	 }
	 
}
