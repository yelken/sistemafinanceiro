package qualiti.banco.contas;

/**
 * Exceção lançada quando uma conta já existe no cadastro de contas. 
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.RepositorioContas
 */
public class ContaExistenteException extends Exception {

	/**
	 * O número da conta já existente no cadastro.
	 */
	public String numero;
	/**
	 * O construtor da classe. Inicializa a mensagem da super-classe com uma mensagem
	 * padrão de conta já cadastrada e inicializa o número cuja conta já existe no cadastro.
	 *
	 * @param num o número da conta já existente no cadastro.
	 */
	public ContaExistenteException(String num) {

		super(MSG_CTA_JA_EXISTENTE);
		this.numero = num;
	}
	/**
	 * Retorna o número da conta já existente no cadastro.
	 *
	 * @return String o número da conta já existente no cadastro.
	 */
	public String getNumero() {

		return numero;
	}
	/**
	 * Constante com a mensagem de conta já cadastrada.
	 */
	private static final String MSG_CTA_JA_EXISTENTE = "Conta ja cadastrada !!";
}