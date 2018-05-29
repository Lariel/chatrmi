package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHistoricoMensagens  extends Remote{
	public void addHistorico(String destinatario, Mensagem m) throws RemoteException;
	public String devolveHistorico(String destinatario) throws RemoteException;
}
