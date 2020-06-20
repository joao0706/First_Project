package com.ifsc.tds.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.tds.entity.Cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroRemedioEditController {

	@FXML
	private Label lblNomeRemedio;

	@FXML
	private TextField txtNomeRemedio;

	@FXML
	private Label lblTarja;

	@FXML
	private TextField txtTarja;

	@FXML
	private Label lblValor;

	@FXML
	private TextField txtValor;

	@FXML
	private Label lblEstoque;

	@FXML
	private TextField txtEstoque;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancela;

	private Stage janelaCadastroEdit;
	private Cadastro cadastro;
	private boolean okClick = false;

	@FXML
	void clickCancela(ActionEvent event) {
		this.janelaCadastroEdit.close();
	}

	@FXML
	void clickOK(ActionEvent event) {
		if (validarCampos()) {
			this.cadastro.setNomeRemedio(this.txtNomeRemedio.getText());
			this.cadastro.setTarja(this.txtTarja.getText());
			this.cadastro.setValor(Double.parseDouble(this.txtValor.getText()));
			this.cadastro.setEstoque(Integer.parseInt(this.txtEstoque.getText()));

			this.okClick = true;
			this.janelaCadastroEdit.close();
		}
	}

	public void Initializable(URL location, ResourceBundle resources) {
	}

	/**
	 * Atribui a janela ao controle
	 * 
	 * @param janelaCadastroEditar
	 */

	public void setJanelaCadastroEdit(Stage janelaCadastroEditar) {
		this.janelaCadastroEdit = janelaCadastroEditar;

	}

	/**
	 * Atribui o cadastro que será editado na janela
	 * 
	 * @param cadastro
	 */

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;

		this.txtNomeRemedio.setText(this.cadastro.getNomeRemedio());
		this.txtTarja.setText(this.cadastro.getTarja());
		this.txtValor.setText(String.valueOf(this.cadastro.getValor()));
		this.txtEstoque.setText(String.valueOf(this.cadastro.getEstoque()));
	}

	/**
	 * Retorna verdadeirose o usuario clicou o botao OK senão retorna false.
	 * 
	 * @return boolean
	 */

	public boolean isOkClick() {
		return okClick;
	}

	/**
	 * Valida os dados digitados nos campos da tela
	 * 
	 * @return true se os dados forem válidos
	 */
	private boolean validarCampos() {
		String mensagemErros = "";

		if (this.txtNomeRemedio.getText() == null || this.txtNomeRemedio.getText().length() == 0) {
			mensagemErros += "Informe o Nome!\n";
		}
		if (this.txtTarja.getText() == null || this.txtTarja.getText().length() == 0) {
			mensagemErros += "Informe a Tarja!\n";
		}
		if (this.txtValor.getText() == null || this.txtValor.getText().length() == 0) {
			mensagemErros += "Informe o Valor!\n";
		}
		
		if (this.txtEstoque.getText() == null || this.txtEstoque.getText().length() == 0) {
			mensagemErros += "Informe a quantidade de estoque!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			// Mostrando os erros.
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.initOwner(this.janelaCadastroEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);

			alerta.showAndWait();

			return false;
		}
	}
}
