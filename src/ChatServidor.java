import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>listaClientes;
	private String clienteIP=null;
	
	protected ChatServidor() throws RemoteException {
		listaClientes=new ArrayList<IChatCliente>();
		System.out.println("Servidor aceitando conexões...");
	}

	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException {
		this.listaClientes.add(cliente);
		clienteIP=RemoteServer.getClientHost();
		System.out.println(cliente.getNome()+ clienteIP + " se conectou ao servidor");
	}
	
	public void enviarMensagem(String texto) throws RemoteException {
		int i=0;
		while(i<listaClientes.size()) {
			listaClientes.get(i++).receberMensagem(texto);
		}
		System.out.println(texto);
	}

	public void enviarMensagem(Mensagem m) throws RemoteException {
		int i=0;
		while(i<listaClientes.size()) {
			listaClientes.get(i++).receberMensagem(m);
		}
		System.out.println(m);
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
