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
import javax.swing.JFrame;

//import step2.ASTProcessor;




public class Parser {
	
	static void gestionGUI() {
		
		 Fenetre guiVisitor = new Fenetre();
		 
		 
		 guiVisitor.getbouton1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ICI POUR LANCER UN TRUC VIA LE BOUTON
				System.out.println("OUI BOUTON");
			}
		 });
	}
	
	public static final String projectPath = "C:\\Users\\beaug\\Desktop\\M2\\M2\\Evo-restru\\TP2AST\\HAI913I_Analyse_StatiqueTP\\File_To_Analyse";
	public static final String projectSourcePath = projectPath;
	//public static final String projectPath = "/home/hayaat/Desktop/Master/M1/Java/TP4/";
	//public static final String projectSourcePath = projectPath + "/src/";
	public static final String jrePath;
	
	static {
		jrePath = System.getProperty("java.home");
	}

	public static void main(String[] args) throws IOException {
		
		// MENU
		int cmd = 100;
		Scanner sc = new Scanner(System.in);
		
		
		
		while(cmd != 0) {
			System.out.println("---- Bienvenue dans notre menu pour obtenir quelques informations sur notre AST ----");
			System.out.println("Veuillez choisir un numéro de question pour obtenir le résultat");
			System.out.println("1 - Toutes les questions sur le terminal" );
			System.out.println("2 - Merci de votre visite, bonne journée !");
			System.out.println("3 - Nombre de classes de l’application");
			System.out.println("4 - Nombre de lignes de code de l’application");
			System.out.println("5 - Nombre total de méthodes de l'application");
			System.out.println("6 - Nombre total de packages de l'application");
			System.out.println("7 - Nombre moyen de méthodes par classe");
			System.out.println("8 - Nombre moyen de lignes de code par méthode");
			System.out.println("9 - Nombre moyen d'attributs par classe");
			System.out.println("10 - Les 10% des classes qui possèdent le plus grand nombre de méthodes.");
			System.out.println("11 - Les 10% des classes qui possèdent le plus grand nombre d'attributs");
			System.out.println("12 - Les classes qui font partie en même temps des deux catégories précédentes");
			System.out.println("13 - Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe)");
			System.out.println("14 - Le nombre	maximal	de paramètres par rapport à toutes les méthodes	de l’application.");
			System.out.println("15 - Version graphique");
			
			
			cmd = sc.nextInt();
			switch(cmd) {
			
				case 0 : System.out.println("Merci de votre visite, bonne journée !");
				break;
				case 1 : System.out.println("Nombre	de classes de l’application");
				break;
				case 3 : System.out.println("Nombre	de lignes de code de l’application");
				break;
				case 4 : System.out.println("Nombre total de méthodes de l'application");
				break;
				case 5 : System.out.println("Nombre total de packages de l'application");
				break;
				case 6 : System.out.println("Nombre moyen de méthodes par classe");
				break;
				case 7 : System.out.println("Nombre moyen de lignes de code par méthode");
				break;
				case 8 : System.out.println("Nombre moyen d'attributs par classe");
				break;
				case 9 : System.out.println("Les 10% des classes qui possèdent le plus grand nombre de méthodes");
				break;
				case 10 : System.out.println("Les 10% des classes qui possèdent le plus grand nombre d'attributs");
				break;
				case 11 : System.out.println("Les classes qui font partie en même temps des deux catégories précédentes");
				break;
				case 12 : System.out.println("Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe)");
				break;
				case 13 : System.out.println("Le nombre	maximal	de paramètres par rapport à toutes les méthodes	de l’application.");
				break;
				case 14 : System.out.println("Toutes les réponses précédentes");
				break;
				case 15 :
				System.out.println("La fenêtre va s'ouvrir...");
				gestionGUI();
				int i = 1;
				System.out.println("Appuyez sur une 0 pour revenir au menu");
				i = sc.nextInt();
				System.out.println();
				if (i == 0) {
				break;
				}
			}
		}
		
		
		// END MENU

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		//
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);

			CompilationUnit parse = parse(content.toCharArray());
			ASTProcessor processor = new ASTProcessor(parse, "InfoVisitor");
			//ASTProcessor processor = new ASTProcessor(parse, "StatVisitor");
			processor.print();
		}
		
		// TODO : GUI part 
	
				 
				 
	    // END : GUI part		 
		
	}

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				// System.out.println(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
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


