package qualiti.banco.contas;

import qualiti.banco.clientes.Cliente;

/**
 * Classe básica que representa uma entidade de conta imposto, com seus dados, validações
 * dos mesmos e suas operações relacionadas. A super-classe contém muitos dos atributos
 * e operações de uma conta simples. A conta imposto tem a ela associada uma taxa, que
 * é aplicada às operações de débito. 
 * 
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.ContaAbstrata
 */
public class ContaImposto extends ContaAbstrata {

	/**
	 * Constante que define o valor da taxa a ser aplicada às operações de débito.
	 */
	public final static double TAXA = 0.001;

	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param cli o cliente da conta.  
	 */
	public ContaImposto(String n, Cliente c) {

		super(n, c);
	}

	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param sld o saldo inicial da conta. 
	 * @param cli o cliente da conta.  
	 */
	public ContaImposto(String n, double s, Cliente c) {

		super(n, s, c);
	}

	/**
	 * Debita um dado valor mais imposto do saldo atual da conta, caso o valor mais o 
	 * imposto seja menor que o saldo da conta. O imposto é calculado aplicando-se
	 * a taxa ao valor a ser debitado. 
	 * 
	 * @param valor o valor a ser debitado.
	 *
	 * @exception SaldoInsuficienteException lançada quando o valor passado como
	 *            parâmetro mais o imposto é maior que o saldo da conta. 
	 */
	public void debitar(double valor) throws SaldoInsuficienteException {

		double imposto = valor * TAXA;
		double saldo = this.getSaldo();
		if (valor + TAXA <= saldo) {
			setSaldo(saldo - (valor + imposto));
		} else {
			throw new SaldoInsuficienteException(getNumero(), getSaldo());
		}
	}
}