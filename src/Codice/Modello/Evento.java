package Codice.Modello;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

/**
 * Classe che definisce un <i>Evento</i>.
 *
 * Un evento contiene le seguenti caratteristiche:
 * categoria;
 * validità;
 * creatore;
 * elenco utenti iscritti;
 * stato.
 *
 * @author Matteo Gusmini
 *
 * @version 4.0 1 Febbraio 2019
 *
 */
public class Evento implements Serializable{

	/*Costanti*/
	final String[] TESTOCHIUSURA={"L'evento "," ha raggiunto un numero sufficiente di iscrizioni e si terra dunque in data "," alle ore "," presso ",". Si ricorda che � necessatrio versare la quota di iscrizione di "," Euro."};
	final String[] TESTOFALLITO={"L'evento "," NON ha raggiunto un numero sufficiente di iscrizioni ed � quindi stato cancellato."};
	final String[] TESTOANNULLATO={"L'evento "," E' stato cancellato dall'organizzatore."};


	/*Attributi*/
	private CategoriaA categoria;
	private Boolean validita;
	private Utente creatore;
	private ArrayList <Iscrizioni> elencoIscritti = new ArrayList<>();
	private String stato;

	/*Costruttori*/
	/**
	 * Una evento e' costituito da categoria, validità, creatore, elenco utenti e stato.
	 *
	 * @param _categoria categoria dell'evento
	 * @param _creatore creatore dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public Evento(CategoriaA _categoria, Utente _creatore){
		categoria= _categoria;
		creatore=_creatore;
		validita = false;
		stato= "Aperta";
	}

	/*Getters*/
	/**
	 * Ritorna la categoria dell'evento
	 * @return categoria dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public CategoriaA getCategoria() {
		return categoria;
	}
	/**
	 * Ritorna la validità dell'evento
	 * @return validita dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public Boolean getvalidita() {
		return validita;
	}
	/**
	 * Ritorna il creatore dell'evento
	 * @return creatore dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public Utente getCreatore() {
		return creatore;
	}
	/**
	 * Ritorna un ArrayList degli utenti iscritti all'evento
	 * @return ArrayList degli utenti iscritti all'evento
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Iscrizioni> getElencoIscritti() {
		return elencoIscritti;
	}
	/**
	 * Ritorna lo stato dell'evento
	 * @return lo stato dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public String getStato() {
		return stato;
	}

	/*Setters*/
	/**
	 * Metodo che imposta la categoria dell'evento
	 * @param categoria dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public void setCategoria(CategoriaA categoria) {
		this.categoria = categoria;
	}
	/**
	 * Metodo che imposta la validità dell'evento
	 * @param validita validità dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public void setvalidita(Boolean validita) {
		this.validita = validita;
	}
	/**
	 * Metodo che imposta il creatore dell'evento
	 * @param creatore il creatore dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}
	/**
	 * Permette di cambiare l'elenco degli iscritti
	 * @param elencoIscritti il nuovo elenco
	 *
	 */
	public void setElencoIscritti(ArrayList<Iscrizioni> elencoIscritti) {
		this.elencoIscritti = elencoIscritti;
	}
	/**
	 * Metodo che imposta lo stato dell'evento
	 * @param stato lo stato dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}


	/*Metodi*/
	/**
	 * Verifica che tutti i campi obbligatori abbiano inserito un valore
	 *
	 * @author Matteo Gusmini
	 */
	public boolean isValido(){
		validita=true;
		
		for (int i=0; i<categoria.getElencoCampi().size(); i++){
			if(categoria.getElencoCampi().get(i).getObbligatorio()&& !categoria.getElencoCampi().get(i).getValore().getInserito()){
				validita=false;
			}
		}
		return validita;
	}
	/**
	 * Assegna i valori a campi dell'evento
	 *
	 * @author Matteo Gusmini
	 */
	public void inserisciDettagliEvento()throws Exception{
		categoria.inserisciCampi();
	}

