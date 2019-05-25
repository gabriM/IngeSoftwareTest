import java.io.Serializable;
import java.util.ArrayList;


import java.util.ArrayList;

/**
 * Classe che definisce una <i>Categoria</i>.
 *
 * Una categoria contiene i dati riguardanti un determinato evento:
 * nome;
 * descrizione;
 * titolo;
 * numero partecipanti;
 * numero di tolleranza partecipanti
 * data termine iscrizione;
 * luogo;
 * data;
 * ora;
 * durata;
 * quota individuale di partecipazione;
 * compreso nella quota;
 * data finale;
 * data ritiro iscrizione;
 * ora finale;
 * note;
 * elenco dei campi.
 *
 *
 * @author Gabriele Manenti
 *
 * @version 4.0 1 Febbraio 2019
 *
 */
public class Categoria implements Serializable{

	/*Costanti*/
	public static final int STRING = 0;
	public static final int INT = 1;
	public static final int DATE = 2;
	public static final int ORA = 3;
	public static final int ETA = 4;

	/*Attributi*/
	private String nome;
	private String descrizione;
	private Campo titolo=new Campo("Titolo","Consiste in un nome di fantasia attribuito all�evento",false,STRING);
	private Campo nPartecipanti=new Campo("Numero di partecipanti"," Stabilisce il numero di persone da coinvolgere nell�evento",true,INT);
	private Campo tolleranzaPartecipanti=new Campo("Numero di partecipanti in esubero"," Stabilisce il numero di persone in esubero al numero di partecipanti",false,INT);
	private Campo termineIscrizione=new Campo("Termine ultimo iscrizione","Indica l'ultimo giorno utile per iscriversi all�evento",true,DATE);
	private Campo luogo=new Campo("Luogo","Indica l'indirizzo in cui si svolger� l�evento oppure il punto di ritrovo",true,STRING);
	private Campo data=new Campo("Data Evento"," Indica la data in cui si svolger� l'evento, o la data di inizio nel caso l'evento duri pi� giorni",true,DATE);
	private Campo ora=new Campo("Ora","Indica l'ora in cui i partecipanti dovranno recarisi nel luogo prestabilito",true,ORA);
	private Campo durata=new Campo("Durata","Indica la durata in termini di numero(approssimativo)di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano pi� giorni consecutivi",false,ORA);
	private Campo quotaIndividuale=new Campo("Quota individuale"," indica la spesa (o una stima della stessa) che ogni partecipante all�iniziativa dovr� sostenere (si noti che la spesa pu� anche essere nulla)",true,INT);
	private Campo compresoQuota=new Campo("Compreso nella quota"," indica tutte le voci di spesa comprese nell�ammontare indicato nella �Quota individuale�",false,STRING);
	private Campo dataFine=new Campo("Data conclusiva"," Indica la data di fine dell�evento",false,DATE);
    private Campo dataRitiroIscrizione=new Campo("Data ritiro iscrizione","Indica la data entro cui sia possibile: per il creatore dell'evento eliminare l'evento, per i partecipanti di annullare l'iscrizione dall'evento",false,DATE);
    private Campo oraFine=new Campo("Ora conclusiva"," Indica l'ora di fine dell�evento",false,ORA);
	private Campo note=new Campo("Note"," Contiene informazioni aggiuntive circa l'evento",false,STRING);
	private ArrayList<Campo> elencoCampi = new ArrayList<>();


	/*Costruttori*/
	/**
	 * Una categoria e' costituita da nome, descrizione, titolo, numero partecipanti, numero di tolleranza partecipanti, data termine iscrizione,
	 * luogo, data, ora, durata, quota individuale di partecipazione, compreso nella quota, data finale, data ritiro iscrizione, ora finale,
	 * note, elenco dei campi.
	 *
	 * @param _nome nome della categoria
	 * @param _descrizione descrizione della categoria
	 *
	 * @author Gabriele Manenti
	 */
	public Categoria(String _nome, String _descrizione){
		nome=_nome;
		descrizione= _descrizione;
	}

