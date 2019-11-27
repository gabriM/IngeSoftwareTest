package Codice.Modello;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

/**
 * La classe Partita estende la classe Categoria per poter creare oggetti Partita.
 *
 * In particolare definisce:
 *
 * genere, indica se i partecipanti sono maschi o femmine;
 * fasciaEta, indica l'età massima e minima per partecipare all'evento;
 * elencoCampi, l'elenco dei campi della categoria.
 *
 *
 * @author Matteo Gusmini
 *
 * @version 5.0 1 Febbraio 2019
 */
public class Partita extends CategoriaA implements Serializable{
	
	final String NOME = "Partita di Calcio";
	final String DESCRIZIONE = "Evento sportivo che prevede un match di 90 minuti";
	
	
	

	/*Attributi*/
	private Campo genere=new Campo("Genere","Indica se i partecipanti sono maschi o femmine",true,new testoV());
	private Campo fasciaEta=new Campo("Fascia di età","Indica l'età massima e minima per partecipare all'evento",true,new etaV());
	private ArrayList<Campo> elencoCampi = new ArrayList<>();

	/*Costruttori*/
	/**
	 * Costruttore di default.
	 * Una partita e' costituita da nome e descizione.
	 *
	 * @author Matteo Gusmini
	 */
	public Partita(){
		super("Partita di Calcio","Evento sportivo che prevede un match di 90 minuti");
		creaArrayCampi();
	}

	/*Getters*/
	/**
	 * Ritorna il genere dei partecipanti dell'evento
	 * @return il genere dei partecipanti dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public Campo getGenere() {
		return genere;
	}
	/**
	 * Ritorna la fascia di età dei partecipanti
	 * @return la fascia di età dei partecipanti
	 *
	 * @author Matteo Gusmini
	 */
	public Campo getFasciaEta() {
		return fasciaEta;
	}
	/**
	 * Ritorna un ArrayList dei campi dell'evento
	 * @return ArrayList dei campi dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}

	/*Setters*/
	/**
	 * Permette di cambiare il genere dei partecipanti dell'evento
	 * @param genere il genere dei partecipanti dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public void setGenere(Campo genere) {
		this.genere = genere;
	}
	/**
	 * Permette di cambiare la fascia di età dei partecipanti
	 * @param fasciaEta la fascia di età dei partecipanti
	 *
	 * @author Matteo Gusmini
	 */
	public void setFasciaEta(Campo fasciaEta) {
		this.fasciaEta = fasciaEta;
	}
	/**
	 * Permette di cambiare l'elenco di Campi
	 * @param elencoCampi il nuovo elenco
	 *
	 * @author Matteo Gusmini
	 */
	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}

	/*Metodi*/

	/**
	 * Ritorna una stringa che descrive tutti i campi della categoria Partita
	 *
	 * @return tutti i campi della categoria partita
	 *
	 * @author Matteo Gusmini
	 */
    public void visualizzaCampi(){
		for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).visualizzaCampo();
		}
	}
	/**
	 * Assegna i valori ai campi di partita
	 *
	 * @author Matteo Gusmini
	 * @throws Exception 
	 */
    public void inserisciCampi() throws Exception{
    	for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).inserisciValore();
			
		}
	}
	/**
	 * Metodo preimpostato che imposta alcuni parametri di default
	 *
	 * @author Matteo Gusmini
	 */
   
	@Override
	public void creaArrayCampi() {
		elencoCampi.add(getTitolo());
		elencoCampi.add(getnPartecipanti());
		elencoCampi.add(getTolleranzaPartecipanti());
		elencoCampi.add(getTermineIscrizione());
		elencoCampi.add(getLuogo());
		elencoCampi.add(getData());
		elencoCampi.add(getOra());
		elencoCampi.add(getDurata());
		elencoCampi.add(getQuotaIndividuale());
		elencoCampi.add(getCompresoQuota());
		elencoCampi.add(getDataFine());
		elencoCampi.add(getDataRitiroIscrizione());
		elencoCampi.add(getOraFine());
		elencoCampi.add(getNote());
		
		
		elencoCampi.add(genere);
		elencoCampi.add(fasciaEta);
	}

	@Override
	public int sceltaOpzioni() {
		
		return (int) getQuotaIndividuale().getValore().getValore();
	}

}

