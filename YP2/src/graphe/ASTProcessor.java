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
	CompilationUnit compilationUnit;
	ArrayList<Method> listMethods = new ArrayList<Method>();
	
	public ASTProcessor(CompilationUnit compilation) {
		this.compilationUnit=compilation;
		methodsWithCalls(compilation);
	}
	
	public String toString() {
		String rslt = "";	
		for(Method method : listMethods){
			rslt+=method.toString()+"\n";			
		}
		return rslt;
	}
	
	public void methodsWithCalls(CompilationUnit parse) {
		//index of main methods inside listMethodsCalls
		int i = 0;
		//create visitor to get main method
		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		for (MethodDeclaration method : visitor1.getMethods()) {
			//get invocations
			MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
			method.accept(visitor2);
			//append method
			listMethods.add(new Method(method.getName().toString(), getMethodsCalls(visitor2)));
			//increment to next main method
			i++;
			}
		}
	
	//Get all method call in a method
	public ArrayList<String> getMethodsCalls(MethodInvocationVisitor methodInvocationvisitor) {
		ArrayList<String> listMethodInvocation = new ArrayList<String>();
		for (MethodInvocation methodInvocation : methodInvocationvisitor.getMethods()) {
			//if the method is called multiple times, only get the first call
			if(!listMethodInvocation.contains(methodInvocation.getName().toString())) {
				listMethodInvocation.add(methodInvocation.getName().toString());
				}		
			}
		return listMethodInvocation;
	}
}
