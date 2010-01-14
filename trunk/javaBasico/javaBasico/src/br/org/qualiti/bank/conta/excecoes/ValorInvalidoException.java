package br.org.qualiti.bank.conta.excecoes;

public class ValorInvalidoException extends Exception {

	private static final long serialVersionUID = -5007946120280382988L;

	public ValorInvalidoException() {
		super("O Valor é inválido");
	}
}
