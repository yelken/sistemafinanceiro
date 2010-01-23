package qualiti.banco.fachada;

import java.util.Vector;

import qualiti.banco.clientes.CadastroClientes;
import qualiti.banco.clientes.Cliente;
import qualiti.banco.clientes.ClienteExistenteException;
import qualiti.banco.clientes.ClienteInexistenteException;
import qualiti.banco.clientes.ClienteInvalidoException;
import qualiti.banco.clientes.RepositorioClientes;
import qualiti.banco.clientes.RepositorioClientesHibernate;
import qualiti.banco.contas.CadastroContas;
import qualiti.banco.contas.ContaAbstrata;
import qualiti.banco.contas.ContaExistenteException;
import qualiti.banco.contas.ContaInexistenteException;
import qualiti.banco.contas.RepositorioContas;
import qualiti.banco.contas.RepositorioContasBDR;
import qualiti.banco.contas.SaldoInsuficienteException;
import qualiti.banco.geral.ErroAcessoRepositorioException;

/**
 * Classe que representa a fachada do sistema. Interage com o meio externo para atender ou encaminhar
 * solicitações de processamento. Esta classe é um Singleton, padrão de projeto que garante a existência
 * de uma única instância desta classe em um programa JAVA. 
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.cliente.CadastroClientes
 * @see qualiti.banco.conta.CadastroContas
 */
public class Fachada {

	/**
	 * Referência estática do Singleton. Guarda o endereço do objeto que representa a única
	 * instância desta classe em um programa JAVA. 
	 */
	private static Fachada instancia;
	/**
	 * Referência para o cadastro de contas. 
	 */
	private CadastroContas contas;
	/**
	 * Referência para o cadastro de clientes. 
	 */
	private CadastroClientes clientes;

	/**
	 * Construtor privado da classe. Ele é assim definido para que o padrão de implementação
	 * do Singleton possa garantir que uma única instância desta classe exista em um programa
	 * JAVA. Para que isto ocorra, uma das premissas é restringir a responsabilidade de criar
	 * objetos do tipo desta classe a ela própria. Isto se faz colocando o construtor com
	 * acesso privado. Este construtor chama o método que inicializa os cadastros de contas e
	 * de clientes.
	 */
	private Fachada() {

		initCadastros();
	}

	/**
	 * Inicializa os cadastros de contas e de clientes, passando para estes as implementações dos repositórios
	 * de contas e de clientes a serem usadas pelos cadastros inicializados. É interessante notar que os
	 * repositórios inicializados são interfaces, que recebem referências de implementações usando arrays em
	 * memória. Uma sofisticação maior do sistema permitirá a implementação de repositórios que utilizam acesso
	 * a bancos de dados SQL. Para mudar a forma de implementação dos repositórios, basta alterar a 1a e a 3a 
	 * linhas deste método, inicializando os repositórios de clientes e de contas com as implementações que usam
	 * acesso a bancos de dados SQL. 
	 */
	private void initCadastros() {

		RepositorioContas rep = new RepositorioContasBDR();
		contas = new CadastroContas(rep);
		RepositorioClientes repClientes = new RepositorioClientesHibernate();
		clientes = new CadastroClientes(repClientes);
	}

	/**
	 * Método responsável por retornar a referência da única instância desta classe
	 * no programa JAVA e por criar esta única instância, caso ela não exista. 
	 * 
	 * @return Fachada referência para a única instância desta classe. 
	 */
	public static Fachada obterInstancia() {

		// Na primeira vez que este método for chamado, instancia == null
		// Assim, o teste retorna true, a instância é criada e sua referência
		// é atribuída ao atributo instancia. Nas próximas chamadas, o teste
		// retorna false e a referência criada na primeira chamada sempre é
		// retornada.
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	/**
	 * Atualiza os dados de um cliente. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de clientes.
	 * 
	 * @param c o cliente com os dados a serem atualizados. 
	 * 
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados 
	 *            atualizados não existe no cadastro. Esta exceção vem da chamada ao
	 *            cadastro de clientes e é repassada diretamente por este método ao
	 *            seu método chamador. 
	 * @throws ErroAcessoRepositorioException 
	 */
	public void atualizar(Cliente c) throws ClienteInexistenteException, ErroAcessoRepositorioException {

		clientes.atualizar(c);
	}

	/**
	 * Busca um cliente do cadastro de clientes. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de clientes.
	 * 
	 * @param cpf o CPF do cliente a ser buscado.
	 *
	 * @return Cliente o cliente com os dados reucperados do cadastro.
	 * 
	 * @exception ClienteInexistenteException lançada quando o cliente a ser buscado não 
	 *            existe no cadastro. Esta exceção vem da chamada ao cadastro de clientes 
	 *            e é repassada diretamente por este método ao seu método chamador. 
	 * @throws ErroAcessoRepositorioException 
	 */
	public Cliente procurarCliente(String cpf)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		return clientes.procurar(cpf);
	}

