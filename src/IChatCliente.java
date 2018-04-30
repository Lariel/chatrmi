import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatCliente extends Remote{
	public void receberMensagem(Mensagem m) throws RemoteException; 
	public void receberMensagem(String texto) throws RemoteException; 
	public String getNome() throws RemoteException; 
	public boolean getStatus()throws RemoteException;

}
