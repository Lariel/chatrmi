import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AgendaContatos implements IAgendaContatos{
	private String nomeProprietario, ipProprietario;
	private ArrayList<IChatCliente>listaContatos;
	HashMap<String, ArrayList<IChatCliente>> contatos;

	public AgendaContatos(IChatCliente proprietario) throws RemoteException {
		this.ipProprietario=proprietario.getIP();
		this.nomeProprietario = proprietario.getNome();
		listaContatos = new ArrayList<IChatCliente>();
		listaContatos.add(proprietario);
		contatos = new HashMap<String, ArrayList<IChatCliente>>();
		contatos.put(ipProprietario, listaContatos);
	}

	//testa se o contato do parametro esta na minha agenda (key ipProprietario), se nao estiver, add ele
	public String addContato(IChatCliente contato, String ipProprietario) throws RemoteException {
		if(eContato(contato, ipProprietario)==-1) {
			ArrayList<IChatCliente>auxContatos=contatos.get(ipProprietario);
			if(auxContatos==null) {
				auxContatos=new ArrayList<IChatCliente>();
				auxContatos.add(contato);
				contatos.put(ipProprietario, auxContatos);
				return "Contato adicionado com sucesso\n";
			}else {
				if(!auxContatos.contains(contato)) {
					auxContatos.add(contato);
					return "Contato adicionado com sucesso\n";
				}
			}
		}
		return "Usuario ja faz parte da sua lista de contatos\n";
	}

	// retorna OBJ cliente caso seja um contato
	public IChatCliente buscaPorNome(String nome, String ipProprietario)throws RemoteException{
		if(contatos.containsKey(ipProprietario)) {
			ArrayList<IChatCliente>auxContatos=contatos.get(ipProprietario);
			for(int i = 0; i<auxContatos.size();i++) {
				if(auxContatos.get(i).getNome().equals(nome)) {
					return auxContatos.get(i);
				}
			}
		}
		return null;
	}
	
	// retorna posicao "i" quando for contato, ou -1 quando nao for contato
	public int eContato(IChatCliente contato, String ipProprietario) throws RemoteException {
		if(contatos.containsKey(ipProprietario)) {
			ArrayList<IChatCliente>auxContatos=contatos.get(ipProprietario);  // cria um array list auxiliar com os contatos da chave ipProprietario
			for(int i = 0; i<auxContatos.size();i++) {
				if(auxContatos.get(i).getNome().equals(contato.getNome())) {
					return i; // ja e contato, retorna a posicao
				}
			}
		}
		return -1;
	}

	public String listaTodos(String ipProprietario) throws RemoteException, ServerNotActiveException{
		String lista="";
		ArrayList<IChatCliente>todosContatos=contatos.get(ipProprietario);
		if(todosContatos==null) {
			todosContatos=new ArrayList<IChatCliente>();
			for(int i=0;i<todosContatos.size();i++) {
				lista=lista+todosContatos.get(i).getNome()+"\n";
			}return lista;
		}else {
			for(int i=0;i<todosContatos.size();i++) {
				lista=lista+todosContatos.get(i).getNome()+"\n";
			}
		}return lista;
	}
	
	public ArrayList<IChatCliente> listaTodosObj(String ipProprietario) throws RemoteException, ServerNotActiveException{
		ArrayList<IChatCliente>todosContatos=contatos.get(ipProprietario);
		return todosContatos;
	}

}