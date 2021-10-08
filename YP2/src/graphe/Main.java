package graphe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import src.MethodDeclarationVisitor;
import src.VariableDeclarationFragmentVisitor;
import src.MethodDeclarationVisitor;

public class Main {
	
	public static final String projectPath = "/home/dnspc/Desktop/M2/Evo-restru/TP2AST/HAI913I_Analyse_StatiqueTP/File_To_Analyse/";
	public static final String projectSourcePath = projectPath;
	//public static final String projectPath = "/home/hayaat/Desktop/Master/M2/Git/HAI913I_Analyse_StatiqueTP/File_To_Analyse";
	//public static final String projectSourcePath = projectPath + "";
	public static final String jrePath;
	

	static {
		jrePath = System.getProperty("java.home");
	}

	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder2(folder);
		// read java files
		ArrayList<String> content = listJavaFilesForProject();
		ArrayList<CompilationUnit> project = new ArrayList<CompilationUnit>();
		
		for(int i=0; i<content.size();i++) {
			CompilationUnit parse = parse(content.get(i).toCharArray());
			project.add(parse);
		}
		ASTProcessor processor = new ASTProcessor(project,content);
		processor.fct();

		//
		//for (File fileEntry : javaFiles) {
			//String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);

			//CompilationUnit parse = parse(content.toCharArray());

			// print methods info
			//printMethodInfo(parse);

			// print variables info
			//printVariableInfo(parse);
			
			//print method invocations
			//printMethodInvocationInfo(parse);

		//}
	}

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder2(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder2(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				// System.out.println(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder2(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}
	
		//
	public static ArrayList<String>  listJavaFilesForProject() throws IOException{
		ArrayList<CompilationUnit> project = new ArrayList<CompilationUnit>();
		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder2(folder);
		ArrayList<String> c = new ArrayList<String>();

		//
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);
			
			c.add(content);
		}
		return c;
	}

	// create AST
	private static CompilationUnit parse(char[] classSource) {
		ASTParser parser = ASTParser.newParser(AST.JLS4); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		parser.setUnitName("");
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);

		return (CompilationUnit) parser.createAST(null); // create and parse
	}


}