import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

public interface IChatServidor extends Remote{
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	//ao registrar o cliente, o servidor cria uma lista para salvar seus contatos

	//requisitos
	// 1
	public String addContato(String nome, IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	//add contato na lista previamente criada

	// 2
	public boolean enviarMensagem(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException;
	public boolean enviarMensagemGrupo(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException;
	
	// 3
	public String exibeHistórico(String nome) throws RemoteException;

	// 4 e 5??

	// 6 
	public boolean criaGrupo(String nomeGrupo, ArrayList<IChatCliente> listaComponentes) throws RemoteException;

	
	//Apoio
	public String listaContatosEGrupos(IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	public ArrayList<IChatCliente> listaContatosObj(IChatCliente cliente)throws RemoteException, ServerNotActiveException;
	public int estaRegistradoNoServidor(String nome) throws RemoteException;
	public int estaRegistradoNoServidor(String nome, String IP) throws RemoteException;
}
