package br.org.qualiti.bank.cliente.persistencia;

import br.org.qualiti.bank.cliente.excecoes.ClienteExistenteException;
import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.model.Cliente;

/**
 * Interface do repositorio de cliente 
 * @author Yelken
 *
 */
public interface RepositorioCliente {

	public void inserir(Cliente cliente) throws ClienteExistenteException;
	
	public void atualizar(Cliente cliente) throws ClienteInexistenteException;
	
	public void remover(Cliente cliente) throws ClienteInexistenteException;
	
	public boolean existe(Cliente cliente);
	
	public Cliente pesquisar(Cliente cliente);
	
	public Cliente[] listar();
}
