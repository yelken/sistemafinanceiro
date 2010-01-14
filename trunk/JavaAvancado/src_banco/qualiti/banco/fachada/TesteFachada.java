package qualiti.banco.fachada;
import qualiti.banco.clientes.*;
import qualiti.banco.contas.*;

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

	public static void main(String[] args) {
		Fachada fachada  = Fachada.obterInstancia();
		
		Cliente c = new Cliente("11", "João");
		ContaAbstrata c1 = new Conta("1", 100, c);
		ContaAbstrata c2 = new Poupanca("2", 10, c);
		
		try {
		
		fachada.cadastrar(c);
		fachada.cadastrar(c1);
		fachada.cadastrar(c2);
		Cliente cli1 = fachada.procurarCliente("11");
		System.out.println("Nome do cliente: "+cli1.getNome());
		
		ContaAbstrata con1 = fachada.procurarConta("1");
		System.out.println("Saldo da conta de número "+con1.getNumero()+": "+con1.getSaldo());
		
		fachada.creditar("2", 300);
		ContaAbstrata con2 = fachada.procurarConta("2");
		((Poupanca)con2).renderJuros(0.008);
		System.out.println("Saldo da conta de número "+con2.getNumero()+": "+con2.getSaldo());
		
		con2.debitar(1000);
		fachada.atualizar(con2);
		con2 = fachada.procurarConta("2");
		System.out.println("Saldo atualizado da conta de número "+con2.getNumero()+": "+con2.getSaldo());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
