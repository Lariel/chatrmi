package clienteGui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaControllerCliente implements Initializable{
	// Tela de Login
	@FXML // fx:id="loginPane"
	private Pane loginPane; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancelar"
	private Button btnCancelar; // Value injected by FXMLLoader

	@FXML // fx:id="btnLogin"
	private Button btnLogin; // Value injected by FXMLLoader

	@FXML // fx:id="tfUser"
	private TextField tfUser; // Value injected by FXMLLoader

	@FXML // fx:id="tfNick"
	private TextField tfNick; // Value injected by FXMLLoader

	@FXML
	private TextField tfChaveCliente;

	@FXML
	private CheckBox cbCriptoCliente;

	// Conversa
	@FXML // fx:id="tContatos"
	private Text tContatos; // Value injected by FXMLLoader

	@FXML // fx:id="tConversa"
	private Text tConversa; // Value injected by FXMLLoader

	@FXML // fx:id="lvContatos"
	private ListView<?> lvContatos; // Value injected by FXMLLoader

	@FXML // fx:id="lvConversa"
	private ListView<?> lvConversa; // Value injected by FXMLLoader

	@FXML // fx:id="tfMensagem"
	private TextField tfMensagem; // Value injected by FXMLLoader

	@FXML // fx:id="btnEnviar"
	private Button btnEnviar; // Value injected by FXMLLoader

	// Menu
	@FXML // fx:id="miSobre"
	private MenuItem miSobre; // Value injected by FXMLLoader



	@FXML
	private MenuItem miClose;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	void login(ActionEvent event) {
		loginPane.setVisible(false);
		tContatos.setVisible(true);
		lvContatos.setVisible(true);
		tConversa.setVisible(true);
		lvConversa.setVisible(true);
		tfMensagem.setVisible(true);
		btnEnviar.setVisible(true);
	}

	@FXML
	void sobre(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre");
		alert.setHeaderText("TF - Sistemas Distribuidos");
		alert.setContentText("Desenvolvido por: \n Ial Jonas\n Lariel Negreiros \n \nProfessor: \n Avelino Zorzo");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/WhatsLike.png")));
		alert.showAndWait();
	}

	@FXML
	void close(ActionEvent event) {
		Platform.exit();
	}



}
