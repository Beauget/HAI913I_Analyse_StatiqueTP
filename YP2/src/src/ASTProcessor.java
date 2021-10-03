package step2;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTProcessor {
	ArrayList<CompilationUnit> project;
	Visitor visitor;
	
	public ASTProcessor(ArrayList<CompilationUnit> p,String s) {
		this.project = p;
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
