package qualiti.banco.contas;

/**
 * Implementação da interface que define os métodos de acesso aos dados de conta 
 * em um mecanismo de armazenamento de dados. Esta implementação é realizada através
 * do armazenamento de objetos do tipo conta abstrata em um array interno de contas
 * abstratas existente em memória e inicializado com 100 posições fixas. Este array
 * suporta armazenamento de objetos de qualquer sub-tipo de conta abstrata. 
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.contas.ContaAbstrata
 * @see qualiti.banco.clientes.RepositorioContas
 */
public class RepositorioContasBDR implements RepositorioContas {

	/**
	 * Array interno de contas abstratas.
	 */
	private ContaAbstrata[] contas;
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
	 * o array de contas com 100 ocorrências, onde 100 é o tamanho da cache.
	 */
	public RepositorioContasBDR() {

		indice = 0;
		contas = new ContaAbstrata[tamCache];
	}

	/**
	 * Cadastra os dados de uma conta no array, inserindo o objeto recebido
	 * como parâmetro na primeira ocorrência vaga daquele.
	 * 
	 * @param c a conta com os dados a serem cadastrados.
	 *
	 * @see Q1 Por que este método não lança a exceção de conta já existente ??
	 * 
	 * @see Q2 Por que este método não testa a existência de uma conta com mesmo
	 *         número da conta passada no array ??
	 */
	public void inserir(ContaAbstrata c) {

		contas[indice] = c;
		indice = indice + 1;
	}

	/**
	 * Método auxiliar que retorna o índice da ocorrência do array que 
	 * contém um objeto ContaAbstrata cujo número é igual ao número passado 
	 * como parâmetro. 
	 * 
	 * @param num o número a ser procurado nas contas do array.
	 *        
	 * @return int o índice da ocorrência do array que contém um objeto ContaAbstrata
	 *         cujo número é igual ao número passado como parâmetro. Se este objeto 
	 *         não for encontrado, o retorno é -1.  
	 */
	private int procurarIndice(String num) {

		int i = 0;
		int ind = -1;
		boolean achou = false;

		while ((i < indice) && !achou) {
			if ((contas[i].getNumero()).equals(num)) {
				ind = i;
				achou = true;
			}
			i = i + 1;
		}
		return ind;
	}

	/**
	 * Verifica se uma conta existe armazenado no array.
	 * 
	 * @param num o número da conta cuja existência no array 
	 *        será verificada.
	 * 
	 * @return boolean true se a conta existir no array e
	 *         false caso contrário.
	 */
	public boolean existe(String num) {

		boolean resp = false;
		int i = this.procurarIndice(num);
		if (i != -1) {
			resp = true;
		}

		return resp;
	}

	/**
	 * Atualiza os dados de uma conta no array em memória, trocando
	 * o objeto atual do array pelo objeto passado como parâmetro.
	 * 
	 * @param c a conta com os dados a serem atualizados. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            atualizados não existe no array. A procura é feita pelo número.
	 */
	public void atualizar(ContaAbstrata c) throws ContaInexistenteException {

		int i = procurarIndice(c.getNumero());
		if (i != -1) {
			contas[i] = c;
		} else {
			throw new ContaInexistenteException(c.getNumero());
		}
	}

	/**
	 * Retorna uma conta armazenada no array.
	 * 
	 * @param num o número da conta que será procurada no array.
	 *        
	 * @return ContaAbstrata a conta com seus dados lidos a partir do array. Pode ser
	 *         qualquer sub-tipo de conta abstrata. 
	 *
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            lidos não existe no array. A procura é feita pelo número.
	 */
	public ContaAbstrata procurar(String num)
		throws ContaInexistenteException {

		ContaAbstrata c = null;
		if (existe(num)) {
			int i = this.procurarIndice(num);
			c = contas[i];
		} else {
			throw new ContaInexistenteException(num);
		}

		return c;
	}

	/**
	 * Exclui uma conta armazenada no array. A exclusão é feita colocando-se
	 * a última ocorrência na ocorrência a ser excluída e se decrementando 1 do
	 * índice. 
	 * 
	 * @param num o número da conta que será excluída do array.
	 *
	 * @exception ContaInexistenteException lançada quando a conta a ser excluída
	 *            não existe no array. A procura é feita por número.
	 */
	public void remover(String num) throws ContaInexistenteException {

		if (existe(num)) {
			int i = this.procurarIndice(num);
			contas[i] = contas[indice - 1];
			contas[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new ContaInexistenteException(num);
		}
	}
}