	/*Getters*/
	/**
	 * Ritorna il nome della Categoria
	 * @return nome della categoria
	 *
	 * @author Gabriele Manenti
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Ritorna la descrizione della Categoria
	 * @return descrizione del campo
	 *
	 * @author Gabriele Manenti
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * Ritorna il titolo dell'evento
	 * @return titolo dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getTitolo() {
		return titolo;
	}
	/**
	 * Ritorna il numero dei partecipanti
	 * @return numero dei partecipanti
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getnPartecipanti() {
		return nPartecipanti;
	}
	/**
	 * Ritorna la data di termine iscrizione
	 * @return la data di termine iscrizione
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getTermineIscrizione() {
		return termineIscrizione;
	}
	/**
	 * Ritorna il luogo dell'evento
	 * @return luogo dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getLuogo() {
		return luogo;
	}
	/**
	 * Ritorna l'ora dell'evento
	 * @return ora dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getOra() {
		return ora;
	}
	/**
	 * Ritorna la durata dell'evento
	 * @return durata dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getDurata() {
		return durata;
	}
	/**
	 * Ritorna la quota individuale di partecipazione
	 * @return quota individuale
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getQuotaIndividuale() {
		return quotaIndividuale;
	}
	/**
	 * Ritorna ciò che è compreso nella quota individuale
	 * @return compreso nella quota individuale
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getCompresoQuota() {
		return compresoQuota;
	}
	/**
	 * Ritorna la data di fine dell'evento
	 * @return data finale evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getDataFine() {
		return dataFine;
	}
	/**
	 * Ritorna l'ora di fine dell'evento
	 * @return ora finale evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getOraFine() {
		return oraFine;
	}
	/**
	 * Ritorna le note dell'evento
	 * @return note aggiuntive circa l'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getNote() {
		return note;
	}
	/**
	 * Ritorna un ArrayList dei campi dell'evento
	 * @return ArrayList dei campi dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}
	/**
	 * Ritorna la data dell'evento
	 * @return data dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getData() {
		return data;
	}
	/**
	 * Ritorna il numero di partecipanti in esubero
	 * @return tolleranza numero partecipanti
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getTolleranzaPartecipanti() {
		return tolleranzaPartecipanti;
	}
	/**
	 * Ritorna la data di ritiro iscrizione e di eliminazione dell'evento
	 * @return data di ritiro iscrizione e di eliminazione dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getDataRitiroIscrizione() {
		return dataRitiroIscrizione;
	}

	/*Setters*/
	/**
	 * Metodo che imposta il nome della Categoria
	 * @param nome della categoria
	 *
	 * @author Gabriele Manenti
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Metodo che imposta la descrizione della Categoria
	 * @param descrizione del campo
	 *
	 * @author Gabriele Manenti
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * Metodo che imposta il titolo dell'evento
	 * @param titolo dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setTitolo(Campo titolo) {
		this.titolo = titolo;
	}
	/**
	 * Metodo che imposta il numero di partecipanti
	 * @param nPartecipanti il numero di partecipanti
	 *
	 * @author Gabriele Manenti
	 */
	public void setnPartecipanti(Campo nPartecipanti) {
		this.nPartecipanti = nPartecipanti;
	}
	/**
	 * Metodo che imposta la data di termine iscrizione
	 * @param termineIscrizione  la data di termine iscrizione
	 *
	 * @author Gabriele Manenti
	 */
	public void setTermineIscrizione(Campo termineIscrizione) {
		this.termineIscrizione = termineIscrizione;
	}
	/**
	 * Metodo che imposta il luogo dell'evento
	 * @param luogo luogo dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setLuogo(Campo luogo) {
		this.luogo = luogo;
	}
	/**
	 * Metodo che imposta l'ora dell'evento
	 * @param ora l'ora dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setOra(Campo ora) {
		this.ora = ora;
	}
	/**
	 * Metodo che imposta la durata dell'evento
	 * @param durata la durata dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setDurata(Campo durata) {
		this.durata = durata;
	}
	/**
	 * Metodo che imposta la quota individuale
	 * @param quotaIndividuale la quota individuale di partecipazione per l'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setQuotaIndividuale(Campo quotaIndividuale) {
		this.quotaIndividuale = quotaIndividuale;
	}
	/**
	 * Metodo che imposta il compreso nella quota
	 * @param compresoQuota il compreso nella quota individuale
	 *
	 * @author Gabriele Manenti
	 */
	public void setCompresoQuota(Campo compresoQuota) {
		this.compresoQuota = compresoQuota;
	}
	/**
	 * Metodo che imposta la data di fine dell'evento
	 * @param dataFine la data di fine dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setDataFine(Campo dataFine) {
		this.dataFine = dataFine;
	}
	/**
	 * Metodo che imposta l'ora di fine dell'evento
	 * @param oraFine l'ora di fine dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setOraFine(Campo oraFine) {
		this.oraFine = oraFine;
	}
	/**
	 * Metodo che imposta le note dell'evento
	 * @param note le note dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setNote(Campo note) {
		this.note = note;
	}
	/**
	 * Permette di cambiare l'elenco di Campi
	 * @param elencoCampi il nuovo elenco
	 *
	 */
	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}
	/**
	 * Metodo che imposta la data dell'evento
	 * @param data la data dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setData(Campo data) {
		this.data = data;
	}
	/**
	 * Metodo che imposta il numero di partecipanti in esubero
	 * @param tolleranzaPartecipanti il numero di partecipanti in esubero
	 *
	 * @author Gabriele Manenti
	 */
	public void setTolleranzaPartecipanti(Campo tolleranzaPartecipanti) {
		this.tolleranzaPartecipanti = tolleranzaPartecipanti;
	}
	/**
	 * Metodo che imposta la data di ritiro iscrizione e di eliminazione evento
	 * @param dataRitiroIscrizione la data di ritiro iscrizione e di eliminazione evento
	 *
	 * @author Gabriele Manenti
	 */
	public void setDataRitiroIscrizione(Campo dataRitiroIscrizione) {
		this.dataRitiroIscrizione = dataRitiroIscrizione;
	}

	/*Metodi*/
	/**
	 * Crea array con tutti i campi relativi all'evento
	 *
	 * @author Gabriele Manenti
	 */
	public void creaArray(){
		elencoCampi.add(titolo);
		elencoCampi.add(nPartecipanti);
        elencoCampi.add(tolleranzaPartecipanti);
		elencoCampi.add(termineIscrizione);
		elencoCampi.add(luogo);
		elencoCampi.add(data);
		elencoCampi.add(ora);
		elencoCampi.add(durata);
		elencoCampi.add(quotaIndividuale);
		elencoCampi.add(compresoQuota);
		elencoCampi.add(dataFine);
        elencoCampi.add(dataRitiroIscrizione);
		elencoCampi.add(oraFine);
		elencoCampi.add(note);
	}
	/*Metodi*/
	/**
	 * Assegna i valori ai campi comuni a tutte le categorie
	 *
	 * @author Gabriele Manenti
	 */
	public void inserisciCampi()throws Exception{
		for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).inserisciValore();
			
		}
	}
	 public void inserisciValoriPredefiniti(){
	    	
	    }
	/**
	 * Ritorna una stringa che descrive tutti i campi di una categoria
	 *
	 * @return tutti i campi di una categoria
	 *
	 * @author Gabriele Manenti
	 */
	public void visualizzaCampi(){
		
		for (int i=0; i< elencoCampi.size(); i++){
			
			System.out.println(elencoCampi.get(i).visualizzaCampo());
		}
		
	}
	/**
	 * Ritorna il numero di partecipanti compresi quelli di tolleranza
	 * @return il numero di partecipanti compresi quelli di tolleranza
	 *
	 * @author Gabriele Manenti
	 */
	public int getPartecipantiMax() {
		if(tolleranzaPartecipanti.getValore().getInserito()){
			return (int) nPartecipanti.getValore().getValore() + (int) tolleranzaPartecipanti.getValore().getValore();
		}
		else{
			return (int) nPartecipanti.getValore().getValore();
		}
		
	}
	/**
	 * Ritorna il numero di partecipanti senza contare quelli in esubero
	 * @return il numero di partecipanti senza contare quelli in esubero
	 *
	 * @author Gabriele Manenti
	 */
	public int getPartecipantiMin() {
		if(tolleranzaPartecipanti.getValore().getInserito()){
			return (int) nPartecipanti.getValore().getValore() - (int) tolleranzaPartecipanti.getValore().getValore();
		}
		else{
			return (int) nPartecipanti.getValore().getValore();
		}
		
	}
	/**
	 * Ritorna l'ammontare della quota individuale di partecipazione all'evento
	 * @return l'ammontare della quota individuale di partecipazione all'evento
	 */
	public int sceltaOpzioni() {
			
			return (int) quotaIndividuale.getValore().getValore();
		}


	
}
