package Codice.Modello;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe ListaEventi permette di creare una lista in cui inserire un elenco di Eventi.
 *
 * @author Matteo Gusmini
 *
 *  @version 5.0 1 Febbraio 2019
 */
public class ListaEventi implements Serializable {

	/*Attributi*/
	private ArrayList<Evento> elencoEventi = new ArrayList<>();

	/*Costruttori*/
	/**
	 * Costruttore di default
	 *
	 * @author Matteo Gusmini
	 */
	public ListaEventi()
	{
		
	}

	/*Getters*/
	/**
	 * Ritorna l'ArrayList elencoEventi che contiene un elenco di eventi
	 *
	 * @return elencoEventi l'ArrayList che contiene un elenco di eventi
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Evento> getElencoEventi() {
		return elencoEventi;
	}

	/*Setters*/
	/**
	 * Metodo che imposta una lista di eventi
	 *
	 * @param elencoEventi l'ArrayList che contiene un elenco di eventi
	 *
	 * @author Matteo Gusmini
	 */
	public void setElencoEventi(ArrayList<Evento> elencoEventi) {
		this.elencoEventi = elencoEventi;
	}

	/*Metodi*/
	/**
	 * Controlla gli stati degli eventi presenti all'interno dell'elenco eventi e genera i messaggi (Restituisce un ArrayList di Messaggio) contenenti
	 * informazioni relative allo stato dell'evento e li manda a tutti gli utenti iscritti all'evento.
	 *
	 * @return <strong>un elenco di messaggi</strong> contenenti informazioni relative allo stato dell'evento che vengono mandati a tutti gli iscritti all'evento
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Messaggio> controlloEventi(){
		ArrayList<Messaggio> messaggi = new ArrayList<>();
		
		
		for(int i=0; i<elencoEventi.size();i++){
			ArrayList<Messaggio> messaggiStato1 = new ArrayList<>(elencoEventi.get(i).controlloNPartecipanti());
			ArrayList<Messaggio> messaggiStato2 = new ArrayList<>(elencoEventi.get(i).controlloData());
			ArrayList<Messaggio> messaggiStato3 = new ArrayList<>(elencoEventi.get(i).controlloEventoCancellato());
			messaggi.addAll(messaggiStato1);
			messaggi.addAll(messaggiStato2);
			messaggi.addAll(messaggiStato3);
		}
		
		return messaggi;
		
	}
}
