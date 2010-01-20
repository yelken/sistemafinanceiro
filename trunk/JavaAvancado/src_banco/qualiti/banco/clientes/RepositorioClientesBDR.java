package qualiti.banco.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import qualiti.banco.geral.ErroAcessoRepositorioException;
import qualiti.banco.jdbcutil.JDBCConnectionUtil;
/**
 * Implementação da interface que define os métodos de acesso aos dados de cliente
 * em um mecanismo de armazenamento de dados. Esta implementação é realizada através
 * do armazenamento de objetos do tipo cliente em uma tabela de um banco de dados
 * relacional. Neste exemplo, está sendo utilizado o banco de dados Access. O driver
 * de acesso a este banco é a bridge da Sun. É importante notar que esta classe tem
 * diversos atributos referentes ao driver e a conexão com o banco a serem utilizados.
 * Estes atributos são inicializados no construtor da classe e podem ser lidos de um
 * arquivo de parâmetros por quem instancia esta classe. Assim, é possível parametrizar
 * o tipo de banco de dados a ser utilizado sem alterar a implementação deste repositório,
 * desde que o banco suporte a sintaxe dos comandos SQL utilizados nesta implementação.
 *
 * @author Qualiti <a href="mailto:qualiti@qualiti.com.br">qualiti@qualiti.com.br</a>
 *
 * @version 1.0
 *
 * @see qualiti.banco.cliente.Cliente
 * @see qualiti.banco.cliente.RepositorioClientes
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 */
public class RepositorioClientesBDR implements RepositorioClientes {

	/**
	 * Template de comando SQL para insert de cliente.
	 */
	private static final String INSERT_CLI =
		"INSERT INTO tb_cliente (cpf,nome) VALUES (?,?)";

	/**
	 * Template de comando SQL para update de cliente.
	 */
	private static final String UPDATE_CLI =
		"UPDATE tb_cliente SET nome = ? WHERE cpf = ?";
	/**
	 * Template de comando SQL para insert de endereco.
	 */
	private static final String INSERT_END =
		"INSERT INTO tb_endereco (cep,numero,complemento,tb_cliente_cpf) VALUES (?,?,?,?)";

	/**
	 * Template de comando SQL para update de endereco.
	 */
	private static final String UPDATE_END =
		"UPDATE tb_endereco SET cep = ?, numero=?, complemento=?  WHERE tb_cliente_cpf = ?";
	/**
	 * Template de comando SQL para delete de cliente.
	 */
	private static final String DELETE_CLI =
		"DELETE FROM tb_cliente WHERE cpf = ?";
	/**
	 * Template de comando SQL para consulta de endereco.
	 */
	private static final String CONSULTA_END =
		"SELECT COUNT(*) FROM tb_endereco WHERE tb_cliente_cpf = ?";
	/**
	 * Template de comando SQL para consulta de cliente.
	 */
	private static final String CONSULTA_CLI =
		"SELECT COUNT(*) FROM tb_cliente WHERE cpf = ?";
	
	/**
	 * Template de comando SQL para listagem de cliente.
	 */
	private static final String LISTA_CLI =
		"SELECT * FROM tb_cliente order by nome";

	/**
	 * Template de comando SQL para procura de cliente.
	 */
	private static final String PROCURA_CLI =
		"SELECT cpf,nome FROM tb_cliente WHERE cpf = ?";
	/**
	 * Template de comando SQL para procura de cliente.
	 */
	private static final String PROCURA_END =
		"SELECT CEP,numero,complemento FROM tb_endereco WHERE tb_cliente_cpf = ?";
	
	/**
	 * Mensagem que indica erro de acesso ao banco de dados.
	 */
	private static final String MSG_ERRO_ACESSO_BD =
		"Erro de acesso ao banco de dados";


