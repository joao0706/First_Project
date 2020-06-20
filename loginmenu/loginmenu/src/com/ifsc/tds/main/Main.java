package com.ifsc.tds.main;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;

import com.ifsc.tds.controller.LoginController;
import com.sun.javafx.scene.control.behavior.ButtonBehavior;
import com.sun.javafx.scene.control.behavior.KeyBinding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			// Inicialização necessária para pressionar o botão ao teclar ENTER
			new EnableButtonEnterKey();

			// Carregando o arquivo da tela de login
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/Login.fxml"));
			Parent loginXML = loader.load();
			

			// Carregando a classe de controle do arquivo da tela de login
			LoginController loginController = loader.getController();
			Scene loginLayout = new Scene(loginXML);

			Stage loginJanela = new Stage();
			loginJanela.initModality(Modality.APPLICATION_MODAL);
			loginJanela.resizableProperty().setValue(Boolean.FALSE);
			loginJanela.setScene(loginLayout);
			
			
			loginJanela.setTitle("Tela de login");

			// Atribuindo evento para fechar a tela de login
			loginJanela.setOnCloseRequest(e -> {
				if (loginController.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}
			});
			loginJanela.show();

			// Posicionando a janela de login no centro da tela do computador
			Rectangle2D posicaoJanela = Screen.getPrimary().getVisualBounds();
			loginJanela.setX((posicaoJanela.getWidth() - loginJanela.getWidth()) / 2);
			loginJanela.setY((posicaoJanela.getHeight() - loginJanela.getHeight()) / 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public class EnableButtonEnterKey extends ButtonBehavior<Button> {

		public EnableButtonEnterKey() {
			super(new Button());
			BUTTON_BINDINGS.add(new KeyBinding(ENTER, KEY_PRESSED, "Press"));
			BUTTON_BINDINGS.add(new KeyBinding(ENTER, KEY_RELEASED, "Release"));
		}
	}
}
