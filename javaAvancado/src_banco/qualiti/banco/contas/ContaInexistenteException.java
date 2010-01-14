package qualiti.banco.contas;

/**
 * Exceção lançada quando uma conta não existe no cadastro de contas. 
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.RepositorioContas
 */
public class ContaInexistenteException extends Exception {

	/**
	 * O número da conta não existente no cadastro.
	 */
	public String numero;
	/**
	 * O construtor da classe. Inicializa a mensagem da super-classe com uma mensagem
	 * padrão de conta não cadastrada e inicializa o número cuja conta não existe no cadastro.
	 *
	 * @param num o número da conta não existente no cadastro.
	 */
	public ContaInexistenteException(String num) {

		super(MSG_CTA_INEXISTENTE);
		this.numero = num;
	}
	/**
	 * Retorna o número da conta não existente no cadastro.
	 *
	 * @return String o número da conta não existente no cadastro.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * Constante com a mensagem de conta não cadastrada.
	 */
	private static final String MSG_CTA_INEXISTENTE = "Conta não cadastrada !!";
}