	/**
	 * Cadastra os dados de um cliente. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de clientes.
	 * 
	 * @param c o cliente com os dados a serem cadastrados. 
	 * 
	 * @exception ClienteExistenteException lançada quando o cliente a ter seus dados 
	 *            cadastrados já existe no cadastro. Esta exceção vem da chamada ao
	 *            cadastro de clientes e é repassada diretamente por este método ao
	 *            seu método chamador. 
	 * @throws ErroAcessoRepositorioException 
	 */
	public void cadastrar(Cliente c) throws ClienteExistenteException, ErroAcessoRepositorioException {

		clientes.cadastrar(c);
	}

	/**
	 * Exclui um cliente do cadastro de clientes. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de clientes.
	 * 
	 * @param cpf o CPF do cliente a ser excluído.
	 * 
	 * @exception ClienteInexistenteException lançada quando o cliente a ser excluído não 
	 *            existe no cadastro. Esta exceção vem da chamada ao cadastro de clientes 
	 *            e é repassada diretamente por este método ao seu método chamador. 
	 * @throws ErroAcessoRepositorioException 
	 */
	public void descadastrarCliente(String cpf)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		clientes.descadastrar(cpf);
	}

	/**
	 * Atualiza os dados de uma conta. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de contas. É interessante notar que este método suporta atualização dos dados
	 * de QUALQUER TIPO DE CONTA, pois recebe como parâmetro uma conta abstrata.
	 * 
	 * @param c a conta com os dados a serem atualizados. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            atualizados não existe no cadastro. Esta exceção vem da chamada ao
	 *            cadastro de contas e é repassada diretamente por este método ao
	 *            seu método chamador. 
	 */
	public void atualizar(ContaAbstrata c) throws ContaInexistenteException {

		contas.atualizar(c);
	}

	/**
	 * Busca QUALQUER TIPO DE CONTA do cadastro de contas. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de contas. É interessante notar que este método suporta busca dos dados de QUALQUER TIPO DE CONTA, 
	 * pois retorna uma conta abstrata.
	 * 
	 * @param n o número da conta a ser buscada.
	 *
	 * @return ContaAbstrata a conta com os dados recuperados do cadastro.
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ser buscada não 
	 *            existe no cadastro. Esta exceção vem da chamada ao cadastro de contas 
	 *            e é repassada diretamente por este método ao seu método chamador. 
	 */
	public ContaAbstrata procurarConta(String n)
		throws ContaInexistenteException {

		return contas.procurar(n);
	}

	/**
	 * Cadastra os dados de uma conta. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de contas. É interessante notar que este método suporta cadastramento dos dados
	 * de QUALQUER TIPO DE CONTA, pois recebe como parâmetro uma conta abstrata.
	 * 
	 * @param c a conta com os dados a serem cadastrados. 
	 * 
	 * @exception ContaExistenteException lançada quando a conta a ter seus dados 
	 *            cadastrados já existe no cadastro. Esta exceção vem da chamada ao
	 *            cadastro de contas e é repassada diretamente por este método ao
	 *            seu método chamador.
	 * @exception ClienteInexistenteException lançada quando o cliente a ser associado 
	 *            à conta a ser cadastrada não existe no cadastro de clientes. Esta exceção
	 *            vem da chamada ao cadastro de clientes e é repassada diretamente por este
	 *            método ao seu método chamador. 
	 * @exception ClienteInvalidoException se o objeto cliente associado ao objeto conta for nulo. 
	 *            Esta exceção é instanciada e lançada por este método.
	 * @throws ErroAcessoRepositorioException 
	 *
	 * @see Q1 Este método realiza uma validação que é pré-condição para o cadastramento de uma
	 *         conta no cadastro de contas. Conceitualmente, este tipo de processamento é de 
	 *         responsabilidade do CADASTRO DE CONTAS. Um bom exercício adicional é passar esta 
	 *         implementação de consistir o cliente da conta para o cadastro de contas. O que o 
	 *         cadastro de contas precisa usar para realizar esta validação ??  
	 */
	public void cadastrar(ContaAbstrata c)
		throws
			ContaExistenteException,
			ClienteInexistenteException,
			ClienteInvalidoException, ErroAcessoRepositorioException {

		Cliente cli = c.getCliente();
		if (cli != null) {
			// "procurar()" lança ClienteInexistenteException
			clientes.procurar(cli.getCpf());
			// "cadastrar()" lança ContaExistenteException
			contas.cadastrar(c);
		} else {
			// O prórpio método lança ClienteInvalidoException se o cliente associado à conta for nulo
			throw new ClienteInvalidoException();
		}
	}

	/**
	 * Exclui uma conta do cadastro de contas. A fachada, neste caso, delega esta responsabilidade ao
	 * cadastro de contas.
	 * 
	 * @param n o número da conta a ser excluída.
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ser excluída não 
	 *            existe no cadastro. Esta exceção vem da chamada ao cadastro de contas 
	 *            e é repassada diretamente por este método ao seu método chamador. 
	 */
	public void descadastrarConta(String n) throws ContaInexistenteException {

		contas.remover(n);
	}

	/**
	 * Credita um valor em uma conta, usando o cadastro de contas. 
	 * 
	 * @param n o número da conta a ser creditada.
	 * @param v o valor a ser creditado. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta cujo número passado como parâmetro
	 *            não existir no cadastro de contas. Esta exceção é lançada pelo cadastro e é repassada
	 *            ao método chamador por este método. 
	 *
	 * @see Q1 Este método repassa a responsabilidade de controlar o fluxo de processamento de uma
	 *         operação de crédito ao cadastro de contas, quando esta responsabilidade é de uma classe
	 *         controladora, que, em operações simples, é também a classe Fachada. Um bom exercício 
	 *         adicional é passar este controle do fluxo de processamento de uma operação de crédito 
	 *         para este método.
	 * 
	 * @see Q2 Que outras validações precisariam ser feitas em um processo de crédito ??  
	 */
	public void creditar(String n, double v) throws ContaInexistenteException {

		contas.creditar(n, v);
	}

	/**
	 * Debita um valor em uma conta, usando o cadastro de contas. 
	 * 
	 * @param n o número da conta a ser debitada.
	 * @param v o valor a ser debitado. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta cujo número passado como parâmetro
	 *            não existir no cadastro de contas. Esta exceção é lançada pelo cadastro e é repassada
	 *            ao método chamador por este método.
	 * @exception SaldoInsuficienteException lançada quando o saldo da conta a ser debitada é menor que o
	 *            valor passado como parâmetro. Esta exceção é lançada pelo cadastro e é repassada
	 *            ao método chamador por este método.
	 *
	 * @see Q1 Este método repassa a responsabilidade de controlar o fluxo de processamento de uma
	 *         operação de débito ao cadastro de contas, quando esta responsabilidade é de uma classe
	 *         controladora, que, em operações simples, é também a classe Fachada. Um bom exercício 
	 *         adicional é passar este controle do fluxo de processamento de uma operação de débito 
	 *         para este método.
	 * 
	 * @see Q2 Que outras validações precisariam ser feitas em um processo de débito ??  
	 */
	public void debitar(String n, double v)
		throws ContaInexistenteException, SaldoInsuficienteException {

		contas.debitar(n, v);
	}

	/**
	 * Transfere um valor de uma conta para outra conta. 
	 * 
	 * @param origem o número da conta a ser debitada.
	 * @param destino o número da conta a ser creditada.
	 * @param val o valor a ser transferido. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta de origem cujo número passado como parâmetro
	 *            não existir no cadastro de contas ou quando a conta de destino cujo número passado como 
	 *            parâmetro não existir no cadastro de contas. Esta exceção é lançada pelo cadastro e é repassada
	 *            ao método chamador por este método.
	 * @exception SaldoInsuficienteException lançada quando o saldo da conta de origem a ser debitada 
	 *            é menor que o valor passado como parâmetro. Esta exceção é lançada pelo cadastro e é 
	 *            repassada ao método chamador por este método.
	 *
	 * @see Q1 Este método repassa a responsabilidade de controlar o fluxo de processamento de uma
	 *         operação de transferência ao cadastro de contas, quando esta responsabilidade é de uma classe
	 *         controladora, que, em operações simples, é também a classe Fachada. Um bom exercício 
	 *         adicional é passar este controle do fluxo de processamento de uma operação de transferência 
	 *         para este método.
	 * 
	 * @see Q2 Que outras validações precisariam ser feitas em um processo de transferência ??  
	 */
	public void transferir(String origem, String destino, double val)
		throws ContaInexistenteException, SaldoInsuficienteException {

		contas.transferir(origem, destino, val);
	}
	
	
	public Vector<Cliente> listarClientes()
	throws ErroAcessoRepositorioException {

		return clientes.listar();
	}
	
}