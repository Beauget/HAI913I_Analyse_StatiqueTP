package src;

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
	MethodDeclarationVisitor visitorMethodes = new MethodDeclarationVisitor();
	TypeDeclarationVisitor visitorTypes = new TypeDeclarationVisitor();
	AnonymousClassDeclarationVisitor visitorClassesAnonymes = new AnonymousClassDeclarationVisitor();

	
	public InfoVisitor(CompilationUnit parse) {
		super(parse);
	} 

	
	public void print() {
		// print methods info
		printMethodInfo();

		// print variables info
		printVariableInfo();
		
		//print method invocations
		printMethodInvocationInfo();
	}
	
	
	
	// navigate method information
	public void printMethodInfo() {
		for (MethodDeclaration method : this.getMethods()) {
			System.out.println("Method name: " + method.getName()
					+ " Return type: " + method.getReturnType2());
		}
	}

	// navigate variables inside method
	public void printVariableInfo() {

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
