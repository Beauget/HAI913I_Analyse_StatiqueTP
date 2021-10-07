package graphe;

import java.util.ArrayList;

public class Feuille extends Composant {
	public void display(){
		String s= "";
		s+="Type : "+this.type+"\n"+
		"Nom : "+this.name+"\n";	
	};
	
	public Feuille(String type, String name, ArrayList<String> calls) {
		super(type, name, calls);
	}
	
	public Feuille(String type, String name) {
		super(type, name);
	}
}
