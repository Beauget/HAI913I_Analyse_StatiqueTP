package src;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class AnonymousClassDeclarationVisitor extends ASTVisitor{
	List<AnonymousClassDeclaration> anonymous= new ArrayList<AnonymousClassDeclaration>();
	
	public boolean visit(AnonymousClassDeclaration node) {
		anonymous.add(node);
		return super.visit(node);
	}
	
	public List<AnonymousClassDeclaration> getAnonymous() {
		return anonymous;
	}

}
