package Codice.Modello;

import java.io.Serializable;
import java.util.ArrayList;

import MyLib.Utility;

/**
 * Classe che definisce un <i>Utente</i>.
 *
 * Un utente ha le seguenti caratteristiche:
 * nome;
 * elenco eventi dell'utente
 * elenco messaggi dell'utente;
 *
 * @author Matteo Gusmini
 *
 * @version 3.0 1 Febbraio 2019
 *
 */
public class Utente implements Serializable{

	/*Costanti*/
	final String NOME="Nome categoria: ";
	final String DESCRIZIONE="Descrizione: ";
	final String SCELTACATEGORIA="Quale categoria vuoi aggiungere ai preferiti?";

	/*Attributi*/
	private String nomeUtente;
	private int[] fasciaEta;
	private ArrayList<CategoriaA> categorieInteresse= new ArrayList<>();
	private ArrayList<Evento> eventiUtente = new ArrayList<>();
	private ArrayList<Messaggio> messaggiUtente = new ArrayList<>();
	private ArrayList <Utente> Utentiamici = new ArrayList<>();

	/*Costruttori*/
	/**
	 * Un utente e' costituito da nome, elenco eventi e elenco messaggi:
	 *
	 * @param _nomeUtente il nome dell'utente
	 *
	 * @author Matteo Gusmini
	 */
	public Utente(String _nomeUtente)
		{
			nomeUtente=_nomeUtente;
		}

	/*Getters*/
	/**
	 * Ritorna il nome dell'utente
	 * @return il nome dell'utente
	 *
	 * @author Matteo Gusmini
	 */
	public String getNomeUtente() {
		return nomeUtente;
	}
	/**
	 * Ritorna un ArrayList degli eventi correlati all'utente
	 * @return ArrayList degli eventi correlati all'utente
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Evento> getEventiUtente() {
		return eventiUtente;
	}
	/**
	 * Ritorna un ArrayList dei messaggi correlati all'utente
	 * @return ArrayList dei messaggi correlati all'utente
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Messaggio> getMessaggiUtente() {
		return messaggiUtente;
	}
	/**
	 * Ritorna un ArrayList delle categorie d'interesse
	 * @return ArrayList delle categorie d'interesse
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<CategoriaA> getCategorieInteresse() {
		return categorieInteresse;
	}
	/**
	 * Ritorna un ArrayList degli utenti "Amici"
	 * @return ArrayList degli utenti "Amici"
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Utente> getUtentiamici() {
		return Utentiamici;
	}
	/**
	 * Ritorna un ArrayList delle fascie d'età
	 * @return ArrayList delle fascie d'età
	 *
	 * @author Matteo Gusmini
	 */
	public int[] getFasciaEta() {
		return fasciaEta;
	}

	/*Setters*/

	/**
	 * Metodo che imposta il nome dell'utente
	 * @param nomeUtente il nome dell'utente
	 *
	 * @author Matteo Gusmini
	 */
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	/**
	 * Permette di cambiare l'elenco degli eventi associati all'utente
	 * @param eventiUtente il nuovo elenco
	 *
	 * @author Matteo Gusmini
	 */
	public void setEventiUtente(ArrayList<Evento> eventiUtente) {
		this.eventiUtente = eventiUtente;
	}
	/**
	 * Permette di cambiare l'elenco dei messaggi associati all'utente
	 * @param messaggiUtente il nuovo elenco
	 *
	 * @author Matteo Gusmini
	 */
	public void setMessaggiUtente(ArrayList<Messaggio> messaggiUtente) {
		this.messaggiUtente = messaggiUtente;
	}
	/**
	 * Permette di cambiare l'elenco delle categorie di interesse
	 * @param categorieInteresse il nuovo elenco
	 *
	 * @author Matteo Gusmini
	 */
	public void setCategorieInteresse(ArrayList<CategoriaA> categorieInteresse) {
		this.categorieInteresse = categorieInteresse;
	}
	/**
	 * Permette di cambiare l'elenco degli utenti "Amici"
	 * @param utentiamici il nuovo elenco
	 *
	 * @author Matteo Gusmini
	 */
	public void setUtentiamici(ArrayList<Utente> utentiamici) {
		Utentiamici = utentiamici;
	}
	/**
	 * Permette di cambiare ArrayList delle fascie d'età
	 * @param fasciaEta il nuovo elenco
	 *
	 * @author Matteo Gusmini
	 */
	public void setFasciaEta(int[] fasciaEta) {
		this.fasciaEta = fasciaEta;
	}

