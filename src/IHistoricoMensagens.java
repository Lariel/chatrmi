import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHistoricoMensagens  extends Remote{
	public void addHistorico(Mensagem m, String destinatario) throws RemoteException;
	public String devolveHistorico() throws RemoteException;
}
