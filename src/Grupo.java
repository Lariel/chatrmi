import java.rmi.RemoteException;
import java.util.ArrayList;

public class Grupo implements IGrupo{
	private String nGrupo;
	private ArrayList<IChatCliente>grupo;
	
	

	public Grupo(String nGrupo, ArrayList<IChatCliente> grupo) {
		super();
		this.nGrupo = nGrupo;
		this.grupo = grupo;
	}

	@Override
	public void addGrupo(String nGrupo, ArrayList<IChatCliente> membros) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String devolveGrupo() throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

}
