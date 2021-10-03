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
	
	public InfoVisitor(CompilationUnit parse) {
		super(parse);
	} 	
	public void print() {
		//Affiche les package
		//printPackageInfo();
		
		//Affiche les classes 
		//printTypeDeclarationInfo();
		
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
		System.out.println("Nombre de lignes : "+this.getParse().getLength());
	}
	
	//navigate cherche les TypesDeclarations/ donc les classes définis au plus haut
	//par recurssion regarde les classes à l'interieur
	
	public void printTypeDeclarationInfo() {
		int i = 0;
		for (TypeDeclaration type : this.getTypes()) {
			System.out.println("Type name : "
					+ type.getName() 
					+ "  estInterface : "
					+ type.isInterface());
			i++;
			System.out.println("début : " + type.getStartPosition());
			System.out.println("Longeur: " + type.getLength());
			
		}
		if(i>0) {
		System.out.println("Nombre de classe(s) dans le fichier : "
				+ i);
		}
	}
	
	// navigate package
	public void printPackageInfo() {
		int i = 0;
		for (PackageDeclaration pack : this.getPackages()) {
			System.out.println("Packages name: " + pack.getName());
			i++;
		}
	}
	// navigate method inside class
	public void printMethodIntoTypeInfo() {
		for (TypeDeclaration type : this.getTypes()) {
			System.out.println("\\\\\\\\\\\\ CLASSE : " + type.getName()+"\\\\\\\\\\\\");
			int i = 0;
			MethodDeclarationVisitor visitor2 = new MethodDeclarationVisitor();
			type.accept(visitor2);
			for(MethodDeclaration method : visitor2.getMethods()){
				System.out.println("Method name: " + method.getName()
				+ " Return type: " + method.getReturnType2()
				+" Parameter : " + method.parameters().size());	
				i++;
			}
			System.out.println("Nombre de methode(s) : " + i);
		}
	}
	
	// navigate method inside class
	public void printVariableIntoTypeInfo() {
		for (TypeDeclaration type : this.getTypes()) {
			System.out.println("\\\\\\\\\\\\ CLASSE : " + type.getName()+"\\\\\\\\\\\\");
			int i = 0;
			VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
			type.accept(visitor2);
			for(VariableDeclarationFragment variable : visitor2.getVariables()){
				System.out.println("Variable name: " + variable.getName());	
				i++;
			}
			System.out.println("Nombre de variable(s) : " + i);
		}
	}
	
	

	
	// navigate method information
	public void printMethodInfo() {
		for (MethodDeclaration method : this.getMethods()) {
			System.out.println("Method name: " + method.getName()
					+ " Return type: " + method.getReturnType2()
					+" Parameter : " + method.parameters().size());
		}
	}
	
	// navigate recherche les classes anonymes dans les methodes
	public void printAnonymousClassInfo() {
		for (MethodDeclaration method : this.getMethods()) {
			AnonymousClassDeclarationVisitor visitor2 = new AnonymousClassDeclarationVisitor();
			method.accept(visitor2);
			for(AnonymousClassDeclaration anonymousClassDeclaration : visitor2.getAnonymous()){
				System.out.println("AnonymousClass: "
						+ anonymousClassDeclaration.toString());				
			}
		}
	}
	
	// navigate method invocations inside method
	public void printMethodInvocationInfo() {
		for (MethodDeclaration method : this.getMethods()) {
			MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
			method.accept(visitor2);

			for (MethodInvocation methodInvocation : visitor2.getMethods()) {
				System.out.println("method " + method.getName() + " invoc method "
						+ methodInvocation.getName());
			}
		}
	}
	
	// navigate variables inside method
	public void printVariableInsideMethodInfo() {
		for (MethodDeclaration method : this.getMethods()) {
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
	
	// navigate variables 
	public void printVariableInfo() {
		for (VariableDeclarationFragment variableDeclarationFragment : visitorVariables
				.getVariables()) {
			System.out.println("variable name: "
					+ variableDeclarationFragment.getName()
					+ " variable Initializer: "
					+ variableDeclarationFragment.getInitializer());
			}
	}
	
}

