package graphe;

import java.util.ArrayList;

public class Composant {
	public String type;
	public String name;
	public ArrayList<String> calls = new ArrayList<String>();
	
	public void display(){
		String s= "";
		s+="Type : "+this.type+"\n"+
		"Nom : "+this.name+"\n"+
				calls.toString()+ "\n \n";
		System.out.println(s);
	}
	
	public Composant(String type, String name, ArrayList<String> calls) {
		super();
		this.type = type;
		this.name = name;
		this.calls = calls;
	};
	
	public Composant(String type, String name) {
		this.type = type;
		this.name = name;
	};
	
	public void addCall(String s) {
		this.calls.add(s);
	}
}
