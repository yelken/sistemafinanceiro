package br.com.qualiti.java.avancado.modulo06.parte1;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import qualiti.banco.clientes.Cliente;
import qualiti.banco.clientes.Endereco;
import qualiti.banco.hibernateutil.HibernateConnectionUtil;

public class TesteHibernateMelhorado {
	
	private static final Session session = HibernateConnectionUtil.getSession();
	
	private static void listarClientes(){
		Query q =  session.getNamedQuery("cliente.listar");
		
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
		
	}
	
	private static String inserirCliente(){
		Transaction t =session.beginTransaction();
		
		Random random = new Random();
		int cpf = random.nextInt(1111111111);
		Cliente cliente = new Cliente(""+cpf,
										">Cliente adicionado pelo Hibernate com CPF Randômico",
										new Endereco("CPF=",""+cpf,""+cpf)
									);
		session.save(cliente);
		
		t.commit();
		session.flush();
		return ""+cpf;
	}

	private static void alterarCliente(String newCpf) {
		Transaction t =session.beginTransaction();
		
		Cliente cliente = (Cliente) session.get(Cliente.class, newCpf);

		cliente.setNome(">Cliente após adicionado foi modificado pelo Hibernate");
		
		cliente.setEndereco(null);
		
		session.saveOrUpdate(cliente);
		
		t.commit();
		session.flush();
	}
	
	private static void removerCliente(String newCpf) {
		Transaction t =session.beginTransaction();
		
		Cliente cliente = (Cliente) session.get(Cliente.class, newCpf);
		
		
		session.delete(cliente);
		
		t.commit();
		session.flush();
	}
	
	public static void main(String[] args) {
		
		
		listarClientes();
		String newCpf = inserirCliente();
		listarClientes();
		alterarCliente(newCpf);
		listarClientes();
		removerCliente(newCpf);
		listarClientes();
		
		session.close();
		
	}

}
