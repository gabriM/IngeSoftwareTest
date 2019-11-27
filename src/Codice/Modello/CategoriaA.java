package Codice.Modello;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class CategoriaA implements Serializable{
	final String NOME = "Nome categoria: ";
	final String STATO = "Stato: ";
	final String POSTILIBERI = "Posti liberi: ";
	final String DESCRIZIONE = "Descrizione: ";
	
	
	
	
	private String nome;
	private String descrizione;
	private Campo titolo = new Campo("Titolo", "Consiste in un nome di fantasia attribuito all�evento", false, new testoV());
	private Campo nPartecipanti = new Campo("Numero di partecipanti", " Stabilisce il numero di persone da coinvolgere nell�evento", true, new interoV());
	private Campo tolleranzaPartecipanti = new Campo("Numero di partecipanti in esubero", " Stabilisce il numero di persone in esubero al numero di partecipanti", false, new interoV());
	private Campo termineIscrizione = new Campo("Termine ultimo iscrizione", "Indica l'ultimo giorno utile per iscriversi all�evento", true, new DataV());
	private Campo luogo = new Campo("Luogo", "Indica l'indirizzo in cui si svolger� l�evento oppure il punto di ritrovo", true, new testoV());
	private Campo data = new Campo("Data Evento", " Indica la data in cui si svolger� l'evento, o la data di inizio nel caso l'evento duri pi� giorni", true, new DataV());
	private Campo ora = new Campo("Ora", "Indica l'ora in cui i partecipanti dovranno recarisi nel luogo prestabilito", true, new testoV());
	private Campo durata = new Campo("Durata", "Indica la durata in termini di numero(approssimativo)di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano pi� giorni consecutivi", false, new testoV());
	private Campo quotaIndividuale = new Campo("Quota individuale", " indica la spesa (o una stima della stessa) che ogni partecipante all�iniziativa dovr� sostenere (si noti che la spesa pu� anche essere nulla)", true, new interoV());
	private Campo compresoQuota = new Campo("Compreso nella quota", " indica tutte le voci di spesa comprese nell�ammontare indicato nella �Quota individuale�", false, new testoV());
	private Campo dataFine = new Campo("Data conclusiva", " Indica la data di fine dell�evento", false, new DataV());
	private Campo dataRitiroIscrizione = new Campo("Data ritiro iscrizione", "Indica la data entro cui sia possibile: per il creatore dell'evento eliminare l'evento, per i partecipanti di annullare l'iscrizione dall'evento", false, new DataV());
	private Campo oraFine = new Campo("Ora conclusiva", " Indica l'ora di fine dell�evento", false, new testoV());
	private Campo note = new Campo("Note", " Contiene informazioni aggiuntive circa l'evento", false, new testoV());
	private ArrayList<Campo> elencoCampi = new ArrayList<>();
	
	
	
	public abstract void creaArrayCampi();
	public abstract void inserisciCampi() throws Exception;
	public abstract void visualizzaCampi();
	public abstract int sceltaOpzioni();
	
	
	public CategoriaA(String _nome, String _descrizione) {
		nome = _nome;
		descrizione = _descrizione;
	}
	
	
	public int getPartecipantiMax() {
		if (tolleranzaPartecipanti.getValore().getInserito()) {
			return (int) nPartecipanti.getValore().getValore() + (int) tolleranzaPartecipanti.getValore().getValore();
		} else {
			return (int) nPartecipanti.getValore().getValore();
		}

	}
	
	public int getPartecipantiMin() {
		if (tolleranzaPartecipanti.getValore().getInserito()) {
			return (int) nPartecipanti.getValore().getValore() - (int) tolleranzaPartecipanti.getValore().getValore();
		} else {
			return (int) nPartecipanti.getValore().getValore();
		}

	}
	
	public void setAutomaticoDataRitiroIscrizione() {
		if(!dataRitiroIscrizione.getValore().getInserito()){
			setDataRitiroIscrizione(termineIscrizione);
		}
		
	}
	
	
	
	
	public String getNome() {
		return nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Campo getTitolo() {
		return titolo;
	}
	public Campo getnPartecipanti() {
		return nPartecipanti;
	}
	public Campo getTolleranzaPartecipanti() {
		return tolleranzaPartecipanti;
	}
	public Campo getTermineIscrizione() {
		return termineIscrizione;
	}
	public Campo getLuogo() {
		return luogo;
	}
	public Campo getData() {
		return data;
	}
	public Campo getOra() {
		return ora;
	}
	public Campo getDurata() {
		return durata;
	}
	public Campo getQuotaIndividuale() {
		return quotaIndividuale;
	}
	public Campo getCompresoQuota() {
		return compresoQuota;
	}
	public Campo getDataFine() {
		return dataFine;
	}
	public Campo getDataRitiroIscrizione() {
		return dataRitiroIscrizione;
	}
	public Campo getOraFine() {
		return oraFine;
	}
	public Campo getNote() {
		return note;
	}
	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setTitolo(Campo titolo) {
		this.titolo = titolo;
	}
	public void setnPartecipanti(Campo nPartecipanti) {
		this.nPartecipanti = nPartecipanti;
	}
	public void setTolleranzaPartecipanti(Campo tolleranzaPartecipanti) {
		this.tolleranzaPartecipanti = tolleranzaPartecipanti;
	}
	public void setTermineIscrizione(Campo termineIscrizione) {
		this.termineIscrizione = termineIscrizione;
	}
	public void setLuogo(Campo luogo) {
		this.luogo = luogo;
	}
	public void setData(Campo data) {
		this.data = data;
	}
	public void setOra(Campo ora) {
		this.ora = ora;
	}
	public void setDurata(Campo durata) {
		this.durata = durata;
	}
	public void setQuotaIndividuale(Campo quotaIndividuale) {
		this.quotaIndividuale = quotaIndividuale;
	}
	public void setCompresoQuota(Campo compresoQuota) {
		this.compresoQuota = compresoQuota;
	}
	public void setDataFine(Campo dataFine) {
		this.dataFine = dataFine;
	}
	public void setDataRitiroIscrizione(Campo dataRitiroIscrizione) {
		this.dataRitiroIscrizione = dataRitiroIscrizione;
	}
	public void setOraFine(Campo oraFine) {
		this.oraFine = oraFine;
	}
	public void setNote(Campo note) {
		this.note = note;
	}
	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}

	
	
}
