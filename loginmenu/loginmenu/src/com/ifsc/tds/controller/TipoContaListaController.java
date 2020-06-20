package com.ifsc.tds.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.TipoContaDAO;

import com.ifsc.tds.entity.TipoConta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TipoContaListaController implements Initializable {

	@FXML
	private TableView<TipoConta> tbvTipoConta;

	@FXML
	private TableColumn<TipoConta, Integer> tbcCodigo;

	@FXML
	private TableColumn<TipoConta, Integer> tbcStatus;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblNomeValor;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	/**
	 * Lista de Tipo Conta.
	 */
	private List<TipoConta> listaTipoConta;
	private ObservableList<TipoConta> observableListaTipoConta = FXCollections.observableArrayList();
	private TipoContaDAO tipoContaDAO;

	public static final String USUARIO_EDITAR = " - Editar";
	public static final String USUARIO_INCLUIR = " - Incluir";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setTipoContaDAO(new TipoContaDAO());
		this.carregarTableViewTipoConta();
		this.selecionarItemTableViewTipoConta(null);

		// Adicionado evento diante de quaisquer alteração na seleção de itens do
		// TableView
		this.tbvTipoConta.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewTipoConta(newValue));
	}

	public void carregarTableViewTipoConta() {
		// preparando as colunas que irão aparecer na tabela
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		// Consulta os tipo de conta da base e depois joga para tela
		this.setListaTipoConta(this.getTipoContaDAO().getAll());
		this.setObservableListaTipoConta(FXCollections.observableArrayList(this.getListaTipoConta()));

		this.tbvTipoConta.setItems(this.getObservableListaTipoConta());
	}

	public void selecionarItemTableViewTipoConta(TipoConta tipoConta) {
		if (tipoConta != null) {
			this.lblNomeValor.setText(tipoConta.getNome().toString());
		} else {
			this.lblNomeValor.setText("");
		}
	}

	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair da lista de tipo conta?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public TipoContaDAO getTipoContaDAO() {
		return tipoContaDAO;
	}

	public void setTipoContaDAO(TipoContaDAO TipoContaDAO) {
		this.tipoContaDAO = TipoContaDAO;
	}

	public List<TipoConta> getListaTipoConta() {
		return listaTipoConta;
	}

	public void setListaTipoConta(List<TipoConta> listaTipoConta) {
		this.listaTipoConta = listaTipoConta;
	}

	public ObservableList<TipoConta> getObservableListaTipoConta() {
		return observableListaTipoConta;
	}

	public void setObservableListaTipoConta(ObservableList<TipoConta> observableListaTipoConta) {
		this.observableListaTipoConta = observableListaTipoConta;
	}

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		TipoConta tipoConta = this.tbvTipoConta.getSelectionModel().getSelectedItem();
		if (tipoConta != null) {
			boolean btnConfirmarClic = this.showTelaTipoContaEditar(tipoConta, TipoContaListaController.USUARIO_EDITAR);
			if (btnConfirmarClic) {
				this.getTipoContaDAO().update(tipoConta, null);
				this.carregarTableViewTipoConta();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um usuário na Tabela!");
			alert.show();
		}
	}

	@FXML
	public void onClickBtnExcluir(ActionEvent event) {
		TipoConta tipoConta = this.tbvTipoConta.getSelectionModel().getSelectedItem();
		if (tipoConta != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Pergunta");
			alert.setHeaderText("Confirma a exclusão da conta " + tipoConta.getNome() + " ?");

			ButtonType buttonTypeNO = ButtonType.NO;
			ButtonType buttonTypeYES = ButtonType.YES;
			alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);

			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.YES) {
				this.getTipoContaDAO().delete(tipoConta);
				this.carregarTableViewTipoConta();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha uma conta na Tabela!");
			alert.show();
		}
	}

	@FXML
	public void onClickBtnIncluir(ActionEvent event) {
		TipoConta tipoConta = new TipoConta();
		tipoConta.setNome("");
		tipoConta.setStatus(0);
		boolean btnConfirmarClic = this.showTelaTipoContaEditar(tipoConta, TipoContaListaController.USUARIO_INCLUIR);
		if (btnConfirmarClic) {

			this.getTipoContaDAO().save(tipoConta);
			this.carregarTableViewTipoConta();
		}
	}

	public boolean showTelaTipoContaEditar(TipoConta tipoConta, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/TipoContaEdit.fxml"));
			Parent tipoContaEditXML = loader.load();

			// Criando uma janela e colocando o layout do xml nessa janela
			Stage janelaTipoContaEditar = new Stage();
			janelaTipoContaEditar.setTitle("Cadastro de Conta" + operacao);
			janelaTipoContaEditar.initModality(Modality.APPLICATION_MODAL);
			janelaTipoContaEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene TipoContaEditLayout = new Scene(tipoContaEditXML);
			janelaTipoContaEditar.setScene(TipoContaEditLayout);

			// Setando o cliente no Controller.
			TipoContaEditController tipoContaEditController = loader.getController();
			tipoContaEditController.setJanelaTipoContaEdit(janelaTipoContaEditar);
			tipoContaEditController.setTipoConta(tipoConta);
			// Mostra o Dialog e espera até que o usuário feche
			janelaTipoContaEditar.showAndWait();

			return tipoContaEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}