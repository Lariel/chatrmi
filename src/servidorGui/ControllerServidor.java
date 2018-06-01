package servidorGui;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ResourceBundle;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import servidor.ChatServidor;

public class ControllerServidor implements Initializable{
	ChatServidor servidor;
	// Menu
	@FXML
    private MenuItem miClose;

    @FXML
    private MenuItem miSobre;
    
    // Corpo do programa
    @FXML
    private TextArea taLogSrv;

    @FXML
    private CheckBox cbAtivarSrv;
    
    @FXML
    private CheckBox cbCripto;

    @FXML
    private TextField tfIpSrv;
    

    @FXML
    private TextField tfChave;

    @FXML
    private Label lblStatusSrv;
    
    private final ModelServidor modelServidor ;
    
    public ControllerServidor(ModelServidor modelServidor) {
    	this.modelServidor=modelServidor;
    	modelServidor.textProperty().addListener((obs, oldText, newText) -> {
			if(oldText==null) {
	    		taLogSrv.setText(newText);	
	    	}else taLogSrv.setText(oldText+"\n"+newText);
    	});
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
    void ativarServidor(ActionEvent event) {
		try {
			Naming.rebind("//"+tfIpSrv.getText()+"/ServidorChatRMI", servidor=new ChatServidor(tfIpSrv.getText(),modelServidor));
			lblStatusSrv.setText("Servidor IP "+tfIpSrv.getText()+" online");
			
		} catch (RemoteException e) {
			taLogSrv.setText("Erro: "+e.toString());
		} catch (MalformedURLException e) {
			taLogSrv.setText("Erro: "+e.toString());
		}
    }
	
    @FXML
    void ativarCriptografia(ActionEvent event) {
    	tfChave.setDisable(false);
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
