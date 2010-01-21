package br.com.qualiti.java.avancado.modulo06.parte1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import qualiti.banco.clientes.Cliente;

public class TesteHibernate {

	
	public static void main(String[] args) {
		AnnotationConfiguration acfg = new AnnotationConfiguration();
		
		acfg.configure();
		
		SessionFactory sessionFactory = acfg.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		Cliente cliente = new Cliente("081112774", "Yelken");		
		
		session.save(cliente);
		
		session.flush();
		
		session.close();
		
	}

}
