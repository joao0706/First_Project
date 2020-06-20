package com.ifsc.tds.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.CadastroDAO;
import com.ifsc.tds.entity.Cadastro;

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

public class CadastroRemedioListaController implements Initializable {

	@FXML
	private TableView<Cadastro> tbvCadastroRemedio;

	@FXML
	private TableColumn<Cadastro, Integer> tbcCodigo;

	@FXML
	private TableColumn<Cadastro, String> tbcNome;

	@FXML
	private Label lblNomeRemedio;

	@FXML
	private Label lblTarja;

	@FXML
	private Label lblValor;

	@FXML
	private Label lblNomeRemedioValor;

	@FXML
	private Label lblTarjaValor;

	@FXML
	private Label lblValorValor;

	@FXML
	private Label lblEstoque;

	@FXML
	private Label lblEstoqueValor;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	/**
	 * Lista de Cadastro.
	 */
	private List<Cadastro> listaCadastro;
	private ObservableList<Cadastro> observableListaCadastro = FXCollections.observableArrayList();
	private CadastroDAO cadastroDAO;

	public static final String CADASTRO_EDITAR = " - Editar";
	public static final String CADASTRO_INCLUIR = " - Incluir";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setCadastroDAO(new CadastroDAO());
		this.carregarTableViewCadastro();
		this.selecionarItemTableViewCadastro(null);

		// Adicionado evento diante de quaisquer alteração na seleção de itens do
		// TableView
		this.tbvCadastroRemedio.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewCadastro(newValue));
	}

	private void carregarTableViewCadastro() {
		// preparando as colunas que irão aparecer na tabela
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nomeRemedio"));

		// Consulta os tipo de conta da base e depois joga para tela
		this.setListaCadastro(this.getCadastroDAO().getAll());
		this.setObservableListaCadastro(FXCollections.observableArrayList(this.getListaCadastro()));

		this.tbvCadastroRemedio.setItems(this.getObservableListaCadastro());
	}

	private void selecionarItemTableViewCadastro(Cadastro cadastro) {
		if (cadastro != null) {
			this.lblNomeRemedioValor.setText(cadastro.getNomeRemedio().toString());
			this.lblTarjaValor.setText(cadastro.getTarja().toString());
			this.lblValorValor.setText(cadastro.getValor().toString());
			this.lblEstoqueValor.setText(cadastro.getEstoque().toString());

		} else {
			this.lblNomeRemedioValor.setText("");
			this.lblTarjaValor.setText("");
			this.lblValorValor.setText("");
			this.lblEstoqueValor.setText("");
		}

	}

	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair do cadastro de remedio?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public CadastroDAO getCadastroDAO() {
		return cadastroDAO;
	}

	public void setCadastroDAO(CadastroDAO CadastroDAO) {
		this.cadastroDAO = CadastroDAO;
	}

	public List<Cadastro> getListaCadastro() {
		return listaCadastro;
	}

	public void setListaCadastro(List<Cadastro> listaCadastro) {
		this.listaCadastro = listaCadastro;
	}

	public ObservableList<Cadastro> getObservableListaCadastro() {
		return observableListaCadastro;
	}

	public void setObservableListaCadastro(ObservableList<Cadastro> observableListaCadastro) {
		this.observableListaCadastro = observableListaCadastro;
	}

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Cadastro cadastro = this.tbvCadastroRemedio.getSelectionModel().getSelectedItem();
		if (cadastro != null) {
			boolean btnConfirmarClic = this.showTelaCadastroEditar(cadastro,
					CadastroRemedioListaController.CADASTRO_EDITAR);
			if (btnConfirmarClic) {
				this.getCadastroDAO().update(cadastro, null);
				this.carregarTableViewCadastro();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um remédio na Tabela!");
			alert.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Cadastro cadastro = this.tbvCadastroRemedio.getSelectionModel().getSelectedItem();
		if (cadastro != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Pergunta");
			alert.setHeaderText("Confirma a exclusão do medicamento?\n" + cadastro.getNomeRemedio());

			ButtonType buttonTypeNO = ButtonType.NO;
			ButtonType buttonTypeYES = ButtonType.YES;
			alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);

			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.YES) {
				this.getCadastroDAO().delete(cadastro);
				this.carregarTableViewCadastro();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um medicamento na Tabela!");
			alert.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Cadastro cadastro = new Cadastro();
		boolean btnConfirmarClic = this.showTelaCadastroEditar(cadastro,
				CadastroRemedioListaController.CADASTRO_INCLUIR);
		if (btnConfirmarClic) {
			this.getCadastroDAO().save(cadastro);
			this.carregarTableViewCadastro();
		}

	}

	public boolean showTelaCadastroEditar(Cadastro cadastro, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/CadastroRemedioEdit.fxml"));
			Parent cadastroEditXML = loader.load();

			// Criando uma janela e colocando o layout do xml nessa janela
			Stage janelaCadastroEditar = new Stage();
			janelaCadastroEditar.setTitle("Cadastro de medicamentos" + operacao);
			janelaCadastroEditar.initModality(Modality.APPLICATION_MODAL);
			janelaCadastroEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene CadastroEditLayout = new Scene(cadastroEditXML);
			janelaCadastroEditar.setScene(CadastroEditLayout);

			// Setando o cliente no Controller.
			CadastroRemedioEditController cadastroEditController = loader.getController();
			cadastroEditController.setJanelaCadastroEdit(janelaCadastroEditar);
			cadastroEditController.setCadastro(cadastro);
			// Mostra o Dialog e espera até que o usuário feche
			janelaCadastroEditar.showAndWait();

			return cadastroEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
