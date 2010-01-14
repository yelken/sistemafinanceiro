package br.org.qualiti.bank.conta.model;

import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.conta.excecoes.ValorInvalidoException;

public class Conta extends ContaAbstrata {

	public Conta(String numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);
	}

	
	@Override
	public void debitar(double valor) throws ValorInvalidoException {
		if (valor <= 0) {
			setSaldo(getSaldo()-valor);
		}
		else {
			throw new ValorInvalidoException();
		}
			
	}

}
