package br.org.qualiti.bank.cliente.model;

public class Endereco {
	
	private String CEP;
	private String complemento;
	
	
	public Endereco(String cep, String complemento) {	
		CEP = cep;
		this.complemento = complemento;
	}
	
	public String getCEP() {
		return CEP;
	}
	
	public void setCEP(String cep) {
		CEP = cep;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}	
}
