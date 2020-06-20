package com.ifsc.tds.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import com.ifsc.tds.entity.Cadastro;

public class CadastroDAO implements DAO<Cadastro> {

	@Override
	public Cadastro get(Long id) {
		Cadastro cadastro = null;
		String sql = "select from cadastro_remedio where id = ?";

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

				cadastro = new Cadastro();
				// Recupera o id do banco e atribui ele ao objeto
				cadastro.setId(rset.getInt("id"));

				// Recupera a tarja do banco e atribui ele ao objeto
				cadastro.setTarja(rset.getString("tarja"));

				// Recupera o nome do remedio do banco e atribui ele ao objeto
				cadastro.setNomeRemedio(rset.getString("nomeRemedio"));

				// Recupera o valor do banco e atribui ele ao objeto
				cadastro.setValor(rset.getDouble("valor"));

				// Recupera a quantidade de estoque do banco e atribui ele ao objeto
				cadastro.setEstoque(rset.getInt("estoque"));
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
		return cadastro;
	}

	@Override
	public List<Cadastro> getAll() {
		List<Cadastro> cadastros = new ArrayList<Cadastro>();

		String sql = "select * from cadastro_remedio";
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

				Cadastro cadastro = new Cadastro();
				// Recupera o id do banco e atribui ele ao objeto
				cadastro.setId(rset.getInt("id"));

				// Recupera a tarja do banco e atribui ele ao objeto
				cadastro.setTarja(rset.getString("tarja"));

				// Recupera o nome do remedio do banco e atribui ele ao objeto
				cadastro.setNomeRemedio(rset.getString("nome_remedio"));

				// Recupera o valor do banco e atribui ele ao objeto
				cadastro.setValor(rset.getDouble("valor_remedio"));

				// Recupera a quantidade de estoque do banco e atribui ele ao objeto
				cadastro.setEstoque(rset.getInt("estoque"));

				// Adiciono o contato recuperado, a lista de contatos
				cadastros.add(cadastro);
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
		return cadastros;
	}

	@Override
	public int save(Cadastro cadastro) {
		/*
		 * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar na base
		 * de dados
		 */
		String sql = "insert into cadastro_remedio ( tarja, nome_remedio, valor_remedio, estoque)"
				+ " values (?, ?, ?, ?)";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, cadastro.getTarja());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setString(2, cadastro.getNomeRemedio());
			// Adicionar o valor do terceiro parâmetro da sql
			stm.setDouble(3, cadastro.getValor());
			// Adicionar o valor no quarto parâmetro da sql
			stm.setInt(4, cadastro.getEstoque());

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
	public boolean update(Cadastro cadastro, String[] params) {
		String sql = "update cadastro_remedio set  tarja = ?, nome_remedio = ?, valor_remedio = ?, estoque = ?"
				+ " where id = ?";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, cadastro.getTarja());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setString(2, cadastro.getNomeRemedio());
			// Adicionar o valor do terceiro parâmetro da sql
			stm.setDouble(3, cadastro.getValor());
			// Adicionar o valor no quarto parâmetro da sql
			stm.setInt(4, cadastro.getEstoque());
			// Adicionar o valor do quinto parâmetro da sql
			stm.setInt(5, cadastro.getId());

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
	public boolean delete(Cadastro cadastro) {
		String sql = "delete from cadastro_remedio where id = ?";

		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// SQL (query)
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, cadastro.getId());
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

	public List<Cadastro> getAllRelatorio() {
		List<Cadastro> cadastros = new ArrayList<Cadastro>();

		String sql = "select id, nome_remedio, codigo_remedio, valor-remedio, estoque from cadastro_remedio";
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

				Cadastro cadastro = new Cadastro();
				// Recupera o id do banco e atribui ele ao objeto
				cadastro.setId(rset.getInt("id"));

				// Recupera a tarja do banco e atribui ele ao objeto
				cadastro.setTarja(rset.getString("tarja"));

				// Recupera o nome do remedio do banco e atribui ela ao objeto
				cadastro.setNomeRemedio(rset.getString("nomeRemedio"));

				// Recupera o valor do banco e atribui ele ao objeto
				cadastro.setValor(rset.getDouble("valor"));

				// Recupera a quantidade de estoque do banco e atribui ele ao objeto
				cadastro.setEstoque(rset.getInt("estoque"));

				// Adiciono o contato recuperado, a lista de contatos
				cadastros.add(cadastro);
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
		return cadastros;
	}

}
