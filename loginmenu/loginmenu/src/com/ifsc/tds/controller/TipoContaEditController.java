package com.ifsc.tds.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.tds.entity.TipoConta;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TipoContaEditController implements Initializable {

    @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtStatus;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancela;

    private Stage janelaTipoContaEdit;
	private TipoConta tipoConta;
	private boolean okClick = false;
	
    @FXML
    void clickCancela(ActionEvent event) {
    	this.janelaTipoContaEdit.close();
    }

    @FXML
    void clickOK(ActionEvent event) {
    	if (validarCampos()) {
			this.tipoConta.setNome(this.txtNome.getText());
			this.tipoConta.setStatus(Integer.parseInt(this.txtStatus.getText()));

			this.okClick = true;
			this.janelaTipoContaEdit.close();
		}
    }

	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * Atribui a janela ao controle
	 * 
	 * @param janelaTipoContaEdit
	 */
	public void setJanelaTipoContaEdit(Stage janelaTipoContaEdit) {
		this.janelaTipoContaEdit = janelaTipoContaEdit;
	}

	/**
	 * Atribui a conta que será editado na janela.
	 * 
	 * @param tipoConta
	 */
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;

		this.txtNome.setText(this.tipoConta.getNome());
		this.txtStatus.setText(this.tipoConta.getStatus().toString());
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
			mensagemErros += "Informe o Nome!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			// Mostrando os erros.
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.initOwner(this.janelaTipoContaEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);

			alerta.showAndWait();

			return false;
		}
	}
}