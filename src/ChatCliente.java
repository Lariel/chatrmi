import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
	
	public boolean receberMensagem(Mensagem m) throws RemoteException {
		System.out.println(
				" "+
				m.getNomeRemetente()+" - "+
				m.getInstante()+":\n  "+
				m.getTexto()
				);
		return true;
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
		String mensagem, texto, destinatario, nomeGrupo;
		System.out.println("----- Bem vindo "+nome +", comandos disponíveis:\n"
					+"# i <nome> <ip> - Insere o <ip> com o <nome> na lista de contatos. \n"
					+"# g <nome> <lista-nomes> - Add o <nome> na lista de grupos <lista-nomes> como membros do grupo.\n"
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
					System.out.println(servidor.addContato(contato, this));
				} catch (RemoteException e2) {
					e2.printStackTrace();
				} catch (ServerNotActiveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			
			case "g":
				//comandos[# i @nomeGrupo componente1 componente2]
				if(comandos[2].startsWith("@")) {
					nomeGrupo=comandos[2];
					ArrayList<IChatCliente>listaComponentes=new ArrayList<IChatCliente>();
					ArrayList<IChatCliente>auxContatos=new ArrayList<IChatCliente>();
					try {
						auxContatos=servidor.listaContatosObj(this);
					} catch (RemoteException | ServerNotActiveException e) {
						e.printStackTrace();
					}
					for(int i=3; i<comandos.length;i++) {
						for(int j=0; j<auxContatos.size();j++) {
							try {
								if(auxContatos.get(j).getNome().equals(comandos[i])) {
									listaComponentes.add(auxContatos.get(j));
								}
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					try {
						if(servidor.criaGrupo(nomeGrupo, listaComponentes)) {
							System.out.println("Grupo "+nomeGrupo+" criado com sucesso");
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("Nomes de grupos devem comecar com @ \n");
				}
			break;
			
			case "l":
				destinatario=comandos[2];
				try {
					System.out.println(servidor.exibeHistórico(destinatario));
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			break;
			
			case "s":
				destinatario=comandos[2];
				mensagem=texto.substring(texto.indexOf(comandos[3]));
				if(destinatario.startsWith("@")) { // mensagem para o grupo todo
					Mensagem m=new Mensagem(this, mensagem); //obj Mensagem recebe o Obj do remetente e uma String com a mensagem
					try {
						servidor.enviarMensagem(m, destinatario);
					} catch (RemoteException | ServerNotActiveException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //método enviarMensagem recebendo a mensagem e o nome do grupo
				}else { // mensagem para 1 contato
					try {
						Mensagem m=new Mensagem(this, mensagem); //obj Mensagem recebe o Obj do remetente e uma String com a mensagem
						servidor.enviarMensagem(m, destinatario); //método enviarMensagem recebendo a mensagem e o destinatário
					} catch (RemoteException | ServerNotActiveException e3){
						e3.printStackTrace();
					}
				}
			break;
			
			case "c":
				try {
					System.out.println("\n--------- Contatos e grupos de: "+ this.getNome()+" ---------");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					System.out.println(servidor.listaContatosEGrupos(this));
				} catch (RemoteException | ServerNotActiveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-------------------------------------------------------\n");
			break;
			
			case "e":
				System.out.println("Encerrando execução");
				onLine=false;
				System.exit(0);
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