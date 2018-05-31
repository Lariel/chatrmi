package servidorGui;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import servidor.AppModelServidor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		AppModelServidor modelServidor = new AppModelServidor();
		
		//Parent root = FXMLLoader.load(getClass().getResource("Tela.fxml"));

		FXMLLoader telaServidor = new FXMLLoader(getClass().getResource("Tela.fxml"));
		telaServidor.setController(new TelaControllerServidor(modelServidor));
		Parent root = telaServidor.load();
		
		//Image applicationIcon = new Image(getClass().getResourceAsStream("/img/favicon.png"));
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		//stage.setMinWidth(620);
		//stage.setMinHeight(300);
		stage.setTitle("Chat RMI Servidor");
		stage.setResizable(false);
		//stage.getIcons().add(applicationIcon);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
