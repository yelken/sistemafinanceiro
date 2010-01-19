package br.com.qualiti.java.avancado.modulo5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;


public class JDBCConnectionUtil {
	
	// criar uma classe só pra conexão yelken 19/01/2010
	private static String driver = "org.hsqldb.jdbcDriver";
	private static String url = "jdbc:hsqldb:hsql://localhost:9090/qib";
	private static String user = "sa";
	private static String pass = "";
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{
		if (connection != null){
			//Tenta estabelecer um,a conexão com o servidor de Banco de dados
			try {
				Class.forName("org.hsqldb.jdbcDriver");
			} catch (ClassNotFoundException e) {
					
			}
		      // Obtendo uma conexão com o banco de dados
		      connection = DriverManager.getConnection(url,user,pass);
		}
		return connection;
	}
	
	
	
	
	public static void createTransaction() throws SQLException{
			getConnection();
	}
	public static Savepoint createSavePoint(String name) throws SQLException{
		return null;
	}
	public static void commitTransaction() throws SQLException{
		
	}
	public static void rollbackTransaction() throws SQLException{
		
	}
	public static void rollbackTransaction(Savepoint savepoint) throws SQLException{
		
	}
}