	/**
	 * Metodo che, dato il nome di un utente, permette di sapere se questo utente è già iscritto ad un evento
	 *
	 * @param utente il nome dell'utente
	 * @return true se l'utente è presente nell'elenco di utenti iscritti all'evento, false altrimenti
	 *
	 * @author Matteo Gusmini
	 */
	public Boolean giaIscritto(Utente utente) {
		Boolean iscritto= false;
		
		for(int i=0; i< elencoIscritti.size(); i++){
			if (utente.confrontaUtenteStringa(elencoIscritti.get(i).getUtente())){
				iscritto= true;
			}
		}
		
		
		return iscritto;
	}
	/**
	 * Metodo che, controlla che le date siano inserite in maniera coerente con il loro significato
	 *
	 * @return true se le date inserite sono coerenti con il loro significato, false altrimenti
	 *
	 * @author Matteo Gusmini
	 */
	public Boolean controlloDate() {
		Boolean valido = true;
		Date termIsc= (Date) categoria.getTermineIscrizione().getValore().getValore();
		Date dataEv= (Date) categoria.getData().getValore().getValore();
		if(categoria.getDataRitiroIscrizione().getValore().getInserito()){
			Date ultimaIscr = (Date) categoria.getDataRitiroIscrizione().getValore().getValore();
			if(categoria.getDataFine().getValore().getInserito()){
				Date dataConc= (Date) categoria.getDataFine().getValore().getValore();;			
				if(termIsc.after(dataEv)||termIsc.after(dataConc)||dataEv.after(dataConc) || ultimaIscr.after(termIsc)){
					valido=false;
				}
			}
			else if(termIsc.after(dataEv)){
					valido=false;
			}else if(ultimaIscr.after(termIsc)){
					valido=false;
			}else if(ultimaIscr.equals(termIsc)){
					valido= true;
			}
		}
		else{
			if(categoria.getDataFine().getValore().getInserito()){
				Date dataConc= (Date) categoria.getDataFine().getValore().getValore();;			
				if(termIsc.after(dataEv)||termIsc.after(dataConc)||dataEv.after(dataConc)){
					valido=false;
				}
			}
			else if(termIsc.after(dataEv)){
					valido=false;
			}
		}
		
				
		return valido;
	}
	/**
	 * Controlla se il numero di partecipanti di un evento ha raggiunto il limite e se è vero imposta lo stato evento come "Chiuso" e
	 * genera i messaggi (Restituisce un ArrayList di Messaggio) contenenti informazioni relative allo stato dell'evento e li manda
	 * a tutti gli utenti iscritti all'evento
	 *
	 * @return <strong>un elenco di messaggi</strong> contenenti informazioni relative allo stato dell'evento che vengono mandati a
	 * tutti gli iscritti all'evento
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Messaggio> controlloNPartecipanti(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		ArrayList<Messaggio> messaggiStato = new ArrayList<>();
		
		if(categoria.getDataRitiroIscrizione().getValore().getInserito()){
			Date ultimaIscr = (Date) categoria.getDataRitiroIscrizione().getValore().getValore();
			
			if (ultimaIscr.before(date)){
				if (getPostiLiberi()==0 && stato.equalsIgnoreCase("Aperta")){
					stato= "Chiusa";
					for (int i=0;i< elencoIscritti.size();i++){
						
						String nomeUtente= elencoIscritti.get(i).getUtente();
						String testo= TESTOCHIUSURA[0] +categoria.getTitolo().getValore().getValore() + TESTOCHIUSURA[1] + dateFormat.format(categoria.getData().getValore().getValore())+ TESTOCHIUSURA[2] + categoria.getOra().getValore().getValore()+ TESTOCHIUSURA[3] + categoria.getLuogo().getValore().getValore() +TESTOCHIUSURA[4] + elencoIscritti.get(i).getCosto()+ TESTOCHIUSURA[5];                               	
						Messaggio msg =new Messaggio(nomeUtente,testo);
						
						messaggiStato.add(msg);
			
					}
				}
			}
		}
		else{
			if (getPostiLiberi()==0 && stato.equalsIgnoreCase("Aperta")){
				stato= "Chiusa";
				for (int i=0;i< elencoIscritti.size();i++){
					
					String nomeUtente= elencoIscritti.get(i).getUtente();
					String testo= TESTOCHIUSURA[0] +categoria.getTitolo().getValore().getValore() + TESTOCHIUSURA[1] + dateFormat.format(categoria.getData().getValore().getValore())+ TESTOCHIUSURA[2] + categoria.getOra().getValore().getValore()+ TESTOCHIUSURA[3] + categoria.getLuogo().getValore().getValore() +TESTOCHIUSURA[4] + elencoIscritti.get(i).getCosto()+ TESTOCHIUSURA[5];                               	
					Messaggio msg =new Messaggio(nomeUtente,testo);
					
					messaggiStato.add(msg);
		
				}
			}
		}
		
		
		return messaggiStato;
		
	}
	/**
	 * Controlla se è stata superata la data di termine iscrizione o quella di svolgimento dell'evento e se i posti disponibili sono esauriti: se è vero imposta
	 * lo stato evento come "Concluso", altrimenti se è stata superata la data di termine iscrizione o quella di svolgimento dell'evento ma non si è raggiunto
	 * il numero minimo di partecipanti, lo stato dell'evento viene impostato come "Fallito" e genera i messaggi (Restituisce un ArrayList di Messaggio) contenenti
	 * informazioni relative allo stato dell'evento e li manda a tutti gli utenti iscritti all'evento.
	 *
	 * @return <strong>un elenco di messaggi</strong> contenenti informazioni relative allo stato dell'evento che vengono mandati a tutti gli iscritti all'evento
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Messaggio> controlloData(){

		/*Data odierna per effettuare il confronto*/
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		
		ArrayList<Messaggio> messaggiStato = new ArrayList<>();
		/*Controlla se è stata superata la data di termine delle iscrizioni senza aver raggiunto il numero minimo di iscritti*/
		/*Genera dei messaggi in caso affermativo*/
		if( ((Date) categoria.getTermineIscrizione().getValore().getValore()).before(date)){
			if (getPostiMinimiPartecipanti()> 0){
				stato="Fallita";
				
				for (int i=0;i< elencoIscritti.size();i++){
					String nomeUtente= elencoIscritti.get(i).getUtente();
					String testo= TESTOFALLITO[0] +categoria.getTitolo().getValore().getValore() + TESTOFALLITO[1]; 
					Messaggio msg =new Messaggio(nomeUtente,testo);
					messaggiStato.add(msg);
				}
			}
			else{
				stato = "Chiusa2";
				
				for (int i=0;i< elencoIscritti.size();i++) {

					String nomeUtente = elencoIscritti.get(i).getUtente();
					String testo = TESTOCHIUSURA[0] + categoria.getTitolo().getValore().getValore() + TESTOCHIUSURA[1] + dateFormat.format(categoria.getData().getValore().getValore()) + TESTOCHIUSURA[2] + categoria.getOra().getValore().getValore() + TESTOCHIUSURA[3] + categoria.getLuogo().getValore().getValore() + TESTOCHIUSURA[4] + elencoIscritti.get(i).getCosto() + TESTOCHIUSURA[5];
					Messaggio msg = new Messaggio(nomeUtente, testo);

					messaggiStato.add(msg);
				}
			}
		}

