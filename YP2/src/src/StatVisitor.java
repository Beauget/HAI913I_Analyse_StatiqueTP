package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


import java.lang.Math;

public class StatVisitor extends Visitor {
	public StatVisitor(ArrayList<CompilationUnit> project,ArrayList<String> c){
		super(project,c);
	}
	
	public void print() {
		// TODO
		//EXERCICE 1
		numberOfClass();
		
		//EXERCICE 2
		System.out.println("Nombre de lignes de code de l’application.");
		
		//EXERCICE 3
		numberOfMethod();
		//EXERCICE 4
		numberOfPackage();
		
		//EXERCICE 5
		System.out.println("Nombre de ligne(s) dans l'application: "+ numberOfLines());
		
		//EXERCICE 6
		averageOfMethod();
		
		//EXERCICE 7
		averageOfVariableByClass();
		
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
		
		
		//EXERCICE 13
		System.out.println("Le nombre maximal de paramètres par rapport à toutes les méthodes de l’application.");
		
	}
	//EXERCICE 1 Nombre de classes de l’application.
	public void numberOfClass(){
		int j = 0;
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				j++;
			}
		}
		System.out.println("Nombre de classe(s) dans l'application : "
				+ j);
	}
	
	//EXERCICE 2 Nombre de lignes de code de l’application.
	public int numberOfLines() {
		int rslt = 0;
		
		for(String s : this.getContent()){
			rslt+= countLines(s);
		}		
		return rslt;
	}
	
	//EXERCICE 3 Nombre total de méthodes de l’application.
	public void numberOfMethod(){
		int j = 0;
		for(int i=0; i<getProject().size(); i++) {
			for (MethodDeclaration method : this.getMethods(i)) {
				j++;
			}
		}
		System.out.println("Nombre de méthode(s) dans l'application : "
				+ j);
	}
	
	//EXERCICE 4 Nombre total de packages de l’application.
	
	public void numberOfPackage() {
		int j =0;
		for(int i=0; i<getProject().size(); i++) {
			for (PackageDeclaration pack : this.getPackages(i)) {
				j++;
			}
		}
		System.out.println("Nombre total de package : " + j);
	}
	
	//EXERCICE 5 Nombre moyen de méthodes par classe.
	
	public void averageOfMethod() {
		int nClass = 0;
		int nMethod = 0;
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				nClass++;
				MethodDeclarationVisitor visitor2 = new MethodDeclarationVisitor();
				type.accept(visitor2);
				for(MethodDeclaration method : visitor2.getMethods()){
					nMethod++;
				}
			}
		}
		System.out.println("Nombre moyen de methodes par classe : " + nMethod+"/"+nClass);
	}	
	
	//EXERCICE 6 Nombre moyen de lignes de code par méthode.
	//TODO
	
	
	//EXERCICE 7 Nombre moyen d’attributs par classe.
	
	public void averageOfVariableByClass() {
		int nClass = 0;
		int nVar = 0;
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				nClass++;
				VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
				type.accept(visitor2);
				for(VariableDeclarationFragment var : visitor2.getVariables()){
					nVar++;
				}
			}
		}
		System.out.println("Nombre moyen d'attributs par classe : " + nVar+"/"+nClass);
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
	
	
	
	//EXERCICE 12 Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe).
	
	
	
	//EXERCICE 13 Le nombre maximal de paramètres par rapport à toutes les méthodes de l’application.
	
	
}



