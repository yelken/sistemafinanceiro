package qualiti.banco.clientes;

import java.util.Vector;

import qualiti.banco.geral.ErroAcessoRepositorioException;
/**
 * Classe que realiza validações referentes às operações de atualização de dados
 * no mecanismo de armazenamento de dados de clientes e usa o repositório de clientes
 * para aualizar e buscar os dados dos mesmos.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 *
 * @see qualiti.banco.cliente.Cliente
 * @see qualiti.banco.cliente.ReposiorioClientes
 */
public class CadastroClientes {

	/**
	 * Referência para a implementação do repositório de clientes.
	 */
	private RepositorioClientes clientes;

	/**
	 * O construtor da classe. Inicializa a referência para o repositório
	 * de clientes com o valor passado como parâmetro.
	 *
	 * @param l a referência para o repositório de clientes.
	 */
	public CadastroClientes(RepositorioClientes l) {

		this.clientes = l;
	}

	/**
	 * Atualiza os dados de um cliente no repositório de clientes.
	 *
	 * @param c o cliente com os dados a serem atualizados.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            atualizados não existe no repositório de clientes.Esta exceção vem da
	 *            chamada ao repositório de clientes e é repassada diretamente por este
	 *            método ao seu método chamador.
	 */
	public void atualizar(Cliente c)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		clientes.atualizar(c);
	}

	/**
	 * Cadastra os dados de um cliente no repositório de clientes. Antes disso,
	 * checa se o CPF do cliente a ser cadastrado já existe no repositório de clientes.
	 *
	 * @param c o cliente com os dados a serem cadastrados.
	 *
	 * @exception ClienteExistenteException se o CPF do cliente a ser cadastrado já
	 *            existir no repositório de clientes. Esta exceção é instanciada e
	 *            lançada por este método, caso a consulta ao CPF feita no repositório
	 *            retorne true.
	 */
	public void cadastrar(Cliente c)
		throws ClienteExistenteException, ErroAcessoRepositorioException {

		String cpf = c.getCpf();
		if (!clientes.existe(cpf)) {
			clientes.inserir(c);
		} else {
			throw new ClienteExistenteException(c.getCpf());
		}
	}

	/**
	 * Exclui um cliente armazenado no repositório de clientes.
	 *
	 * @param cpf o CPF do cliente que será excluído do repositório de clientes.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ser excluído
	 *            não existe no repositório de clientes. Esta exceção vem da chamada ao
	 *            repositório de clientes e é repassada diretamente por este método
	 *            ao seu método chamador.
	 */
	public void descadastrar(String cpf)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		clientes.remover(cpf);
	}

	/**
	 * Retorna um cliente armazenado no repositório de clientes.
	 *
	 * @param cpf o CPF do cliente que será procurado no repositório de clientes.
	 *
	 * @return Cliente o cliente com seus dados lidos a partir do repositório
	 *         de clientes.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            lidos não existe no repositório de clientes. Esta exceção vem da chamada
	 *            ao repositório de clientes e é repassada diretamente por este método ao
	 *            seu método chamador.
	 */
	public Cliente procurar(String cpf)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		return clientes.procurar(cpf);
	}

	public Vector<Cliente> listar()throws ErroAcessoRepositorioException {
		return clientes.listar();
	}
}
