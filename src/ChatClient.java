import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
import java.util.Scanner;

public class ChatClient { 
	public static void main(String[] args) {
		
		try { 
			if (args.length < 1) {
				System.err.println("Use java ChatClient <server address>");
				System.exit(1);
			}
			System.out.println("Connecting to remote object ...");
			Chat c = (Chat) Naming.lookup( "rmi://"+args[0]+"/ChatService");
			System.out.println("Connected to Chat Server: "+c.name());
			
			while(true){
				Scanner in = new Scanner(System.in);
				String msg = in.nextLine();
				if(msg.equals("sair")){
					System.out.println("Parando a execucao");
					System.exit(0);
				}else c.send(msg);
				
				//System.out.println( c.add(4, 5) );
			}
			
		} catch (Exception e) {
			System.err.println();
			System.err.println(e);
		}
	} 
} 