	/*Metodi*/
	/**
	 * Metodo che, dato il nome di un utente, permette di sapere se è lo stesso con cui si fa il confronto
	 *
	 * @param uConfronto il nome dell'utente
	 * @return true se l'utente è lo stesso, false altrimenti
	 *
	 * @author Matteo Gusmini
	 */
	public Boolean confrontaUtente(Utente uConfronto){
		Boolean uguale;
		if(nomeUtente.equalsIgnoreCase(uConfronto.getNomeUtente()))
			uguale=true;
		else
			uguale=false;

		return uguale;
	}
	/**
	 * Metodo che, dato il nome di un utente, permette di sapere se è lo stesso con cui si fa il confronto (in formato Stringa)
	 *
	 * @param uConfronto il nome dell'utente
	 * @return true se l'utente è lo stesso, false altrimenti
	 *
	 * @author Matteo Gusmini
	 */
	public Boolean confrontaUtenteStringa(String uConfronto){
			Boolean uguale;
			if(nomeUtente.equalsIgnoreCase(uConfronto))
				uguale=true;
			else
				uguale=false;
			
			return uguale;
		}
	/**
	 * Metodo che permette di definire i dati personali di un utente
	 *
	 * @param categorie l'elenco delle categorie
	 *
	 * @author Matteo Gusmini
	 */
	public void inserisciDatiPersonali(ArrayList<CategoriaA> categorie){
		int inserimento= Utility.leggiIntero(0,1, "Vuoi modificare l'elenco delle tue categorie preferite? Digita 1 per SI e 0 pre NO");

		if(inserimento==0){

		}
		else{
			ArrayList<CategoriaA> nuoveCategorie= new ArrayList<>();
			ArrayList<CategoriaA> sceltaCategorie= new ArrayList<>();
			sceltaCategorie= (ArrayList<CategoriaA>) categorie.clone();
			int numCat=0;
			do{
				System.out.println("0) Esci");
				for (int i=0; i<sceltaCategorie.size();i++){
					System.out.println(i+1+")");
					System.out.println(NOME + sceltaCategorie.get(i).getNome());
					System.out.println(DESCRIZIONE + sceltaCategorie.get(i).getDescrizione()+"\n");
				}

				numCat=Utility.leggiIntero(0, sceltaCategorie.size()+1, SCELTACATEGORIA);
				if(numCat!=0){
					nuoveCategorie.add(sceltaCategorie.get(numCat-1));
					sceltaCategorie.remove(numCat-1);
				}

			}while(sceltaCategorie.size()>0 && numCat!=0);
			categorieInteresse=nuoveCategorie;
		}

		int inserimento2= Utility.leggiIntero(0,1, "Vuoi inserire la tua fascia di età? Digita 1 per SI e 0 per NO");
		if(inserimento2==0){
			}
		else{
			fasciaEta= Utility.leggiFasciaEta("Seleziona la tua fascia d'eta");
		}

		}

		public boolean controlloEventiNonPubblicati(){

		if(eventiUtente.size()!=0)
			return true;
		else
			return false;
		}

	public boolean controlloPresenzaMessaggi(){

		if(getMessaggiUtente().size()!=0)
			return true;
		else
			return false;
	}

	public void removeDate(int n) {
		eventiUtente.get(n).getCategoria().getData().getValore().removeValore();
        eventiUtente.get(n).getCategoria().getDataFine().getValore().removeValore();
        eventiUtente.get(n).getCategoria().getTermineIscrizione().getValore().removeValore();
        eventiUtente.get(n).getCategoria().getDataRitiroIscrizione().getValore().removeValore();
	}

	

}
