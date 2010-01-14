package br.com.qualiti.java.avancado.modulo02.parte1;

public class MeuThread extends Thread {

	private String nome;

	public MeuThread(String n) {
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

	public static void main(String[] args) {
		MeuThread t1 = new MeuThread("Thread 1");
		MeuThread t2 = new MeuThread("Thread 2");
		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (Exception e) {
		}
		System.out.println("Termino da execucao");
	}
}