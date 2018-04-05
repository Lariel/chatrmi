

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatCliente extends Remote{
	public void receberMensagem(String mensagem) throws RemoteException; 
	public String getNome() throws RemoteException; 

}
