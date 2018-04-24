import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
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
	
	public String getNome() throws RemoteException{
		return nome;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String mensagem;
		while(true) {
			mensagem=sc.nextLine();
			if(mensagem.equals("sair")) {
				System.out.println("Parando execucao");
				try {
					servidor.enviarMensagem(nome + " saiu do chat");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}else {
				try {
					
					LocalDateTime agora = LocalDateTime.now();
					DateTimeFormatter formatador = DateTimeFormatter
					  .ofLocalizedDateTime(FormatStyle.SHORT)
					  .withLocale(new Locale("pt", "br"));
					agora.format(formatador); //24/04/18 18:02
					
															
					servidor.enviarMensagem(" "+agora+" "+nome+": "+mensagem);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
