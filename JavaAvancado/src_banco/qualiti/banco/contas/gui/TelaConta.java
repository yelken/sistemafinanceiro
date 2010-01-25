package qualiti.banco.contas.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class TelaConta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1535235538152811913L;
	
	private JLabel labelNConta = new JLabel("Numero da conta: ");

	  
	   public TelaConta () {  
	     //Define o título da janela  
	     super ("Operações de Crédito / Débito");  
	     this.montaJanela ();  
	   }  
	  
	   private void montaJanela () {  
		 labelNConta.setName("Numero da conta: ");
		 labelNConta.setVisible(true);
		 labelNConta.setLayout(null);
		 this.getContentPane ().add (labelNConta);  
	   }  	
	
	public static void main(String[] args) {
		TelaConta telaConta = new TelaConta();
		telaConta.setSize(1024,768);
		telaConta.setVisible(true);

	}

}
