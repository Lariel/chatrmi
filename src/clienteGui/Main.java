package clienteGui;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		ModelCliente modelCliente = new ModelCliente();
		
		//Parent root = FXMLLoader.load(getClass().getResource("Tela.fxml"));
		
		FXMLLoader telaLogin = new FXMLLoader(getClass().getResource("ViewLoginCliente.fxml"));
		//telaCliente.setController(new ControllerCliente(modelCliente));
		telaLogin.setController(new ControllerLoginCliente(modelCliente));
		Parent root = telaLogin.load();
		
		Scene scene = new Scene(root);
		
		stage.getIcons().add(new Image("/img/WhatsLike.png"));
		stage.setScene(scene);
		stage.setTitle("WhatsLike - Login Cliente");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
