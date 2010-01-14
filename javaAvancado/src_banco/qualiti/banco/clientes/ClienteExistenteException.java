package qualiti.banco.clientes;

/**
 * Exceção lançada quando um cliente já existe no cadastro de clientes.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 *
 * @see qualiti.banco.cliente.RepositorioClientes
 */
public class ClienteExistenteException extends Exception {

	/**
	 * O CPF do cliente já existente no cadastro.
	 */
	private String cpf;
	/**
	 * O construtor da classe. Inicializa a mensagem da super-classe com uma mensagem
	 * padrão de cliente já cadastrado e inicializa o cpf cujo cliente já existe no cadastro.
	 *
	 * @param cpf o CPF do cliente já existente no cadastro.
	 */
	public ClienteExistenteException(String cpf) {

		super(MSG_CLI_JA_EXISTENTE);
		this.cpf = cpf;
	}

	/**
	 * Retorna o CPF do cliente já existente no cadastro.
	 *
	 * @return String o CPF do cliente já existente no cadastro.
	 */
	public String getCpf() {

		return cpf;
	}
	/**
	 * Constante com a mensagem de cliente já cadastrado.
	 */
	private static final String MSG_CLI_JA_EXISTENTE =
		"Cliente ja cadastrado !!";
}