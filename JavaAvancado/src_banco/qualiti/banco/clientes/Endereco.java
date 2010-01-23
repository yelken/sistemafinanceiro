package qualiti.banco.clientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe básica que representa uma entidade endereco, com seus dados, validações
 * dos mesmos e suas operações relacionadas.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 */
@Entity
@Table( name="tb_endereco" )
public class Endereco {

	/** To make hibernate happy */
	@Id
	@Column ( name="tb_cliente_cpf" )
	private String clienteCPF;
	
	/** CEP do endereco. */
	@Column ( name="CEP" )
	private String CEP;
	
	/** Número do endereco. */
	@Column ( name="numero" )
	private String numero;
	
	/** Complemento textual do endereco. */
	@Column ( name="complemento" )
	private String complemento;

	/** Default constructor to make hibernate happy. */
	public Endereco(){
		this.clienteCPF = null;
		this.CEP = null;
		this.numero = null;
		this.complemento = null;
	}
	
	public Endereco(String CEP, String numero, String complemento){
		this.CEP = CEP;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cep) {
		CEP = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getClienteCPF() {
		return clienteCPF;
	}

	public void setClienteCPF(String clienteCPF) {
		this.clienteCPF = clienteCPF;
	}
}
