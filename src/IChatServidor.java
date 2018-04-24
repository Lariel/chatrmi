import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatServidor extends Remote{
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException;
	//ao registrar o cliente, o servidor cria uma lista para salvar seus contatos

	//requisitos
	// 1
	public void addContato(IChatCliente cliente) throws RemoteException;
	//add contato na lista previamente criada

	// 2
	public void enviarMensagem(String mensagem) throws RemoteException;

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


}
