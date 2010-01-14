package br.org.qualiti.bank.conta.persistencia;

import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.excecoes.ContaInexistenteException;
import br.org.qualiti.bank.conta.excecoes.ContaExistenteException;
import br.org.qualiti.bank.conta.model.ContaAbstrata;

public interface RepositorioConta {
	
	public void inserir(ContaAbstrata conta) 
		throws ClienteInexistenteException, 
		       ContaExistenteException;
	
	public void atualizar(ContaAbstrata conta) 
		throws ContaInexistenteException, 
		       ClienteInexistenteException;
	
	public void remover(ContaAbstrata conta)
		throws ContaInexistenteException;
	
	public boolean existe(ContaAbstrata conta);
	
	public ContaAbstrata pesquisar(ContaAbstrata conta);
	
	public ContaAbstrata[] listar();
		
}
