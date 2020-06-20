package com.ifsc.tds.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.entity.Usuario;
import com.ifsc.tds.util.ImpressoraPDF;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController implements Initializable {

	private Usuario usuario;

	@FXML
	private MenuItem mnoFavorecido;

	@FXML
	private MenuItem mnoTipoConta;

	@FXML
	private MenuItem mnoUsuario;

	@FXML
	private MenuItem mnoContasPagar;

	@FXML
	private MenuItem mnoContasReceber;

	@FXML
	private MenuItem mnoSair;

	@FXML
	private MenuItem mnoGraficoRecebimentosPagamentos;

	@FXML
	private MenuItem mnoGraficoGastosCategoria;

	@FXML
	private MenuItem mnoRelatorioContasReceber;

	@FXML
	private MenuItem mnoRelatorioContasPagar;

	@FXML
	private MenuItem mnoRelatorioUsuario;

	@FXML
	private MenuItem mnoGraficoListaUsuarioCadastroPorMes;

	@FXML
	private MenuItem mnoSobre;

	@FXML
	private HBox pnlStatusBar;

	@FXML
	private Label lblUsuario;

	@FXML
	private Label lblData;

	@FXML
	private Label lblHora;

	private Stage stage;

	// Configurações iniciais da tela de menu
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.configuraBarraStatus();
		this.configuraStage();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Quando abre a tela e coloca o nome do usuário da tela de status
	public void onShow() {
		this.lblUsuario.setText("Usuário: " + this.getUsuario().getNome());
	}

	@FXML
	void mnoSair(ActionEvent event) {
		if (this.onCloseQuery()) {
			System.exit(0);
		} else {
			event.consume();
		}
	}

	// Evento de fechamento da tela com pergunta
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

	// Configura a tela inicialmente
	public void configuraStage() {
		this.setStage(new Stage());
		this.getStage().initModality(Modality.APPLICATION_MODAL);
		this.getStage().resizableProperty().setValue(Boolean.FALSE);
	}

	// Configura a barra de status para mostrar a hora e nome do usuário
	public void configuraBarraStatus() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.lblData.setText("Data: " + LocalDateTime.now().format(dataFormatada));

		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
			this.lblHora.setText("Hora: " + LocalDateTime.now().format(horaFormatada));
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
	}

	public void mnoFavorecido(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/FavorecidoLista.fxml"));
			Parent favorecidoListaXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			FavorecidoListaController favorecidoListaController = loader.getController();
			Scene favorecidoListaLayout = new Scene(favorecidoListaXML);

			this.getStage().setScene(favorecidoListaLayout);
			this.getStage().setTitle("Cadastro de favorecidos");

			// Atribuindo evento para fechar a tela
			this.getStage().setOnCloseRequest(e -> {
				if (favorecidoListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});
			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void mnoTipoConta(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/TipoContaLista.fxml"));
			Parent tipoContaListaXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			TipoContaListaController tipoContaListaController = loader.getController();
			Scene tipoListaLayout = new Scene(tipoContaListaXML);

			this.getStage().setScene(tipoListaLayout);
			this.getStage().setTitle("Cadastro de tipo de conta");

			// Atribuindo evento para fechar a tela
			this.getStage().setOnCloseRequest(e -> {
				if (tipoContaListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});
			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Carregando a tela de conta a pagar a parte de listagem
	@FXML
	public void mnoContasPagar(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/ContaPagarLista.fxml"));
			Parent contaPagarListaXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			ContaPagarListaController contaPagarListaController = loader.getController();
			Scene contaPagarListaLayout = new Scene(contaPagarListaXML);

			this.getStage().setScene(contaPagarListaLayout);
			this.getStage().setTitle("Cadastro de conta a pagar");

			// Atribuindo evento para fechar a tela
			this.getStage().setOnCloseRequest(e -> {
				if (contaPagarListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});
			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Carregando a tela de conta a pagar a parte de listagem
	@FXML
	public void mnoContasReceber(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/ContaReceberLista.fxml"));
			Parent contaReceberListaXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			ContaReceberListaController contaReceberListaController = loader.getController();
			Scene contaReceberListaLayout = new Scene(contaReceberListaXML);

			this.getStage().setScene(contaReceberListaLayout);
			this.getStage().setTitle("Cadastro de conta a receber");

			// Atribuindo evento para fechar a tela
			this.getStage().setOnCloseRequest(e -> {
				if (contaReceberListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});
			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Carregando a tela de usuários a parte de listagem
	@FXML
	public void mnoUsuario(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/UsuarioLista.fxml"));
			Parent usuarioListaXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			UsuarioListaController usuarioListaController = loader.getController();
			Scene usuarioListaLayout = new Scene(usuarioListaXML);

			this.getStage().setScene(usuarioListaLayout);
			this.getStage().setTitle("Cadastro de usuário");

			// Atribuindo evento para fechar a tela
			this.getStage().setOnCloseRequest(e -> {
				if (usuarioListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});
			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void mnoRelatorioUsuario(ActionEvent event) {
		try {
			UsuarioRelatorioController relatorioController = new UsuarioRelatorioController();

			ImpressoraPDF.criarArquivo(UsuarioRelatorioController.RELATORIO_ARQUIVO,
					UsuarioRelatorioController.RELATORIO_TITULO, UsuarioRelatorioController.RELATORIO_CABECALHO,
					relatorioController.dadosRelatorio());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informação");
			alert.setHeaderText(null);
			alert.setContentText("Relatório criado!\nDisponível em: " + ImpressoraPDF.caminhoRelatorio);
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void mnoGraficoListaUsuarioCadastroPorMes(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/ifsc/tds/view/GraficoUsuarioCadastroPorMes.fxml"));
			Parent usuarioGraficoXML = loader.load();

			// Carregando a classe de controle do arquivo da tela de login
			@SuppressWarnings("unused")
			UsuarioGraficoController usuarioGraficoController = loader.getController();
			Scene usuarioListaLayout = new Scene(usuarioGraficoXML);

			this.getStage().setScene(usuarioListaLayout);
			this.getStage().setTitle("Gráfico");

			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
