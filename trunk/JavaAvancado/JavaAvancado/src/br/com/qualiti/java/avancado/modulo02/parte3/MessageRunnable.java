package br.com.qualiti.java.avancado.modulo02.parte3;

public class MessageRunnable implements Runnable {

	private String message;
	
	public MessageRunnable(String msg){
		this.message = "->> Executando ["+msg+"]";
	}
	
	public void run() {
		for (int i = 0; i<10; i++){
			System.out.println(this.message);
			Thread.yield();
		}	
	}

}
