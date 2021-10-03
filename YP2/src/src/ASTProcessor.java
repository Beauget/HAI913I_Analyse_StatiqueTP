package src;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTProcessor {
	ArrayList<CompilationUnit> project;
	ArrayList<String> content;
	Visitor visitor;
	
	public ASTProcessor(ArrayList<CompilationUnit> p,String s,ArrayList<String> c) {
		this.project = p;
		if(s.equals("InfoVisitor")){
			visitor = new InfoVisitor(p,c);
		}
		if(s.equals("StatVisitor")) {
			visitor = new StatVisitor(p,c);
		}
	}
	public void print(){
		visitor.print();
	}
}

