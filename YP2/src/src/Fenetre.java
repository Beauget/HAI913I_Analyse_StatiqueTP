package src;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {
	public ASTProcessor visit;
	
	
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
	private JButton bouton11 = new JButton("Les classes qui possèdent plus de X mét");
	private JButton bouton12 = new JButton("Les 10% des m�thodes qui poss�dent le plus grand nombre de lignes de code (par classe)");
	private JButton bouton13 = new JButton("Le nombre	maximal	de param�tres par rapport � toutes les m�thodes	de l�application.");
	

	public Fenetre(ASTProcessor visit) {
		
		this.visit = visit;
		this.setTitle("AST Visitor");
		this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		

		pan.add(bouton1);
		this.bouton1.addActionListener(this);
		pan.add(bouton2);
		this.bouton2.addActionListener(this);
		pan.add(bouton3);
		this.bouton3.addActionListener(this);
		pan.add(bouton4);
		this.bouton4.addActionListener(this);
		pan.add(bouton5);
		this.bouton5.addActionListener(this);
		pan.add(bouton6);
		this.bouton6.addActionListener(this);
		pan.add(bouton7);
		this.bouton7.addActionListener(this);
		pan.add(bouton8);
		this.bouton8.addActionListener(this);
		pan.add(bouton9);
		this.bouton9.addActionListener(this);
		pan.add(bouton10);
		this.bouton10.addActionListener(this);
		pan.add(bouton11);
		this.bouton11.addActionListener(this);
		pan.add(bouton12);
		this.bouton12.addActionListener(this);
		
		this.setContentPane(pan);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String res;

		if(arg0.getSource().equals(bouton1)) {
			res = visit.exercice1();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton2)) {
			res = visit.exercice2();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton3)) {
			res = visit.exercice3();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton4)) {
			res = visit.exercice4();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton5)) {
			res = visit.exercice5();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton6)) {
			res = visit.exercice6();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton7)) {
			res = visit.exercice7();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton8)) {
			res = visit.exercice8();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton9)) {
			res = visit.exercice9();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton10)) {
			res = visit.exercice10();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton11)) {
			res = visit.exercice11();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton12)) {
			res = visit.exercice12();
			JOptionPane.showMessageDialog(pan, res);
		}
		else if (arg0.getSource().equals(bouton13)) {
			res = visit.exercice13();
			JOptionPane.showMessageDialog(pan, res);
		}
		
	}
	
}
