package qualiti.banco.clientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

 

/**
 * Classe básica que representa uma entidade cliente, com seus dados, validações
 * dos mesmos e suas operações relacionadas.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 */
@Entity(name = "Cliente")
@Table(name = "TB_CLIENTE")
public class Cliente {

	/**
	 * O CPF do cliente.
	 */
	@Id
	@Column(name = "cpf")
	private String cpf;
	/**
	 * O nome do cliente.
	 */
	@Column(name = "nome")
	private String nome;
	/**
	 *Endereco do cliente.
	 */	
	@Transient
	private Endereco endereco;

	/**
	 * O construtor da classe. Inicializa os atributos CPF e nome.
	 *
	 * @param newCpf o valor do CPF.
	 * @param newNome o valor do nome.
	 *
	 * @see Q1 Está certo o construtor atribuir diretamente os valores
	 *         de newCpf e de newNome aos atributos cpf e nome ??
	 */
	public Cliente(String newCpf, String newNome) {

		this.cpf = newCpf;
		this.nome = newNome;
		this.endereco = null;
	}

	/**
	 * O construtor da classe. Inicializa os atributos CPF, nome e endereco.
	 *
	 * @param newCpf o valor do CPF.
	 * @param newNome o valor do nome.
	 * @param newEndereco o valor do endereco.
	 * @see Q1 Está certo o construtor atribuir diretamente os valores
	 *         de newCpf e de newNome aos atributos cpf e nome ??
	 */
	public Cliente(String newCpf, String newNome, Endereco endereco) {

		this.cpf = newCpf;
		this.nome = newNome;
		this.endereco = endereco;
	}
	/**
	 * Retorna o CPF do cliente.
	 *
	 * @return String o CPF do cliente.
	 */
	public String getCpf() {

		return cpf;
	}
	/**
	 * Retorna o nome do cliente.
	 *
	 * @return String o nome do cliente.
	 */
	public String getNome() {

		return nome;
	}
	/**
	 * Retorna o endereco do cliente.
	 *
	 * @return String o endereco do cliente.
	 */
	public Endereco getEndereco() {
		return endereco;
	}
	/**
	 * Atualiza o valor do CPF do cliente.
	 *
	 * @param newCpf o novo valor.
	 *
	 * @see Q1 Será que o CPF de um cliente pode ser nulo ou branco ??
	 */
	public void setCpf(String newCpf) {

		cpf = newCpf;
	}
	/**
	 * Atualiza o valor do nome do cliente.
	 *
	 * @param newNome o novo valor.
	 *
	 * @see Q1 Será que o nome de um cliente pode ser nulo ou branco ??
	 */
	public void setNome(String newNome) {

		nome = newNome;
	}
	/**
	 * Atualiza o valor do endereco do cliente.
	 *
	 * @param endereco o novo valor.
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}