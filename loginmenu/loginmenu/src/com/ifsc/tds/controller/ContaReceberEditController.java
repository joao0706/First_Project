package com.ifsc.tds.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.ifsc.tds.dao.FavorecidoDAO;
import com.ifsc.tds.dao.TipoContaDAO;
import com.ifsc.tds.dao.UsuarioDAO;
import com.ifsc.tds.entity.ContaReceber;
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

public class ContaReceberEditController implements Initializable {


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

	private Stage janelaContaReceberEdit;
	private ContaReceber contaReceber;
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
		this.janelaContaReceberEdit.close();
	}

	@FXML
	void clickOK(ActionEvent event) {
		if (validarCampos()) {
			this.contaReceber.setDescricao(this.txtDescricao.getText());
			this.contaReceber.setUsuario((Usuario) this.cbxUsuario.getSelectionModel().getSelectedItem());
			this.contaReceber.setFavorecido((Favorecido) this.cbxFavorecido.getSelectionModel().getSelectedItem());
			this.contaReceber.setTipoConta((TipoConta) this.cbxTipoConta.getSelectionModel().getSelectedItem());
			this.contaReceber.setDataPagamento(Date.valueOf(this.dtpDataPagamento.getValue()));
			this.contaReceber.setDataVencimento(Date.valueOf(this.dtpDataVencimento.getValue()));
			this.contaReceber.setDataCadastro(Date.valueOf(this.dtpDataCadastro.getValue()));
			this.contaReceber.setValorTotal(Double.parseDouble(this.txtValorTotal.getText()));

			this.okClick = true;
			this.janelaContaReceberEdit.close();
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
	public void setJanelaContaReceberEdit(Stage janelaContaReceberEdit) {
		this.janelaContaReceberEdit = janelaContaReceberEdit;
	}

	/**
	 * Atribui a Conta a Receber que ser� editado na janela.
	 * 
	 * @param contaPagar
	 */
	public void setContaReceber(ContaReceber contaReceber) {
		this.contaReceber = contaReceber;
		
		this.txtDescricao.setText(contaReceber.getDescricao());
	}

	/**
	 * Retorna verdadeiro se o usu�rio clicou o bot�o OK, sen�o retorna false.
	 * 
	 * @return boolean
	 */
	public boolean isOkClick() {
		return okClick;
	}

	/**
	 * Valida os dados digitados nos campos da tela.
	 * 
	 * @return true se os dados forem v�lidos
	 */
	private boolean validarCampos() {
		String mensagemErros = "";

		if (this.txtDescricao.getText() == null || this.txtDescricao.getText().length() == 0) {
			mensagemErros += "Informe a descri��o!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			// Mostrando os erros.
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.initOwner(this.janelaContaReceberEdit);
			alerta.setTitle("Dados inv�lidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informa��es:");
			alerta.setContentText(mensagemErros);

			alerta.showAndWait();

			return false;
		}
	}

	/**
	 * Carrega a tela com os dados dispon�veis dos usu�rios para servir como chave
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

