package com.ifsc.tds.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.UsuarioDAO;
import com.ifsc.tds.entity.Usuario;

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

public class UsuarioListaController implements Initializable {

	@FXML
	private TableView<Usuario> tbvUsuarios;

	@FXML
	private TableColumn<Usuario, Integer> tbcCodigo;

	@FXML
	private TableColumn<Usuario, String> tbcNome;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblLogin;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblDataCadastro;

	@FXML
	private Label lblNomeValor;

	@FXML
	private Label lblLoginValor;

	@FXML
	private Label lblEmailValor;

	@FXML
	private Label lblDataCadastroValor;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	/**
	 * Lista de usuários.
	 */
	private List<Usuario> listaUsuarios;
	private ObservableList<Usuario> observableListaUsuarios = FXCollections.observableArrayList();
	private UsuarioDAO usuarioDAO;

	public static final String USUARIO_EDITAR = " - Editar";
	public static final String USUARIO_INCLUIR = " - Incluir";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setUsuarioDAO(new UsuarioDAO());
		this.carregarTableViewUsuarios();
		this.selecionarItemTableViewUsuarios(null);

		// Adicionado evento diante de quaisquer alteração na seleção de itens do
		// TableView
		this.tbvUsuarios.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewUsuarios(newValue));
	}

	public void carregarTableViewUsuarios() {

		// preparando as colunas que irão aparecer na tabela
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		// Consulta os usuários da base e depois joga para tela
		this.setListaUsuarios(this.getUsuarioDAO().getAll());
		this.setObservableListaUsuarios(FXCollections.observableArrayList(this.getListaUsuarios()));

		this.tbvUsuarios.setItems(this.getObservableListaUsuarios());
	}

	public void selecionarItemTableViewUsuarios(Usuario usuario) {
		if (usuario != null) {
			this.lblNomeValor.setText(usuario.getNome());
			this.lblLoginValor.setText(usuario.getLogin());
			this.lblEmailValor.setText(usuario.getEmail());
			this.lblDataCadastroValor.setText(usuario.getDataCadastroFormatada());
		} else {
			this.lblNomeValor.setText("");
			this.lblLoginValor.setText("");
			this.lblEmailValor.setText("");
			this.lblDataCadastroValor.setText("");
		}
	}

	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair da lista de farmacêuticos?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ObservableList<Usuario> getObservableListaUsuarios() {
		return observableListaUsuarios;
	}

	public void setObservableListaUsuarios(ObservableList<Usuario> observableListaUsuarios) {
		this.observableListaUsuarios = observableListaUsuarios;
	}

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Usuario usuario = this.tbvUsuarios.getSelectionModel().getSelectedItem();
		if (usuario != null) {
			boolean btnConfirmarClic = this.showTelaUsuarioEditar(usuario, UsuarioListaController.USUARIO_EDITAR);
			if (btnConfirmarClic) {
				this.getUsuarioDAO().update(usuario, null);
				this.carregarTableViewUsuarios();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um farmacêuticos na Tabela!");
			alert.show();
		}
	}

	@FXML
	public void onClickBtnExcluir(ActionEvent event) {
		Usuario usuario = this.tbvUsuarios.getSelectionModel().getSelectedItem();
		if (usuario != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Pergunta");
			alert.setHeaderText("Confirma a exclusão do farmacêuticos " + usuario.getNome() + "?");

			ButtonType buttonTypeNO = ButtonType.NO;
			ButtonType buttonTypeYES = ButtonType.YES;
			alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);

			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.YES) {
				this.getUsuarioDAO().delete(usuario);
				this.carregarTableViewUsuarios();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Por favor, escolha um farmacêuticos na Tabela!");
			alert.show();
		}
	}

	@FXML
	public void onClickBtnIncluir(ActionEvent event) {
		Usuario usuario = new Usuario();
		boolean btnConfirmarClic = this.showTelaUsuarioEditar(usuario, UsuarioListaController.USUARIO_INCLUIR);
		if (btnConfirmarClic) {
	 		this.getUsuarioDAO().save(usuario);
			this.carregarTableViewUsuarios();
		}
	}

	public boolean showTelaUsuarioEditar(Usuario usuario, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/UsuarioEdit.fxml"));
			Parent usuarioEditXML = loader.load();

			// Criando uma janela e colocando o layout do xml nessa janela
			Stage janelaUsuarioEditar = new Stage();
			janelaUsuarioEditar.setTitle("Cadastro de farmacêuticos" + operacao);
			janelaUsuarioEditar.initModality(Modality.APPLICATION_MODAL);
			janelaUsuarioEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene usuarioEditLayout = new Scene(usuarioEditXML);
			janelaUsuarioEditar.setScene(usuarioEditLayout);

			// Setando o cliente no Controller.
			UsuarioEditController usuarioEditController = loader.getController();
			usuarioEditController.setJanelaUsuarioEdit(janelaUsuarioEditar);
			usuarioEditController.setUsuario(usuario);

			// Mostra o Dialog e espera até que o usuário feche
			janelaUsuarioEditar.showAndWait();

			return usuarioEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
