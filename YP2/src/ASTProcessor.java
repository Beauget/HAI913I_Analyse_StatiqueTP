package src;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTProcessor {
	CompilationUnit parse;
	Visitor visitor;
	
	public ASTProcessor(CompilationUnit p,String s) {
		this.parse = p;
		if(s.equals("InfoVisitor")){
			visitor = new InfoVisitor(p);
		}
		if(s.equals("StatVisitor")) {
			visitor = new StatVisitor(p);
		}
	}
	public void print(){
		visitor.print();
	}
}
