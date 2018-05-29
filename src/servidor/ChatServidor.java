package servidor;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatServidor extends UnicastRemoteObject implements IChatServidor{
	private static final long serialVersionUID = 1L;
	private ArrayList<IChatCliente>registradosNoServidor; // lista compartilhada com todos conectados no servidor
	//private ArrayList<IChatCliente>contatos;
	private IAgendaContatos contatos;
	private IHistoricoMensagens historico;
	private IGrupo grupo;
	private ArrayList<IGrupo>listaGrupos;
		
	public ChatServidor() throws RemoteException {
		registradosNoServidor=new ArrayList<IChatCliente>();
		//contatos=new ArrayList<IChatCliente>();
		
		historico=new HistoricoMensagens();
		listaGrupos=new ArrayList<IGrupo>();
		System.out.println("Servidor aceitando conexoes\n");
	}

	// recebe obj IChatCliente cliente do construtor da classe ChatCliente
	public void registrarClienteChat(IChatCliente cliente) throws RemoteException, ServerNotActiveException {
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String instante = (dateFormat.format(dt));
		this.registradosNoServidor.add(cliente);
		//clienteIP=RemoteServer.getClientHost();
		contatos=new AgendaContatos(cliente); // cria uma nova agenda de contatos para este cliente
		System.out.println("---------- "+instante+" ----------");
		System.out.println(cliente.getIP() + " - " + cliente.getNome()+" se conectou ao servidor");
		System.out.println("--------------------------------------------------\n");
	}
 
	//add contato na agenda recebendo Obj cliente como dono da agenda, e o nome do contato para registrar
	public String addContato(String nome, IChatCliente cliente) throws RemoteException, ServerNotActiveException {
		if(estaRegistradoNoServidor(nome)!=-1){ //segue adiante só se estiver registrado no servidor
			return contatos.addContato(		// adiciona o contato encontrado abaixo
					registradosNoServidor.get(estaRegistradoNoServidor(nome)),cliente.getIP()); //  RemoteServer.getClientHost()
		}else return "Usuario não registrado no servidor \n";
	}
	
	// enviar mensagem para um contato especifico
	public String enviarMensagem(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException {
		if(estaRegistradoNoServidor(destinatario)!=-1){ //segue adiante só se estiver registrado no servidor
			historico.addHistorico(destinatario, m);
			historico.addHistorico(m.getNomeRemetente(),m);
			if(contatos.buscaPorNome(destinatario, m.getIpRemetente())!=null) {
				contatos.buscaPorNome(destinatario, m.getIpRemetente()).receberMensagem(m);
			}else return "Usuário não é um contato\n";
			return "\n";
		}else return "Usuario não registrado no servidor \n";
	}
	
	 //enviar para um grupo
	public boolean enviarMensagemGrupo(Mensagem m, String destinatario) throws RemoteException, ServerNotActiveException {
		historico.addHistorico(destinatario, m);
		historico.addHistorico(m.getNomeRemetente(),m);
		ArrayList<IChatCliente>auxComponentes=new ArrayList<IChatCliente>();
		
		//percorre a lista de grupos, se encontrar o grupo com o nome passado no parametro, pega a lista de componentes e armazena em auxComponentes
		for(int i=0;i<listaGrupos.size();i++) {
			if(listaGrupos.get(i).getNomeGrupo().equals(destinatario)) {
				auxComponentes=listaGrupos.get(i).getListaComponentes();
			}else {
				return false; //grupo n�o existe
			}
		}
		for(int i=0; i<auxComponentes.size();i++) {
			enviarMensagem(m, auxComponentes.get(i).getNome());
		}
		return false;
	}
	
	public String exibeHistorico(String nome) throws RemoteException{
		return historico.devolveHistorico(nome);
	}

	public String criaGrupo(String nomeGrupo, ArrayList<String> listaPossiveisComponentes, IChatCliente donoGrupo) throws RemoteException, ServerNotActiveException {
		//contatos possui um hasmap cuja chave é o IP do proprietario, e os valores são arraylist de clientes
		//percorrendo esta lista de contatos do dono do grupo, podemos comparar com o nome (String) de cada componente e validar se é contato
		ArrayList<IChatCliente>listaComponentes=new ArrayList<IChatCliente>();
		ArrayList<IChatCliente>auxContatos=new ArrayList<IChatCliente>();
		ArrayList<String>listaRejeitados=new ArrayList<String>();
		String rejeitados="";
		auxContatos=contatos.listaTodosObj(donoGrupo.getIP());
		
		for(int i=0; i<auxContatos.size();i++) { 
			if(listaPossiveisComponentes.get(i).equals(auxContatos.get(i).getNome())) {
				listaComponentes.add(auxContatos.get(i));
			}else {
				listaRejeitados.add(listaPossiveisComponentes.get(i));
			}
		}
		
		for(int i=0; i<listaRejeitados.size();i++) {
			rejeitados=listaRejeitados.get(i)+"\n";
		}
		
		
		listaComponentes.add(donoGrupo); // ao criar um grupo, eu me add nele automaticamente
		
		grupo=new Grupo(nomeGrupo, listaComponentes);
		listaGrupos.add(grupo);
		
		if(rejeitados.equals("")){
			return "Grupo "+nomeGrupo+" criado com sucesso! \n";
		}else return "Grupo "+nomeGrupo+" criado com sucesso! \n Os seguintes usuários não são contatos, e não foram adicionados: \n  "+rejeitados+"\n  ";
	}
	
	public String listaContatosEGrupos(IChatCliente cliente) throws RemoteException, ServerNotActiveException{
		String contatosEGrupos="";
		contatosEGrupos=contatos.listaTodos(cliente.getIP())+"\n"; //puxo a agenda de contatos de um determinado IP no formato String, falta add os grupos
		// para add os grupos, percorrer a listaGrupos, e em cada Grupo verificar se o cliente do parametro faz parte do Array contido no obj Grupo.
		for(int i=0;i<listaGrupos.size();i++) {
			if(listaGrupos.get(i).eComponente(cliente)) {
				contatosEGrupos=contatosEGrupos+listaGrupos.get(i).getNomeGrupo()+"\n";
			}
		}
		return contatosEGrupos;
	}
	
	// retorna ArrayList de contatos registrados
	public ArrayList<IChatCliente> listaContatosObj(IChatCliente cliente)throws RemoteException, ServerNotActiveException {
		ArrayList<IChatCliente> listaContatosObj=new ArrayList<IChatCliente>();
		listaContatosObj=contatos.listaTodosObj(cliente.getIP());
		return listaContatosObj;
	}
	

	//retorna posicao na lista ou -1 caso nao esteja registrado no servidor
	public int estaRegistradoNoServidor(String nome) throws RemoteException {
		for(int i=0;i<registradosNoServidor.size();i++) {
			if(nome.equals(registradosNoServidor.get(i).getNome())) {
				return i; // já esta registrado, retorna a posição
			}
		}
		return -1; //retorna -1 quando nao esta registrado
	}
	
	//retorna posicao na lista ou -1 caso não esteja registrado no servidor
	public int estaRegistradoNoServidor(String nome, String IP) throws RemoteException {
		for(int i=0;i<registradosNoServidor.size();i++) {
			if(nome.equals(registradosNoServidor.get(i).getNome())&&(IP.equals(registradosNoServidor.get(i).getIP()))) {
				return i; // ja esta registrado, retorna a posicao
			}
		}
		return -1; //retorna -1 quando nao esta registrado
	}


	
	
}
