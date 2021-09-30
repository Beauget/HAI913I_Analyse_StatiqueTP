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

public class InfoVisitor extends Visitor{
	
	public InfoVisitor(CompilationUnit parse) {
		super(parse);
	} 

	
	public void print() {
		// print methods info
		printMethodInfo();
		
		// print variables info
		//printVariableInfo();
		
		//print method invocations
		//printMethodInvocationInfo();
		
		//printAnonymousClassInfo();
		//printCodeLength();
		//printTypeDeclarationInfo();
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
			
		}
		if(i>0) {
		System.out.println("Nombre de classe(s) dans le fichier : "
				+ i);
		}
	}
	
	// navigate recherche les classes anonymes dans les methodes
	public void printAnonymousClassInfo() {
		AnonymousClassDeclarationVisitor visitor2 = new AnonymousClassDeclarationVisitor();
		for (MethodDeclaration method : this.getMethods()) {
			method.accept(visitor2);
			for(AnonymousClassDeclaration anonymousClassDeclaration : visitor2.getAnonymous()){
				System.out.println("AnonymousClass: "
						+ anonymousClassDeclaration.toString());
				
			}
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
	
	// navigate variables 
	public void printVariableInfo() {
		for (VariableDeclarationFragment variableDeclarationFragment : visitorVariable
				.getVariables()) {
			System.out.println("variable name: "
					+ variableDeclarationFragment.getName()
					+ " variable Initializer: "
					+ variableDeclarationFragment.getInitializer());
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
}

