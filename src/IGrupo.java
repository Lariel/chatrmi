import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGrupo {
	public void addGrupo(String nGrupo, ArrayList<IChatCliente> membros)throws RemoteException;
	public String devolveGrupo()throws RemoteException;
}
