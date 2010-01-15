package qualiti.banco.clientes;

/**
 * Classe básica que representa uma entidade cliente, com seus dados, validações
 * dos mesmos e suas operações relacionadas.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 */
public class Cliente {

	/**
	 * O CPF do cliente.
	 */
	private String cpf;
	/**
	 * O nome do cliente.
	 */
	private String nome;

	/**
	 * O construtor da classe. Inicializa os atributos CPF e nome.
	 *
	 * @param newCpf o valor do CPF.
	 * @param newNome o valor do nome.
	 *
	 * @see Q1 Está certo o construtor atribuir diretamente os valores
	 *         de newCpf e de newNome aos atributos cpf e nome ??
	 */
	public Cliente(String newCpf, String newNome) {

		this.cpf = newCpf;
		this.nome = newNome;
	}

	/**
	 * Retorna o CPF do cliente.
	 *
	 * @return String o CPF do cliente.
	 */
	public String getCpf() {

		return cpf;
	}
	/**
	 * Retorna o nome do cliente.
	 *
	 * @return String o nome do cliente.
	 */
	public String getNome() {

		return nome;
	}
	/**
	 * Atualiza o valor do CPF do cliente.
	 *
	 * @param newCpf o novo valor.
	 *
	 * @see Q1 Será que o CPF de um cliente pode ser nulo ou branco ??
	 */
	public void setCpf(String newCpf) {

		cpf = newCpf;
	}
	/**
	 * Atualiza o valor do nome do cliente.
	 *
	 * @param newNome o novo valor.
	 *
	 * @see Q1 Será que o nome de um cliente pode ser nulo ou branco ??
	 */
	public void setNome(String newNome) {

		nome = newNome;
	}
}