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

	public boolean controlloEventiBacheca(){
	if(elencoEventi.size()!=0)
			return true;
		else
			return false;
}

	public ArrayList<Evento> eventiValidi(Utente utente) {
		
		ArrayList<Evento> eventiValidi=new ArrayList<>();
		/*Controlla se esite almeno un evento a cui potersi iscrivere*/
		for(int i=0; i< elencoEventi.size(); i++){
			if(!elencoEventi.get(i).giaIscritto(utente)&& elencoEventi.get(i).getStato().equalsIgnoreCase("Aperta")&& elencoEventi.get(i).getPostiLiberi()>0)
				eventiValidi.add(elencoEventi.get(i));
		}
		return eventiValidi;
	}

	public boolean controlloIscrizione(Utente utente){
		boolean iscritto = false;

		for (int i = 0; i < elencoEventi.size(); i++) {
			if (elencoEventi.get(i).giaIscritto(utente) && elencoEventi.get(i).controlloDataEliminazione()){
				iscritto = true;
			}else{
				iscritto = false;
			}

		}
		return iscritto;
	}

	public boolean controlloEliminazioneEventi(Utente utente){
		Boolean eventiCancellabili=false;

		for(int i=0; i<elencoEventi.size();i++){
			if (utente.confrontaUtente(elencoEventi.get(i).getCreatore()) && elencoEventi.get(i).controlloDataEliminazione()){
				eventiCancellabili=true;
			}
		}
		return eventiCancellabili;
	}

	public boolean controlloEventiPubblicati(Utente utente){
		Boolean eventiPubblicati=false;

		for(int i=0; i<elencoEventi.size();i++){
			if (utente.confrontaUtente(elencoEventi.get(i).getCreatore())){
				eventiPubblicati=true;
			}
		}
		return eventiPubblicati;
	}


	public void eliminaEventiStato(){
		for(int i=0; i< elencoEventi.size();i++){
			if(!elencoEventi.get(i).getStato().equalsIgnoreCase("Aperta") ){
				elencoEventi.remove(i);
			}
		}
	}

	public void addIscrizione(Evento evento, Iscrizioni iscrizione) {
		for(int i=0;i<elencoEventi.size();i++){
			if(evento.isEqual(elencoEventi.get(i)))
				elencoEventi.get(i).getElencoIscritti().add(iscrizione);
		}
		
	}

	public ArrayList<Evento> eventiIscritto(Utente utente) {
		ArrayList<Evento> eventiIscritto=new ArrayList<>();
		for (int i = 0; i < elencoEventi.size(); i++) {
			if (elencoEventi.get(i).giaIscritto(utente) && elencoEventi.get(i).controlloDataEliminazione()){
				eventiIscritto.add(elencoEventi.get(i));
			}
		}
		return eventiIscritto;
	}

	public ArrayList<Evento> eventiCreati(Utente utente) {
		ArrayList<Evento> eventiCreati=new ArrayList<>();
		for(int i=0; i<elencoEventi.size();i++){
			if (utente.confrontaUtente(elencoEventi.get(i).getCreatore()) && elencoEventi.get(i).controlloDataEliminazione()){
				eventiCreati.add(elencoEventi.get(i));
			}
		}
		return eventiCreati;
	}

	public void eliminaEvento(Evento evento) {
		for(int i=0;i<elencoEventi.size();i++){
			if(evento.isEqual(elencoEventi.get(i)))
				elencoEventi.get(i).setStato("Annullato");
		}
		
	}
}
