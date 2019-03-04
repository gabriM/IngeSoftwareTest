import java.io.Serializable;
import java.util.ArrayList;


import java.util.ArrayList;


public class Categoria implements Serializable{
	
	//Costanti
	public static final int STRING = 0;
	public static final int INT = 1;
	public static final int DATE = 2;
	public static final int ORA = 3;
	
	
	
	
	
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
	
	
	//Costruttori
	public Categoria(String _nome, String _descrizione){
		nome=_nome;
		descrizione= _descrizione;
	}

	//Metodi
	
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
	
	//Metodo che permette di inserire i valori ai campi comuni a tutte le categorie
	public void inserisciCampi()throws Exception{
		for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).inserisciValore();
			
		}
	}
	
	 public void inserisciValoriPredefiniti(){
	    	
	    }
	
	// Visualizza tutti i campi di una categoria
	public void visualizzaCampi(){
		
		for (int i=0; i< elencoCampi.size(); i++){
			
			System.out.println(elencoCampi.get(i).visualizzaCampo());
		}
		
	}
	
	public int getPartecipantiMax() {
		if(tolleranzaPartecipanti.getValore().getInserito()){
			return (int) nPartecipanti.getValore().getValore() + (int) tolleranzaPartecipanti.getValore().getValore();
		}
		else{
			return (int) nPartecipanti.getValore().getValore();
		}
		
	}
	
	public int getPartecipantiMin() {
		if(tolleranzaPartecipanti.getValore().getInserito()){
			return (int) nPartecipanti.getValore().getValore() - (int) tolleranzaPartecipanti.getValore().getValore();
		}
		else{
			return (int) nPartecipanti.getValore().getValore();
		}
		
	}
	
	public int sceltaOpzioni() {
			
			return (int) quotaIndividuale.getValore().getValore();
		}
		
	
	// Getters and Setters generati automaticamente
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


	public Campo getTermineIscrizione() {
		return termineIscrizione;
	}


	public Campo getLuogo() {
		return luogo;
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


	public Campo getOraFine() {
		return oraFine;
	}


	public Campo getNote() {
		return note;
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


	public void setTermineIscrizione(Campo termineIscrizione) {
		this.termineIscrizione = termineIscrizione;
	}


	public void setLuogo(Campo luogo) {
		this.luogo = luogo;
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


	public void setOraFine(Campo oraFine) {
		this.oraFine = oraFine;
	}


	public void setNote(Campo note) {
		this.note = note;
	}

	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}

	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}

	public Campo getData() {
		return data;
	}

	public void setData(Campo data) {
		this.data = data;
	}

    public Campo getTolleranzaPartecipanti() {
        return tolleranzaPartecipanti;
    }

    public void setTolleranzaPartecipanti(Campo tolleranzaPartecipanti) {
        this.tolleranzaPartecipanti = tolleranzaPartecipanti;
    }

    public Campo getDataRitiroIscrizione() {
        return dataRitiroIscrizione;
    }

    public void setDataRitiroIscrizione(Campo dataRitiroIscrizione) {
        this.dataRitiroIscrizione = dataRitiroIscrizione;
    }

	
}
