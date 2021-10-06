package src;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {
	public ASTProcessor visit;
	
	
	// Bouton pour les questions
	private JPanel pan = new JPanel();
	private JButton bouton1 = new JButton("Q1 : Nombre de classe");
	private JButton bouton2 = new JButton("Q2 : Nombre	de lignes de code de l'application");
	private JButton bouton3 = new JButton("Q3 : Nombre total de méthodes de l'application");
	private JButton bouton4 = new JButton("Q4 : Nombre total de packages de l'application");
	private JButton bouton5 = new JButton("Q5 : Nombre moyen de méthodes par classe");
	private JButton bouton6 = new JButton("Q6 : Nombre moyen de lignes de code par méthode");
	private JButton bouton7 = new JButton("Q7 : Nombre moyen d'attributs par classe");
	private JButton bouton8 = new JButton("Q8 : Les 10% des classes qui possèdent le plus grand nombre de méthodes");
	private JButton bouton9 = new JButton("Q9 : Les 10% des classes qui possèdent le plus grand nombre d'attributs");
	private JButton bouton10 = new JButton("Q10 : Les classes qui font partie en même temps des deux cat�gories précédentes");
	private JButton bouton11 = new JButton("Q11 : Les classes qui possèdent plus de X méthodes");
	private JButton bouton12 = new JButton("Q12 : Les 10% des m�thodes qui possèdent le plus grand nombre delignes de code (par classe)");
	private JButton bouton13 = new JButton("Q13 : Le nombre	maximal	de paramètres par rapport à toutes les méthodes	de l'application.");
	

	public Fenetre(ASTProcessor visit) {
		
		this.visit = visit;
		this.setTitle("AST Visitor");
		this.setSize(700,400);
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
		
		this.setLayout(new GridLayout(4, 4));
	    this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String res;

		if(arg0.getSource().equals(bouton1)) {
			res = visit.exercice1();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton2)) {
			res = visit.exercice2();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton3)) {
			res = visit.exercice3();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton4)) {
			res = visit.exercice4();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton5)) {
			res = visit.exercice5();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton6)) {
			res = visit.exercice6();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton7)) {
			res = visit.exercice7();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton8)) {
			res = visit.exercice8();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton9)) {
			res = visit.exercice9();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton10)) {
			res = visit.exercice10();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton11)) {
			res = visit.exercice11(4);
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton12)) {
			res = visit.exercice12();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		else if (arg0.getSource().equals(bouton13)) {
			res = visit.exercice13();
			JOptionPane.showMessageDialog(pan, res,"Résultat", JOptionPane.CLOSED_OPTION);
		}
		
	}
	
}
