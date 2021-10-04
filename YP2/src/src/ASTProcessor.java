package src;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

import src.Visitor;

public class ASTProcessor {
	ArrayList<CompilationUnit> project;
	ArrayList<String> content;
	StatVisitor statVisitor;
	InfoVisitor infoVisitor;
	
	public ASTProcessor(ArrayList<CompilationUnit> p,ArrayList<String> c) {
		this.project = p;
		this.statVisitor= new StatVisitor(p,c);
		this.infoVisitor= new InfoVisitor(p,c);
	}	
	public Visitor getStatVisitor() {
		return this.statVisitor;
	}
	
	public Visitor getInfoVisitor() {
		return this.infoVisitor;
	}
	
	public String exercice1() {return statVisitor.numberOfClass();}

	public String exercice2() {return statVisitor.numberOfLines();}
	
	public String exercice3() {return statVisitor.numberOfMethod();}
	
	public String exercice4() {return statVisitor.numberOfPackage();}
	
	public String exercice5() {return statVisitor.averageOfMethod();}
	
	public String exercice6() {return statVisitor.averageLineOfMethod();}
	
	public String exercice7() {return statVisitor.averageOfVariableByClass();}
	
	public String exercice8() {return infoVisitor.top10Method().toString();}
	
	public String exercice9() {return infoVisitor.top10Var().toString();}
	
	public String exercice10() {return infoVisitor.top10MethodAndVar().toString();}
	
	public String exercice11() {return infoVisitor.classWithXMethod(4).toString();}
	
	public String exercice12() {return "TODO infoVisitor";}
	
	public String exercice13() {return "TODO statVisitor.";}
	
}


