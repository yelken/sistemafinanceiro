package br.com.qualiti.java.avancado.modulo06.parte1;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import qualiti.banco.clientes.Cliente;

public class TesteHibernateMelhorado {

	/**
	 * @param args	
	 */
	public static void main(String[] args) {
		Session session = HibernateConnectionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Cliente cliente = new Cliente("63565", "Hahahahahah");
		session.save(cliente);
		
		Query queryCliente = session.createQuery("from Cliente");  
		
		List<Cliente> results = queryCliente.list();
		
		for (Cliente client : results) {
			System.out.println("Nome: "+client.getNome());
			System.out.println("/tCpf: "+client.getCpf());
		}

		System.out.println(results.size());
		
		session.flush();
		session.close();
	}

}
