package br.com.qualiti.java.avancado.modulo02.parte3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Criação de um Executor do tipo Single Thread Pool
		ExecutorService singlePool_1 = Executors.newSingleThreadExecutor();
		//Criação de um Executor do tipo Single Thread Pool
		ExecutorService singlePool_2 = Executors.newSingleThreadExecutor();
		
		singlePool_1.execute( new MessageRunnable("Sequencia A - 1 - Concorre com B") );
		singlePool_1.execute( new MessageRunnable("Sequencia A - 2 - Espera terminar 1") );
		singlePool_1.execute( new MessageRunnable("Sequencia A - 3 - Espera terminar 2") );
		singlePool_2.execute( new MessageRunnable("Sequencia B - Concorre com A - 1 ") );
		
		singlePool_1.shutdown(); //Libera as instancias de thread do pool (Quando terminarem a execução)
		singlePool_2.shutdown(); //Libera as instancias de thread do pool (Quando terminarem a execução)
	}

}
