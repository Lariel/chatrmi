import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatCliente extends UnicastRemoteObject implements IChatCliente, Runnable{
	private static final long serialVersionUID = 1L;
	private IChatServidor servidor;
	private String nome=null;
	
	protected ChatCliente(String nome, IChatServidor servidor) throws RemoteException, ServerNotActiveException {
		this.nome=nome;
		this.servidor=servidor;
		servidor.registrarClienteChat(this); //me registrei no servidor
	}

	public void receberMensagem(String texto) throws RemoteException {
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

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		int op = -1;
		String mensagem;
		while(true) {
			System.out.println(nome +" escolha a operação desejada: \n"
					+ "0 - Sair \n"
					+ "1 - Adicionar contato \n"
					+ "2 - Iniciar conversa 1:1 \n"
					+ "3 - Criar grupo \n"
					+ "4 - Iniciar conversa em grupo"
					);
			op=sc.nextInt();
			switch (op) {
			case 0:
				System.out.println("Encerrando execução");
				System.exit(0);
			
			case 1:
				System.out.println("\n----- Adicionar Contato -----\n");
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
			
			case 2:
				System.out.println("\n----- Conversa 1:1 -----\n");
				System.out.println("informe o nome do contato para enviar uma mensagem:");
				String destinatario=sc.next();
				try {
					if(servidor.eContato(destinatario)==true) {
						System.out.println("----- Conversa com "+destinatario+" -----\n");
						mensagem="";
						while(!mensagem.equals("sair")) {
							mensagem=sc.nextLine();
							Mensagem m=new Mensagem(this.nome, mensagem); //obj Mensagem recebe o nome do remetente e uma String com a mensagem
							servidor.enviarMensagem(m, destinatario); //método enviarMensagem recebendo a mensagem e o destinatário
						}
					}else{
						System.out.println("Contato não cadastrado!\n");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			
			case 3:
				System.out.println("\n----- Criar Grupo -----\n");
				//TODO criar um grupo que será um array com contatos previamente cadastratos no array princial, atribuir nome ao grupo #Grupo1 por ex
			break;
			
			case 4:
				System.out.println("\n----- Conversa em Grupo -----\n");
			break;
			}		
		}
	}	
}
