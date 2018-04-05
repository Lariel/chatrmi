import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AppChatCliente {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		//String URLChatServidor="rmi://"+args[0]+"/ServidorChatRMI";
		//IChatServidor servidor = (IChatServidor) Naming.lookup(URLChatServidor);
		IChatServidor servidor = (IChatServidor) Naming.lookup("rmi://"+args[0]+"/ServidorChatRMI");
		new Thread(new ChatCliente(args[1],servidor)).start();
	}
}