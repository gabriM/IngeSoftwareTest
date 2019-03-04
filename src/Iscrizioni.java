import java.io.Serializable;

import MyLib.Utility;

public class Iscrizioni implements Serializable{
	
	public String utente;
	public Evento evento;
	public int costo;
	
	
	
	public Iscrizioni (String _utente, Evento _evento){
		utente=_utente;
		evento=_evento;
		costo=(int) evento.getCategoria().getQuotaIndividuale().getValore().getValore();
	}


	
	
	public String getUtente() {
		return utente;
	}



	public Evento getEvento() {
		return evento;
	}



	public int getCosto() {
		return costo;
	}



	public void setUtente(String utente) {
		this.utente = utente;
	}



	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	public void setCosto(int costo) {
		this.costo = costo;
	}



	
	
	
	
	
}
