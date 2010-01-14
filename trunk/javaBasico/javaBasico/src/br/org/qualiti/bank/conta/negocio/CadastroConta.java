package br.org.qualiti.bank.conta.negocio;

import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.conta.excecoes.ContaExistenteException;
import br.org.qualiti.bank.conta.excecoes.ContaInexistenteException;
import br.org.qualiti.bank.conta.model.ContaAbstrata;
import br.org.qualiti.bank.conta.persistencia.RepositorioConta;

public class CadastroConta {
	
	RepositorioConta repositorio;
	
	public CadastroConta(RepositorioConta repositorio) {
		this.repositorio = repositorio;
	}
	
	
	public void inserir(ContaAbstrata conta) 
	throws ClienteInexistenteException, 
	       ContaExistenteException{
		this.repositorio.inserir(conta);
	}

	public void atualizar(ContaAbstrata conta) 
	throws ContaInexistenteException, 
	       ClienteInexistenteException {
		this.repositorio.atualizar(conta);
	}

	public void remover(ContaAbstrata conta)
	throws ContaInexistenteException {
		this.repositorio.remover(conta);
	}

	public boolean existe(ContaAbstrata conta) {
		return this.repositorio.existe(conta);
	}

	public ContaAbstrata pesquisar(ContaAbstrata conta) {
		return this.repositorio.pesquisar(conta);
	}

	public ContaAbstrata[] listar() {
		return this.repositorio.listar();
	}
}
