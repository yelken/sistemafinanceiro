package qualiti.banco.clientes;

/**
 * Implementação da interface que define os métodos de acesso aos dados de cliente
 * em um mecanismo de armazenamento de dados. Esta implementação é realizada através
 * do armazenamento de objetos do tipo cliente em um array interno de clientes existente
 * em memória e inicializado com 100 posições fixas.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 *
 * @see qualiti.banco.cliente.Cliente
 * @see qualiti.banco.cliente.RepositorioClientes
 */
public class RepositorioClientesArray implements RepositorioClientes {

	/**
	 * Array interno de clientes.
	 */
	private Cliente[] clientes;
	/**
	 * Atributo auxiliar que representa a posição do "próximo novo objeto".
	 */
	private int indice;
	/**
	 * Tamanho do array a ser criado.
	 */
	private final static int tamCache = 100;

	/**
	 * O construtor da classe. Inicializa o índice com zero e instancia
	 * o array de clientes com 100 ocorrências, onde 100 é o tamanho da cache.
	 */
	public RepositorioClientesArray() {

		indice = 0;
		clientes = new Cliente[tamCache];
	}

	/**
	 * Atualiza os dados de um cliente no array em memória, trocando
	 * o objeto atual do array pelo objeto passado como parâmetro.
	 *
	 * @param c o cliente com os dados a serem atualizados.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            atualizados não existe no array. A procura é feita pelo CPF.
	 */
	public void atualizar(Cliente c) throws ClienteInexistenteException {

		int i = procurarIndice(c.getCpf());
		if (i != -1) {
			clientes[i] = c;
		} else {
			throw new ClienteInexistenteException(c.getCpf());
		}
	}

	/**
	 * Verifica se um cliente existe armazenado no array.
	 *
	 * @param cpf o CPF do cliente cuja existência no array
	 *        será verificada.
	 *
	 * @return boolean true se o cliente existir no array e
	 *         false caso contrário.
	 */
	public boolean existe(String cpf) {

		boolean resp = false;
		int i = this.procurarIndice(cpf);
		if (i != -1) {
			resp = true;
		}
		return resp;
	}

	/**
	 * Cadastra os dados de um cliente no array, inserindo o objeto recebido
	 * como parâmetro na primeira ocorrência vaga daquele.
	 *
	 * @param c o cliente com os dados a serem cadastrados.
	 *
	 * @see Q1 Por que este método não lança a exceção de cliente já existente ??
	 *
	 * @see Q2 Por que este método não testa a existência de um cliente com mesmo
	 *         CPF do cliente passado no array ??
	 */
	public void inserir(Cliente c) {

		clientes[indice] = c;
		indice = indice + 1;
	}

	/**
	 * Retorna um cliente armazenado no array.
	 *
	 * @param cpf o CPF do cliente que será procurado no array.
	 *
	 * @return Cliente o cliente com seus dados lidos a partir do array.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            lidos não existe no array. A procura é feita pelo CPF.
	 */
	public Cliente procurar(String cpf) throws ClienteInexistenteException {

		Cliente c = null;
		if (existe(cpf)) {
			int i = this.procurarIndice(cpf);
			c = clientes[i];
		} else {
			throw new ClienteInexistenteException(cpf);
		}

		return c;
	}

	/**
	 * Método auxiliar que retorna o índice da ocorrência do array que
	 * contém um objeto Cliente cujo CPF é igual ao CPF passado como parâmetro.
	 *
	 * @param cpf o CPF a ser procurado nos clientes do array.
	 *
	 * @return int o índice da ocorrência do array que contém um objeto Cliente
	 *         cujo CPF é igual ao CPF passado como parâmetro. Se este objeto
	 *         não for encontrado, o retorno é -1.
	 */
	private int procurarIndice(String cpf) {

		int i = 0;
		int ind = -1;
		boolean achou = false;

		while ((i < indice) && !achou) {
			if ((clientes[i].getCpf()).equals(cpf)) {
				ind = i;
				achou = true;
			}
			i = i + 1;
		}
		return ind;
	}

	/**
	 * Exclui um cliente armazenado no array. A exclusão é feita colocando-se
	 * a última ocorrência na ocorrência a ser excluída e se decrementando 1 do
	 * índice.
	 *
	 * @param cpf o CPF do cliente que será excluído do array.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ser excluído
	 *            não existe no array. A procura é feita por CPF.
	 */
	public void remover(String cpf) throws ClienteInexistenteException {

		if (existe(cpf)) {
			int i = this.procurarIndice(cpf);
			clientes[i] = clientes[indice - 1];
			clientes[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new ClienteInexistenteException(cpf);
		}
	}
}