package br.com.qualiti.java.avancado.modulo02.parte3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCachedThreadPool {
	
	public static void main(String args[]){
		
		//Criação de um Executor do tipo Cached Thread Pool
		ExecutorService cachedPool= Executors.newCachedThreadPool();
		
		cachedPool.execute( new MessageRunnable("A") );
		cachedPool.execute( new MessageRunnable("B") );
		cachedPool.execute( new MessageRunnable("C") );
		cachedPool.execute( new MessageRunnable("D") );
		
		cachedPool.shutdown(); //Libera as instancias de thread do pool (Quando terminarem a execução)
	}
	
}
