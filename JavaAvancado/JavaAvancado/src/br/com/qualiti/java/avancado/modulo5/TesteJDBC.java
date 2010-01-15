package br.com.qualiti.java.avancado.modulo5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteJDBC {
	public static void main(String[] args) {
	    try {
	      // Driver sendo carregado 
	      Class.forName("org.hsqldb.jdbcDriver");
	      // Obtendo uma conexão com o banco de dados
	      Connection con = 		DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost:9090/qib","sa", "");
	      /* Neste ponto as consultas já poderiam ser executadas,  
			 uma vez que a conexão foi estabelecida com sucesso */
	      // Fechando a conexão obtida
	      con.close();
	    } catch (ClassNotFoundException e) {
			System.out.println("Erro1");
	    } catch (SQLException e) {
	       System.out.println("Erro2 "+e.getMessage());
	    }
	  }
}
