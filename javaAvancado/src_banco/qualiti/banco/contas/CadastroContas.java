package qualiti.banco.contas;

/**
 * Classe que realiza validações referentes às operações de atualização de dados
 * no mecanismo de armazenamento de dados de contas e usa o repositório de contas
 * para aualizar e buscar os dados das mesmas. Realiza também operações de crédito,
 * de débito e de transferência entre contas.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.cliente.Cliente
 * @see qualiti.banco.cliente.ReposiorioClientes
 */
public class CadastroContas {

	/**
	 * Referência para a implementação do repositório de contas.
	 */
	private RepositorioContas contas;

	/**
	 * O construtor da classe. Inicializa a referência para o repositório
	 * de contas com o valor passado como parâmetro. 
	 * 
	 * @param r a referência para o repositório de contas.
	 */
	public CadastroContas(RepositorioContas r) {

		this.contas = r;
	}

	/**
	 * Atualiza os dados de uma conta no repositório de contas.
	 * 
	 * @param c a conta com os dados a serem atualizados. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            atualizados não existe no repositório de contas.Esta exceção vem da 
	 *            chamada ao repositório de contas e é repassada diretamente por este 
	 *            método ao seu método chamador. 
	 */
	public void atualizar(ContaAbstrata c) throws ContaInexistenteException {

		contas.atualizar(c);
	}

	/**
	 * Cadastra os dados de uma conta no repositório de contas. Antes disso, 
	 * checa se o número da conta a ser cadastrada já existe no repositório  
	 * de contas.
	 * 
	 * @param c a conta com os dados a serem cadastrados.
	 *
	 * @exception ContaExistenteException se o número da conta a ser cadastrada já 
	 *            existir no repositório de contas. Esta exceção é instanciada e
	 *            lançada por este método, caso a consulta ao número feita no 
	 *            repositório retorne true. 
	 */
	public void cadastrar(ContaAbstrata c) throws ContaExistenteException {

		if (!contas.existe(c.getNumero())) {
			contas.inserir(c);
		} else {
			throw new ContaExistenteException(c.getNumero());
		}
	}

	/**
	 * Realiza uma operação de crédito de um dado valor em uma conta no repositório de contas. 
	 * 
	 * @param n o número da conta a ser creditada.
	 * @param v o valor a ser creditado.
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ser creditada
	 *            não existe no repositório de contas.Esta exceção vem da chamada 
	 *            ao repositório de contas e é repassada diretamente por este 
	 *            método ao seu método chamador.
	 *
	 * @see Q1 Por que este método, após realizar a operação de creditar na
	 *         conta, não atualiza os dados da mesma usando o repositório ??
	 *         Será que esta forma de implementação irá funcionar quando a 
	 *         implementação do repositório for trocada por uma classe que 
	 *         acessa um banco SQL ??   
	 */
	public void creditar(String n, double v) throws ContaInexistenteException {

		// lança ContaInexistenteException  
		ContaAbstrata c = contas.procurar(n);
		c.creditar(v);
	}

	/**
	 * Realiza uma operação de débito de um dado valor em uma conta no repositório de contas. 
	 * 
	 * @param n o número da conta a ser debitada.
	 * @param v o valor a ser debitado.
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ser debitada
	 *            não existe no repositório de contas.Esta exceção vem da chamada 
	 *            ao repositório de contas e é repassada diretamente por este 
	 *            método ao seu método chamador.
	 * @exception SaldoInsuficienteException lançada quando o saldo da conta a
	 *            ser debitada é menor que o valor a ser debitado. Esta exceção 
	 *            vem da chamada ao método debitar() de contas e é repassada 
	 *            diretamente por este método em questão ao seu método chamador.
	 *
	 * @see Q1 Por que este método, após realizar a operação de debitar na
	 *         conta, não atualiza os dados da mesma usando o repositório ??
	 *         Será que esta forma de implementação irá funcionar quando a 
	 *         implementação do repositório for trocada por uma classe que 
	 *         acessa um banco SQL ??   
	 */
	public void debitar(String n, double v)
		throws ContaInexistenteException, SaldoInsuficienteException {

		// lança ContaInexistenteException  
		ContaAbstrata c = contas.procurar(n);
		// lança SaldoInsuficienteException
		c.debitar(v);
	}

	/**
	 * Exclui uma conta armazenada no repositório de contas.
	 * 
	 * @param n o número da conta que será excluída do repositório de contas.
	 *
	 * @exception ContaInexistenteException lançada quando a conta a ser excluída 
	 *            não existe no repositório de contas. Esta exceção vem da chamada ao 
	 *            repositório de contas e é repassada diretamente por este método 
	 *            ao seu método chamador.  
	 */
	public void remover(String n) throws ContaInexistenteException {

		contas.remover(n);
	}

	/**
	 * Retorna uma conta armazenada no repositório de contas.
	 * 
	 * @param n o número da conta que será procurada no repositório de contas.
	 *        
	 * @return ContaAbstrata a conta com seus dados lidos a partir do repositório
	 *         de contas. Pode ser qualquer sub-tipo de conta abstrata. 
	 *
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            lidos não existe no repositório de contas. Esta exceção vem da chamada 
	 *            ao repositório de contas e é repassada diretamente por este método ao 
	 *            seu método chamador. 
	 */
	public ContaAbstrata procurar(String n) throws ContaInexistenteException {

		return contas.procurar(n);
	}

	/**
	 * Realiza uma operação de transferência de um dado valor de uma conta 
	 * para outra conta.
	 * 
	 * @param origem o número da conta a ser debitada.
	 * @param destino o número da conta a ser creditada.
	 * @param val o valor a ser transferido.
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ser debitada ou 
	 *            a conta a ser creditada não existe no repositório de contas.Esta 
	 *            exceção vem da chamada  ao repositório de contas e é repassada 
	 *            diretamente por este método ao seu método chamador.
	 * @exception SaldoInsuficienteException lançada quando o saldo da conta a
	 *            ser debitada é menor que o valor a ser transferido. Esta exceção 
	 *            vem da chamada ao método debitar() de contas e é repassada 
	 *            diretamente por este método em questão ao seu método chamador.
	 *
	 * @see Q1 Por que este método, após realizar a operação de transferir na
	 *         conta, não atualiza os dados da mesma usando o repositório ??
	 *         Será que esta forma de implementação irá funcionar quando a 
	 *         implementação do repositório for trocada por uma classe que 
	 *         acessa um banco SQL ??   
	 */
	public void transferir(String origem, String destino, double val)
		throws ContaInexistenteException, SaldoInsuficienteException {

		// lança ContaInexistenteException  
		ContaAbstrata o = contas.procurar(origem);
		// lança ContaInexistenteException  
		ContaAbstrata d = contas.procurar(destino);
		// lança SaldoInsuficienteException 
		o.transferir(d, val);
	}
}