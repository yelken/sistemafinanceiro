package br.org.qualiti.bank.cliente.excecoes;

public class ClienteExistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493619732353586726L;

	public ClienteExistenteException() {
		super("Cliente já existente");
	}	

}
