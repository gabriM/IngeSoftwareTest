package Codice.Modello;

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
public class Partita extends Categoria {

	/*Attributi*/
	private Campo genere=new Campo("Genere","Indica se i partecipanti sono maschi o femmine",true,STRING);
	private Campo fasciaEta=new Campo("Fascia di età","Indica l'età massima e minima per partecipare all'evento",true,ETA);
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
		creaArray();
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
	 * Crea array con tutti i campi relativi alla categoria Partita
	 *
	 * @author Matteo Gusmini
	 */
	public void creaArray(){
		
		elencoCampi.add(super.getTitolo());
		elencoCampi.add(super.getnPartecipanti());
		elencoCampi.add(super.getTolleranzaPartecipanti());
		elencoCampi.add(super.getTermineIscrizione());
		elencoCampi.add(super.getLuogo());
		elencoCampi.add(super.getData());
		elencoCampi.add(super.getOra());
		elencoCampi.add(super.getDurata());
		elencoCampi.add(super.getQuotaIndividuale());
		elencoCampi.add(super.getCompresoQuota());
		elencoCampi.add(super.getDataFine());
		elencoCampi.add(super.getDataRitiroIscrizione());
		elencoCampi.add(super.getOraFine());
		elencoCampi.add(super.getNote());
		elencoCampi.add(genere);
		elencoCampi.add(fasciaEta);
	}
	/**
	 * Ritorna una stringa che descrive tutti i campi della categoria Partita
	 *
	 * @return tutti i campi della categoria partita
	 *
	 * @author Matteo Gusmini
	 */
    public void visualizzaCampi(){
		super.visualizzaCampi();
		for (int i=0; i< elencoCampi.size(); i++){
			
			System.out.println(elencoCampi.get(i).visualizzaCampo());
		}
	}
	/**
	 * Assegna i valori ai campi di partita
	 *
	 * @throws Exception in caso di arraylist vuoto
	 *
	 * @author Matteo Gusmini
	 */
    public void inserisciCampi()throws Exception{
    	super.inserisciCampi();
    	for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).inserisciValore();
			
		}
	}
	/**
	 * Metodo preimpostato che imposta alcuni parametri di default
	 *
	 * @author Matteo Gusmini
	 */
    public void inserisciValoriPredefiniti(){
		ArrayList<Integer> eta = new ArrayList<Integer>();
		eta.add(16);
		eta.add(45);
		genere.getValore().setValore("Maschi");
		fasciaEta.getValore().setEta(eta);
    }

}

