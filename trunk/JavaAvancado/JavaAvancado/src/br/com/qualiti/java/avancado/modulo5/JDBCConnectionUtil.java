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
	private static String driver;
	private static String url;
	private static String user;
	private static String pass;
	
	private static Connection connection;
	
	static {
		//Tenta carregar os dados da conexao
		File file = new File("jdbc.properties");    
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			//lê os dados que estão no arquivo
			props.load(fis);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			user = props.getProperty("user");
			pass = props.getProperty("pass");
			fis.close();
		}catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} 
		
		//Tenta carregar o driver do servidor de banco de dados
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver JDBC do HSQLDB. ");
			System.exit(-1);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		if (connection == null || connection.isClosed()){
			//Tenta estabelecer um,a conexão com o servidor de Banco de dados
			connection = DriverManager.getConnection(url, user, pass);
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
