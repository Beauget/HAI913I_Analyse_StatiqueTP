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
		System.out.println(numberOfLines());
		
		//EXERCICE 3
		System.out.println(numberOfMethod());
		//EXERCICE 4
		System.out.println(numberOfPackage());
		
		//EXERCICE 5
		averageOfMethod();

		//EXERCICE 6 Nombre moyen de lignes de code par méthode.
		averageLineOfMethod();
		
		//EXERCICE 7
		averageOfVariableByClass();
		
		
		//EXERCICE 13
		System.out.println("Le nombre maximal de paramètres par rapport à toutes les méthodes de l’application.");
		
	}
	//EXERCICE 1 Nombre de classes de l’application.
	public String numberOfClass(){
		ArrayList<String> listeClass = new ArrayList<String>();
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				listeClass.add(type.getName().toString());
			}
		}
		return(printArrayString(listeClass)+"Nombre de classe(s) dans l'application : "
				+ listeClass.size());
	}
	
	//EXERCICE 2 Nombre de lignes de code de l’application.
	public String numberOfLines() {
		int rslt = 0;		
		for(String s : this.getContent()){
			rslt+= countLines(s);
		}		
		return ("Nombre de ligne(s) de code de l’application :"+rslt);
	}
	
	//EXERCICE 3 Nombre total de méthodes de l’application.
	public String numberOfMethod(){
		ArrayList<String> listeMethod= new ArrayList<String>();
		for(int i=0; i<getProject().size(); i++) {
			for (MethodDeclaration method : this.getMethods(i)) {
				listeMethod.add(method.getName().toString());
			}
		}
		return(printArrayString(listeMethod)+"\n"+"Nombre de méthode(s) dans l'application : "+ listeMethod.size());
	}
	
	//EXERCICE 4 Nombre total de packages de l’application.
	
	public String numberOfPackage() {
		ArrayList<String> packages = new ArrayList<String>();
		for(int i=0; i<getProject().size(); i++) {
			for (PackageDeclaration pack : this.getPackages(i)) {
				if(!(isInArrayList(packages, pack.getName().toString())))
					packages.add(pack.getName().toString());
			}
		}
		return(printArrayString(packages)+"\n"+"Nombre total de package (different) : " + packages.size());
	}
	
	//EXERCICE 5 Nombre moyen de méthodes par classe.
	
	public String averageOfMethod() {
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
		return("Nombre de classe(s) : "+nClass+"\n"
		+"Nombre de méthode(s) : "+nMethod+"\n"		
		+"Nombre moyen de methodes par classe : " + (float)nMethod/(float)nClass);
	}	
	
	//EXERCICE 6 Nombre moyen de lignes de code par méthode.
	public String averageLineOfMethod() {
		int nMethod = 0;
		int nLigneMethodTotal = 0;
		
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				MethodDeclarationVisitor visitor2 = new MethodDeclarationVisitor();
				type.accept(visitor2);
				int nLigneMethod = 0;
				for(MethodDeclaration method : visitor2.getMethods()){
					nMethod ++;
					nLigneMethod = countLines(method.toString());
					nLigneMethodTotal = nLigneMethod + nLigneMethodTotal;
				}				
			}
		}
		return("Nombre de methode(s) : "+nMethod+"\n"
				+"Nombre de ligne(s) (total des methodes) : "+nLigneMethodTotal+"\n"	
				+"Nombre moyen de lignes de code par méthode "+ (float)nLigneMethodTotal/ (float)nMethod);
	}
	
	
	//EXERCICE 7 Nombre moyen d’attributs par classe.
	
	public String averageOfVariableByClass() {
		int nClass = 0;
		int nVar = 0;
		float res = 0;
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
		res = (float)nVar/(float)nClass;
		return("Nombre de classe(s) : "+nClass+"\n"
				+"Nombre d'attribut(s) : "+nVar+"\n"	
				+"Nombre moyen d'attributs par classe : " + res);
	}
	
	
	//EXERCICE 12 Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe).
	
	
	
	//EXERCICE 13 Le nombre maximal de paramètres par rapport à toutes les méthodes de l’application.
	

}



