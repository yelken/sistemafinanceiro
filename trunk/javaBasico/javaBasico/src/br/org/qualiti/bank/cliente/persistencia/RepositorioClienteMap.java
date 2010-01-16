package br.org.qualiti.bank.cliente.persistencia;

import br.org.qualiti.bank.cliente.excecoes.ClienteExistenteException;
import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.model.Cliente;

public class RepositorioClienteMap implements RepositorioCliente {

	@Override
	public void inserir(Cliente cliente) throws ClienteExistenteException {

	}

	@Override
	public void atualizar(Cliente cliente) throws ClienteInexistenteException {

	}

	@Override
	public void remover(Cliente cliente) throws ClienteInexistenteException {

	}

	@Override
	public boolean existe(Cliente cliente) {
		return false;
	}

	@Override
	public Cliente pesquisar(Cliente cliente) {
		return null;
	}

	@Override
	public Cliente[] listar() {
		return null;
	}

}
