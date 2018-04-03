import java.net.InetAddress;
import java.net.UnknownHostException;

/**
Objeto remoto implementa a interface Chat.
Este é o arquivo que deve ser usado pelo rmic, para a geração dos stubs
 **/


public class ChatImpl extends java.rmi.server.UnicastRemoteObject implements Chat {
	String myName, msg;

	public ChatImpl() throws java.rmi.RemoteException {
		myName = new String("Padrão");
	}
	public ChatImpl(String n) throws java.rmi.RemoteException {
		myName = n;
	}

	public String name() throws java.rmi.RemoteException {
		return myName;
	}

	public String forward(String msg) throws java.rmi.RemoteException {
		return msg;
	}

	public void send(String msg) throws java.rmi.RemoteException {
		this.msg=msg;
		forward(msg);
		System.out.println(msg);
		//return msg;
		
	}
	
	public long add(long a, long b) throws java.rmi.RemoteException { 
	     return a + b; 
	}
} 
