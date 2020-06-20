package com.ifsc.tds.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.FavorecidoDAO;
import com.ifsc.tds.dao.TipoContaDAO;
import com.ifsc.tds.dao.UsuarioDAO;
import com.ifsc.tds.entity.ContaPagar;
import com.ifsc.tds.entity.Favorecido;
import com.ifsc.tds.entity.TipoConta;
import com.ifsc.tds.entity.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContaPagarEditController implements Initializable {


    @FXML
    private Label lblDescricao;

    @FXML
    private TextField txtDescricao;

    @FXML
    private Label lblDataCadastro;

    @FXML
    private DatePicker dtpDataCadastro;

    @FXML
    private Label lblUsuario;

    @FXML
    private ComboBox<Usuario> cbxUsuario;

    @FXML
    private Label lblDataPagamento;

    @FXML
    private DatePicker dtpDataPagamento;

    @FXML
    private Label lblDataVencimento;

    @FXML
    private DatePicker dtpDataVencimento;

    @FXML
    private Label lblValorTotal;

    @FXML
    private TextField txtValorTotal;

    @FXML
    private Label lblNomeFavorecido;

    @FXML
    private ComboBox<Favorecido> cbxFavorecido;

    @FXML
    private ComboBox<TipoConta> cbxTipoConta;


	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancela;

	private Stage janelaContaPagarEdit;
	private ContaPagar contaPagar;
	private boolean okClick = false;

	private List<Usuario> listaUsuarios;
	private UsuarioDAO UsuarioDAO;
	private ObservableList<Usuario> observableListaUsuarios;


	private List<Favorecido> listaFavorecidos;
	private FavorecidoDAO favorecidosDAO;
	private ObservableList<Favorecido> observableListaFavorecidos;
	
	private List<TipoConta> listaTipoConta;
	private ObservableList<TipoConta> observableListaTipoConta;
	private TipoContaDAO tipoContaDAO;



	@FXML
	void clickCancela(ActionEvent event) {
		this.janelaContaPagarEdit.close();
	}

	@FXML
	void clickOK(ActionEvent event) {
		if (validarCampos()) {
			this.contaPagar.setDescricao(this.txtDescricao.getText());
			this.contaPagar.setUsuario((Usuario) this.cbxUsuario.getSelectionModel().getSelectedItem());
			this.contaPagar.setFavorecido((Favorecido) this.cbxFavorecido.getSelectionModel().getSelectedItem());
			this.contaPagar.setTipoConta((TipoConta) this.cbxTipoConta.getSelectionModel().getSelectedItem());
			this.contaPagar.setDataPagamento(Date.valueOf(this.dtpDataPagamento.getValue()));
			this.contaPagar.setDataVencimento(Date.valueOf(this.dtpDataVencimento.getValue()));
			this.contaPagar.setDataCadastro(Date.valueOf(this.dtpDataCadastro.getValue()));
			this.contaPagar.setValorTotal(Double.parseDouble(this.txtValorTotal.getText()));

			this.okClick = true;
			this.janelaContaPagarEdit.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.UsuarioDAO = new UsuarioDAO();
		this.favorecidosDAO = new FavorecidoDAO();
		this.tipoContaDAO = new TipoContaDAO();
		
		this.carregarComboBoxUsuarios();
		this.carregarComboBoxFavorecidos();
		this.carregarComboBoxTipoConta();
	}
	
	

	/**
	 * Atribui a janela ao controle
	 * 
	 * @param janelaContaPagarEdit
	 */
	public void setJanelaContaPagarEdit(Stage janelaContaPagarEdit) {
		this.janelaContaPagarEdit = janelaContaPagarEdit;
	}

	/**
	 * Atribui a conta a pagar que será editado na janela.
	 * 
	 * @param contaPagar
	 */
	public void setContaPagar(ContaPagar contaPagar) {
		this.contaPagar = contaPagar;
		
		this.txtDescricao.setText(contaPagar.getDescricao());
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

		if (this.txtDescricao.getText() == null || this.txtDescricao.getText().length() == 0) {
			mensagemErros += "Informe a descrição!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			// Mostrando os erros.
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.initOwner(this.janelaContaPagarEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);

			alerta.showAndWait();

			return false;
		}
	}

	/**
	 * Carrega a tela com os dados disponíveis dos usuários para servir como chave
	 * estrangeira da conta a pagar no campo usuario_id.
	 * 
	 * @return void
	 */
	public void carregarComboBoxUsuarios() {
		this.listaUsuarios = this.UsuarioDAO.getAll();

		this.observableListaUsuarios = FXCollections.observableArrayList(listaUsuarios);
		this.cbxUsuario.setItems(this.observableListaUsuarios);
	}
	
	private void carregarComboBoxTipoConta() {
	this.listaFavorecidos = this.favorecidosDAO.getAll();
	
	this.observableListaFavorecidos = FXCollections.observableArrayList(listaFavorecidos);
	this.cbxFavorecido.setItems(this.observableListaFavorecidos);
		
	}

	private void carregarComboBoxFavorecidos() {
		this.listaTipoConta = this.tipoContaDAO.getAll();
		
		this.observableListaTipoConta = FXCollections.observableArrayList(listaTipoConta);
		this.cbxTipoConta.setItems(this.observableListaTipoConta);
		
	}
}

