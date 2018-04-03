/** 
 Interface para objeto remoto em Java.
 **/

public interface Chat  extends java.rmi.Remote {

	public String name() throws java.rmi.RemoteException;
	public String forward(String msg) throws java.rmi.RemoteException;
	public void send(String msg) throws java.rmi.RemoteException;
	public long add(long a, long b) throws java.rmi.RemoteException;
} 
