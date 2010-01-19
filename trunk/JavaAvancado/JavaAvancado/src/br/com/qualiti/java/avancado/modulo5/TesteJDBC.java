package br.com.qualiti.java.avancado.modulo5;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	      
	      Statement stm = con.createStatement();
	      String sql = "SELECT * FROM TB_CLIENTE";
	      
	      ResultSet rs = stm.executeQuery(sql);
	      
	      while(rs.next()) {
	    	  String cpf = rs.getString("CPF");
	    	  String nome = rs.getString("NOME");
	    	  System.out.println("Cliente: "+nome+" CPF: "+cpf);
	      }
	      // Fechando a conexão obtida
	      con.close();
	    } catch (ClassNotFoundException e) {
			System.out.println("Erro: Carregando driver JDBC "+e.getMessage());
	    } catch (SQLException e) {
	       System.out.println("Erro: SQLException "+e.getMessage());
	    }
	  }
}
