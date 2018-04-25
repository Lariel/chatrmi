import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.*;

public class Mensagem extends UnicastRemoteObject implements IMensagem{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ipRemetente;
	private String texto;
	private String instante;
	
	public Mensagem(String ipRemetente, String texto) throws RemoteException {
		
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.instante = (dateFormat.format(dt));
		this.ipRemetente = ipRemetente;
		this.texto = texto;
	}

	public String getIpRemetente() throws RemoteException {
		return ipRemetente;
	}

	public void setIpRemetente(String ipRemetente) throws RemoteException {
		this.ipRemetente = ipRemetente;
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
