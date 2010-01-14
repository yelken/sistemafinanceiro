package br.org.qualiti.bank.conta.model;

import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.conta.excecoes.ValorInvalidoException;

public abstract class ContaAbstrata {
	
	private String numero;
	private double saldo;
	private Cliente cliente;
	
	
	public ContaAbstrata(String numero, double saldo, Cliente cliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}
	
	protected void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
		
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void creditar(double valor) throws ValorInvalidoException {
		if (valor > 0 ) {
			this.saldo += valor;
		}
		else {
			throw new ValorInvalidoException();
		}
			
	}
	
	public abstract void debitar(double valor) throws ValorInvalidoException;
}
