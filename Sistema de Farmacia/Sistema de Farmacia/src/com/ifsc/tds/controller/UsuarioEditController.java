package com.ifsc.tds.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.tds.entity.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UsuarioEditController implements Initializable {

	@FXML
	private Label lblNome;

	@FXML
	private TextField txtNome;

	@FXML
	private Label lblLogin;

	@FXML
	private TextField txtLogin;

	@FXML
	private Label lblSenha;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Label lblEmail;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancela;

	private Stage janelaUsuarioEdit;
	private Usuario usuario;
	private boolean okClick = false;

	@FXML
	void clickCancela(ActionEvent event) {
		this.janelaUsuarioEdit.close();
	}

	@FXML
	void clickOK(ActionEvent event) {
		if (validarCampos()) {
			this.usuario.setNome(this.txtNome.getText());
			this.usuario.setLogin(this.txtLogin.getText());
			this.usuario.setSenha(this.txtSenha.getText());
			this.usuario.setEmail(this.txtEmail.getText());

			this.okClick = true;
			this.janelaUsuarioEdit.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * Atribui a janela ao controle
	 * 
	 * @param janelaUsuarioEdit
	 */
	public void setJanelaUsuarioEdit(Stage janelaUsuarioEdit) {
		this.janelaUsuarioEdit = janelaUsuarioEdit;
	}

	/**
	 * Atribui o usuário que será editado na janela.
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

		this.txtNome.setText(usuario.getNome());
		this.txtLogin.setText(usuario.getLogin());
		this.txtSenha.setText(usuario.getSenha());
		this.txtEmail.setText(usuario.getEmail());
	}

	/**
	 * Retorna verdadeiro se o usuário clicou o botão OK, senão retorna false.
	 * 
	 * @return boolean
	 */
	public boolean isOkClick() {
		return okClick;
	}

	/**
	 * Valida os dados digitados nos campos da tela.
	 * 
	 * @return true se os dados forem válidos
	 */
	private boolean validarCampos() {
		String mensagemErros = "";

		if (this.txtNome.getText() == null || this.txtNome.getText().length() == 0) {
			mensagemErros += "Informe o nome!\n";
		}
		if (this.txtLogin.getText() == null || this.txtLogin.getText().length() == 0) {
			mensagemErros += "Informe o login!\n";
		}
		if (this.txtSenha.getText() == null || this.txtSenha.getText().length() == 0) {
			mensagemErros += "Informe a senha!\n";
		}
		if (this.txtEmail.getText() == null || this.txtEmail.getText().length() == 0) {
			mensagemErros += "Informe a senha!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			// Mostrando os erros.
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.initOwner(this.janelaUsuarioEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);

			alerta.showAndWait();

			return false;
		}
	}

}
