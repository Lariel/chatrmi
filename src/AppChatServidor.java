import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class AppChatServidor {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		//Naming.rebind("ServidorChatRMI", new ChatServidor());
		if(args.length != 1) {
			System.err.println("Use java AppChatServidor <IP do Servidor>");
            System.exit(1);
		}
		Naming.rebind("//"+args[0]+"/ServidorChatRMI", new ChatServidor());
	}
}
