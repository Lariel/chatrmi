import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoricoMensagens extends UnicastRemoteObject implements IHistoricoMensagens{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String destinatario;
	private HashMap<String, ArrayList<Mensagem>> historico;
	
	public HistoricoMensagens() throws RemoteException {
		historico = new HashMap<String,ArrayList<Mensagem>>();
	}

	public void addHistorico(Mensagem m, String destinatario) throws RemoteException {
		this.destinatario=destinatario;
		ArrayList<Mensagem>auxMensagens=historico.get(destinatario);
		if(auxMensagens==null) {
			auxMensagens=new ArrayList<Mensagem>();
			auxMensagens.add(m);
			historico.put(destinatario, auxMensagens);
		}else {
			if(!auxMensagens.contains(destinatario)) {
				auxMensagens.add(m);
			}
		}
		
	}

	public String devolveHistorico(String destinatario) throws RemoteException {
		String historicoMensagens="";
		ArrayList<Mensagem>auxMensagens=historico.get(destinatario);
		for(int i=0;i<auxMensagens.size();i++) {
			historicoMensagens=historicoMensagens+auxMensagens.get(i).getTexto()+"\n";
		}
		return historicoMensagens;
	}

}