		/*Verifica se è stata passata la data conclusiva dell'evento (nel caso sia inserita)o la data dell'evento*/
		if(categoria.getDataFine().getValore().getInserito()){
			if( ((Date) categoria.getDataFine().getValore().getValore()).before(date)){	
					stato= "Conclusa";
			}
		}
		else{
			if( ((Date) categoria.getData().getValore().getValore()).before(date)){
					stato= "Conclusa";
			}
		}

	
		
		
		return messaggiStato;
	}
	/**
	 * Controlla se l'evento è stato cancellato dal creatore evento e genera i messaggi (Restituisce un ArrayList di Messaggio) contenenti
	 * informazioni relative allo stato dell'evento e li manda a tutti gli utenti iscritti all'evento.
	 *
	 * @return <strong>un elenco di messaggi</strong> contenenti informazioni relative allo stato dell'evento che vengono mandati a tutti gli iscritti all'evento
	 *
	 * @author Matteo Gusmini
	 */
	public ArrayList<Messaggio> controlloEventoCancellato(){
		ArrayList<Messaggio> messaggiStato = new ArrayList<>();
		if(stato.equalsIgnoreCase("Annullato")){
			for (int i=0;i< elencoIscritti.size();i++){
				String nomeUtente= elencoIscritti.get(i).getUtente();
				String testo= TESTOANNULLATO[0] +categoria.getTitolo().getValore().getValore() + TESTOANNULLATO[1];
				Messaggio msg =new Messaggio(nomeUtente,testo);
				messaggiStato.add(msg);
			}

		}
		return messaggiStato;
	}
	/**
	 * Metodo che controlla che la data odierna sia precedente o uguale alla data di ritiro Iscrizione
	 *
	 * @return true se la data odierna è precedente o uguale alla data di ritiro Iscrizione, false altrimenti
	 *
	 * @author Matteo Gusmini
	 */
	public boolean controlloDataEliminazione(){
		Boolean valido= false;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		if(categoria.getDataRitiroIscrizione().getValore().getInserito()){
			Date ultimaIscr = (Date) categoria.getDataRitiroIscrizione().getValore().getValore();

			if(date.before(ultimaIscr) || date.equals(ultimaIscr)){
				valido = true;
			}else {
				valido = false;
			}
		}
		return valido;

	}
	/**
	 * Ritorna il numero di posti liberi di un evento senza tenere conto dei posti in esubero
	 * @return il numero di posti liberi di un evento senza tenere conto dei posti in esubero
	 *
	 * @author Matteo Gusmini
	 */
	public int getPostiMinimiPartecipanti()
	{
			return categoria.getPartecipantiMin() - elencoIscritti.size();
	}
	/**
	 * Ritorna il numero di posti liberi di un evento
	 * @return il numero di posti liberi di un evento
	 *
	 * @author Matteo Gusmini
	 */
	public int getPostiLiberi(){
		
		return categoria.getPartecipantiMax() - elencoIscritti.size();
	}
	/**
	 * Ritorna l'ammontare della quota individuale di partecipazione all'evento
	 * @return costo l'ammontare della quota individuale di partecipazione all'evento
	 */
	public int sceltaOpzioniGita() {
		int costo=categoria.sceltaOpzioni();
		return costo;
	}

	public static void eliminaEventoBacheca(ListaEventi bacheca1){
		for(int i=0; i< bacheca1.getElencoEventi().size();i++){
			if(!bacheca1.getElencoEventi().get(i).getStato().equalsIgnoreCase("Aperta") ){
				bacheca1.getElencoEventi().remove(i);
			}
		}
	}

	public void creaEvento(Iscrizioni iscrizione) throws Exception {
		inserisciDettagliEvento();
		elencoIscritti.add(iscrizione);
		
	}

	public boolean isEqual(Evento evento) {
		if(evento.getCreatore().getNomeUtente().equalsIgnoreCase(creatore.getNomeUtente())){
			if(evento.getCategoria().getNome().equalsIgnoreCase(categoria.getNome())){
				String nome1=(String) evento.getCategoria().getTitolo().getValore().getValore();
				String nome2=(String) categoria.getTitolo().getValore().getValore();
				if(nome1.equalsIgnoreCase(nome2))
					return true;
			}
		}
			return false;
	}

	public void eliminaIscrizione(Utente utenteAttivo) {
		for(int i=0;i<elencoIscritti.size();i++){
			if(elencoIscritti.get(i).getUtente().equalsIgnoreCase(utenteAttivo.getNomeUtente())){
				elencoIscritti.remove(i);
			}
		}
		
	}

	public ArrayList<Utente> UtentiInvitabili(Utente utente) {
		
		ArrayList<Utente> utentiInvitabili=new ArrayList<>();
		for(int i=0; i<utente.getUtentiamici().size();i++){
			boolean giaIscrittoEv=false;
			for (int j=0; j<elencoIscritti.size();j++){
				if(utente.getUtentiamici().get(i).confrontaUtenteStringa(elencoIscritti.get(j).getUtente())){
					giaIscrittoEv=true;
				}
			}
			if(!giaIscrittoEv)
				utentiInvitabili.add(utente.getUtentiamici().get(i));
		}
		return utentiInvitabili;
	}



}
