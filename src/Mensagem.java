import java.util.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.*;

public class Mensagem implements Serializable{
	//extends UnicastRemoteObject implements IMensagem
	private static final long serialVersionUID = 1L;
	private String nomeRemetente;
	private String texto;
	private String instante;
	
	public Mensagem(String nomeRemetente, String texto) throws RemoteException {
		
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.instante = (dateFormat.format(dt));
		this.nomeRemetente = nomeRemetente;
		this.texto = texto;
	}

	public String getNomeRemetente() throws RemoteException {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) throws RemoteException {
		this.nomeRemetente = nomeRemetente;
	}

	public String getTexto() throws RemoteException{
		return texto;
	}

	public void setTexto(String texto) throws RemoteException{
		this.texto = texto;
	}

	public String getInstante() throws RemoteException{
		return instante;
	}	
			
	
}
