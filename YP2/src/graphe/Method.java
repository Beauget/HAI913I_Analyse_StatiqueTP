package graphe;

import java.util.ArrayList;

public class Method {
	String name;
	ArrayList<String> calls = new ArrayList<String>();
	
	
	public Method(String name, ArrayList<String> calls) {
		super();
		this.name = name;
		this.calls = calls;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getCalls() {
		return calls;
	}
	public void setCalls(ArrayList<String> calls) {
		this.calls = calls;
	}
	
	public String toString(){
		String rslt = "";
		rslt+= "Méthode : "+this.getName()+"\n"
		+"Méthodes invoquées : "+ this.getCalls().toString()+"\n";
		return rslt;
	}

}
