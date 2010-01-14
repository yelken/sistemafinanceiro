package br.com.qualiti.java.avancado.modulo03.parte1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCollection {
	
	public Map<String, String> criarMapClientes(String[] cpfs) {
		Map<String, String> mapaClientes = new HashMap<String, String>();
		int i = 10;
		for (String cpf : cpfs) {
			mapaClientes.put(cpf, "Meu Cliente " + i);
			i--;
		}
		return mapaClientes;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Criando List e Set...");
		List<String> minhaLista = new ArrayList<String>();
		Set<String> meuConjunto = new HashSet<String>();
		
		minhaLista.add("A");
		minhaLista.add("B");
		minhaLista.add("C");
		minhaLista.add("D");
		minhaLista.add("E");
		minhaLista.add("F");
		minhaLista.add("G");
		minhaLista.add("C");
		minhaLista.add("D");
		
		meuConjunto.add("A");
		meuConjunto.add("B");
		meuConjunto.add("C");
		meuConjunto.add("D");
		meuConjunto.add("E");
		meuConjunto.add("F");
		meuConjunto.add("G");
		meuConjunto.add("C");
		meuConjunto.add("D");
		
		System.out.println("Tamanho da List minhaLista é " + minhaLista.size());
		System.out.println("Tamanho do Set meuConjunto é " + meuConjunto.size());

		String segundoEmentoLista = minhaLista.get(1);
		String segundoEmentoConjunto = (String) meuConjunto.toArray()[1];
		
		System.out.println("Segundo elemento de minhaLista é " + segundoEmentoLista);
		System.out.println("Segundo elemento de meuConjunto é " + segundoEmentoConjunto);
		
		System.out.println("minhaLista tem os seguintes elementos: " + minhaLista);
		System.out.println("meuConjunto tem os seguintes elementos: " + meuConjunto);
		
		System.out.println("Criando Map de clientes");
		
		TestCollection testCollection = new TestCollection();
		
		String[] cpfs = { "004", "003", "002", "001", "005" };
		
		Map<String, String> mapaClientes = testCollection.criarMapClientes(cpfs);
		
		System.out.println("Cliente com cpf 001 é " + mapaClientes.get("001"));
		System.out.println("Cliente com cpf 002 é " + mapaClientes.get("002"));
		System.out.println("Cliente com cpf 003 é " + mapaClientes.get("003"));
		
	}

}
