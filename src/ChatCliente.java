import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatCliente extends UnicastRemoteObject implements IChatCliente, Runnable{
	private static final long serialVersionUID = 1L;
	private IChatServidor servidor;
	private String nome, IP=null;
	private boolean onLine=false;
	private String[] comandos;
	
	protected ChatCliente(String nome, IChatServidor servidor, String IP) throws RemoteException, ServerNotActiveException {
		this.nome=nome;
		this.servidor=servidor;
		this.IP=IP;
		this.onLine=true;
		servidor.registrarClienteChat(this); //me registrei no servidor
	}

	public void receberMensagem(String texto) throws RemoteException {  //remover método
		System.out.println(texto);
	}
	
	public void receberMensagem(Mensagem m) throws RemoteException {
		System.out.println(
				m.getNomeRemetente()+" - "+
				m.getInstante()+":\n"+
				m.getTexto()
				);
	}
	
	public String getNome() throws RemoteException{
		return nome;
	}

	public String getIP() throws RemoteException{
		return IP;
	}
	
	public boolean getStatus()throws RemoteException{
		return onLine;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String op="";
		String mensagem, texto, destinatario;
		System.out.println("----- Bem vindo "+nome +", comandos disponíveis:\n"
					+"# i <nome> <ip> - Insere o <ip> com o <nome> na lista de contatos. \n"
					+"# g <nome> <lista-nomes> - Insere o <nome> na lista de grupos. Insere <lista-nomes> como membros do grupo.\n"
					+"# l <nome> - Lista as mensagens enviadas e recebidas para um contato ou grupo\n"
					+"# s <nome> <msg> - Envia uma mensagem <msg> para o <nome>\n"
					+"# c - Lista todos os contatos e grupos.\n"
					+"# e - Sair do chat\n"
					+"# h - Ver esta lista sempre que necessário\n"
					);
		while(onLine) {
			texto=sc.nextLine();
			if (texto.substring(0, 1).equals("#")) { //se for um comando
				comandos=texto.split(" ");
				op=comandos[1];
			}
			switch (op) {
			case "i":
				try {
					String contato=comandos[2];
					System.out.println(servidor.addContato(contato));
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
			break;
			
			case "g":
				
			break;
			
			case "l":
				
			break;
			
			case "s":
				destinatario=comandos[2];
				mensagem=texto.substring(texto.indexOf(comandos[3]));
				try {
					Mensagem m=new Mensagem(this.nome, mensagem); //obj Mensagem recebe o nome do remetente e uma String com a mensagem
					servidor.enviarMensagem(m, destinatario); //método enviarMensagem recebendo a mensagem e o destinatário
				} catch (RemoteException e3){
					e3.printStackTrace();
				}
			break;
			
			case "c":
				System.out.println("\n----- Lista de pessoas -----\n");
				try {
					System.out.println(servidor.listaContatos());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n----- Lista de grupos -----\n");
			break;
			
			case "e":
				System.out.println("Encerrando execução");
				onLine=false;
				//System.exit(0);
			break;
				
			case "h":
					System.out.println("----- Comandos disponíveis -----\n"
							+"# i <nome> <ip> - Insere o <ip> com o <nome> na lista de contatos.\n"
							+"# g <nome> <lista-nomes> - Insere o <nome> na lista de grupos. Insere <lista-nomes> como membros do grupo.\n"
							+"# l <nome> - Lista as mensagens enviadas e recebidas para um contato ou grupo\n" 
							+"# s <nome> <msg> - Envia uma mensagem <msg> para o <nome>\n" 
							+"# c - Lista todos os contatos e grupos.\n"
							+"# e - Sair do chat\n" 
							+"# h - Ver esta lista sempre que necessário\n" 
							);

			break;
			
			default: 
				System.out.println("Informe uma opção válida!\n");
			break;
			}		
		}
	}	
}