package graphe;

import java.util.ArrayList;

public class Method {
	String name;
	ArrayList<String> calls = new ArrayList<String>();
	ArrayList<String> entries = new ArrayList<String>();
	
	
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
	
	public ArrayList<String> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<String> entries) {
		this.entries = entries;
	}
	
	public String toString(){
		String rslt = "";
		rslt+= "Méthode : "+this.getName()+"\n"
		+"Méthode(s) sortie : "+ this.getCalls().toString()+"\n"
				+"Méthode(s) entrée : "+ this.getEntries().toString()+"\n";
		return rslt;
	}
	
	public void isEntryThenAdd(Method method){
		if(method.getCalls().contains(this.getName()))
			this.getEntries().add(method.getName());
	}

}
