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
	private MenuItem mnoUsuario;

	@FXML
	private MenuItem mnoCadastroRemedio;

	@FXML
	private MenuItem mnoSair;

	@FXML
	private MenuItem mnoGraficoListaUsuarioCadastroPorMes;

	@FXML
	private MenuItem mnoRelatorioUsuario;

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
		this.lblUsuario.setText("Farmacêuticos: " + this.getUsuario().getNome());
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
			this.getStage().setTitle("Cadastro de farmacêuticos");

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
	
	public void mnoCadastroRemedio(ActionEvent event) {
		try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/CadastroRemedioLista.fxml"));
			Parent cadastroListaXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			CadastroRemedioListaController cadastroListaController = loader.getController();
			Scene cadastroListaLayout = new Scene(cadastroListaXML);

			this.getStage().setScene(cadastroListaLayout);
			this.getStage().setTitle("Cadastro de medicamentos");

			// Atribuindo evento para fechar a tela
			this.getStage().setOnCloseRequest(e -> {
				if (cadastroListaController.onCloseQuery()) {
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
