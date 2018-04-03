
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class ChatServer {

	public ChatServer(String ip) {
		try {
			Chat c = new ChatImpl();
			Naming.rebind("//"+ip+"/ChatService", c);
			System.out.println("Chat Service Installed.");
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	public static void main(String args[]) {
		if(System.getSecurityManager() == null) {
			System.out.println("Installing a Security Manager.");
			System.setSecurityManager(new RMISecurityManager());
		}
		new ChatServer(args[0]);
	}

}

