import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class HistoricoMensagens extends UnicastRemoteObject implements IHistoricoMensagens{
	private String destinatario;
	private ArrayList<Mensagem>mensagens;
	
	public HistoricoMensagens() throws RemoteException {
		mensagens = new ArrayList<Mensagem>();
	}

	public void addHistorico(Mensagem m, String destinatario) throws RemoteException {
		mensagens.add(m);
		this.destinatario=destinatario;
	}

	public String devolveHistorico() throws RemoteException {
		for(int i=0;i<mensagens.size();i++){
			System.out.println(mensagens.get(i).getTexto());
		}
		return null;
	}

}
