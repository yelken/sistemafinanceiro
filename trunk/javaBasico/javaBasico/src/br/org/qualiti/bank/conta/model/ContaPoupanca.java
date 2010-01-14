package br.org.qualiti.bank.conta.model;

import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.conta.excecoes.ValorInvalidoException;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(String numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);
		// TODO Auto-generated constructor stub
	}
	
	public void renderJuros(float taxa) throws ValorInvalidoException {
		
		if (taxa >= 0) {
			creditar( (getSaldo() * taxa) );
		}else {
			throw new ValorInvalidoException();
		}
	}

}
