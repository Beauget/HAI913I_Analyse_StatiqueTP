package step2;

import java.util.ArrayList;
import java.util.List;

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
	
	public InfoVisitor(ArrayList<CompilationUnit> project) {
		super(project);
	} 	
	public void print() {
		//Affiche les package
		//printPackageInfo();
		
		//Affiche les classes 
		printTypeDeclarationInfo();
		
		// Affiche les methodes
		//printMethodInfo();
		
		//Affiche les Variables
		//printVariableInfo();
		
		//Affiche les methodes appeler au sein des methodes
		//printMethodInvocationInfo();
		
		//Affiche les classes ANonymes
		//printAnonymousClassInfo();
		
		
		//Affiche les methodes dans chaque classe
		//printMethodIntoTypeInfo();
		
		//Affiche les variables dans chaque classe 
		//printVariableIntoTypeInfo();
		
		//Etrange
		//System.out.println(parse.getLength());
		//printCodeLength();

	}
	
	public void printCodeLength() {
		int i = 0;
		for(CompilationUnit parse :  this.project) {
			i+= parse.getLength();
		}
		System.out.println("Nombre de caractères : "+ i);
	}
	
	//navigate cherche les TypesDeclarations/ donc les classes définis au plus haut
	//par recurssion regarde les classes à l'interieur
	
	public void printTypeDeclarationInfo() {
		for(int i=0; i<getProject().size(); i++) {
			int j = 0;
			for (TypeDeclaration type : this.getTypes(i)) {
				System.out.println("Type name : "
						+ type.getName() 
						+ "  estInterface : "
						+ type.isInterface());
				j++;
				//System.out.println("début : " + type.getStartPosition());
				//System.out.println("Longeur: " + type.getLength());
			}
			if(j>0) {
			System.out.println("Nombre de classe(s) dans le fichier : "
					+ j);
			}
		}
	}
	
	// navigate package
	public void printPackageInfo() {
		int j =0;
		for(int i=0; i<getProject().size(); i++) {
			for (PackageDeclaration pack : this.getPackages(i)) {
				System.out.println("Packages name: " + pack.getName());
				j++;
			}
		}
		System.out.println("Nombre total de package : " + j);
	}
	// navigate method inside class
	public void printMethodIntoTypeInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				System.out.println("\\\\\\\\\\\\ CLASSE : " + type.getName()+"\\\\\\\\\\\\");
				int j = 0;
				MethodDeclarationVisitor visitor2 = new MethodDeclarationVisitor();
				type.accept(visitor2);
				for(MethodDeclaration method : visitor2.getMethods()){
					System.out.println("Method name: " + method.getName()
					+ " Return type: " + method.getReturnType2()
					+" Parameter : " + method.parameters().size());	
					j++;
				}
				System.out.println("Nombre de methode(s) : " + j);
			}
		}
	}
	
	// navigate method inside class
	public void printVariableIntoTypeInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (TypeDeclaration type : this.getTypes(i)) {
				System.out.println("\\\\\\\\\\\\ CLASSE : " + type.getName()+"\\\\\\\\\\\\");
				int j = 0;
				VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
				type.accept(visitor2);
				for(VariableDeclarationFragment variable : visitor2.getVariables()){
					System.out.println("Variable name: " + variable.getName());	
					j++;
				}
				System.out.println("Nombre de variable(s) : " + j);
			}
		}
	}
	
	

	
	// navigate method information
	public void printMethodInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (MethodDeclaration method : this.getMethods(i)) {
				System.out.println("Method name: " + method.getName()
						+ " Return type: " + method.getReturnType2()
						+" Parameter : " + method.parameters().size());
			}
		}
	}
	
	// navigate recherche les classes anonymes dans les methodes
	public void printAnonymousClassInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (MethodDeclaration method : this.getMethods(i)) {
				AnonymousClassDeclarationVisitor visitor2 = new AnonymousClassDeclarationVisitor();
				method.accept(visitor2);
				for(AnonymousClassDeclaration anonymousClassDeclaration : visitor2.getAnonymous()){
					System.out.println("AnonymousClass: "
							+ anonymousClassDeclaration.toString());				
				}
			}
		}
	}
	
	// navigate method invocations inside method
	public void printMethodInvocationInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (MethodDeclaration method : this.getMethods(i)) {
				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);
	
				for (MethodInvocation methodInvocation : visitor2.getMethods()) {
					System.out.println("method " + method.getName() + " invoc method "
							+ methodInvocation.getName());
				}
			}
		}
	}
	
	// navigate variables inside method
	public void printVariableInsideMethodInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (MethodDeclaration method : this.getMethods(i)) {
				VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
				method.accept(visitor2);
	
				for (VariableDeclarationFragment variableDeclarationFragment : visitor2
						.getVariables()) {
					System.out.println("variable name: "
							+ variableDeclarationFragment.getName()
							+ " variable Initializer: "
							+ variableDeclarationFragment.getInitializer());
					}
				}	
			}
		}
	
	// navigate variables 
	public void printVariableInfo() {
		for(int i=0; i<getProject().size(); i++) {
			for (VariableDeclarationFragment variableDeclarationFragment : this.getVariables(i)) {
				System.out.println("variable name: "
						+ variableDeclarationFragment.getName()
						+ " variable Initializer: "
						+ variableDeclarationFragment.getInitializer());
			}
		}
	}
	
}
