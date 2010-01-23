package qualiti.banco.clientes;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import qualiti.banco.contas.ContaAbstrata;
import qualiti.banco.gerentes.Gerente;




/**
 * Classe básica que representa uma entidade cliente, com seus dados, validações
 * dos mesmos e suas operações relacionadas.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 */
@Entity
@Table( name="tb_cliente" )
@NamedQuery(name="cliente.listar", query="from Cliente c order by c.nome asc")
public class Cliente {

	/**
	 * O CPF do cliente.
	 */
	@Id
	@Column ( name="cpf" )
	private String cpf;
	/**
	 * O nome do cliente.
	 */
	@Column ( name="nome" )
	private String nome;
	/**
	 *Endereco do cliente.
	 */	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Endereco endereco;
	
	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Collection<ContaAbstrata> contas;

	@ManyToMany(
			targetEntity=qualiti.banco.gerentes.Gerente.class,
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY
	)
	@JoinTable(
			name="tb_gerentes_cliente",
			joinColumns={@JoinColumn(name="tb_cliente_cpf")},
			inverseJoinColumns={@JoinColumn(name="tb_gerente_id")}
	)
	private Collection<Gerente> gerentes;
	
	/**
	 * O construtor default da classe. Usado pelo hibernate.
	 */
	public Cliente() {

		this.cpf = null;
		this.nome = null;
		this.endereco = null;
	}	
	
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
		setEndereco(endereco);
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
		this.endereco.setClienteCPF(this.getCpf());
	}

	public Collection<ContaAbstrata> getContas() {
		return contas;
	}

	public void setContas(Collection<ContaAbstrata> contas) {
		this.contas = contas;
	}

	public Collection<Gerente> getGerentes() {
		return gerentes;
	}

	public void setGerentes(Collection<Gerente> gerentes) {
		this.gerentes = gerentes;
	}
}