package br.org.qualiti.bank.cliente.persistencia;

import br.org.qualiti.bank.cliente.excecoes.ClienteExistenteException;
import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.conta.excecoes.CapacidadeMaximaExcedidaException;

	public class RepositorioClienteArray implements RepositorioCliente {
	
	private static final byte MAX_TAM = 100;
	
	private Cliente[] clientes;
	
	private int index;
	
	
	public RepositorioClienteArray() {
		this.clientes = new Cliente[MAX_TAM];		
	}
	
	
	private int procurarIndice(Cliente cliente) {
		int indiceEncontrado = -1;
		for(int i =0;i<index;i++){	
			if(this.clientes[i].getCpf().equals(cliente.getCpf())) {
				indiceEncontrado = i;
				break;
			}
		}

		return indiceEncontrado;
	}
	
	
	@Override
	public void inserir(Cliente cliente) throws ClienteExistenteException {
		if (existe(cliente)) {
			throw new ClienteExistenteException();
		}
		
		if(index == MAX_TAM) {
			throw new CapacidadeMaximaExcedidaException();
		}
		
		this.clientes[index++] = cliente;
	}

	@Override
	public void atualizar(Cliente cliente) throws ClienteInexistenteException {		
		int i = procurarIndice(cliente);
		
		if (i == -1 ) {
			throw new ClienteInexistenteException();
		}
		
		this.clientes[i] = cliente;
	}

	@Override
	public void remover(Cliente cliente) throws ClienteInexistenteException {	
		int i = procurarIndice(cliente);
		
		if (i>=0) {
			this.clientes[i] = this.clientes[index--];
		}
		else {
			throw new ClienteInexistenteException();
		}
	}

	@Override
	public boolean existe(Cliente cliente) {
		return (procurarIndice(cliente)!= -1);
	}

	@Override
	public Cliente pesquisar(Cliente cliente) {
		Cliente retorno = null; 
		
		int i = procurarIndice(cliente);
		
		if  (i >= 0) {
			retorno = this.clientes[i];
		}
		return retorno;
	}

	@Override
	public Cliente[] listar() {
		return this.clientes;
	}

}
