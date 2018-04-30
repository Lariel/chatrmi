import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatCliente extends UnicastRemoteObject implements IChatCliente, Runnable{
	private static final long serialVersionUID = 1L;
	private IChatServidor servidor;
	private String nome=null;
	private boolean onLine=false;
	private String[] comandos;
	
	protected ChatCliente(String nome, IChatServidor servidor) throws RemoteException, ServerNotActiveException {
		this.nome=nome;
		this.servidor=servidor;
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
	
	public boolean getStatus()throws RemoteException{
		return onLine;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String op="";
		String mensagem, texto, destinatario;
		onLine=true;
		while(onLine) {
			System.out.println("----- Bem vindo "+nome +", comandos disponíveis:\n"
					+"# i <nome> <ip> - Insere o <ip> com o <nome> na lista de contatos. \n"
					+"# g <nome> <lista-nomes> - Insere o <nome> na lista de grupos. Insere <lista-nomes> como membros do grupo.\n"
					+"# l <nome> - Lista as mensagens enviadas e recebidas para um contato ou grupo\n"
					+"# s <nome> <msg> - Envia uma mensagem <msg> para o <nome>\n"
					+"# c - Lista todos os contatos e grupos.\n"
					+"# e - Sair do chat\n"
					+"# h - Ver esta lista sempre que necessário\n"
					);
			
			
			texto=sc.nextLine();
			if (texto.substring(0, 1).equals("#")) { //se for um comando
				comandos=texto.split(" ");
				op=comandos[1];
			}
			switch (op) {
			case "i":
				try {
					String contato=comandos[2];
					if(servidor.eContato(contato)==true) { 
						System.out.println("Contato já existente!\n");
					}else {
						if(servidor.addContato(contato)==true) { //se não é contato
							System.out.println("Contato adicionado com sucesso!\n");
						}else System.out.println("Contato não disponivel para add :/ \n"); //se não é contato mas também não está registrado no sistema (lista todos)
					}
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			break;
			
			case "g":
				
			break;
			
			case "l":
				
			break;
			
			case "s":
				try {
					if(servidor.eContato(comandos[2])==true) {
						destinatario=comandos[2];
						mensagem=comandos[3];
						System.out.println("----- Conversa com "+destinatario+" -----\n");
						Mensagem m=new Mensagem(this.nome, mensagem); //obj Mensagem recebe o nome do remetente e uma String com a mensagem
						if(servidor.enviarMensagem(m, destinatario)==false) { //método enviarMensagem recebendo a mensagem e o destinatário
							System.out.println("Contato não está online no momento, irá receber a mensagem quando ficar online");	
						}
					}else{
						System.out.println("Contato não cadastrado!\n");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			break;
			
			case "c":
				System.out.println("\n----- Lista de pessoas -----\n");
				try {
					System.out.println(servidor.listaTodos());
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

/*
 * 
 * BACKUP
 
 
 
 
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatCliente extends UnicastRemoteObject implements IChatCliente, Runnable{
	private static final long serialVersionUID = 1L;
	private IChatServidor servidor;
	private String nome=null;
	private boolean onLine=false;
	
	protected ChatCliente(String nome, IChatServidor servidor) throws RemoteException, ServerNotActiveException {
		this.nome=nome;
		this.servidor=servidor;
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
	
	public boolean getStatus()throws RemoteException{
		return onLine;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String op="";
		String mensagem;
		while(true) {
			System.out.println(nome +" escolha a operação desejada: \n"
					+"Comandos disponíveis:\n"
					+"# i <nome> <ip> - Insere o <ip> com o <nome> na lista de contatos. \n"
					+"# g <nome> <lista-nomes> - Insere o <nome> na lista de grupos. Insere <lista-nomes> como membros do grupo.\n"
					+"# l <nome> - Lista as mensagens enviadas e recebidas para um contato ou grupo\n"
					+"# s <nome> <msg> - Envia uma mensagem <msg> para o <nome>\n"
					+"# c - Lista todos os contatos e grupos.\n"
					+ "0 - Sair \n"
					+ "1 - Adicionar contato \n"
					+ "2 - Iniciar conversa 1:1 \n"
					+ "3 - Criar grupo \n"
					+ "4 - Iniciar conversa em grupo"
					);
			op=sc.next();
			switch (op) {
			case "0":
				System.out.println("Encerrando execução");
				System.exit(0);
			
			case "1":
				System.out.println("\n----- Adicionar contato -----");
				System.out.println("\n----- Lista de pessoas disponíveis para add -----\n");
				try {
					System.out.println(servidor.listaTodos());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Informe o nome do contato para adicionar");
				try {
					String contato=sc.next();
					if(servidor.eContato(contato)==true) { 
						System.out.println("Contato já existente!\n");
					}else {
						if(servidor.addContato(contato)==true) { //se não é contato
							System.out.println("Contato adicionado com sucesso!\n");
						}else System.out.println("Contato não disponivel para add :/ \n"); //se não é contato mas também não está registrado no sistema (lista todos)
					}
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			break;
			
			case "2":
				System.out.println("\n----- Conversa 1:1 -----\n");
				System.out.println("informe o nome do contato para enviar uma mensagem:");
				String destinatario=sc.next();
				try {
					if(servidor.eContato(destinatario)==true) {
						System.out.println("----- Conversa com "+destinatario+" -----\n");
						onLine=true;
						mensagem="";
						while(onLine) {
							mensagem=sc.nextLine();
							if(!mensagem.equals("sair")) {
								Mensagem m=new Mensagem(this.nome, mensagem); //obj Mensagem recebe o nome do remetente e uma String com a mensagem
								if(servidor.enviarMensagem(m, destinatario)==false) { //método enviarMensagem recebendo a mensagem e o destinatário
									System.out.println("Contato não está online no momento, irá receber a mensagem quando ficar online");
								}
							}else {
								System.out.println("Parando execução");
								onLine=false;
							}
							
						}
					}else{
						System.out.println("Contato não cadastrado!\n");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			
			case "3":
				System.out.println("\n----- Criar Grupo -----\n");
				//TODO criar um grupo que será um array com contatos previamente cadastratos no array princial, atribuir nome ao grupo #Grupo1 por ex
			break;
			
			case "4":
				System.out.println("\n----- Conversa em Grupo -----\n");
			break;
			
			default: 
				System.out.println("Informe uma opção válida!\n");
			break;
			}		
		}
	}	
}

 
 
 
 
 * 
 * 
 */
