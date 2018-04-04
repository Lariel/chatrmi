
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class ChatServer {
	private String nome;
	public ChatServer(String ip, String nome) {
		this.nome=nome;
		try {
			Chat c = new ChatImpl(nome);
			Naming.rebind("//"+ip+"/ChatService", c);
			System.out.println("Chat Service Started.");
			System.out.println("Server " + c.name() + " is running");
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	public static void main(String args[]) {
		if(System.getSecurityManager() == null) {
			System.out.println("Installing a Security Manager.");
			System.setSecurityManager(new RMISecurityManager());
		}
		new ChatServer(args[0], args[1]);
	}
}

