import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatCliente extends UnicastRemoteObject implements IChatCliente, Runnable{
	private static final long serialVersionUID = 1L;
	private IChatServidor servidor;
	private String nome=null;
	
	protected ChatCliente(String nome, IChatServidor servidor) throws RemoteException {
		this.nome=nome;
		this.servidor=servidor;
		servidor.registrarClienteChat(this); //me registrei no servidor
	}

	public void receberMensagem(String mensagem) throws RemoteException {
		System.out.println(mensagem);
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String mensagem;
		while(true) {
			mensagem=sc.nextLine();
			try {
				servidor.enviarMensagem(nome+": "+mensagem);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
