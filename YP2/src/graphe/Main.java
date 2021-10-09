package graphe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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
	
	//public static final String projectPath = "/home/dnspc/Desktop/M2/Evo-restru/TP2AST/HAI913I_Analyse_StatiqueTP/File_To_Analyse/";
	//public static final String projectSourcePath = projectPath;
	public static final String projectPath = "/home/hayaat/Desktop/Master/M2/Git/HAI913I_Analyse_StatiqueTP/File_To_Analyse/";
	public static final String projectSourcePath = projectPath + "";
	public static final String jrePath;
	

	static {
		jrePath = System.getProperty("java.home");
	}

	public static void main(String[] args) throws IOException {

		//processor.fct();
		
		// MENU
				int cmd = 100;
				Scanner sc = new Scanner(System.in);
												
				while(cmd != 0) {
					System.out.println("---- Bienvenue dans notre menu pour obtenir quelques informations sur une AST ----");
					System.out.println("Veuillez choisir une option");
					
					System.out.println("0 - Quitter" );
					System.out.println("1 - Démarrer" );
					
					cmd = sc.nextInt();
					switch(cmd) {
					
						case 0 : System.out.println("Merci de votre visite, bonne journée !");
						break;
						case 1 : 
						String str="";
						while(!str.equals("Quitter")) {
							System.out.println("Veuillez donner le chemin vers les .java de l'application (avec /src/) ou écrire Quitter :");
							Scanner sc2 = new Scanner(System.in);
							str = sc2.nextLine();
							// read java files
							final File folder = new File(str);
							if(folder.exists()) {
								System.out.println("Veuillez donner le nom du fichier (sans .java) ou écrire Quitter : ");
								Scanner sc3 = new Scanner(System.in);
								str = sc3.nextLine();
								//read java 
								String content = javaFileContent(str);
								if(content.isEmpty() && !str.equals("Quitter")) {
									System.out.println(str+".java n'existe pas");
								}
								else if (!str.equals("Quitter")) {		
									System.out.println(str+".java : \n");
									//get and create
									CompilationUnit project ;		
									project = parse(content.toCharArray());
									ASTProcessor processor = new ASTProcessor(project);								
									//call
									System.out.println(processor.toString());								
								}
							}
							else if(!folder.exists() && !str.equals("Quitter") ) {
								System.out.println("Ce chemin n'existe pas");
							}
						}
						System.out.println(" ");
						break;
						default:
						break;
					}
				}
	}

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				javaFiles.add(fileEntry);
			}
		}
		return javaFiles;
	}
	
	public static ArrayList<File> listJavaFilesByName(final File folder, String name) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				if(fileEntry.getName().toString().equals(name+".java"))
				javaFiles.add(fileEntry);
			}
		}
		return javaFiles;
	}

	public static String  javaFileContent(String name) throws IOException{
		ArrayList<CompilationUnit> project = new ArrayList<CompilationUnit>();
		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesByName(folder,name);
		ArrayList<String> c = new ArrayList<String>();

		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);			
			return content;
		}
		return "";
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