import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMensagem extends Remote{
	public String getIpRemetente() throws RemoteException;
	public void setIpRemetente(String ipRemetente) throws RemoteException;
	public String getTexto() throws RemoteException;
	public void setTexto(String texto) throws RemoteException;
	public String getInstante() throws RemoteException;

}
