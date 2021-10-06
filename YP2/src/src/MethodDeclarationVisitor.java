package src;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodDeclarationVisitor extends ASTVisitor {
	List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	
	//Possibilité d'ignorer des constructeurs etc
    private boolean useExternBindings = false; 
    private boolean includeConstructor = true;
	
	public boolean visit(MethodDeclaration node) {
        if(shouldAddMethod(node)){
            methods.add(node);
        }
		return super.visit(node);
	}
	
    private boolean shouldAddMethod(MethodDeclaration node){
        if(node.isConstructor() && !includeConstructor){
            return false;
        }
        /*else if(!useExternBindings && node.getReturnType2().resolveBinding() == null){
            return false;
        }
        else if(node.resolveBinding() == null){
            return false;
        }*/
        return true;
    }
    
    public void useExternBindings(boolean useExternBindings){
        this.useExternBindings = useExternBindings;
    }
    
    public void includeConstructor(boolean includeConstructor){
        this.includeConstructor = includeConstructor;
    }
	
	public List<MethodDeclaration> getMethods() {
		return methods;
	}
}

