package qualiti.banco.contas;

import qualiti.banco.clientes.Cliente;

/**
 * Classe básica que representa uma entidade poupança, com seus dados, validações
 * dos mesmos e suas operações relacionadas. A super-classe contém muitos dos atributos
 * e operações de uma conta. A poupança se caracteriza por ter uma operação de render
 * juros, calculados em cima de uma dada taxa informada no ato desta operação.
 * 
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 *
 * @see qualiti.banco.conta.Conta
 */
public class Poupanca extends Conta {

	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param cli o cliente da conta.  
	 */
	public Poupanca(String num, Cliente cli) {

		super(num, cli);
	}
	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param sld o saldo inicial da conta. 
	 * @param cli o cliente da conta.  
	 */
	public Poupanca(String num, double s, Cliente c) {
		super(num, s, c);
	}

	/**
	 * Credita ao saldo da conta os juros, rendidos a partir da taxa passada como
	 * parâmetro. 
	 * 
	 * @param taxa a taxa sobre a qual os juros serão rendidos.
	 */
	public void renderJuros(double taxa) {

		double saldo = this.getSaldo();
		this.creditar(saldo * taxa);
	}
}
