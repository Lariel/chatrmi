package servidor;

import java.util.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.*;

public class Mensagem implements Serializable{
	//extends UnicastRemoteObject implements IMensagem, Serializable
	
	private static final long serialVersionUID = 1L;
	private IChatCliente remetente;
	private String texto;
	private String instante, hora, data;
	private Boolean lida=false;
	
	public Mensagem(IChatCliente remetente, String texto) {
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		this.instante = (dateFormat.format(dt));
		this.hora = instante.substring(11);
		this.remetente = remetente;
		this.texto = texto;
	}

	public String getNomeRemetente() throws RemoteException {
		return remetente.getNome();
	}
	
	public String getIpRemetente() throws RemoteException {
		return remetente.getIP();
	}

	public String getTexto() {
		return texto;
	}

	public String getInstante() {
		return instante;
	}	
	
	public String getHora() {
		return hora;
	}
	
	public Boolean setLida() {
		lida=true;
		return lida;
	}
	
	public Boolean getLida() {
		return lida;
	}
	
}
