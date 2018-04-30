import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>todos;
	private ArrayList<IChatCliente>contatos;
	private ArrayList<IChatCliente>grupo;
	private String clienteIP=null;
	
	protected ChatServidor() throws RemoteException {
		todos=new ArrayList<IChatCliente>();
		contatos=new ArrayList<IChatCliente>();
		grupo=new ArrayList<IChatCliente>();
		System.out.println("Servidor aceitando conexões...");
	}

	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException {
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String instante = (dateFormat.format(dt));
		this.todos.add(cliente);
		clienteIP=RemoteServer.getClientHost();
		System.out.println("---------- "+instante+" ----------");
		System.out.println(clienteIP + " - " + cliente.getNome()+" se conectou ao servidor\n");
	}
	
	public void enviarMensagem(String texto) throws RemoteException { //remover método
		int i=0;
		while(i<todos.size()) {
			todos.get(i++).receberMensagem(texto);
		}
		System.out.println(texto);
	}

	public boolean enviarMensagem(Mensagem m, String destinatario) throws RemoteException {  //enviar para um destinatário específico
		for(int i=0;i<contatos.size();i++) {
			if(contatos.get(i).getNome().equals(destinatario)){
				if(contatos.get(i).getStatus()==true) {
					contatos.get(i).receberMensagem(m);
					
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean enviarMensagem(Mensagem m) throws RemoteException { //enviar para um grupo
		int i=0;
		while(i<grupo.size()) {
			if(grupo.get(i).getStatus()==true) {
				grupo.get(i++).receberMensagem(m);
				return true;
			}
		}
		return false;
	}
	

	@Override
	public boolean addContato(String nome) throws RemoteException {
		for(int i=0;i<todos.size();i++) {
			if(nome.equals(todos.get(i).getNome())) {
				this.contatos.add(todos.get(i));
				return true;
			}
		}return false;
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

	@Override
	public String listaTodos() throws RemoteException {
		String lista="";
		for(int i=0; i<todos.size();i++) {
			lista=lista+todos.get(i).getNome()+"\n";
		}
		return lista;
	}

	@Override
	public boolean eContato(String nome) throws RemoteException {
		for(int i=0;i<contatos.size();i++) {
			if(nome.equals(contatos.get(i).getNome())) {
				return true; // já é contato
			}
		}
		return false;
	}

	
}
