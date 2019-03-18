import java.io.Serializable;

import MyLib.Utility;

/**
 * Classe che definisce un'<i>Iscrizione</i>.
 *
 * Un'iscrizione ha le seguenti caratteristiche:
 * utente;
 * evento associato;
 * costo;
 *
 * @author Matteo Gusmini
 *
 * @version 5.0 1 Febbraio 2019
 *
 */
public class Iscrizioni implements Serializable{

	/*Attributi*/
	public String utente;
	public Evento evento;
	public int costo;


	/*Costruttori*/
	/**
	 * Un'uscrizione e' costituita da utente, evento e costo:
	 *
	 * @param _utente l'utente iscritto
	 * @param _evento l'evento associato
	 *
	 * @author Matteo Gusmini
	 */
	public Iscrizioni (String _utente, Evento _evento){
		utente=_utente;
		evento=_evento;
		costo=(int) evento.getCategoria().getQuotaIndividuale().getValore().getValore();
	}

	/*Getters*/
	/**
	 * Ritorna l'utente
	 * @return l'utente
	 *
	 * @author Matteo Gusmini
	 */
	public String getUtente() {
		return utente;
	}
	/**
	 * Ritorna l'evento associato all'iscrizione
	 * @return l'evento associato all'iscrizione
	 *
	 * @author Matteo Gusmini
	 */
	public Evento getEvento() {
		return evento;
	}
	/**
	 * Ritorna il costo associato all'iscrizione
	 * @return il costo associato all'iscrizione
	 *
	 * @author Matteo Gusmini
	 */
	public int getCosto() {
		return costo;
	}

	/*Setters*/
	/**
	 * Metodo che imposta l'utente associato all'iscrizione
	 * @param utente l'utente associato all'iscrizione
	 *
	 * @author Matteo Gusmini
	 */
	public void setUtente(String utente) {
		this.utente = utente;
	}
	/**
	 * Metodo che imposta l'evento associato all'iscrizione
	 * @param evento l'evento associato all'iscrizione
	 *
	 * @author Matteo Gusmini
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	/**
	 * Metodo che imposta il costo associato all'iscrizione
	 * @param costo il costo associato all'iscrizione
	 *
	 * @author Matteo Gusmini
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}
}
