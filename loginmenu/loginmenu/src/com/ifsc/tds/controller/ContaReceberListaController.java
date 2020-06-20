package com.ifsc.tds.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.ContaReceberDAO;
import com.ifsc.tds.entity.ContaReceber;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ContaReceberListaController implements Initializable {

	@FXML
	private TableView<ContaReceber> tbvContasReceber;

	@FXML
	private TableColumn<ContaReceber, Integer> tbcCodigo;

	@FXML
	private Label lblDescricao;

	@FXML
	private Label lblLogin;

	@FXML
	private Label lblValor;

	@FXML
	private Label lblUsuarioValor;

	@FXML
	private Label lblNomeFavorecidoValor;

	@FXML
	private Label lblTipoContaValor;

	@FXML
	private Label lblDescricaoValor;

	@FXML
	private Label lblDataCadastroValor;

	@FXML
	private Label lblDataPagamentoValor;

	@FXML
	private Label lblDataVencimentoValor;

	@FXML
	private Label lblValorValor;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	/**
	 * Lista de contas a pagar.
	 */
	private List<ContaReceber> listaContasReceber;
	private ObservableList<ContaReceber> observableListaContasReceber = FXCollections.observableArrayList();
	private ContaReceberDAO contaReceberDAO;

	public static final String CONTA_RECEBER_EDITAR = " - Editar";
	public static final String CONTA_RECEBER_INCLUIR = " - Incluir";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setContaReceberDAO(new ContaReceberDAO());
		this.carregarTableViewContasReceber();
		this.selecionarItemTableViewContasReceber(null);

		// Adicionado evento diante de quaisquer alteração na seleção de itens do
		// TableView
		this.tbvContasReceber.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewContasReceber(newValue));

	}

	public void carregarTableViewContasReceber() {

		// preparando as colunas que irão aparecer na tabela
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));

		// Consulta os usuários da base e depois joga para tela
		this.setListaContasReceber(this.getContaReceberDAO().getAll());
		this.setObservableListaContasReceber(FXCollections.observableArrayList(this.getListaContasReceber()));

		this.tbvContasReceber.setItems(this.getObservableListaContasReceber());
	}

	public void selecionarItemTableViewContasReceber(ContaReceber contaReceber) {

		if (contaReceber != null) {
			this.lblDescricaoValor.setText(contaReceber.getDescricao());
			this.lblUsuarioValor.setText(contaReceber.getUsuario().getNome());
			this.lblNomeFavorecidoValor.setText(contaReceber.getFavorecido().getNome());
			this.lblDataCadastroValor.setText(contaReceber.getDataCadastro().toString());
			this.lblDataPagamentoValor.setText(contaReceber.getDataPagamento().toString());
			this.lblDataVencimentoValor.setText(contaReceber.getDataVencimento().toString());
			this.lblValorValor.setText(contaReceber.getValorTotal().toString());
			this.lblTipoContaValor.setText(contaReceber.getTipoConta().toString());
		} else {
			this.lblDescricaoValor.setText("");
			this.lblUsuarioValor.setText("");
			this.lblNomeFavorecidoValor.setText("");
			this.lblDataCadastroValor.setText("");
			this.lblDataPagamentoValor.setText("");
			this.lblDataVencimentoValor.setText("");
			this.lblValorValor.setText("");
			this.lblTipoContaValor.setText("");
		}
	}

	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair da lista de contas a receber?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public ContaReceberDAO getContaReceberDAO() {
		return contaReceberDAO;
	}

	public void setContaReceberDAO(ContaReceberDAO contaReceberDAO) {
		this.contaReceberDAO = contaReceberDAO;
	}

	public List<ContaReceber> getListaContasReceber() {
		return listaContasReceber;
	}

	public void setListaContasReceber(List<ContaReceber> listaContasReceber) {
		this.listaContasReceber = listaContasReceber;
	}

	public ObservableList<ContaReceber> getObservableListaContasReceber() {
		return observableListaContasReceber;
	}

	public void setObservableListaContasReceber(ObservableList<ContaReceber> observableListaContasReceber) {
		this.observableListaContasReceber = observableListaContasReceber;
	}

	@FXML
	public void onClickBtnEditar(ActionEvent event) {
		ContaReceber contaReceber = this.tbvContasReceber.getSelectionModel().getSelectedItem();
		if (contaReceber != null) {
			boolean btnConfirmarClic = this.showTelaContaReceberEditar(contaReceber,
					ContaReceberListaController.CONTA_RECEBER_EDITAR);
			if (btnConfirmarClic) {
				this.getContaReceberDAO().update(contaReceber, null);
				this.carregarTableViewContasReceber();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha uma conta na Tabela!");
			alert.show();
		}
	}

	@FXML
	public void onClickBtnExcluir(ActionEvent event) {
		ContaReceber contaReceber = this.tbvContasReceber.getSelectionModel().getSelectedItem();
		if (contaReceber != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Pergunta");
			alert.setHeaderText("Confirma a exclusão do usuário " + contaReceber.getUsuario() + " ?");

			ButtonType buttonTypeNO = ButtonType.NO;
			ButtonType buttonTypeYES = ButtonType.YES;
			alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);

			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.YES) {
				this.getContaReceberDAO().delete(contaReceber);
				this.carregarTableViewContasReceber();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um usuário na Tabela!");
			alert.show();
		}
	}

	@FXML
	public void onClickBtnIncluir(ActionEvent event) {
		ContaReceber contaReceber = new ContaReceber();
		boolean btnConfirmarClic = this.showTelaContaReceberEditar(contaReceber,
				ContaReceberListaController.CONTA_RECEBER_INCLUIR);
		if (btnConfirmarClic) {
			this.getContaReceberDAO().save(contaReceber);
			this.carregarTableViewContasReceber();
		}
	}

	public boolean showTelaContaReceberEditar(ContaReceber contaReceber, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/ContaReceberEdit.fxml"));
			Parent contaReceberEditXML = loader.load();

			// Criando uma janela e colocando o layout do xml nessa janela
			Stage janelaContaReceberEditar = new Stage();
			janelaContaReceberEditar.setTitle("Cadastro de conta a receber" + operacao);
			janelaContaReceberEditar.initModality(Modality.APPLICATION_MODAL);
			janelaContaReceberEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene contaReceberEditLayout = new Scene(contaReceberEditXML);
			janelaContaReceberEditar.setScene(contaReceberEditLayout);

			// Setando o cliente no Controller.
			ContaReceberEditController contaReceberEditController = loader.getController();
			contaReceberEditController.setJanelaContaReceberEdit(janelaContaReceberEditar);
			contaReceberEditController.setContaReceber(contaReceber);

			// Mostra o Dialog e espera até que o usuário feche
			janelaContaReceberEditar.showAndWait();

			return contaReceberEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
