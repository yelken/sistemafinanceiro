package qualiti.banco.fachada;
import java.util.Collection;
import java.util.Random;
import java.util.Vector;

import qualiti.banco.clientes.Cliente;
import qualiti.banco.clientes.ClienteExistenteException;
import qualiti.banco.clientes.ClienteInexistenteException;
import qualiti.banco.clientes.Endereco;
import qualiti.banco.contas.ContaAbstrata;
import qualiti.banco.geral.ErroAcessoRepositorioException;
import qualiti.banco.gerentes.Gerente;

/*
 * Created on 15/10/2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author crsj
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TesteFachada {

	
	private static void testarModuloClientes() throws ErroAcessoRepositorioException, ClienteExistenteException, ClienteInexistenteException{
		
		//Testar listar
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println(":::::::>>>>> Testando listar clientes");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		
		Vector<Cliente> clientes = Fachada.obterInstancia().listarClientes();
		
		for(Cliente c : clientes){
			System.out.print(" Nome: "+c.getNome());
			System.out.println(" CPF: "+c.getCpf());
			if(c.getEndereco()!=null){
				System.out.println("          ENDERECO["+c.getEndereco().getCEP()+","+c.getEndereco().getNumero()+","+c.getEndereco().getComplemento()+"]");
			}else{
				System.out.println("          ENDERECO[******NÃO CADASTRADO*****] ");
			}
			
			Collection<ContaAbstrata> contas = c.getContas();
			Collection<Gerente>       gerentes = c.getGerentes();
			
			if(contas!=null){
				System.out.println("          Contas[");
				for(ContaAbstrata conta : contas){
					System.out.println("                 NUM:"+conta.getNumero()+" SALDO:"+conta.getSaldo());
				}
				System.out.println("                ]");
			}else{
				System.out.println("          CLIENTE SEM CONTAS");
			}
			
			if(gerentes!=null){
				System.out.println("          Gerentes[");
				for(Gerente gerente : gerentes){
					System.out.println("                 NOME:"+gerente.getNome()+" EMAIL:"+gerente.getEmail());
				}
				System.out.println("                ]");
			}else{
				System.out.println("          CLIENTE SEM GERENTES");
			}
		}
		System.out.println("\n\n\n");
		
		//Testar inserir
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println(":::::::>>>>> Testando inserir cliente");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		Random random = new Random();
		int cpf = random.nextInt(1111111111);
		Cliente c = new Cliente(""+cpf,"Teste de insercao SEM endereco");
		Fachada.obterInstancia().cadastrar(c);

		int cpfComEndereco = random.nextInt(1111111111);
		Cliente cComEndereco = new Cliente(""+cpfComEndereco,"Teste de insercao COM endereco",new Endereco("1235813","1235813","Apto FIBONACCI"));
		Fachada.obterInstancia().cadastrar(cComEndereco);
		
		clientes = Fachada.obterInstancia().listarClientes();
		for(Cliente cliente : clientes){
			System.out.print(" Nome: "+cliente.getNome());
			System.out.println(" CPF: "+cliente.getCpf());
			if(cliente.getEndereco()!=null){
				System.out.println("          ENDERECO["+cliente.getEndereco().getCEP()+","+cliente.getEndereco().getNumero()+","+cliente.getEndereco().getComplemento()+"]");
			}else{
				System.out.println("          ENDERECO[******NÃO CADASTRADO*****] ");
			}
		}
		System.out.println("\n\n\n");
		//Testar atualizar
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println(":::::::>>>>> Testando atualizar cliente");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		c.setNome("Teste de atualizacao SEM endereco");
		Fachada.obterInstancia().atualizar(c);

		cComEndereco.setNome("Teste de atualizacao COM endereco");
		cComEndereco.getEndereco().setComplemento("Apto FIBONACCI ATUALIZADO");
		Fachada.obterInstancia().atualizar(cComEndereco);
		
		clientes = Fachada.obterInstancia().listarClientes();
		for(Cliente cliente : clientes){
			System.out.print(" Nome: "+cliente.getNome());
			System.out.println(" CPF: "+cliente.getCpf());
			if(cliente.getEndereco()!=null){
				System.out.println("          ENDERECO["+cliente.getEndereco().getCEP()+","+cliente.getEndereco().getNumero()+","+cliente.getEndereco().getComplemento()+"]");
			}else{
				System.out.println("          ENDERECO[******NÃO CADASTRADO*****] ");
			}
		}
		
		System.out.println("\n\n\n");
		
		//Testar atualizar
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println(":::::::>>>>> Testando Adicionando um endereco a um cliente sem endereco");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		c.setEndereco(new Endereco("NOVO","NOVO","NOVO"));
		Fachada.obterInstancia().atualizar(c);
		
		clientes = Fachada.obterInstancia().listarClientes();
		for(Cliente cliente : clientes){
			System.out.print(" Nome: "+cliente.getNome());
			System.out.println(" CPF: "+cliente.getCpf());
			if(cliente.getEndereco()!=null){
				System.out.println("          ENDERECO["+cliente.getEndereco().getCEP()+","+cliente.getEndereco().getNumero()+","+cliente.getEndereco().getComplemento()+"]");
			}else{
				System.out.println("          ENDERECO[******NÃO CADASTRADO*****] ");
			}
		}
		
		System.out.println("\n\n\n");
		
		//Testar procurar
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println(":::::::>>>>> Testando procurar cliente");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		Cliente c2 = Fachada.obterInstancia().procurarCliente(c.getCpf());		
		System.out.println("Cliente encontrado: "+c2.getNome());
		
		System.out.println("\n\n\n");
		//Testar remover
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println(":::::::>>>>> Testando remover cliente");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		Fachada.obterInstancia().descadastrarCliente(c.getCpf());		
		Fachada.obterInstancia().descadastrarCliente(cComEndereco.getCpf());
		clientes = Fachada.obterInstancia().listarClientes();
		for(Cliente cliente : clientes){
			System.out.print(" Nome: "+cliente.getNome());
			System.out.println(" CPF: "+cliente.getCpf());
			if(cliente.getEndereco()!=null){
				System.out.println("          ENDERECO["+cliente.getEndereco().getCEP()+","+cliente.getEndereco().getNumero()+","+cliente.getEndereco().getComplemento()+"]");
			}else{
				System.out.println("          ENDERECO[******NÃO CADASTRADO*****] ");
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		try {
			testarModuloClientes();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
