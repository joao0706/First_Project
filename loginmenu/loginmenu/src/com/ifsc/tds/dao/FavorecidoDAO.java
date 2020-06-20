package com.ifsc.tds.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.entity.Favorecido;

public class FavorecidoDAO implements DAO<Favorecido> {

	@Override
	public Favorecido get(Long id) {
		Favorecido favorecido = null;
		String sql = "select * from favorecido where id = ?";
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

				favorecido = new Favorecido();
				// Recupera o id do banco e atribui ele ao objeto
				favorecido.setId(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				favorecido.setNome(rset.getString("nome"));

				// Recupera o login do banco e atribui ele ao objeto
				favorecido.setNumeroTitular(rset.getString("numero_titular"));

				// Recupera a senha do banco e atribui ele ao objeto
				favorecido.setValor(rset.getString("valor"));
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
		return favorecido;
	}

	@Override
	public List<Favorecido> getAll() {
		List<Favorecido> favorecidos = new ArrayList<Favorecido>();
		String sql = "select * from favorecido";
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

				Favorecido favorecido = new Favorecido();
				// Recupera o id do banco e atribui ele ao objeto
				favorecido.setId(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				favorecido.setNome(rset.getString("nome"));

				// Recupera o login do banco e atribui ele ao objeto
				favorecido.setNumeroTitular(rset.getString("numero_titular"));

				// Recupera a senha do banco e atribui ele ao objeto
				favorecido.setValor(rset.getString("valor"));

				favorecidos.add(favorecido);
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
		return favorecidos;
	}

	@Override
	public int save(Favorecido favorecido) {

		String sql = "insert into favorecido (nome, numero_titular, valor) " + " values (?, ?, ?)";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, favorecido.getNome());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setString(2, favorecido.getNumeroTitular());
			// Adicionar o valor do terceiro parâmetro da sql
			stm.setString(3, favorecido.getValor());

			// Executa a sql para inserção dos dados
			System.out.println(stm);
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
	public boolean update(Favorecido favorecido, String[] params) {

		String sql = "update favorecido set nome = ?, numero_titular = ?, valor = ?" + " where id = ?";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, favorecido.getNome());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setString(2, favorecido.getNumeroTitular());
			// Adicionar o valor do terceiro parâmetro da sql
			stm.setString(3, favorecido.getValor());

			stm.setInt(4, favorecido.getId());

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
	public boolean delete(Favorecido favorecido) {
		String sql = "delete from favorecido where id = ?";

		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// SQL (query)
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, favorecido.getId());
			System.out.println("deleteee " + stm);
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

}
