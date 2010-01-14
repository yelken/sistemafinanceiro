import br.org.qualiti.bank.Fachada;
import br.org.qualiti.bank.cliente.excecoes.ClienteInexistenteException;
import br.org.qualiti.bank.cliente.model.Cliente;
import br.org.qualiti.bank.cliente.model.Endereco;
import br.org.qualiti.bank.conta.excecoes.ContaExistenteException;
import br.org.qualiti.bank.conta.model.Conta;


public class Main {

	public static void main(String[] args) {
		Fachada f = Fachada.getInstace();
		
		Endereco end = new Endereco("00001","TRE");
		Cliente cliente = new Cliente("00001","Yelken", end);
		
		
		System.out.println("Primeira inserção");
		try {
			f.inserirConta(new Conta("00-1",100.00,cliente));
			System.out.println("OK 1");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Segunda inserção");
		try {
			f.inserirConta(new Conta("00-2",100.00,cliente));
			System.out.println("OK 2");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
