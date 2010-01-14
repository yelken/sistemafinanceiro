package br.com.qualiti.java.avancado.modulo02.parte1;

public class MeuRunnable implements Runnable {

	private String nome;

	public MeuRunnable(String n) {
		this.nome = n;
	}

	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(this.nome + " gerou valor: " + i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void execute() {
		Thread t = new Thread(this);
		t.start();
	}

	public static void main(String[] args) {
		MeuRunnable m1 = new MeuRunnable("Thread 1");
		MeuRunnable m2 = new MeuRunnable("Thread 2");
		m1.execute();
		m2.execute();
	}
}
