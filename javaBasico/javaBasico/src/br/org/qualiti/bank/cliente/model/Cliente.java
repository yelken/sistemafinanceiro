package br.org.qualiti.bank.cliente.model;

public class Cliente {
	private String cpf;
	private String nome;
	private Endereco endereco;
	
	public Cliente(String cpf, String nome, Endereco endereco) {		
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
