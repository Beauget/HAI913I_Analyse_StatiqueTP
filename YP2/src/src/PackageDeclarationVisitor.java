package step2;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public class PackageDeclarationVisitor extends ASTVisitor {
	List<PackageDeclaration> packages = new ArrayList<PackageDeclaration>();
	private static int count = 0;
	
	public boolean visit(PackageDeclaration node) {
		packages.add(node);
		count++;
        //int start = node.getStartPosition();
        //int end = start + node.getLength();
        // source is a string representing your source code
        //String comment = source.substring(start, end);
        //System.out.println(start +"     " +  end);
		//System.out.println(super.visit(node));
		return super.visit(node);
	}
	
	public void endVisit(PackageDeclaration node) {
		//System.out.println(super.visit(node));
		//System.out.println("Nombre de package(s) (fct endvisit() ) : " + count); 
	}
	
	public List<PackageDeclaration> getPackages() {
		return packages;
		
	}
	
	public int getCount(){
		return count;
	}
	public void setCountToZero(){
		this.count = 0;
	}

}
