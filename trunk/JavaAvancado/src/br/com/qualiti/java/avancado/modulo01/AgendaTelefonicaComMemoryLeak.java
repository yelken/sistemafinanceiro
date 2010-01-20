package br.com.qualiti.java.avancado.modulo01;

public class AgendaTelefonicaComMemoryLeak {

	
	private String agendaTelefonica[];

	private int numeroRegistros;	
	
	private AgendaTelefonicaComMemoryLeak(){
		agendaTelefonica = new String[10];
		numeroRegistros  = 0;
	}
	
	public void adicionarContato(String contato){
		if(numeroRegistros<agendaTelefonica.length){
			agendaTelefonica[numeroRegistros++]=contato;
		}
	}
	
	public void removerContato(String contato){
		int found = -1;
		
		for(int i=0; i<numeroRegistros; i++){
			if(agendaTelefonica[i].equals(contato)){
				found = i;
			}
		}
		
		if(found!=-1){
			numeroRegistros--;
			for(int i=found; i<numeroRegistros; i++){
				agendaTelefonica[i] = agendaTelefonica[i+1];
			}
		}	
		
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		
		for(int i=0; i<agendaTelefonica.length; i++) {
			if (i<numeroRegistros){
				buffer.append(agendaTelefonica[i]).append("\t").append(agendaTelefonica[i]).append("\n");
			}else{
				buffer.append("LEAK      ").append("\t").append(agendaTelefonica[i]).append("\n");
			}
		}
		
		return buffer.toString();
	}
	
	public static void main(String args[]){
		AgendaTelefonicaComMemoryLeak ag = new AgendaTelefonicaComMemoryLeak();
		
		System.out.println(ag);
		
		ag.adicionarContato("A");
		ag.adicionarContato("B");
		ag.adicionarContato("C");
		ag.adicionarContato("D");
		ag.adicionarContato("E");
		
		System.out.println(ag);
		
		ag.adicionarContato("F");
		ag.adicionarContato("G");
		ag.adicionarContato("H");
		ag.adicionarContato("I");
		ag.adicionarContato("J");
		ag.adicionarContato("K");
		
		System.out.println(ag);
		
		ag.removerContato("B");
		ag.removerContato("H");
		
		
		System.out.println(ag);
	}
}
