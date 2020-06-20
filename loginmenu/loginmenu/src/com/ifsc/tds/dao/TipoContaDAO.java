package com.ifsc.tds.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.entity.TipoConta;

public class TipoContaDAO implements DAO<TipoConta> {

	@Override
	public TipoConta get(Long id) {
		TipoConta tipoConta = null;
		String sql = "select * from tipo_conta where id = ?";

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

				tipoConta = new TipoConta();
				// Recupera o id do banco e atribui ele ao objeto
				tipoConta.setId(rset.getInt("id"));

				// Recupera a descrição do banco e atribui ele ao objeto
				tipoConta.setNome(rset.getString("nome"));

				// Recupera o login do banco e atribui ele ao objeto
				tipoConta.setStatus(rset.getInt("status"));

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
		return tipoConta;
	}

	@Override
	public List<TipoConta> getAll() {
		List<TipoConta> tipoConta = new ArrayList<TipoConta>();

		String sql = "select * from tipo_conta";
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
				TipoConta TipoConta = new TipoConta();
				// Recupera o id do banco e atribui ele ao objeto
				TipoConta.setId(rset.getInt("id"));

				// Recupera o nome do banco e atribui ele ao objeto
				TipoConta.setNome(rset.getString("nome"));

				// Recupera o login do banco e atribui ele ao objeto
				TipoConta.setStatus(rset.getInt("status"));

				// Recupera a data de cadastro do banco e atribui ela ao objeto

				// Adiciono o contato recuperado, a lista de contatos
				tipoConta.add(TipoConta);
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
		return tipoConta;
	}

	@Override
	public int save(TipoConta tipoConta) {

		String sql = "insert into tipo_conta (nome, status)" + " values (?, ?)";
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
			stm.setString(1, tipoConta.getNome());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setInt(2, tipoConta.getStatus());
			// Adiciona o valor do quarto parâmetro da sql
			// stm.setDate(4, new Date(System.currentTimeMillis()));

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
	public boolean update(TipoConta tipoConta, String[] params) {
		String sql = "UPDATE tipo_conta SET nome = ?, status = ? WHERE id = ? ";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, tipoConta.getNome());
			// Adicionar o valor do segundo parâmetro da sql
			stm.setInt(2, tipoConta.getStatus());
			// Adiciona o valor do quarto parâmetro da sql
			stm.setInt(3, tipoConta.getId());
			stm.executeUpdate();
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
	public boolean delete(TipoConta tipoConta) {

		String sql = "delete from tipo_conta where id = ?";

		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// SQL (query)
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, tipoConta.getId());
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
