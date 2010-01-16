package br.org.qualiti.bank.cliente.negocio;

import br.org.qualiti.bank.cliente.excecoes.ClienteExistenteException;
import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.cliente.persistencia.RepositorioCliente;

/**
 * Esta classe chama apenas os métodos do repositorio é interessante que seja tratado 
 * a regra de negócio aqui.
 * @author Yelken
 *
 */

public class CadastroCliente {

	private RepositorioCliente repositorio;
	
	
	public CadastroCliente(RepositorioCliente repositorio) {
		this.repositorio = repositorio;
	}
	
	public void inserirCliente(Cliente cliente) throws ClienteExistenteException {
		this.repositorio.inserir(cliente);
	}

	
	public void atualizarCliente(Cliente cliente) throws ClienteInexistenteException {
		this.repositorio.atualizar(cliente);
	}

	
	public void removerCliente(Cliente cliente) throws ClienteInexistenteException {
		this.repositorio.remover(cliente);
	}

	
	public boolean existeCliente(Cliente cliente) {		
		return this.repositorio.existe(cliente);
	}

	
	public Cliente pesquisarCliente(Cliente cliente) {
		return this.repositorio.pesquisar(cliente);
	}


	public Cliente[] listarCliente() {
		return this.repositorio.listar();
	}

}
