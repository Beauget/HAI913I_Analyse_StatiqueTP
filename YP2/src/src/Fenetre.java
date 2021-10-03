package src;



import java.awt.event.ActionListener;

import javax.swing.*;

public class Fenetre extends JFrame {
	
	
	
	// Bouton pour les questions
	private JPanel pan = new JPanel();
	private JButton bouton1 = new JButton("Nombre de classe");
	private JButton bouton2 = new JButton("Nombre	de lignes de code de l�application");
	private JButton bouton3 = new JButton("Nombre total de m�thodes de l'application");
	private JButton bouton4 = new JButton("Nombre total de packages de l'application");
	private JButton bouton5 = new JButton("Nombre moyen de m�thodes par classe");
	private JButton bouton6 = new JButton("Nombre moyen de lignes de code par m�thode");
	private JButton bouton7 = new JButton("Nombre moyen d'attributs par classe");
	private JButton bouton8 = new JButton("Les 10% des classes qui poss�dent le plus grand nombre de m�thodes");
	private JButton bouton9 = new JButton("Les 10% des classes qui poss�dent le plus grand nombre d'attributs");
	private JButton bouton10 = new JButton("Les classes qui font partie en m�me temps des deux cat�gories pr�c�dentes");
	private JButton bouton11 = new JButton("Les 10% des m�thodes qui poss�dent le plus grand nombre de lignes de code (par classe)");
	private JButton bouton12 = new JButton("Le nombre	maximal	de param�tres par rapport � toutes les m�thodes	de l�application.");
	

	public Fenetre() {
		this.setTitle("AST Visitor");
		this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		

		pan.add(bouton1);
		pan.add(bouton2);
		pan.add(bouton3);
		pan.add(bouton4);
		pan.add(bouton5);
		pan.add(bouton1);
		pan.add(bouton6);
		pan.add(bouton7);
		pan.add(bouton8);
		pan.add(bouton9);
		pan.add(bouton10);
		pan.add(bouton11);
		pan.add(bouton12);
		
		this.setContentPane(pan);
		this.setVisible(true);
		
	}
	
	public JButton getbouton1() {
		return this.bouton1;
	}
	
}
