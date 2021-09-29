package src;

import java.util.List;

import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Visitor {
	MethodDeclarationVisitor visitorMethodes = new MethodDeclarationVisitor();
	TypeDeclarationVisitor visitorTypes = new TypeDeclarationVisitor();
	AnonymousClassDeclarationVisitor visitorClassesAnonymes = new AnonymousClassDeclarationVisitor();

	
	public Visitor(CompilationUnit parse) {
		parse.accept(visitorMethodes);
		parse.accept(visitorTypes);
		parse.accept(visitorClassesAnonymes);	
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
	
	public void print() {}
}
