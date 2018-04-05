import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatServidor extends Remote{
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException;
	public void enviarMensagem(String mensagem) throws RemoteException;
}
