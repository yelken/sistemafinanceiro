package qualiti.banco.gerentes;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Id;

import qualiti.banco.clientes.Cliente;

@Entity
@Table( name="tb_gerente" )
public class Gerente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="fone")
	private String fone;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="email")
	private String email;

	@ManyToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy="gerentes",
			targetEntity=Cliente.class,
			fetch = FetchType.EAGER
	)
	private Collection<Cliente> clientes;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}
}
