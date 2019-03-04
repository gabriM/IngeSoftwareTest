import MyLib.Utility;

public class Iscrizioni {
	
	public Utente utente;
	public Evento evento;
	public int costo;
	
	
	
	public Iscrizioni (Utente _utente, Evento _evento){
		utente=_utente;
		evento=_evento;
		costo=(int) evento.getCategoria().getQuotaIndividuale().getValore().getValore();
	}


	
	
	public Utente getUtente() {
		return utente;
	}



	public Evento getEvento() {
		return evento;
	}



	public int getCosto() {
		return costo;
	}



	public void setUtente(Utente utente) {
		this.utente = utente;
	}



	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	public void setCosto(int costo) {
		this.costo = costo;
	}



	
	
	
	
	
}
