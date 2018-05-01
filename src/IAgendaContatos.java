import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface IAgendaContatos extends Remote{
	public String addContato(IChatCliente contato, String ipProprietario) throws RemoteException;
	public int eContato(IChatCliente contato, String ipProprietario) throws RemoteException;
	public IChatCliente buscaPorNome(String nome, String ipProprietario)throws RemoteException;
	public String listaTodos(String IP)  throws RemoteException, ServerNotActiveException;
}





