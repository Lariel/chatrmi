package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

import servidor.Mensagem;

public interface IChatCliente extends Remote{
	public boolean receberMensagem(Mensagem m) throws RemoteException;  
	public String getNome() throws RemoteException; 
	public String getIP() throws RemoteException;
	public boolean getStatus()throws RemoteException;
	
	//novos para GUI
	public void addContato(String nick, IChatCliente cliente)throws RemoteException, ServerNotActiveException;
	public ArrayList<IChatCliente> listaContatos (IChatCliente cliente)throws RemoteException, ServerNotActiveException;
	
}
