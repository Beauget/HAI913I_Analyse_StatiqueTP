package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.eclipse.jdt.core.dom.MethodInvocation;

import com.sun.tools.sjavac.server.SysInfo;

import javax.swing.JFrame;

//import step2.ASTProcessor;




public class Main {
	
	static void gestionGUI(ASTProcessor visit) {
		
		 Fenetre guiVisitor = new Fenetre(visit);

	}
	
	//public static final String projectPath = "/home/dnspc/Desktop/M2/Evo-restru/TP2AST/HAI913I_Analyse_StatiqueTP/File_To_Analyse/";
	//public static final String projectSourcePath = projectPath;
	//public String projectPath = "/home/hayaat/Desktop/Master/M1/Java/TP4/";
	//public String projectSourcePath = projectPath + "/src/";
	public static final String jrePath;
	

	static {
		jrePath = System.getProperty("java.home");
	}
	public static void main(String[] args) throws IOException {
		
	
		// MENU
		
		int cmd = 100;
		Scanner sc = new Scanner(System.in);
										
		while(cmd != 0) {
			System.out.println("---- Bienvenue dans notre menu pour obtenir quelques informations l'AST d'une application ----");
			System.out.println("Veuillez choisir une option");
			
			System.out.println("0 - Quitter" );
			System.out.println("1 - Démarrer" );			
			cmd = sc.nextInt();
			
			switch(cmd) {
			case 0 : System.out.println("Merci de votre visite, bonne journée !");
			break;
			case 1 :{
				String path = "";
				while(!path.equals("Quitter")) {
					System.out.println("Veuillez donner le chemin vers une application (sans /src/) ou écrire Quitter :");
					Scanner sc2 = new Scanner(System.in);
					path = sc2.nextLine();
					// read java files
					final File folder = new File(path);
					cmd=100;
					if(folder.exists()&& cmd!=0) {
						while(cmd != 0) {
							// read java files
							
							ArrayList<String> content = listJavaFilesForProject(path);
							ArrayList<CompilationUnit> project = new ArrayList<CompilationUnit>();
							
							for(int i=0; i<content.size();i++) {
								CompilationUnit parse = parse(content.get(i).toCharArray(),path+"/src/");
								project.add(parse);
							}
							ASTProcessor processor = new ASTProcessor(project,content);	
							//System.out.println("---- Bienvenue dans notre menu pour obtenir quelques informations sur notre AST ----");
							System.out.println("Veuillez choisir un numéro de question pour obtenir le résultat \n");
							
							System.out.println("0 - Changer de chemin" );
							System.out.println("1 - Nombre de classes de l'application" );
							System.out.println("2 - Nombre de lignes de code de l'application");
							System.out.println("3 - Nombre total de méthodes de l'application");
							System.out.println("4 - Nombre total de packages de l'application");
							System.out.println("5 - Nombre moyen de méthodes par classe");
							System.out.println("6 - Nombre moyen de lignes de code par méthode");
							System.out.println("7 - Nombre moyen d'attributs par classe");
							System.out.println("8 - Les 10% des classes qui possèdent le plus grand nombre de méthodes.");
							System.out.println("9 - Les 10% des classes qui possèdent le plus grand nombre d'attributs");
							System.out.println("10 - Les classes qui font partie en même temps des deux catégories précédentes");
							System.out.println("11 - Les classes qui possèdent plus de X méthodes");
							System.out.println("12 - Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe)");
							System.out.println("13 - Le nombre	maximal	de paramètres par rapport à toutes les méthodes	de l'application.");
							System.out.println("14 - Toutes les réponses avec X = 4 pour la question 11");
							System.out.println("15 - Version graphique");
							
							
							cmd = sc.nextInt();
							switch(cmd) {
							
								case 0 :
								break;
								case 1 : System.out.println(processor.exercice1());
								System.out.println(" ");
								break;
								case 2 : System.out.println(processor.exercice2());
								System.out.println(" ");
								break;
								case 3 : System.out.println(processor.exercice3());
								System.out.println(" ");
								break;
								case 4 : System.out.println(processor.exercice4());
								System.out.println(" ");
								break;
								case 5 : System.out.println(processor.exercice5());
								System.out.println(" ");
								break;
								case 6 :System.out.println(processor.exercice6());
								System.out.println(" ");
								break;
								case 7 :System.out.println(processor.exercice7());
								System.out.println(" ");
								break;
								case 8 : System.out.println(processor.exercice8());
								System.out.println(" ");
								break;
								case 9 : System.out.println(processor.exercice9());
								System.out.println(" ");
								break;
								case 10 : System.out.println(processor.exercice10());
								System.out.println(" ");
								break;
								case 11 : System.out.println(processor.exercice11(3));
								System.out.println(" ");
								break;
								case 12 : System.out.println(processor.exercice12());
								System.out.println(" ");
								break;
								case 13 : System.out.println(processor.exercice13());
								System.out.println(" ");
								break;
								case 14 : System.out.println(processor.printAll());
								System.out.println(" ");
								break;
								case 15 :
								System.out.println("La fenètre va s'ouvrir...");
								gestionGUI(processor);
								int i = 1;
								System.out.println("Appuyez sur une 0 pour revenir au menu");
								i = sc.nextInt();
								System.out.println();
								if (i == 0) {
								break;
								}
								}
							}
						}
					else if(!folder.exists() && !path.equals("Quitter") ) {
						System.out.println("Ce chemin n'existe pas");
					}
					}
				}
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
	
		//
	public static ArrayList<String>  listJavaFilesForProject(String projectSourcePath) throws IOException{
		ArrayList<CompilationUnit> project = new ArrayList<CompilationUnit>();
		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);
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
	private static CompilationUnit parse(char[] classSource,String projectSourcePath) {
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


