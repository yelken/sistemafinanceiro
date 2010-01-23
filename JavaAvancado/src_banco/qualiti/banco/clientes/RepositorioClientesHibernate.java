package qualiti.banco.clientes;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import qualiti.banco.geral.ErroAcessoRepositorioException;
import qualiti.banco.hibernateutil.HibernateConnectionUtil;

public class RepositorioClientesHibernate implements RepositorioClientes{

	private Session session; 
	
	private Session getSession(){
		if(session==null || !session.isOpen()){
			session = HibernateConnectionUtil.getSession();
		}		
		return session;
	}
	
	@Override
	public void atualizar(Cliente c) throws ClienteInexistenteException,
			ErroAcessoRepositorioException {
		Transaction t =session.beginTransaction();
		
		session.saveOrUpdate(c);
		
		t.commit();
		session.flush();
	}

	@Override
	public boolean existe(String cpf) throws ErroAcessoRepositorioException {
		boolean existe = true;
		try {
			procurar(cpf);
		} catch (Exception e) {
			existe = false;
		}
		return existe;
	}

	@Override
	public void inserir(Cliente c) throws ErroAcessoRepositorioException {
		Session session = getSession();
		
		Transaction t =session.beginTransaction();
		
		session.save(c);
		
		t.commit();
		session.flush();
	}

	@Override
	public Vector<Cliente> listar() throws ErroAcessoRepositorioException {
		//Listar todos os clientes
		Session session = getSession();
		
		Query q =  session.getNamedQuery("cliente.listar");
		
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = q.list();

		Vector<Cliente> retorno = new Vector<Cliente>(clientes.size(),1);
		
		for(Cliente c : clientes){
			retorno.add(c);
		}
		
		return retorno;
	}

	@Override
	public Cliente procurar(String cpf) throws ClienteInexistenteException,
			ErroAcessoRepositorioException {
		Session session = getSession();
		Cliente retorno = (Cliente) session.get(Cliente.class,cpf);
		if(retorno == null){
			throw new ClienteInexistenteException(cpf);
		}
		return retorno;
	}

	@Override
	public void remover(String cpf) throws ClienteInexistenteException,
			ErroAcessoRepositorioException {
		Session session = getSession();
		Cliente cliente = procurar(cpf);
		session.delete(cliente);
	}

}
