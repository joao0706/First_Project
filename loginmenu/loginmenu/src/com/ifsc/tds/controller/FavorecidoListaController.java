package com.ifsc.tds.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.FavorecidoDAO;

import com.ifsc.tds.entity.Favorecido;

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


public class FavorecidoListaController implements Initializable {

	@FXML
	private TableView<Favorecido> tbvFavorecidos;

	@FXML
	private TableColumn<Favorecido, Integer> tbcCodigo;

	@FXML
	private TableColumn<Favorecido, String> tbcNome;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblLogin;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblNomeValor;

	@FXML
	private Label lblNumeroTitularValor;

	@FXML
	private Label lblValorValor;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	/**
	 * Lista de usuários.
	 */
	private List<Favorecido> listaFavorecidos;
	private ObservableList<Favorecido> observableListaFavorecidos = FXCollections.observableArrayList();
	private FavorecidoDAO favorecidosDAO;

	public static final String FAVORECIDOS_EDITAR = " - Editar";
	public static final String FAVORECIDOS_INCLUIR = " - Incluir";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setFavorecidosDAO(new FavorecidoDAO());
		this.carregarTableViewFavorecidos();
		this.selecionarItemTableViewFavorecidos(null);

		// Adicionado evento diante de quaisquer alteração na seleção de itens do
		// TableView
		this.tbvFavorecidos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewFavorecidos(newValue));

	}

	private void carregarTableViewFavorecidos() {
		// preparando as colunas que irão aparecer na tabela
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		// Consulta os usuários da base e depois joga para tela
		this.setListaFavorecido(this.getFavorecidoDAO().getAll());

		this.setObservableListaFavorecidos(FXCollections.observableArrayList(this.getListaFavorecidos()));
	
		this.tbvFavorecidos.setItems(this.getObservableListaFavorecidos());

	}

	public void selecionarItemTableViewFavorecidos(Favorecido favorecido) {

		if (favorecido != null) {
		
			this.lblNomeValor.setText(favorecido.getNome());
			this.lblNumeroTitularValor.setText(favorecido.getNumeroTitular());
			this.lblValorValor.setText(favorecido.getValor());
		} else {
			this.lblNomeValor.setText("");
			this.lblNumeroTitularValor.setText("");
			this.lblValorValor.setText("");
		}

	}

	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair da lista de favorecidos?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	private FavorecidoDAO getFavorecidoDAO() {
		return favorecidosDAO;
	}

	private void setFavorecidosDAO(FavorecidoDAO favorecidosDAO) {
		this.favorecidosDAO = favorecidosDAO;

	}

	public void setListaFavorecido(List<Favorecido> listaFavorecidos) {
		this.listaFavorecidos = listaFavorecidos;
	}

	public List<Favorecido> getListaFavorecidos() {
		return listaFavorecidos;
	}

	public ObservableList<Favorecido> getObservableListaFavorecidos() {
		return observableListaFavorecidos;
	}

	private void setObservableListaFavorecidos(ObservableList<Favorecido> observableListaFavorecidos) {
		this.observableListaFavorecidos = observableListaFavorecidos;

	}

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Favorecido favorecido = this.tbvFavorecidos.getSelectionModel().getSelectedItem();
		if (favorecido != null) {
			boolean btnConfirmarClic = this.showTelaFavorecidoEditar(favorecido,
					FavorecidoListaController.FAVORECIDOS_EDITAR);
			if (btnConfirmarClic) {
				this.getFavorecidoDAO().update(favorecido, null);
				this.carregarTableViewFavorecidos();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um usuário na Tabela!");
			alert.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Favorecido favorecido = this.tbvFavorecidos.getSelectionModel().getSelectedItem();
		if (favorecido != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Pergunta");
			alert.setHeaderText("Confirma a exclusão do favorecido " + favorecido.getNome() + " ?");

			ButtonType buttonTypeNO = ButtonType.NO;
			ButtonType buttonTypeYES = ButtonType.YES;
			alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);

			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.YES) {
				this.getFavorecidoDAO().delete(favorecido);
				this.carregarTableViewFavorecidos();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um favorecido na Tabela!");
			alert.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Favorecido favorecido = new Favorecido();
		boolean btnConfirmarClic = this.showTelaFavorecidoEditar(favorecido,
				FavorecidoListaController.FAVORECIDOS_INCLUIR);
		if (btnConfirmarClic) {
			this.getFavorecidoDAO().save(favorecido);
			this.carregarTableViewFavorecidos();
		}
	}

	private boolean showTelaFavorecidoEditar(Favorecido favorecido, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/FavorecidoEdit.fxml"));
			Parent favorecidoEditXML = loader.load();

			// Criando uma janela e colocando o layout do xml nessa janela
			Stage janelaFavorecidoEditar = new Stage();
			janelaFavorecidoEditar.setTitle("Cadastro do favorecido" + operacao);
			janelaFavorecidoEditar.initModality(Modality.APPLICATION_MODAL);
			janelaFavorecidoEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene favorecidoEditLayout = new Scene(favorecidoEditXML);
			janelaFavorecidoEditar.setScene(favorecidoEditLayout);

			// Setando o cliente no Controller.
			FavorecidoEditController favorecidoEditController = loader.getController();
			favorecidoEditController.setJanelaFavorecidoEdit(janelaFavorecidoEditar);
			favorecidoEditController.setFavorecido(favorecido);

			// Mostra o Dialog e espera até que o usuário feche
			janelaFavorecidoEditar.showAndWait();

			return favorecidoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
