package qualiti.banco.contas;

import qualiti.banco.clientes.Cliente;

/**
 * Classe básica que representa uma entidade conta bonificada, com seus dados, validações
 * dos mesmos e suas operações relacionadas. A super-classe contém muitos dos atributos
 * e operações de uma conta. A conta bonificada se caracteriza por ter um bônus
 * a ela associado, calculado em função dos valores creditados. Através de uma operação
 * específica, é possível incorporar o bônus acumulado ao saldo atual da conta. 
 * 
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.Conta
 */
public class ContaBonificada extends Conta {

	/**
	 * O valor do bônus.
	 */
	private double bonus;

	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param cli o cliente da conta.  
	 */
	public ContaBonificada(String numeroConta, Cliente c) {
		super(numeroConta, c);
	}
	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param sld o saldo inicial da conta. 
	 * @param cli o cliente da conta.  
	 */
	public ContaBonificada(String numeroConta, double saldo, Cliente c) {
		super(numeroConta, saldo, c);
	}

	/**
	 * Sobrescrita do método creditar() da super-classe. Credita 1% do valor
	 * a ser creditado ao bônus e chama o método creditar() da super-classe.
	 * 
	 * @param valor o valor a ser creditado.
	 */
	public void creditar(double valor) {

		bonus = bonus + valor * 0.01;
		super.creditar(valor);
	}
	/**
	 * Incorpora o valor atual do bônus ao saldo da conta. Usa o método creditar()
	 * da super-classe para realizar o crédito do bônus e zera o valor do bônus.
	 */
	public void renderBonus() {
		super.creditar(bonus);
		bonus = 0;
	}
	/**
	 * Retorna o valor do bônus.
	 * 
	 * @return double o valor do bônus.
	 */
	public double getBonus() {

		return bonus;
	}
}