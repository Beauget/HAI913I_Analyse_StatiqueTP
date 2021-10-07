package graphe;

import java.util.ArrayList;

public class Composite extends Composant{
	ArrayList<Composant> subComposant = new ArrayList<Composant>();
	
	public Composite(String type, String name, ArrayList<String> calls) {
		super(type, name, calls);
	}
	
	public Composite(String type, String name) {
		super(type, name);
	}


	public void display(){
		String s= "";
		s+="Type : "+this.type+"\n"+
		"Nom : "+this.name+"\n";
		System.out.println(s);
		for(Composant c : subComposant) {
			c.display();
		}
	};
	public ArrayList<Composant> getChild(){return subComposant;};
	public void add(Composant c){ 
		if (c.name.length()>0)
		subComposant.add(c);};
	public void remove(){};
}
