import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>listaClientes;
	
	protected ChatServidor() throws RemoteException {
		listaClientes=new ArrayList<IChatCliente>();
	}

	public void registrarClienteChat(IChatCliente cliente) throws RemoteException {
		this.listaClientes.add(cliente);
	}

	public void enviarMensagem(String mensagem) throws RemoteException {
		int i=0;
		while(i<listaClientes.size()) {
			listaClientes.get(i++).receberMensagem(mensagem);
		}
	}
}
