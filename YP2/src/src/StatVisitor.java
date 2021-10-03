package step2;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class StatVisitor extends Visitor {
	public StatVisitor(ArrayList<CompilationUnit> parse){
		super(parse);
	}
	
	public void print() {
		// TODO
	}
}
