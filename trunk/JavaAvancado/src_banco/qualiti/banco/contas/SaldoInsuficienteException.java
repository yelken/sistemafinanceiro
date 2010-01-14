package qualiti.banco.contas;

/**
 * Exceção lançada quando uma operação de débito ou de transferência a ser
 * realizada em uma conta não é realizada porque o saldo da conta a ser debitada
 * é menor que o valor envolvido no débito. 
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.ContaAbstrata
 * @see qualiti.banco.conta.Conta
 * @see qualiti.banco.conta.ContaImposto
 */
public class SaldoInsuficienteException extends Exception {

	/**
	 * O número da conta que teve saldo insuficiente.
	 */
	private String numero;
	/**
	 * O saldo da conta que teve saldo insuficiente.
	 */
	private double saldo;
	/**
	 * O construtor da classe. Inicializa a mensagem da super-classe com uma mensagem
	 * padrão de saldo insuficiente e inicializa o número e o saldo da conta com saldo 
	 * insuficiente.
	 *
	 * @param num o número da conta com saldo insuficiente.
	 * @param saldo o saldo da conta com saldo insuficiente.
	 */
	public SaldoInsuficienteException(String num, double saldo) {

		super(MSG_SALDO_INSUFICIENTE);
		this.numero = num;
		this.saldo = saldo;
	}
	/**
	 * Retorna o número da conta que teve saldo insuficiente.
	 *
	 * @return String o número da conta que teve saldo insuficiente.
	 */
	public String getNumero() {

		return numero;
	}
	/**
	 * Retorna o saldo da conta que teve saldo insuficiente.
	 *
	 * @return double o saldo da conta que teve saldo insuficiente.
	 */
	public double getSaldo() {

		return saldo;
	}
	/**
	 * Constante com a mensagem de saldo insuficiente. 
	 */
	private static final String MSG_SALDO_INSUFICIENTE =
		"Saldo insuficiente para realizar operação de débito ou de transferência !!";
}
