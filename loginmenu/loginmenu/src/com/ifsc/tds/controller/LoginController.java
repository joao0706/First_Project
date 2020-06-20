package com.ifsc.tds.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.UsuarioDAO;
import com.ifsc.tds.entity.Usuario;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable {

	@FXML
	private GridPane pnlLogin;

	@FXML
	private Text lblAcesso;

	@FXML
	private Label lblLogin;

	@FXML
	private TextField txtLogin;

	@FXML
	private Label lblSenha;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private HBox pnlBotoes;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnLimpar;

	@FXML
	void entrar(ActionEvent event) throws IOException {
		Usuario user = new Usuario();
		user.setSenha(this.txtSenha.getText());
		user.setLogin(this.txtLogin.getText());
		UsuarioDAO userdao = new UsuarioDAO();

		if (userdao.login(user)) {
			Usuario usuario = new Usuario();
			usuario.setNome(this.txtLogin.getText());

			// Carregando o arquivo da tela de menu
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/Menu.fxml"));
			Parent menuXML = loader.load();

			// Carregando a classe de controle do arquivo da tela de login
			MenuController menuController = loader.getController();
			menuController.setUsuario(usuario);

			Scene menuLayout = new Scene(menuXML);
			Stage menuJanela = new Stage();
			menuJanela.resizableProperty().setValue(Boolean.FALSE);
			menuJanela.setMaximized(true);
			menuJanela.setTitle("Sistema de Contas a Pagar e Receber - IFSC");
			menuJanela.setScene(menuLayout);
			menuJanela.setOnShown(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					menuController.onShow();
				}
			});
			// Atribuindo evento para fechar a tela de login
			menuJanela.setOnCloseRequest(e -> {
				if (menuController.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}
			});
			menuJanela.show();

			// Fechando a janela (palco) de login
			Stage loginJanela = (Stage) this.btnLogin.getScene().getWindow();
			loginJanela.close();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no login");
			alert.setContentText("Login e ou senha inválidos!");

			alert.showAndWait();
			this.txtLogin.requestFocus();
		}
	}

	@FXML
	void limpar(ActionEvent event) {
		this.txtLogin.setText("");
		this.txtSenha.setText("");
		this.txtLogin.requestFocus();
	}

	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair do sistema?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
