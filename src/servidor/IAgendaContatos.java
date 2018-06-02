package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

import cliente.IChatCliente;

public interface IAgendaContatos extends Remote{
	public String addContato(IChatCliente contato, String ipProprietario) throws RemoteException;
	public int eContato(IChatCliente contato, String ipProprietario) throws RemoteException;
	public IChatCliente buscaPorNome(String nome, String ipProprietario)throws RemoteException;
	public String listaTodos(String IP)  throws RemoteException, ServerNotActiveException;
	public ArrayList<IChatCliente> listaTodosObj(String ipProprietario) throws RemoteException, ServerNotActiveException;
}





