import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.HashMap;

public class Grupo implements IGrupo{
	private String nomeGrupo;
	private ArrayList<IChatCliente>listaComponentes;
	
	public Grupo(String nomeGrupo, ArrayList<IChatCliente> listaComponentes) {
		this.nomeGrupo = nomeGrupo;
		this.listaComponentes=new ArrayList<IChatCliente>();
		this.listaComponentes=listaComponentes;
	}
	
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	
	public boolean eComponente(IChatCliente contato) throws RemoteException {
		for(int i=0;i<listaComponentes.size();i++) {
			if(contato.getNome().equals(listaComponentes.get(i).getNome())) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<IChatCliente> getListaComponentes() throws RemoteException{
		return listaComponentes;
	}
}
