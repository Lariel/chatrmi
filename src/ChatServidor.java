import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>registradosNoServidor;
	private ArrayList<IChatCliente>contatos;
	private ArrayList<IChatCliente>grupo;
		
	protected ChatServidor() throws RemoteException {
		registradosNoServidor=new ArrayList<IChatCliente>();
		contatos=new ArrayList<IChatCliente>();
		grupo=new ArrayList<IChatCliente>();
		System.out.println("Servidor aceitando conexões...");
	}

	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException {
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String instante = (dateFormat.format(dt));
		this.registradosNoServidor.add(cliente);
		//clienteIP=RemoteServer.getClientHost();
		System.out.println("---------- "+instante+" ----------");
		System.out.println(cliente.getIP() + " - " + cliente.getNome()+" se conectou ao servidor\n");
	}

	@Override
	public String addContato(String nome) throws RemoteException {
		if(estaRegistradoNoServidor(nome)!=-1){ //segue adiante se estiver registrado no servidor
			if(eContato(nome)==-1){ //segue adiante apenas se o nome (ou IP quando eu quiser alterar) NÃO for um contato
				int i = estaRegistradoNoServidor(nome);
				this.contatos.add(registradosNoServidor.get(i));
				return "Contato adicionado com sucesso!";
			}return "Contato já faz parte de sua lista";
		}return "Usuário não registrado no servidor";
	}
	
	public void enviarMensagem(String texto) throws RemoteException { //remover método
		int i=0;
		while(i<registradosNoServidor.size()) {
			registradosNoServidor.get(i++).receberMensagem(texto);
		}
		System.out.println(texto);
	}

	public boolean enviarMensagem(Mensagem m, String destinatario) throws RemoteException {  //enviar para um destinatário específico
		if(eContato(destinatario)!=-1){
			contatos.get(eContato(destinatario)).receberMensagem(m);
			//implementar funcionalidade de mensagem lida
			return true;
		}
		return false;
	}
	
	public boolean enviarMensagem(Mensagem m) throws RemoteException { //enviar para um grupo
		//rever este metodo
		int i=0;
		while(i<grupo.size()) {
			if(grupo.get(i).getStatus()==true) {
				grupo.get(i++).receberMensagem(m);
				return true;
			}
		}
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

	public String listaContatos() throws RemoteException {
		String lista="";
		for(int i=0; i<contatos.size();i++) {
			lista=lista+contatos.get(i).getNome()+"\n";
		}
		return lista;
	}

	//retorna posição na lista ou -1 caso não esteja registrado no servidor
	public int estaRegistradoNoServidor(String nome) throws RemoteException {
		//Para validar por IP alterar este método
		for(int i=0;i<registradosNoServidor.size();i++) {
			if(nome.equals(registradosNoServidor.get(i).getNome())) {
				return i; // já esta registrado, retorna a posição
			}
		}
		return -1; //retorna -1 quando não está registrado
	}

	//retorna posição na lista ou -1 caso não seja contato
	public int eContato(String nome) throws RemoteException {
		//Para validar por IP alterar este método
		for(int i=0;i<contatos.size();i++) {
			if(nome.equals(contatos.get(i).getNome())) {
				return i; // já é contato, retorna a posição
			}
		}
		return -1; //retorna -1 quando não é contato
	}

	
}
