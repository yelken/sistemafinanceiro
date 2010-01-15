package qualiti.banco.clientes;

/**
 * Interface que define os métodos de acesso aos dados de cliente em um
 * mecanismo de armazenamento de dados.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 *
 * @see qualiti.banco.cliente.Cliente
 */
public interface RepositorioClientes {
	/**
	 * Atualiza os dados de um cliente EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @param c o cliente com os dados a serem atualizados.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            atualizados não existe no MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??
	 */
	public void atualizar(Cliente c) throws ClienteInexistenteException;
	/**
	 * Verifica se um cliente existe armazenado EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @param cpf o CPF do cliente cuja existência EM UM MECANISMO DE ARMAZENAMENTO DE DADOS
	 *        será verificada.
	 *
	 * @return boolean true se o cliente existir EM UM MECANISMO DE ARMAZENAMENTO DE DADOS e
	 *         false caso contrário.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??
	 */
	public boolean existe(String cpf);
	/**
	 * Cadastra os dados de um cliente EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @param c o cliente com os dados a serem cadastrados.
	 *
	 * @see Q1 Por que este método não lança a exceção de cliente já existente ??
	 *
	 * @see Q2 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??
	 */
	public void inserir(Cliente c);
	/**
	 * Retorna um cliente armazenado EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @param cpf o CPF do cliente que será procurado EM UM MECANISMO DE ARMAZENAMENTO
	 *        DE DADOS.
	 *
	 * @return Cliente o cliente com seus dados lidos a partir DE UM MECANISMO DE
	 *         ARMAZENAMENTO DE DADOS.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            lidos não existe no MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??
	 */
	public Cliente procurar(String cpf) throws ClienteInexistenteException;
	/**
	 * Exclui um cliente armazenado EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @param cpf o CPF do cliente que será excluído EM UM MECANISMO DE ARMAZENAMENTO
	 *        DE DADOS.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ser excluído
	 *            não existe no MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??
	 */
	public void remover(String cpf) throws ClienteInexistenteException;
}