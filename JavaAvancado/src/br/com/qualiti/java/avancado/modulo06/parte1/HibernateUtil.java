package br.com.qualiti.java.avancado.modulo06.parte1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {   

	  public static final SessionFactory sessionFactory;

	    static {
	        try {
	            sessionFactory = new Configuration().configure().buildSessionFactory();
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	public static final ThreadLocal session = new ThreadLocal();

	public static Session currentSession() throws HibernateException {
	        Session s = (Session) session.get();
	        if (s == null) {
	            s = sessionFactory.openSession();
	            session.set(s);
	        }
	        return s;
	    }
	
	public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s != null)
            s.close();
        session.set(null);
    }
    
    static Connection conn; 
    static Statement st;
  public static void setup(String sql) {
    try {
      Class.forName("org.hsqldb.jdbcDriver");      
      String url = "jdbc:hsqldb:hsql://localhost:9090/qib";
      String user = "sa";
      String pass = "";
      conn = DriverManager.getConnection(url, user, pass);
      st = conn.createStatement();
      st.executeUpdate(sql);
    } catch (Exception e) {
      System.exit(0);
    }
  }
  public static void checkData(String sql) {
    try {
      HibernateUtil.outputResultSet(st
          .executeQuery(sql));
//      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    public static void outputResultSet(ResultSet rs) throws Exception{
    ResultSetMetaData metadata = rs.getMetaData();

    int numcols = metadata.getColumnCount();
    String[] labels = new String[numcols]; 
    int[] colwidths = new int[numcols];
    int[] colpos = new int[numcols];
    int linewidth;

      for (int i = 0; i < numcols; i++) {
        labels[i] = metadata.getColumnLabel(i + 1); // get its label
        System.out.print(labels[i]+"  ");
    }
      System.out.println("------------------------");

    while (rs.next()) {
        for (int i = 0; i < numcols; i++) {
        Object value = rs.getObject(i + 1);
        if(value == null){
            System.out.print("       ");
        }else{
            System.out.print(value.toString().trim()+"   ");
        }
        
      }
        System.out.println("       ");
    }
    }
}

