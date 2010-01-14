package br.org.qualiti.bank.conta.excecoes;

public class CapacidadeMaximaExcedidaException extends RuntimeException {

	private static final long serialVersionUID = -4529565184140426943L;

	public CapacidadeMaximaExcedidaException() {
		super("Capacidade máxima excedida");
	}

}
