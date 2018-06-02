package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

import cliente.IChatCliente;

public interface IChatServidor extends Remote{
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	public String addContato(String nome, IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	public String enviarMensagem(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException;
	public boolean enviarMensagemGrupo(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException;
	public String exibeHistorico(String nome) throws RemoteException;
	public String criaGrupo(String nomeGrupo, ArrayList<String> listaComponentes, IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	
	//Apoio
	public String listaContatosEGrupos(IChatCliente cliente) throws RemoteException, ServerNotActiveException;
	public ArrayList<IChatCliente> listaContatosObj(IChatCliente cliente)throws RemoteException, ServerNotActiveException;
	public int estaRegistradoNoServidor(String nome) throws RemoteException;
	public int estaRegistradoNoServidor(String nome, String IP) throws RemoteException;
	public String getIpServidor()throws RemoteException;
}
