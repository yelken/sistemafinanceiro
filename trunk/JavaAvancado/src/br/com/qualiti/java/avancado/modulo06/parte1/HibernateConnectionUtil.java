package br.com.qualiti.java.avancado.modulo06.parte1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateConnectionUtil {
	private static AnnotationConfiguration acfg;
	
	static {
		acfg = new AnnotationConfiguration();
		acfg.configure();		
	}
	
	public static Session getSession() {
		SessionFactory sessionFactory = acfg.buildSessionFactory();		
		Session session = sessionFactory.openSession();
		return session;
	}
}
