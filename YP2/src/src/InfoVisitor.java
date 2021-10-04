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
	public void print() {

		//EXERCICE 8
		System.out.println("Les 10% des classes qui possèdent le plus grand nombre de méthodes.");
		System.out.println(top10Method().toString());
		
		//EXERCICE 9
		System.out.println("Les 10% des classes qui possèdent le plus grand nombre d’attributs");
		System.out.println(top10Var().toString());
		
		//EXERCICE 10
		System.out.println("Les classes qui font partie en même temps des deux");
		System.out.println(top10MethodAndVar().toString());
		
		//EXERCICE 11
		System.out.println("Les classes qui possèdent plus de X méthodes");
		System.out.println(classWithXMethod(4).toString());
		
		//EXERCICE 12
		System.out.println("Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe).");

	}
	
	public void printCodeLength() {
		int i = 0;
		for(CompilationUnit parse :  this.project) {
			i+= parse.getLength();
		}
		System.out.println("Nombre de caractères : "+ i);
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
	
	
	public ArrayList<String>  top10Method(){
		int nb = Math.floorDiv(project.size(),10);
		ArrayList<String> rslt= new ArrayList<String>();
		if(nb==0)
			nb=1;
		ArrayList<Pair> listeM = nbMethodInType();
		
		//TRIE CROISSANT
		Collections.sort(listeM);
		
		//Parcours decroissant
		for(int i=listeM.size()-1; i>(listeM.size()-nb-1);i--) {
			rslt.add(listeM.get(i).getKey());
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
	
	public ArrayList<String> top10Var(){
		int nb = Math.floorDiv(project.size(),10);
		ArrayList<String> rslt= new ArrayList<String>();
		if(nb==0)
			nb=1;
		ArrayList<Pair> listeV = nbVarInType();
		
		//TRIE CROISSANT
		Collections.sort(listeV);

		//Parcours decroissant
		for(int i=listeV.size()-1; i>(listeV.size()-nb-1);i--) {
			rslt.add(listeV.get(i).getKey());
		}
		return rslt;
	}
	
	
	//EXERCICE 10 Les classes qui font partie en même temps des deux catégories précédentes.
	//TODO
	public ArrayList<String> top10MethodAndVar(){
		ArrayList<String> rslt= new ArrayList<String>();
		
		ArrayList<String> listeM = top10Method();
		ArrayList<String> listeV = top10Var();
		
		for(int i=0 ;i<listeM.size();i++) {
			for(int j = 0; j<listeV.size();j++){
				if(listeM.get(i).equals(listeV.get(j)))
						rslt.add(listeM.get(i));
			}
		}	
		return rslt;
	}
	
	
	
	//EXERCICE 11 Les classes qui possèdent plus de X méthodes (la valeur de X est donnée).
	public ArrayList<String> classWithXMethod(int x){
		ArrayList<String> rslt= new ArrayList<String>();		
		ArrayList<Pair> listeM = nbMethodInType();
		
		for(int i=0 ; i<listeM.size(); i++) {
			if(listeM.get(i).getValue()==x) {
				rslt.add(listeM.get(i).getKey());
			}
		}
		return rslt;
	}
	
}

