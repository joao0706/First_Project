package com.ifsc.tds.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ifsc.tds.entity.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

	@Override
	public Usuario get(Long id) {
		Usuario usuario = null;
		String sql = "select * from usuario where id = ?";
		
		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// SQL (query)
		PreparedStatement stm = null;

		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id.intValue());
			rset = stm.executeQuery();

			// Enquanto existir dados (registros) no banco de dados, recupera
			while (rset.next()) {

				usuario = new Usuario();
				// Recupera o id do banco e atribui ele ao objeto
				usuario.setId(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				usuario.setNome(rset.getString("nome"));

				// Recupera o login do banco e atribui ele ao objeto
				usuario.setLogin(rset.getString("login"));

				// Recupera a senha do banco e atribui ele ao objeto
				usuario.setSenha(rset.getString("senha"));

				// Recupera o login do banco e atribui ele ao objeto
				usuario.setEmail(rset.getString("email"));

				// Recupera a data de cadastro do banco e atribui ela ao objeto
				usuario.setDataCadastro(rset.getDate("data_cadastro"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

	public Boolean login(Usuario user) {
		@SuppressWarnings("unused")
		Usuario usuario = null;
		Boolean auth = null;
		String sql = "select * from usuario where login = ?";
		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// sql(query)
		PreparedStatement stm = null;

		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();

			if (user.getLogin() == null || user.getLogin().isEmpty())
				return false;

			stm = conexao.prepareStatement(sql);
			
			String username = user.getLogin();
			
			stm.setString(1, username);
			rset = stm.executeQuery();

			String password = null;

			// Enquanto existir dados(registro) no banco de dados, recupera
			while (rset.next()) {
				password = rset.getString("senha");
			}
			auth = user.getSenha().equals(password);

			System.out.println(auth);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return auth;
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "select * from usuario";
		Connection conexao = null;
		PreparedStatement stm = null;

		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();
			stm = conexao.prepareStatement(sql);

			rset = stm.executeQuery();

			// Enquanto existir dados (registros) no banco de dados, recupera
			while (rset.next()) {

				Usuario usuario = new Usuario();
				// Recupera o id do banco e atribui ele ao objeto
				usuario.setId(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				usuario.setNome(rset.getString("nome"));

				// Recupera o login do banco e atribui ele ao objeto
				usuario.setLogin(rset.getString("login"));

				// Recupera a senha do banco e atribui ele ao objeto
				usuario.setSenha(rset.getString("senha"));

				// Recupera o login do banco e atribui ele ao objeto
				usuario.setEmail(rset.getString("email"));

				// Recupera a data de cadastro do banco e atribui ela ao objeto
				usuario.setDataCadastro(rset.getDate("data_cadastro"));

				// Adiciono o contato recuperado, a lista de contatos
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}

	@Override
	public int save(Usuario usuario) {
		/*
		 * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar na base
		 * de dados
		 */
		String sql = "insert into usuario (nome, login, senha, email, data_cadastro)" + " values (?, ?, ?, ?, ?)";
		System.out.println(sql);
		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, usuario.getNome());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setString(2, usuario.getLogin());
			// Adicionar o valor do terceiro parâmetro da sql
			stm.setString(3, usuario.getSenha());
			// Adicionar o valor do quarto parâmetro da sql
			stm.setString(4, usuario.getEmail());
			// Adiciona o valor do quinto parâmetro da sql
			stm.setDate(5, new Date(System.currentTimeMillis()));

			// Executa a sql para inserção dos dados
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conexões
			try {
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public boolean update(Usuario usuario, String[] params) {
		String sql = "update usuario set nome = ?, login = ?, senha = ?, email = ?" + " where id = ?";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, usuario.getNome());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setString(2, usuario.getLogin());
			// Adicionar o valor do terceiro parâmetro da sql
			stm.setString(3, usuario.getSenha());
			// Adicionar o valor do quarto parâmetro da sql
			stm.setString(4, usuario.getEmail());
			// Adicionar o valor do quinto parâmetro da sql
			stm.setInt(5, usuario.getId());

			// Executa a sql para inserção dos dados
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conexões
			try {
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Usuario usuario) {
		String sql = "delete from usuario where id = ?";

		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// SQL (query)
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, usuario.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<Usuario> getAllRelatorio() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "select id, nome, data_cadastro from usuario";
		Connection conexao = null;
		PreparedStatement stm = null;

		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();
			stm = conexao.prepareStatement(sql);

			rset = stm.executeQuery();

			// Enquanto existir dados (registros) no banco de dados, recupera
			while (rset.next()) {

				Usuario usuario = new Usuario();
				// Recupera o id do banco e atribui ele ao objeto
				usuario.setId(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				usuario.setNome(rset.getString("nome"));

				// Recupera a data de cadastro do banco e atribui ela ao objeto
				usuario.setDataCadastro(rset.getDate("data_cadastro"));

				// Adiciono o contato recuperado, a lista de contatos
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Integer, ArrayList> listaUsuarioCadastroPorMes() {
		String sql = "select count(id) as conta, extract(year from data_cadastro) as ano, extract(month from data_cadastro) as mes from usuario group by ano, mes order by ano, mes";
		Map<Integer, ArrayList> retorno = new HashMap();

		Connection conexao = null;
		PreparedStatement stm = null;

		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();
			stm = conexao.prepareStatement(sql);

			rset = stm.executeQuery();

			while (rset.next()) {
				ArrayList linha = new ArrayList();
				if (!retorno.containsKey(rset.getInt("ano"))) {
					linha.add(rset.getInt("mes"));
					linha.add(rset.getInt("conta"));
					retorno.put(rset.getInt("ano"), linha);
				} else {
					ArrayList linhaNova = retorno.get(rset.getInt("ano"));
					linhaNova.add(rset.getInt("mes"));
					linhaNova.add(rset.getInt("conta"));
				}
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

}
