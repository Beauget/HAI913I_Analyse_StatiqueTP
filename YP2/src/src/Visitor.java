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
			visitorPackages.add(new PackageDeclarationVisitor());
			
			parse.accept(visitorTypes.get(i));
			parse.accept(visitorMethodes.get(i));
			parse.accept(visitorPackages.get(i));
		}
	} 
	
	public List<TypeDeclaration> getTypes(int i) {
		return visitorTypes.get(i).getTypes();
	}
	
	public List<MethodDeclaration> getMethods(int i) {
		return visitorMethodes.get(i).getMethods();
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
	
	public int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}
	
	public String printArrayString(ArrayList<String> array) {
		String rslt="";
		for(String s : array) {
			rslt+=s+"\n";
		}
		return rslt;
	}
	
	public boolean isInArrayList(ArrayList<String> array,String key) {
		for(String s : array) {
			if(s.equals(key))
				return true;
		}
		return false;
	}
}

