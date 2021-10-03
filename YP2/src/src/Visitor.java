package src;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class Visitor {
	ArrayList<CompilationUnit> project;
	ArrayList<String> content;
	ArrayList<TypeDeclarationVisitor> visitorTypes = new ArrayList<TypeDeclarationVisitor>();
	ArrayList<AnonymousClassDeclarationVisitor> visitorClassesAnonymes = new ArrayList<AnonymousClassDeclarationVisitor>();
	ArrayList<VariableDeclarationFragmentVisitor> visitorVariables = new ArrayList<VariableDeclarationFragmentVisitor>();
	ArrayList<MethodDeclarationVisitor> visitorMethodes = new ArrayList<MethodDeclarationVisitor>();

	ArrayList<PackageDeclarationVisitor> visitorPackages = new ArrayList<PackageDeclarationVisitor>();
	
	public Visitor(ArrayList<CompilationUnit> p, ArrayList<String> c) {
		this.project = p;
		for (int i = 0; i<p.size(); i++)
		{
			CompilationUnit parse = p.get(i);
			this.content = c;
			
			visitorTypes.add(new TypeDeclarationVisitor());
			visitorMethodes.add(new MethodDeclarationVisitor());
			visitorClassesAnonymes.add(new AnonymousClassDeclarationVisitor());
			visitorVariables.add(new VariableDeclarationFragmentVisitor());
			visitorPackages.add(new PackageDeclarationVisitor());
			
			parse.accept(visitorTypes.get(i));
			parse.accept(visitorMethodes.get(i));
			parse.accept(visitorClassesAnonymes.get(i));	
			parse.accept(visitorVariables.get(i));
			parse.accept(visitorPackages.get(i));
		}
	} 
	
	public List<TypeDeclaration> getTypes(int i) {
		return visitorTypes.get(i).getTypes();
	}
	
	public List<MethodDeclaration> getMethods(int i) {
		return visitorMethodes.get(i).getMethods();
	}

	public List<AnonymousClassDeclaration> getClassesAnonymes(int i) {
		return visitorClassesAnonymes.get(i).getAnonymous();
	}
	
	public List<VariableDeclarationFragment> getVariables(int i) {
		return visitorVariables.get(i).getVariables();
	}
	
	public List<PackageDeclaration> getPackages(int i) {
		return visitorPackages.get(i).getPackages();
	}
	
	public PackageDeclarationVisitor packages(int i) {
		return visitorPackages.get(i);
	}
	
	public CompilationUnit getParse(int i){
		return this.project.get(i);
	}
	
	public ArrayList<CompilationUnit> getProject(){
		return this.project;
	}
	
	public ArrayList<String>getContent(){
		return this.content;
	}
	
	public void print() {}
	
	public int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}
}

