package br.org.qualiti.bank;

import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.excecoes.ContaInexistenteException;
import br.org.qualiti.bank.conta.excecoes.ContaExistenteException;
import br.org.qualiti.bank.conta.model.ContaAbstrata;
import br.org.qualiti.bank.conta.negocio.CadastroConta;
import br.org.qualiti.bank.conta.persistencia.RepositorioConta;
import br.org.qualiti.bank.conta.persistencia.RepositorioContasArray;

public class Fachada {
	static Fachada instance;
	CadastroConta cadastroConta;
	
	private Fachada() {
		initCadastros();
	}
	
	public void inserirConta(ContaAbstrata conta) 
	throws ClienteInexistenteException, 
	       ContaExistenteException{
		this.cadastroConta.inserir(conta);
	}
	
	public static Fachada getInstace(){
		if (instance == null) {
			instance = new Fachada();
		}
		return instance;
	}
	
	private  void initCadastros() {
		RepositorioConta rc = new RepositorioContasArray();
		this.cadastroConta = new CadastroConta(rc);
	}

	public void atualizarConta(ContaAbstrata conta) 
	throws ContaInexistenteException, 
	       ClienteInexistenteException{
		this.cadastroConta.atualizar(conta);
	}

	public void removerConta(ContaAbstrata conta)
	throws ContaInexistenteException{
		this.cadastroConta.remover(conta);
	}

	public boolean existeConta(ContaAbstrata conta) {
		return this.cadastroConta.existe(conta);
	}

	public ContaAbstrata pesquisarConta(ContaAbstrata conta) {
		return this.cadastroConta.pesquisar(conta);
	}

	public ContaAbstrata[] listarConta() {
		return this.cadastroConta.listar();
	}
}
