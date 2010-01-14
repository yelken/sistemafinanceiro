package br.org.qualiti.bank.conta.model;

import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.conta.excecoes.ValorInvalidoException;

public class ContaImposto extends ContaAbstrata {
	
	private static final float TAXA = 0.0345f; 
	
	public ContaImposto(String numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);	
	}

	@Override
	public void debitar(double valor) throws ValorInvalidoException {	
		if (valor <= 0) {
			double saldo = getSaldo();
			setSaldo((saldo-valor) - (valor*TAXA));
		}
		else {
			throw new ValorInvalidoException();
		}
	}

}
