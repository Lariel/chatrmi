import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface IChatServidor extends Remote{
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	//ao registrar o cliente, o servidor cria uma lista para salvar seus contatos

	//requisitos
	// 1
	public boolean addContato(String nome) throws RemoteException;
	//add contato na lista previamente criada

	// 2
	public void enviarMensagem(Mensagem m, String destinatario) throws RemoteException;
	public void enviarMensagem(Mensagem m) throws RemoteException;
	public void enviarMensagem(String texto) throws RemoteException;

	// 3.1
	public void armazenaEnviada() throws RemoteException;

	// 3.2
	public void armazenaRecebida() throws RemoteException;

	// 4 e 5??

	// 6 
	public void criaGrupo(String nomeGrupo) throws RemoteException;
	//cria lista vazia

	public void addContatoGrupo(IChatCliente cliente) throws RemoteException;
	//add se for contato, testar se está na lista de contatos
	
	//Apoio
	public String listaTodos() throws RemoteException;
	public boolean eContato(String nome) throws RemoteException;

}