	/**
	 * Atualiza os dados de um cliente no banco de dados relacional.
	 *
	 * @param c o cliente com os dados a serem atualizados.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            atualizados não existe no banco de dados relacional.
	 *
	 * @exception ErroAcessoRepositorioException lançada quando ocorrer erro de acesso
	 *            ao banco de dados relacional.
	 */
	public void atualizar(Cliente c)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		if (c != null) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConexao();
				JDBCConnectionUtil.createTransaction();
				ps = con.prepareStatement(UPDATE_CLI);
				ps.setString(1, c.getNome());
				ps.setString(2, c.getCpf());
				int rows = ps.executeUpdate();
				if (rows <= 0) {
					JDBCConnectionUtil.rollbackTransaction();
					throw new ClienteInexistenteException(c.getCpf());
				}

				salvaEndereco(c);
				
				JDBCConnectionUtil.commitTransaction();
			} catch (SQLException e) {
				try {
					JDBCConnectionUtil.rollbackTransaction();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new ErroAcessoRepositorioException(
					MSG_ERRO_ACESSO_BD,
					e,
					e.getErrorCode());
			} finally {
				fechaRecursos(con, ps, null);
			}
		} else {
			throw new ClienteInexistenteException("");
		}
	}
	
	private void salvaEndereco(Cliente c) throws SQLException {
		if (c != null) {
			if(c.getEndereco()!=null){
				Connection con = getConexao();
				PreparedStatement ps = null;
					ps = con.prepareStatement(CONSULTA_END);
					ps.setString(1, c.getCpf());
					ResultSet rs = ps.executeQuery();
					
					//Se já existe um endereco atualiza
					if (rs.next() && rs.getInt(1)>0){
						ps = con.prepareStatement(UPDATE_END);
					}else { //Caso contrário inclue
						ps = con.prepareStatement(INSERT_END);
					}
					
					ps.setString(1, c.getEndereco().getCEP());
					ps.setString(2, c.getEndereco().getNumero());
					ps.setString(3, c.getEndereco().getComplemento());
					ps.setString(4, c.getCpf());
					ps.executeUpdate();
			}
		} else {
			throw new IllegalArgumentException("");
		}
	}
	/**
	 * Verifica se um cliente existe armazenado no banco de dados relacional.
	 *
	 * @param cpf o CPF do cliente cuja existência no banco de dados relacional
	 *        será verificada.
	 *
	 * @return boolean true se o cliente existir no banco de dados relacional e
	 *         false caso contrário.
	 *
	 * @exception ErroAcessoRepositorioException lançada quando ocorrer erro de acesso
	 *            ao banco de dados relacional.
	 */
	public boolean existe(String cpf) throws ErroAcessoRepositorioException {

		if (cpf != null) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				con = getConexao();
				ps = con.prepareStatement(CONSULTA_CLI);
				ps.setString(1, cpf);
				rs = ps.executeQuery();
				if (rs.next()) {
					int qtdLinhas = rs.getInt(1);
					return (qtdLinhas > 0);
				} else {
					return false;
				}
			} catch (SQLException e) {
				throw new ErroAcessoRepositorioException(
					MSG_ERRO_ACESSO_BD,
					e,
					e.getErrorCode());
			} finally {
				fechaRecursos(con, ps, rs);
			}
		} else {
			return false;
		}
	}
	
	public Vector<Cliente> listar() throws ErroAcessoRepositorioException{
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<Cliente>  clientes = new Vector<Cliente>();
		try {
			con = getConexao();
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(PROCURA_END);
			
			rs = stmt.executeQuery(LISTA_CLI);
			while(rs.next()){
				Cliente c = new Cliente(rs.getString("cpf").trim(),rs.getString("nome").trim());
				clientes.add(c);
				
				//Procura o endereco do cliente
				ps.setString(1,c.getCpf());
				ResultSet rsEndereco =  ps.executeQuery();
				//Se existir seta non cliente consultado
				if (rsEndereco.next()){
					Endereco end = new Endereco(rsEndereco.getString("CEP"),rsEndereco.getString("numero"),rsEndereco.getString("Complemento"));
					c.setEndereco(end);
				}
				
			}
			return clientes;
		} catch (SQLException e) {
			System.out.println(e);
			throw new ErroAcessoRepositorioException(
				MSG_ERRO_ACESSO_BD,
				e,
				e.getErrorCode());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	/**
	 * Inicializa o driver do banco, caso este não esteja inicializado e
	 * retorna uma conexão do banco de dados, obtida a partir do driver manager.
	 *
	 * @return Connection a conexão do banco de dados
	 *
	 */
	private Connection getConexao() throws SQLException {

		Connection con = null; 
		
		con = JDBCConnectionUtil.getConnection();
		
		return con;
	}
	/**
	 * Cadastra os dados de um cliente no banco de dados relacional.
	 *
	 * @param c o cliente com os dados a serem cadastrados.
	 *
	 * @exception ErroAcessoRepositorioException lançada quando ocorrer erro de acesso
	 *            ao banco de dados relacional.
	 *
	 * @see Q1 Por que este método não lança a exceção de cliente já existente ??
	 */
	public void inserir(Cliente c) throws ErroAcessoRepositorioException {

		if (c != null) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConexao();
				JDBCConnectionUtil.createTransaction();
				ps = con.prepareStatement(INSERT_CLI);
				ps.setString(1, c.getCpf());
				ps.setString(2, c.getNome());
				ps.executeUpdate();
				salvaEndereco(c);
				JDBCConnectionUtil.commitTransaction();
			} catch (SQLException e) {
				try {
					JDBCConnectionUtil.rollbackTransaction();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new ErroAcessoRepositorioException(
					MSG_ERRO_ACESSO_BD,
					e,
					e.getErrorCode());
			} finally {
				fechaRecursos(con, ps, null);
			}
		}
	}
	/**
	 * Retorna um cliente armazenado no banco de dados relacional.
	 *
	 * @param cpf o CPF do cliente que será procurado no banco de dados relacional.
	 *
	 * @return Cliente o cliente com seus dados lidos a partir no banco de dados relacional.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ter seus dados
	 *            lidos não existe no no banco de dados relacional.
	 *
	 * @exception ErroAcessoRepositorioException lançada quando ocorrer erro de acesso
	 *            ao banco de dados relacional.
	 */
	public Cliente procurar(String cpf)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {

		if (cpf != null) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				con = getConexao();
				ps = con.prepareStatement(PROCURA_CLI);
				ps.setString(1, cpf);
				rs = ps.executeQuery();
				if (rs.next()) {
					String bdCpf = rs.getString(1);
					String bdNome = rs.getString(2);
					Cliente cli = new Cliente(bdCpf.trim(), bdNome.trim());
					return cli;
				} else {
					throw new ClienteInexistenteException(cpf);
				}
			} catch (SQLException e) {
				throw new ErroAcessoRepositorioException(
					MSG_ERRO_ACESSO_BD,
					e,
					e.getErrorCode());
			} finally {
				fechaRecursos(con, ps, rs);
			}
		} else {
			throw new ClienteInexistenteException("");
		}
	}
	/**
	 * Exclui um cliente armazenado no banco de dados relacional.
	 *
	 * @param cpf o CPF do cliente que será excluído no banco de dados relacional.
	 *
	 * @exception ClienteInexistenteException lançada quando o cliente a ser excluído
	 *            não existe no no banco de dados relacional.
	 *
	 * @exception ErroAcessoRepositorioException lançada quando ocorrer erro de acesso
	 *            ao banco de dados relacional.
	 */
	public void remover(String cpf)
		throws ClienteInexistenteException, ErroAcessoRepositorioException {
		if (cpf != null) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConexao();
				ps = con.prepareStatement(DELETE_CLI);
				ps.setString(1, cpf);
				int rows = ps.executeUpdate();
				if (rows <= 0) {
					throw new ClienteInexistenteException(cpf);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ErroAcessoRepositorioException(
					MSG_ERRO_ACESSO_BD,
					e,
					e.getErrorCode());
			} finally {
				fechaRecursos(con, ps, null);
			}
		} else {
			throw new ClienteInexistenteException("");
		}
	}
	/**
	 * Fecha os recursos passados como parâmetros.
	 *
	 * @param con a conexão a ser fechada.
	 * @param ps o prepared statement a ser fechado.
	 * @param rs o result set a ser fechado.
	 */
	private void fechaRecursos(
		Connection con,
		PreparedStatement ps,
		ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
		}
	}
}
