package br.org.qualiti.bank.conta.persistencia;

import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.excecoes.ContaInexistenteException;
import br.org.qualiti.bank.conta.excecoes.CapacidadeMaximaExcedidaException;
import br.org.qualiti.bank.conta.excecoes.ContaExistenteException;
import br.org.qualiti.bank.conta.model.ContaAbstrata;

/**
 * Classe que herda os metódos da interface RepositorioConta.java
 * @author Yelken
 *
 */

public class RepositorioContasArray implements RepositorioConta {

	private static final byte MAX_TAM = 100;
	
	private ContaAbstrata[] contas;
	
	private int index;
	
	public RepositorioContasArray() {
		this.contas = new ContaAbstrata[MAX_TAM];
	}

	@Override
	public void atualizar(ContaAbstrata conta)
			throws ContaInexistenteException, ClienteInexistenteException {
		int i = procurarIndice(conta);
		
		if (i == -1 ) {
			throw new ContaInexistenteException();
		}
		
		// verificar e atualizar cliente
		
		this.contas[i] = conta;
	}

	
	@Override
	public boolean existe(ContaAbstrata conta) {
		return (procurarIndice(conta)!= -1);
	}
	
	
	
	
	private int procurarIndice(ContaAbstrata conta) {
		int indiceEncontrado = -1;
		for(int i =0;i<index;i++){	
			if(this.contas[i].getNumero().equals(conta.getNumero())) {
				indiceEncontrado = i;
				break;
			}
		}

		return indiceEncontrado;
	}

	@Override
	public void inserir(ContaAbstrata conta)
			throws ClienteInexistenteException, ContaExistenteException {
		if (existe(conta)) {
			throw new ContaExistenteException();
		}
		
		if(index == MAX_TAM) {
			throw new CapacidadeMaximaExcedidaException();
		}
		
		this.contas[index++] = conta;
	}

	@Override
	public ContaAbstrata[] listar() {
		return this.contas;
	}

	@Override
	public ContaAbstrata pesquisar(ContaAbstrata conta) {
		ContaAbstrata retorno = null; 
		
		int i = procurarIndice(conta);
		
		if  (i >= 0) {
			retorno = this.contas[i];
		}
		return retorno;
	}

	@Override
	public void remover(ContaAbstrata conta) throws ContaInexistenteException {
		int i = procurarIndice(conta);
		
		if (i>=0) {
			this.contas[i] = this.contas[index--];
		}
		else {
			throw new ContaInexistenteException();
		}
	}
}
