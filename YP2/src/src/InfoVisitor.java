package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public class InfoVisitor extends Visitor{
	
	public InfoVisitor(ArrayList<CompilationUnit> project,ArrayList<String> c) {
		super(project,c);
	} 	
	
	public String codeLength() {
		int i = 0;
		for(CompilationUnit parse :  this.project) {
			i+= parse.getLength();
		}
		return("Nombre de caractères : "+ i+ "\n \n");
	}

	
	//EXERCICE 8 Les 10% des classes qui possèdent le plus grand nombre de methodes.
	//Hashmap key : class value : nombre de methods
	public ArrayList<Pair>  nbMethodInType(){
		Map<String, Integer> mapClass = new HashMap<String, Integer>();
		ArrayList<Pair> liste = new ArrayList<Pair>();

		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				int nMethod = 0;
				MethodDeclarationVisitor visitor2 = new MethodDeclarationVisitor();
				type.accept(visitor2);
				for(MethodDeclaration method : visitor2.getMethods()){
					nMethod++;
				}
				mapClass.put(type.getName().toString(),nMethod);
				liste.add(new Pair(type.getName().toString(),nMethod));
			}
		}
		return liste;
	}
	
	
	public ArrayList<Pair> top10Method(){
		int nb = Math.floorDiv(project.size(),10);
		if(nb==0)
			nb=1;
		ArrayList<Pair> listeM = nbMethodInType();
		ArrayList<Pair> rslt = new ArrayList<Pair>();
		
		//TRIE CROISSANT
		Collections.sort(listeM);
		
		//Parcours decroissant
		for(int i=listeM.size()-1; i>(listeM.size()-nb-1);i--) {
			rslt.add(listeM.get(i));
		}
		return rslt;
	}
	
	public String top10MethodToString() {
		String rslt = "";
		for(Pair p : top10Method()){
			rslt+="La classe "+p.getKey()+ " avec "+p.value+ " méthode(s) \n \n";
		}
		return rslt;
	}
	
	
	//EXERCICE 9 Les 10% des classes qui possèdent le plus grand nombre d’attributs
	//Hashmap key : class value : nombre de variables
	
	public ArrayList<Pair>  nbVarInType(){
		Map<String, Integer> mapClass = new HashMap<String, Integer>();
		ArrayList<Pair> liste = new ArrayList<Pair>();

		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				int nVar = 0;
				VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
				type.accept(visitor2);
				for(VariableDeclarationFragment var : visitor2.getVariables()){
					nVar++;
				}
				mapClass.put(type.getName().toString(),nVar);
				liste.add(new Pair(type.getName().toString(),nVar));
			}
		}
		return liste;
	}
	
	public ArrayList<Pair> top10Var(){
		int nb = Math.floorDiv(project.size(),10);
		ArrayList<Pair> rslt= new ArrayList<Pair>();
		if(nb==0)
			nb=1;
		ArrayList<Pair> listeV = nbVarInType();
		
		//TRIE CROISSANT
		Collections.sort(listeV);

		//Parcours decroissant
		for(int i=listeV.size()-1; i>(listeV.size()-nb-1);i--) {
			rslt.add(listeV.get(i));
		}
		return rslt;
	}
	
	public String top10VarToString() {
		String rslt = "";
		for(Pair p : top10Var()){
			rslt+="La classe "+p.getKey()+ " avec "+p.value+ " attribut(s) \n \n";
		}
		
		return rslt;
	}
	
	//EXERCICE 10 Les classes qui font partie en même temps des deux catégories précédentes.
	public String top10MethodAndVar(){
		String rslt= "";
		
		ArrayList<Pair> listeM = top10Method();
		ArrayList<Pair> listeV = top10Var();
		
		for(int i=0 ;i<listeM.size();i++) {
			for(int j = 0; j<listeV.size();j++){
				if(listeM.get(i).getKey().equals(listeV.get(j).getKey()))
					rslt+="La classe "+listeV.get(j).getKey()+ " avec "+listeM.get(i).getValue()+ " méthodes(s) et "
							+listeV.get(j).getValue()+ " attribut(s) \n \n";
			}
		}	
		return rslt;
	}
	
	
	
	//EXERCICE 11 Les classes qui possèdent plus de X méthodes (la valeur de X est donnée).
	public String classWithXMethod(int x){
		ArrayList<Pair> array= new ArrayList<Pair>();		
		ArrayList<Pair> listeM = nbMethodInType();
		String rslt = "Les classes qui possèdent plus de "+ x +" méthode(s) : \n";
		
		for(int i=0 ; i<listeM.size(); i++) {
			if(listeM.get(i).getValue()>=x) {
				array.add(listeM.get(i));
				rslt+=listeM.get(i).key +" avec "+listeM.get(i).getValue()+" methode(s) \n";
			}
		}
		return rslt +"\n";
	}
	
	//EXERCICE 12 Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe).
	public String top10MethodLigneParClasse() {
		String rslt = "";
		ArrayList<ArrayList<Pair>> listeMethode = new ArrayList<ArrayList<Pair>>();
		ArrayList<String> listeClasse = new ArrayList<String>();
		
		int index = 0;
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				
				listeClasse.add(type.getName().toString());
				listeMethode.add(new ArrayList<Pair>());


				MethodDeclarationVisitor visitor2 = new MethodDeclarationVisitor();
				type.accept(visitor2);
				for(MethodDeclaration method : visitor2.getMethods()){
					listeMethode.get(index).add(new Pair (method.getName().toString(), countLines(method.toString() ) ));
					}
				index++;
				}
			}
		
		for(int i=0; i<listeMethode.size();i++) {
				//TRIE CROISSANT
				Collections.sort(listeMethode.get(i));
				int nb = Math.floorDiv(listeMethode.get(i).size(),10);
				if(nb==0)
					nb=1;
				rslt+= "Pour la classe : "+listeClasse.get(i)+ "\n";
				//Parcours decroissant
				if(listeMethode.get(i).size()>1) {
				for(int j=listeMethode.get(i).size()-1; j>(listeMethode.get(i).size()-nb-1);j--) {
					rslt+= "La méthode : "+ listeMethode.get(i).get(j).getKey() +" avec " + listeMethode.get(i).get(j).getValue() +" ligne(s) \n";
				}}
				else {
					rslt+="Pas de méthode \n";
				}
		}
		
		return rslt + "\n";
	}
}

