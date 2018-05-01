import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>registradosNoServidor; // lista compartilhada com todos conectados no servidor
	//private ArrayList<IChatCliente>contatos;
	private IAgendaContatos contatos;
	private IHistoricoMensagens historico;
		
	public ChatServidor() throws RemoteException {
		registradosNoServidor=new ArrayList<IChatCliente>();
		//contatos=new ArrayList<IChatCliente>();
		historico=new HistoricoMensagens();
		System.out.println("Servidor aceitando conexões");
	}

	// recebe obj IChatCliente cliente do construtor da classe ChatCliente
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException {
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String instante = (dateFormat.format(dt));
		this.registradosNoServidor.add(cliente);
		//clienteIP=RemoteServer.getClientHost();
		contatos=new AgendaContatos(cliente.getNome(), cliente.getIP()); // cria uma nova agenda de constatos para este cliente
		System.out.println("---------- "+instante+" ----------");
		System.out.println(cliente.getIP() + " - " + cliente.getNome()+" se conectou ao servidor\n");
	}

	
	public String addContato(String nome) throws RemoteException, ServerNotActiveException {
		if(estaRegistradoNoServidor(nome)!=-1){ //segue adiante só se estiver registrado no servidor
			return contatos.addContato(
					registradosNoServidor.get(
							estaRegistradoNoServidor(nome)),
							RemoteServer.getClientHost());
		}return "Usuário não registrado no servidor";
	}
	
	// enviar mensagem para um contato especifico
	public boolean enviarMensagem(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException {
		historico.addHistorico(m, destinatario);
		return contatos.buscaPorNome(destinatario,RemoteServer.getClientHost()).receberMensagem(m);
	}
	
	 //enviar para um grupo
	public boolean enviarMensagem(Mensagem m) throws RemoteException {
		//TODO
		
		
		return false;
	}
	
	public void armazenaEnviada() throws RemoteException {
		
	}

	public void armazenaRecebida() throws RemoteException {
		
	}

	public void criaGrupo(String nomeGrupo) throws RemoteException {
		
	}

	public void addContatoGrupo(IChatCliente cliente) throws RemoteException {
		
	}

	public String listaContatos() throws RemoteException, ServerNotActiveException{
		return contatos.listaTodos(RemoteServer.getClientHost());
	}

	//retorna posição na lista ou -1 caso não esteja registrado no servidor
	public int estaRegistradoNoServidor(String nome) throws RemoteException {
		for(int i=0;i<registradosNoServidor.size();i++) {
			if(nome.equals(registradosNoServidor.get(i).getNome())) {
				return i; // já esta registrado, retorna a posição
			}
		}
		return -1; //retorna -1 quando não está registrado
	}
	
	//retorna posição na lista ou -1 caso não esteja registrado no servidor
	public int estaRegistradoNoServidor(String nome, String IP) throws RemoteException {
		for(int i=0;i<registradosNoServidor.size();i++) {
			if(nome.equals(registradosNoServidor.get(i).getNome())
					&&
			(IP.equals(registradosNoServidor.get(i).getIP()))
				) {
					return i; // já esta registrado, retorna a posição
				}
		}
		return -1; //retorna -1 quando não está registrado
	}
	
	
}
