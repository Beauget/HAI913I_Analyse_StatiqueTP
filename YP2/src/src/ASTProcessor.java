package src;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

import src.Visitor;

public class ASTProcessor {
	ArrayList<String> content;
	StatVisitor statVisitor;
	InfoVisitor infoVisitor;
	
	public ASTProcessor(ArrayList<CompilationUnit> p,ArrayList<String> c) {
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
	
	public String exercice8() {return infoVisitor.top10MethodToString();}
	
	public String exercice9() {return infoVisitor.top10VarToString();}
	
	public String exercice10() {return infoVisitor.top10MethodAndVar();}
	
	public String exercice11(int x) {return infoVisitor.classWithXMethod(x);}
	
	public String exercice12() {return infoVisitor.top10MethodLigneParClasse();}
	
	public String exercice13() {return statVisitor.nbMaxParamMethod();}
	
	public String printAll() {
		String rslt = "";
		rslt +=("1 - Nombre de classes de l'application \n" );
		rslt += exercice1();
		rslt +=("2 - Nombre de lignes de code de l'application. \n");
		rslt += exercice2();
		rslt +=("3 - Nombre total de méthodes de l'application. \n");
		rslt += exercice3();
		rslt +=("4 - Nombre total de packages de l'application. \n");
		rslt += exercice4();
		rslt +=("5 - Nombre moyen de méthodes par classe. \n");
		rslt += exercice5();
		rslt +=("6 - Nombre moyen de lignes de code par méthode. \n");
		rslt += exercice6();
		rslt +=("7 - Nombre moyen d'attributs par classe. \n");
		rslt += exercice7();
		rslt +=("8 - Les 10% des classes qui possèdent le plus grand nombre de méthodes. \n");
		rslt += exercice8();
		rslt +=("9 - Les 10% des classes qui possèdent le plus grand nombre d'attributs. \n");
		rslt += exercice9();
		rslt +=("10 - Les classes qui font partie en même temps des deux catégories précédentes. \n");
		rslt += exercice10();
		rslt +=("11 - Les classes qui possèdent plus de 4 méthodes. \n");
		rslt += exercice11(4);
		rslt +=("12 - Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe). \n");
		rslt += exercice12();
		rslt +=("13 - Le nombre	maximal	de paramètres par rapport à toutes les méthodes	de l'application. \n");
		rslt += exercice13();
		
		return rslt;
	}
	
}


