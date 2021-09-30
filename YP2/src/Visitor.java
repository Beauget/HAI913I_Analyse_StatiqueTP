package step2;

import java.util.List;

import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class Visitor {
	CompilationUnit parse;
	MethodDeclarationVisitor visitorMethodes = new MethodDeclarationVisitor();
	TypeDeclarationVisitor visitorTypes = new TypeDeclarationVisitor();
	AnonymousClassDeclarationVisitor visitorClassesAnonymes = new AnonymousClassDeclarationVisitor();
	VariableDeclarationFragmentVisitor visitorVariable = new VariableDeclarationFragmentVisitor();

	
	public Visitor(CompilationUnit parse) {
		this.parse = parse;
		parse.accept(visitorMethodes);
		parse.accept(visitorTypes);
		parse.accept(visitorClassesAnonymes);	
		parse.accept(visitorVariable);
	} 
	
	public List<TypeDeclaration> getTypes() {
		return visitorTypes.getTypes();
	}
	
	public List<MethodDeclaration> getMethods() {
		return visitorMethodes.getMethods();
	}

	public List<AnonymousClassDeclaration> getClassesAnonymes() {
		return visitorClassesAnonymes.getAnonymous();
	}
	
	public List<VariableDeclarationFragment> getVariables() {
		return visitorVariable.getVariables();
	}
	
	public CompilationUnit getParse(){
		return this.parse;
	}
	
	public void print() {}
}

