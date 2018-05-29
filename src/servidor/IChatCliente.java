package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatCliente extends Remote{
	public boolean receberMensagem(Mensagem m) throws RemoteException;  
	public String getNome() throws RemoteException; 
	public String getIP() throws RemoteException;
	public boolean getStatus()throws RemoteException;

}
