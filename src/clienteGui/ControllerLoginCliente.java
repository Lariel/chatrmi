package clienteGui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.ResourceBundle;

import cliente.ChatCliente;
import cliente.IChatCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import servidor.ChatServidor;
import servidor.IChatServidor;

public class ControllerLoginCliente implements Initializable{

    @FXML
    private Pane loginPane;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfNick;

    @FXML
    private CheckBox cbCriptoCliente;

    @FXML
    private TextField tfChaveCliente;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnLogin;
    
	private final ModelCliente modelCliente;
	private IChatCliente cliente;
	
	//métodos    
	public ControllerLoginCliente(ModelCliente modelCliente) {
		this.modelCliente=modelCliente;
	}
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	tfUser.setTooltip(new Tooltip("Informe SEU_IP@IP_SERVIDOR"));
		
	}
    @FXML
    void ativarCriptografia(ActionEvent event) {

    }

	@FXML
	void login(ActionEvent event) throws IOException {
		String ipServidor="";
		String ipCliente="";
		String nickCliente = tfNick.getText();

		if(tfUser.getText().contains("@")) {
			int i = tfUser.getText().indexOf("@");
			ipCliente=tfUser.getText().substring(0,i);
			ipServidor=tfUser.getText().substring(i+1);
			
			    
			try {
				//Registry registry = LocateRegistry.getRegistry("rmi://"+ipServidor+"/ServidorChatRMI",Registry.REGISTRY_PORT);
				//IChatServidor iServidor = (IChatServidor) registry.lookup("rmi://"+ipServidor+"/ServidorChatRMI");
				
				IChatServidor iServidor = (IChatServidor) Naming.lookup("rmi://"+ipServidor+"/ServidorChatRMI");
				
				cliente = new ChatCliente(nickCliente,iServidor,ipCliente,modelCliente);
				
				Stage primStage = (Stage) tfNick.getScene().getWindow();
				primStage.setTitle("WhatsLike - "+tfNick.getText());

				//abre tela principal
				chamaTelaPrincipal();

				tfChaveCliente.clear();
				tfNick.clear();
				tfUser.clear();
				
			} catch (ServerNotActiveException | MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
			
			/*

			try {
				IChatServidor servidor = (IChatServidor) Naming.lookup("rmi://"+ipServidor+"/ServidorChatRMI");
				cliente = new ChatCliente(nickCliente,servidor,ipCliente,modelCliente);

				Stage primStage = (Stage) tfNick.getScene().getWindow();
				primStage.setTitle("WhatsLike - "+tfNick.getText());

				
				
				//abre tela principal
				chamaTelaPrincipal();

				
			

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
			
			*/

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
    void CancelarLogin(ActionEvent event) {
    	tfChaveCliente.clear();
		tfNick.clear();
		tfUser.clear();
    }
	
	public void chamaTelaPrincipal() throws IOException {
		FXMLLoader telaPrincipal = new FXMLLoader(getClass().getResource("ViewCliente.fxml"));
		telaPrincipal.setController(new ControllerCliente(modelCliente, cliente));

		Parent parentTelaPrincipal = telaPrincipal.load();

		Scene sceneTelaPrincipal = new Scene(parentTelaPrincipal);

		Stage stageTelaPrincipal = new Stage();
		//stageTelaPrincipal.setAlwaysOnTop(true);
		stageTelaPrincipal.setScene(sceneTelaPrincipal);
		stageTelaPrincipal.getIcons().add(new Image("/img/WhatsLike.png"));
		stageTelaPrincipal.setTitle("WhatsLike - "+cliente.getNome());
		stageTelaPrincipal.setResizable(false);
		//stageAddContato.initModality(Modality.APPLICATION_MODAL); //bloqueia main window
		stageTelaPrincipal.show();
		
		Stage loginStage = (Stage) tfNick.getScene().getWindow();
		loginStage.close();
	}

}
