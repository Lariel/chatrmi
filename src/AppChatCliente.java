import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public class AppChatCliente {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ServerNotActiveException {
		if(args.length != 3) {
			System.err.println("Use java AppChatServidor <IP do Servidor> <Seu Nome> <Seu IP>");
            System.exit(1);
		}
		IChatServidor servidor = (IChatServidor) Naming.lookup("rmi://"+args[0]+"/ServidorChatRMI");
		new Thread(new ChatCliente(args[1],servidor,args[2])).start();
	}
}