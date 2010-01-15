package qualiti.banco.contas;

import qualiti.banco.clientes.Cliente;

/**
 * Classe abstrata básica que representa uma entidade geral conta, com seus dados, validações
 * dos mesmos e suas operações relacionadas. Esta é a super-classe da hierarquia dos
 * diversos tipos de contas existentes, agregando elementos e operações comuns a 
 * todos os sub-tipos de conta existentes.
 * 
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 */
public abstract class ContaAbstrata {

	/**
	 * O número da conta.
	 */
	private String numero;
	/**
	 * O saldo da conta.
	 */
	private double saldo;
	/**
	 * O cliente associado à conta.
	 */
	private Cliente cliente;

	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando os métodos sets dos mesmos.
	 * 
	 * @param num o número da conta.
	 * @param c o cliente da conta.  
	 */
	public ContaAbstrata(String num, Cliente c) {

		// Chama o construtor sobrecarregado desta classe que recebe um número,
		// um saldo e um cliente. Neste caso, passa zero para o saldo.
		this(num, 0, c);
	}

	/**
	 * O construtor da classe. Inicializa os atributos com os valores passados como
	 * parâmetro chamando os métodos sets dos mesmos.
	 * 
	 * @param num o número da conta.
	 * @param s o saldo inicial da conta. 
	 * @param c o cliente da conta.  
	 */
	public ContaAbstrata(String num, double s, Cliente c) {

		setNumero(num);
		setSaldo(s);
		setCliente(c);

	}

	/**
	 * Credita um dado valor no saldo atual da conta.
	 * 
	 * @param valor o valor a ser creditado.
	 */
	public void creditar(double valor) {

		saldo = saldo + valor;
	}

	/**
	 * Método abstrato com semântica de débito a ser implementado pelos sub-tipos de
	 * conta abstrata. Define uma operação de débito com possibilidade de checagem 
	 * de saldo antes da efetivação da operação.
	 * 
	 * @param valor o valor a ser debitado.
	 *
	 * @exception SaldoInsuficienteException lançada quando o valor passado como
	 *            parâmetro é maior que o saldo da conta. 
	 */
	public abstract void debitar(double valor)
		throws SaldoInsuficienteException;

	/**
	 * Retorna o cliente associado à conta.
	 * 
	 * @return Cliente o cliente associado à conta.
	 */
	public Cliente getCliente() {

		return cliente;
	}

	/**
	 * Retorna o número da conta.
	 * 
	 * @return String o número da conta.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Retorna o saldo da conta.
	 * 
	 * @return double o saldo da conta.
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Atualiza o cliente associado à conta.
	 * 
	 * @param cliente o novo valor.
	 *
	 * @see Q1 O cliente da conta pode ser nulo ?? 
	 */
	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	/**
	 * Atualiza o número da conta.
	 * 
	 * @param num o novo valor.
	 *
	 * @see Q1 O número da conta pode ser nulo ou branco ?? 
	 * 
	 * @see Q2 Uma vez definido para uma dada conta, o número da mesma pode 
	 *         mudar, já que, por definição, número é a chave de uma conta ??
	 *         Como se pode definir bem no JAVA a questão de não alterar 
	 *         atributos que definem a chave de uma entidade ??  
	 */
	public void setNumero(String num) {
		this.numero = num;
	}

	/**
	 * Atualiza o saldo da conta.
	 * 
	 * @param valor o novo valor do saldo.
	 *
	 * @see Q1 O saldo pode ser menor que zero ??
	 */
	public void setSaldo(double valor) {
		saldo = valor;
	}

	/**
	 * Transfere um dado valor da conta para uma conta destino passada como parâmetro.
	 * 
	 * @param c a conta destino.
	 * @param v o valor a ser transferido.
	 *
	 * @exception SaldoInsuficienteException se o saldo da conta for menor que o valor
	 *            a ser transferido.
	 */
	public void transferir(ContaAbstrata c, double v)
		throws SaldoInsuficienteException {

		this.debitar(v);
		c.creditar(v);
	}
}
