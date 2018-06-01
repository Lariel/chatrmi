package clienteGui;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ResourceBundle;

import cliente.ChatCliente;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import servidor.IChatServidor;
import servidorGui.ModelServidor;

public class ControllerCliente implements Initializable{
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

    @FXML
    private Label lblStatus;
    
	// Conversa
	@FXML // fx:id="tContatos"
	private Text tContatos; // Value injected by FXMLLoader

	@FXML // fx:id="tConversa"
	private Text tConversa; // Value injected by FXMLLoader

	@FXML // fx:id="lvContatos"
	private ListView<?> lvContatos; // Value injected by FXMLLoader

	@FXML // fx:id="lvConversa"
	private ListView<String> lvConversa; // Value injected by FXMLLoader

	@FXML // fx:id="tfMensagem"
	private TextField tfMensagem; // Value injected by FXMLLoader

	@FXML // fx:id="btnEnviar"
	private Button btnEnviar; // Value injected by FXMLLoader

	// Menu
	@FXML // fx:id="miSobre"
	private MenuItem miSobre; // Value injected by FXMLLoader

	@FXML
	private MenuItem miClose;
	
    @FXML
    private MenuItem miLogout;

    private final ModelCliente modelCliente;
    private ObservableList<String> conversa = FXCollections.observableArrayList();
    
    public ControllerCliente(ModelCliente modelCliente) {
    	this.modelCliente=modelCliente;
    	
    	modelCliente.textProperty().addListener((obs,oldText,newText) -> {
    		if(oldText==null) {
    			conversa.add(newText);
    		}else conversa.add(oldText+newText);
    	});
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfUser.setTooltip(new Tooltip("Informe SEU_IP@IP_SERVIDOR"));
		lvConversa.setItems(conversa);

	}

	@FXML
	void login(ActionEvent event) {
		String ipServidor="";
		String ipCliente="";
		String nickCliente = tfNick.getText();
		
		if(tfUser.getText().contains("@")) {
			int i = tfUser.getText().indexOf("@");
			ipCliente=tfUser.getText().substring(0,i);
			ipServidor=tfUser.getText().substring(i+1);
			
			try {
				IChatServidor servidor = (IChatServidor) Naming.lookup("rmi://"+ipServidor+"/ServidorChatRMI");
				new Thread(new ChatCliente(nickCliente,servidor,ipCliente,modelCliente)).start();
				
				Stage primStage = (Stage) tfNick.getScene().getWindow();
			    primStage.setTitle("WhatsLike - "+tfNick.getText());
			    
			    lblStatus.setText("Conectado ao servidor: "+servidor.getIpServidor());
			    
				
				loginPane.setVisible(false);
				tContatos.setVisible(true);
				lvContatos.setVisible(true);
				tConversa.setVisible(true);
				lvConversa.setVisible(true);
				tfMensagem.setVisible(true);
				btnEnviar.setVisible(true);
				
				tfChaveCliente.clear();
				tfNick.clear();
				tfUser.clear();
				
			} catch (ServerNotActiveException | MalformedURLException | RemoteException | NotBoundException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Atenção");
				alert.setHeaderText("Não foi possível conectar");
				alert.setContentText(e.toString());
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/WhatsLike.png")));
				alert.showAndWait();
			}
			
		}else {
			tfUser.clear();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Atenção");
			alert.setHeaderText("Não foi possível conectar");
			alert.setContentText("Informe SEU_IP@IP_DO_SERVIDOR");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/WhatsLike.png")));
			alert.showAndWait();
		}
		
	}
	
    @FXML
    void ativarCriptografia(ActionEvent event) {
    	tfChaveCliente.setDisable(false);
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
    void logout(ActionEvent event) {
    	loginPane.setVisible(true);
		tContatos.setVisible(false);
		lvContatos.setVisible(false);
		tConversa.setVisible(false);
		lvConversa.setVisible(false);
		tfMensagem.setVisible(false);
		btnEnviar.setVisible(false);
    }

	@FXML
	void close(ActionEvent event) {
		Platform.exit();
	}

}
