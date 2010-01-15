package qualiti.banco.contas;

/**
 * Interface que define os métodos de acesso aos dados de conta em um
 * mecanismo de armazenamento de dados. 
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0 
 *
 * @see qualiti.banco.conta.ContaAbstrata
 */
public interface RepositorioContas {
	/**
	 * Cadastra os dados de uma conta EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 * 
	 * @param c a conta com os dados a serem cadastrados. 
	 *
	 * @see Q1 Por que este método não lança a exceção de conta já existente ??
	 *
	 * @see Q2 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??  
	 */
	public void inserir(ContaAbstrata c);
	/**
	 * Verifica se uma conta existe armazenada EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 * 
	 * @param num o número da conta cuja existência EM UM MECANISMO DE ARMAZENAMENTO DE DADOS 
	 *        será verificada.
	 * 
	 * @return boolean true se a conta existir EM UM MECANISMO DE ARMAZENAMENTO DE DADOS e
	 *         false caso contrário.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??  
	 */
	public boolean existe(String num);
	/**
	 * Atualiza os dados de uma conta EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 * 
	 * @param c a conta com os dados a serem atualizados. 
	 * 
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            atualizados não existe no MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??  
	 */
	public void atualizar(ContaAbstrata c) throws ContaInexistenteException;
	/**
	 * Retorna uma conta armazenada EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 * 
	 * @param num o número da conta que será procurado EM UM MECANISMO DE ARMAZENAMENTO 
	 *        DE DADOS.
	 *        
	 * @return ContaAbstrata a conta com seus dados lidos a partir DE UM MECANISMO DE 
	 *         ARMAZENAMENTO DE DADOS.
	 *
	 * @exception ContaInexistenteException lançada quando a conta a ter seus dados 
	 *            lidos não existe no MECANISMO DE ARMAZENAMENTO DE DADOS.
	 * 
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??   
	 */
	public ContaAbstrata procurar(String num) throws ContaInexistenteException;
	/**
	 * Exclui uma conta armazenada EM UM MECANISMO DE ARMAZENAMENTO DE DADOS.
	 * 
	 * @param num o número da conta que será excluída EM UM MECANISMO DE ARMAZENAMENTO 
	 *        DE DADOS.
	 *
	 * @exception ContaInexistenteException lançada quando a conta a ser excluída 
	 *            não existe no MECANISMO DE ARMAZENAMENTO DE DADOS.
	 *
	 * @see Q1 Além das exceções definidas, os métodos do repositório não deveriam
	 *         lançar outra exceção para indicar problemas no mecanismo de acesso ??  
	 */
	public void remover(String num) throws ContaInexistenteException;
}
