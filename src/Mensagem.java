import java.util.*;
import java.io.Serializable;
import java.text.*;

public class Mensagem implements Serializable{
	//extends UnicastRemoteObject implements IMensagem, Serializable
	
	private static final long serialVersionUID = 1L;
	private String nomeRemetente;
	private String texto;
	private String instante;
	
	public Mensagem(String nomeRemetente, String texto) {
		
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.instante = (dateFormat.format(dt));
		this.nomeRemetente = nomeRemetente;
		this.texto = texto;
	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getInstante() {
		return instante;
	}	
	
}
