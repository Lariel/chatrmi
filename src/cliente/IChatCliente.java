package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import servidor.Mensagem;

public interface IChatCliente extends Remote{
	public boolean receberMensagem(Mensagem m) throws RemoteException;  
	public String getNome() throws RemoteException; 
	public String getIP() throws RemoteException;
	public boolean getStatus()throws RemoteException;
	public boolean addContato(String ip, String nickname) throws RemoteException, ServerNotActiveException;

}
