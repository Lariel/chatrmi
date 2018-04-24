import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>listaClientes;
	
	protected ChatServidor() throws RemoteException {
		System.out.println("Servidor aceitando conexões...");
	}

	public void registrarClienteChat(IChatCliente cliente) throws RemoteException {
		listaClientes=new ArrayList<IChatCliente>();
		this.listaClientes.add(cliente);
		System.out.println(cliente.getNome() + " se conectou ao servidor");
	}

	public void enviarMensagem(String mensagem) throws RemoteException {
		int i=0;
		while(i<listaClientes.size()) {
			listaClientes.get(i++).receberMensagem(mensagem);
		}
		System.out.println(mensagem);
	}

	@Override
	public void addContato(IChatCliente cliente) throws RemoteException {
		this.listaClientes.add(cliente);
	}

	@Override
	public void armazenaEnviada() throws RemoteException {
		
	}

	@Override
	public void armazenaRecebida() throws RemoteException {
		
	}

	@Override
	public void criaGrupo(String nomeGrupo) throws RemoteException {
		
	}

	@Override
	public void addContatoGrupo(IChatCliente cliente) throws RemoteException {
		
	}
}
