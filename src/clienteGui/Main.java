package clienteGui;
	
import java.io.IOException;

import cliente.AppModelCliente;
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
		AppModelCliente modelCliente = new AppModelCliente();
		
		//Parent root = FXMLLoader.load(getClass().getResource("Tela.fxml"));
		
		FXMLLoader telaCliente = new FXMLLoader(getClass().getResource("Tela.fxml"));
		telaCliente.setController(new TelaControllerCliente(modelCliente));
		Parent root = telaCliente.load();
		
		Scene scene = new Scene(root);
		
		stage.getIcons().add(new Image("/img/WhatsLike.png"));
		stage.setScene(scene);
		stage.setTitle("WhatsLike - Cliente");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
