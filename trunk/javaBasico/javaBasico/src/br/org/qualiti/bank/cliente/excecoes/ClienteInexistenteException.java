package br.org.qualiti.bank.cliente.excecoes;

public class ClienteInexistenteException extends Exception {

	private static final long serialVersionUID = 8165449523612003237L;

	public ClienteInexistenteException() {
		super("O Cliente não é cadastrado");
	}

}
