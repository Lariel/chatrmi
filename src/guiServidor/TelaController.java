package guiServidor;

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

public class TelaController implements Initializable{
	
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
    private TextField tfIpSrv;

    

    @FXML
    private Label lblStatusSrv;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    void ativarServidor(ActionEvent event) {
		try {
			Naming.rebind("//"+tfIpSrv.getText()+"/ServidorChatRMI", new ChatServidor());
		} catch (RemoteException e) {
			//cbAtivarSrv.setDisable(true);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText("deu merda: RemoteException");
			alert.setContentText(e.toString());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			alert.showAndWait();
		} catch (MalformedURLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText("deu merda: MalformedURLException");
			alert.setContentText(e.toString());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			alert.showAndWait();
		}

    }

    @FXML
    void close(ActionEvent event) {
    	Platform.exit();
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
	

}
