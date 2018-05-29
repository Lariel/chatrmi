package servidor;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

public interface IGrupo {
	public String getNomeGrupo()throws RemoteException;
	public boolean eComponente(IChatCliente contato) throws RemoteException;
	public ArrayList<IChatCliente> getListaComponentes() throws RemoteException;
}
