package br.com.qualiti.java.avancado.modulo03.parte1;

import java.util.*;


public class TestCollection {

	
	public void testeListSet(){
		Set<String> conjunto = new HashSet<String>();
	    List<String> lista = new ArrayList<String>();
	    conjunto.add("A");
	    conjunto.add("B");
	    conjunto.add("C");
	    conjunto.add("C");
	    conjunto.add("D");
	    conjunto.add("E");
	    conjunto.add("E");

	    lista.add("A");
	    lista.add("B");
	    lista.add("C");
	    lista.add("C");
	    lista.add("D");
	    lista.add("E");
	    lista.add("E");

	    System.out.println("tamanho do Set: "+conjunto.size());
	    System.out.println("tamanho do List: "+lista.size());

	    System.out.println("segundo da list: "+lista.get(1));
	    System.out.println("segundo do set: "+conjunto.toArray()[1]);

	    System.out.println("set: "+conjunto.toString());
	    System.out.println("list: "+lista.toString());
	}
	
  
  public Map<String,String> criarMapClientes (String[] cpfs) {
      Map<String,String> mapa = new HashMap<String,String>();
      mapa.put(cpfs[0], "joao");
      mapa.put(cpfs[1], "claudia");
      mapa.put(cpfs[2], "silvia");
      return mapa;
  }
  
  public static void main(String[] args) {

    TestCollection testCollection = new TestCollection();

    testCollection.testeListSet();
    
    
    String[] cpfs = { "1", "2", "3", "4", "5" };
    Map<String,String> clientes = testCollection.criarMapClientes(cpfs);
    System.out.println("numero: 1 "+clientes.get("1"));
    System.out.println("numero: 2 "+clientes.get("2"));
    System.out.println("numero: 3 "+clientes.get("3"));

  }

}