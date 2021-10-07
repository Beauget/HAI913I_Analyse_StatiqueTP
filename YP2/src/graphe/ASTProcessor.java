package graphe;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import src.InfoVisitor;
import src.MethodDeclarationVisitor;
import src.StatVisitor;
import src.VariableDeclarationFragmentVisitor;

public class ASTProcessor {
	ArrayList<String> content = new ArrayList<String>();
	ArrayList<CompilationUnit> compilationUnit = new ArrayList<CompilationUnit>();
	
	public ASTProcessor(ArrayList<CompilationUnit> compil,ArrayList<String> c) {
		this.content = c;
		this.compilationUnit=compil;
	}
	
	// navigate method information
	public static void printMethodInfo(CompilationUnit parse) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods()) {
			System.out.println("Method name: " + method.getName()
					+ " Return type: " + method.getReturnType2());
		}

	}
	
	public void fct() {
		Composite composite = new Composite("racine","racine");
		for (CompilationUnit c : this.compilationUnit) {
			composite.add(printVariableInfo(c));
		}
		composite.display();
	}

	// navigate variables inside method
	public static Composant printVariableInfo(CompilationUnit parse) {
		Composant c = new Composant("","");
		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		for (MethodDeclaration method : visitor1.getMethods()) {
			
			ArrayList<String> calls = new ArrayList<String>();
			Composant composant = new Composant("Method", method.getName().toString(),calls);
			
			MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
			method.accept(visitor2);
			
			for (MethodInvocation methodInvocation : visitor2.getMethods()) {
				//System.out.println("method " + method.getName() + " invoc method "
						//+ methodInvocation.getName());
				composant.addCall(methodInvocation.getName().toString());
			}
			return composant;
		}
		return c;
	}
	
	// navigate method invocations inside method
		public static void printMethodInvocationInfo(CompilationUnit parse) {

			MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
			parse.accept(visitor1);
			for (MethodDeclaration method : visitor1.getMethods()) {

				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);

				for (MethodInvocation methodInvocation : visitor2.getMethods()) {
					System.out.println("method " + method.getName() + " invoc method "
							+ methodInvocation.getName());
				}
				
				VariableDeclarationFragmentVisitor visitor3 = new VariableDeclarationFragmentVisitor();
				method.accept(visitor2);

				for (VariableDeclarationFragment variableDeclarationFragment : visitor3
						.getVariables()) {
					System.out.println("variable name: "
							+ variableDeclarationFragment.getName()
							+ " variable Initializer: "
							+ variableDeclarationFragment.getInitializer());
				}

			}
		}
}
