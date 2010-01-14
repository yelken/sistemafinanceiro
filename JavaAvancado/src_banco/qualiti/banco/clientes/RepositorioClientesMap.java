package qualiti.banco.clientes;

import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;

public class RepositorioClientesMap implements RepositorioClientes {
	
	private Map<String, Cliente> mapaClientes;
	
	public RepositorioClientesMap() {
		this.mapaClientes = new HashMap<String, Cliente>();
	}

	@Override
	public void atualizar(Cliente c) throws ClienteInexistenteException {
		if(existe(c.getCpf())) {
			this.mapaClientes.put(c.getCpf(), c);
		} else {
			throw new ClienteInexistenteException(c.getCpf());
		}
	}

	@Override
	public boolean existe(String cpf) {
		boolean existe = false;
		existe = this.mapaClientes.containsKey(cpf);
		return existe;
	}

	@Override
	public void inserir(Cliente c) {
		this.mapaClientes.put(c.getCpf(), c);
	}

	@Override
	public Cliente procurar(String cpf) throws ClienteInexistenteException {
		Cliente c = null;
		if(existe(cpf)) {
			c = this.mapaClientes.get(cpf);
		} else {
			throw new ClienteInexistenteException(cpf);
		}
		return c;
	}

	@Override
	public void remover(String cpf) throws ClienteInexistenteException {
		if(existe(cpf)) {
			this.mapaClientes.remove(cpf);
		} else {
			throw new ClienteInexistenteException(cpf);
		}
	}

}
