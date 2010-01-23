package br.com.qualiti.java.avancado.modulo06.parte1;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import qualiti.banco.clientes.Cliente;

public class TesteHibernate {

	
	private static SessionFactory sessionFactory;
	
	
	public static void main(String[] args) {
	
		try {
			AnnotationConfiguration acfg = new AnnotationConfiguration();
			acfg.configure();
			sessionFactory = acfg.buildSessionFactory();
		} catch (Throwable ex) {
				// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = sessionFactory.openSession();
		
		Query q =  session.createQuery("from Cliente");
		
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = q.list();
		
		for(Cliente c : clientes){
			System.out.print(" Nome: "+c.getNome());
			System.out.println(" CPF: "+c.getCpf());
			if(c.getEndereco()!=null){
				System.out.println("          ENDERECO["+c.getEndereco().getCEP()+","+c.getEndereco().getNumero()+","+c.getEndereco().getComplemento()+"]");
			}else{
				System.out.println("          ENDERECO[******NÃO CADASTRADO*****] ");
			}
		}
		
		session.close();

	}
	
}
