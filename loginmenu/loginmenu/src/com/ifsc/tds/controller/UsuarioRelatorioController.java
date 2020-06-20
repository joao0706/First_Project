package com.ifsc.tds.controller;

import java.util.List;

import com.ifsc.tds.dao.UsuarioDAO;
import com.ifsc.tds.entity.Usuario;

public class UsuarioRelatorioController {

	public static final String RELATORIO_ARQUIVO = "listagem_usuario.pdf";
	public static final String RELATORIO_TITULO = "Listagem de usuários";
	public static final String[] RELATORIO_CABECALHO = { "Código", "Nome", "Data de Cadastro" };

	private UsuarioDAO usuarioDAO;

	public UsuarioRelatorioController() {
		this.setUsuarioDAO(new UsuarioDAO());
	}

	public List<Usuario> dadosRelatorio() {
		return this.getUsuarioDAO().getAllRelatorio();
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
