package br.org.qualiti.bank.conta.excecoes;

public class ContaExistenteException extends Exception {
	
	private static final long serialVersionUID = -8279369948087366340L;

	public ContaExistenteException() {
		super("Conta já cadastrada no sistema");
	}

}
