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

	public void addHistorico(String destinatario, Mensagem m) throws RemoteException {
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
		String historicoMensagens="------ Conversa com "+destinatario+" ------\n";
		if(historico.containsKey(destinatario)) { //testa primeiro se existe alguma conversa com este usuario
			ArrayList<Mensagem>auxMensagens=historico.get(destinatario);
			for(int i=0;i<auxMensagens.size();i++) {
				historicoMensagens=historicoMensagens+"> "+auxMensagens.get(i).getHora()+" - "+auxMensagens.get(i).getNomeRemetente()+": "+auxMensagens.get(i).getTexto()+"\n";
			}
			return historicoMensagens;
		}else {
			return "------ Sem conversas com "+destinatario+" ------\n";
		}
	}
}
