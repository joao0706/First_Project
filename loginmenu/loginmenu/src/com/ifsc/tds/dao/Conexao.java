package com.ifsc.tds.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String LOGIN_BANCO = "root";
	private static final String SENHA_BANCO = "";
	private static final String URL_BANCO = "jdbc:mysql://localhost:3306/javafx?autoReconnect=true&useSSL=false";

	public Connection getConnection() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(Conexao.URL_BANCO, Conexao.LOGIN_BANCO, Conexao.SENHA_BANCO);
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados." + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possível carregar a classe do JDBC MySQL." + e);
		} catch (Exception e) {
			System.out.println("Erro geral." + e);
		}
		return conexao;
	}
}
