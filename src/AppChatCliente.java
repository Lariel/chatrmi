import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AppChatCliente {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		//String URLChatServidor="rmi://"+args[0]+"/ServidorChatRMI";
		//IChatServidor servidor = (IChatServidor) Naming.lookup(URLChatServidor);
		if(args.length < 2) {
			System.err.println("Use java AppChatServidor <IP do Servidor> <Seu Nome>");
            System.exit(1);
		}
		IChatServidor servidor = (IChatServidor) Naming.lookup("rmi://"+args[0]+"/ServidorChatRMI");
		new Thread(new ChatCliente(args[1],servidor)).start();
	}
}