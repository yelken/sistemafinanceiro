package qualiti.banco.contas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import qualiti.banco.clientes.Cliente;

/**
 * Classe básica que representa uma entidade conta, com seus dados, validações
 * dos mesmos e suas operações relacionadas. A super-classe contém muitos dos atributos
 * e operações de uma conta simples. 
 * 
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.ContaAbstrata
 */
@Entity
@DiscriminatorValue("1")
public class Conta extends ContaAbstrata {
	
	public Conta() {
		super();
	}
	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando o construtor da super-classe.
	 * 
	 * @param num o número da conta.
	 * @param cli o cliente da conta.  
	 */
	public Conta(String num, Cliente cli) {

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
	public Conta(String num, double sld, Cliente cli) {

		super(num, sld, cli);
	}
	/**
	 * Debita um dado valor do saldo atual da conta, caso este seja maior que o valor
	 * a ser debitado. 
	 * 
	 * @param valor o valor a ser debitado.
	 *
	 * @exception SaldoInsuficienteException lançada quando o valor passado como
	 *            parâmetro é maior que o saldo da conta. 
	 */
	@Override
	public void debitar(double valor) throws SaldoInsuficienteException {

		double saldo = getSaldo();
		if (valor <= saldo) {
			setSaldo(saldo - valor);
		} else {
			throw new SaldoInsuficienteException(getNumero(), getSaldo());
		}
	}
}
