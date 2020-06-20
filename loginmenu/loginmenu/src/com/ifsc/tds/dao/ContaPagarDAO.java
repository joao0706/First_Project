package com.ifsc.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.entity.ContaPagar;
import com.ifsc.tds.entity.Favorecido;
import com.ifsc.tds.entity.TipoConta;
import com.ifsc.tds.entity.Usuario;

public class ContaPagarDAO implements DAO<ContaPagar> {

	private UsuarioDAO usuarioDAO;
	private FavorecidoDAO favorecidoDAO;
	private TipoContaDAO tipoContaDAO;

	public ContaPagarDAO() {
		this.setFavorecidoDAO(new FavorecidoDAO());
		this.setTipoContaDAO(new TipoContaDAO());
		this.setUsuarioDAO(new UsuarioDAO());
	}

	@Override
	public ContaPagar get(Long id) {
		return null;
	}

	@Override
	public List<ContaPagar> getAll() {
		List<ContaPagar> contasPagar = new ArrayList<ContaPagar>();

		String sql = "select * from contas_pagar";
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

				ContaPagar contaPagar = new ContaPagar();
				// Recupera o id do banco e atribui ele ao objeto
				contaPagar.setId(rset.getInt("id"));

				// Recupera a descrição do banco e atribui ele ao objeto
				contaPagar.setDescricao(rset.getString("descricao"));

				// Recupera a data de cadastro do banco e atribui ele ao objeto
				contaPagar.setDataCadastro(rset.getDate("data_cadastro"));

				// Recupera a data de vencimento do banco e atribui ele ao objeto
				contaPagar.setDataVencimento(rset.getDate("data_vencimento"));

				// Recupera a data de pagamento do banco e atribui ele ao objeto
				contaPagar.setDataPagamento(rset.getDate("data_pagamento"));

				// Recupera o valor total do banco e atribui ao objeto
				contaPagar.setValorTotal(rset.getDouble("valor_total"));

				// Pesquisa a chave do usuario para atribuir depois o objeto Usuário na conta
				Long id = rset.getLong("usuario_id");
				Usuario usuario = this.getUsuarioDAO().get(id);
				contaPagar.setUsuario(usuario);

				// Pesquisa a chave do favorecido para atribuir depois o objeto Favorecido na
				// conta
				id = rset.getLong("favorecido_id");
				Favorecido favorecido = this.getFavorecidoDAO().get(id);
				contaPagar.setFavorecido(favorecido);

				// Pesquisa a chave do tipo de conta para atribuir depois o objeto Favorecido na
				// conta
				id = rset.getLong("tipo_conta_id");
				TipoConta tipoConta = this.getTipoContaDAO().get(id);
				contaPagar.setTipoConta(tipoConta);

				// Adiciono o contato recuperado, a lista de contatos
				contasPagar.add(contaPagar);
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
		return contasPagar;
	}

	@Override
	public int save(ContaPagar contaPagar) {
		
		String sql = "INSERT INTO `contas_pagar` ( `descricao`, `data_pagamento`, `data_vencimento`, `data_cadastro`, `valor_total`, `usuario_id`, `favorecido_id`, `tipo_conta_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, contaPagar.getDescricao());
			// Adiciona o valor do segundo parâmetro da sql
			stm.setDate(2, contaPagar.getDataPagamento());
			// Adiciona o valor do terceiro parâmetro da sql
			stm.setDate(3, contaPagar.getDataVencimento());
			// Adiciona o valor do quarto parâmetro da sql
			stm.setDate(4, contaPagar.getDataCadastro());
			// Adiciona o valor do quinto parâmetro da sql
			stm.setDouble(5, contaPagar.getValorTotal());
			// Adiciona o valor do sexto parâmetro da sql
			stm.setInt(6, contaPagar.getUsuario().getId());
			// Adiciona o valor do setimo parâmetro da sql
			stm.setInt(7, contaPagar.getFavorecido().getId());
			// Adiciona o valor do oitavo parâmetro da sql
			stm.setInt(8, contaPagar.getTipoConta().getId());
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
	public boolean update(ContaPagar contaPagar, String[] params) {
		String sql = "update contas_pagar set descricao = ?, data_pagamento = ?, data_vencimento = ?, data_cadastro = ?, valor_total = ?";

		Connection conexao = null;
		PreparedStatement stm = null;

		try {
			// Recupera uma conexão com o banco
			conexao = new Conexao().getConnection();

			// Cria uma instância de PreparedStatment, classe usada para executar a operação
			// SQL (query)
			stm = conexao.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			stm.setString(1, contaPagar.getDescricao());
			// Adiciona o valor do segundo parâmetro da sql
			stm.setDate(2, contaPagar.getDataPagamento());
			// Adiciona o valor do terceiro parâmetro da sql
			stm.setDate(3, contaPagar.getDataCadastro());
			// Adiciona o valor do quarto parâmetro da sql
			stm.setDate(4, contaPagar.getDataVencimento());
			// Adiciona o valor do quinto parâmetro da sql
			stm.setDouble(5, contaPagar.getValorTotal());
			// Adiciona o valor do sexto parâmetro da sql
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
	public boolean delete(ContaPagar contaPagar) {
		String sql = "delete from contas_pagar where id = ?";

		// Recupera uma conexão com o banco
		Connection conexao = null;
		// Cria uma instância de PreparedStatment, classe usada para executar a operação
		// SQL (query)
		PreparedStatement stm = null;
		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, contaPagar.getId());
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

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public FavorecidoDAO getFavorecidoDAO() {
		return favorecidoDAO;
	}

	public void setFavorecidoDAO(FavorecidoDAO favorecidoDAO) {
		this.favorecidoDAO = favorecidoDAO;
	}

	public TipoContaDAO getTipoContaDAO() {
		return tipoContaDAO;
	}

	public void setTipoContaDAO(TipoContaDAO tipoContaDAO) {
		this.tipoContaDAO = tipoContaDAO;
	}

}
