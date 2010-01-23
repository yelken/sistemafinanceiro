package br.com.qualiti.java.avancado.modulo05.parte1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import qualiti.banco.jdbcutil.JDBCConnectionUtil;

public class TesteJDBCMelhorado {

	public static void main(String[] args) {

		Connection con = null;
		
		try {
			//Obtem uma conexão com o banco através do DriverManager
			con = JDBCConnectionUtil.getConnection();
			
			//Cria um Statement
			Statement stmt = con.createStatement();
			
			//Executa a consulta
			ResultSet rs = stmt.executeQuery("select * from tb_cliente");
			
			//Exibe o resultado da consulta
			System.out.println("CPF\tNome");
			while (rs.next()){
				System.out.println(rs.getString("CPF")+"\t"+rs.getString("nome"));
			}
			rs.close();
			
			//Fecha a conexão não mais usada
			con.close();
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
	}
}
