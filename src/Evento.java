import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

public class Evento implements Serializable{
	
	
	
	final String[] TESTOCHIUSURA={"L'evento "," ha raggiunto un numero sufficiente di iscrizioni e si terra dunque in data "," alle ore "," presso ",". Si ricorda che � necessatrio versare la quota di iscrizione di "," Euro."};
	final String[] TESTOFALLITO={"L'evento "," NON ha raggiunto un numero sufficiente di iscrizioni ed � quindi stato cancellato."};
	final String[] TESTOANNULLATO={"L'evento "," E' stato cancellato dall'organizzatore."};
	
	
	//Attributi
	private Categoria categoria;
	private Boolean validita;
	private Utente creatore;
	private ArrayList <Iscrizioni> elencoIscritti = new ArrayList<>();
	private String stato;

	
	
	//Costruttori
	public Evento(Categoria _categoria, Utente _creatore){
		categoria= _categoria;
		creatore=_creatore;
		validita = false;
		stato= "Aperta";
	}
	
	
	
	//Metodi
	
	
	//Metodo che verifica che tutti i campi obbligatori abbiano inserito un valore
	public void isValido(){
		validita=true;
		
		for (int i=0; i<categoria.getElencoCampi().size(); i++){
			if(categoria.getElencoCampi().get(i).getObbligatorio()&& !categoria.getElencoCampi().get(i).getValore().getInserito()){
				validita=false;
			}
		}
		
	}
	
	
	// Metodo che permette di inserire i valori a campi dell'evento
	public void inserisciDettagliEvento()throws Exception{
		categoria.inserisciCampi();
	}
	
	
	// Metodo che genera un evento standard per velocizzare la fase di testing
	public void inserisciValoriPredefinitiEvento()throws Exception{
		Date termineIsc=new Date("10/07/2019");
		Date dataEV=new Date("10/08/2019");
		Date dataFine=new Date("10/09/2019");
		Date dataRitiroIsc=new Date("10/06/2019");
		
		categoria.getTitolo().getValore().setValore("Amichevole test");
		categoria.getnPartecipanti().getValore().setValore(5);
		categoria.getTolleranzaPartecipanti().getValore().setValore(2);
		categoria.getTermineIscrizione().getValore().setValore(termineIsc);
		categoria.getLuogo().getValore().setValore("Predore");
		categoria.getData().getValore().setValore(dataEV);
		categoria.getOra().getValore().setValore("10:10");
		categoria.getDurata().getValore().setValore("02:00");
		categoria.getQuotaIndividuale().getValore().setValore("10 Euro");
		categoria.getCompresoQuota().getValore().setValore("Niente");
		categoria.getDataFine().getValore().setValore(dataFine);
		categoria.getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
		categoria.getOraFine().getValore().setValore("12:10");
		categoria.getNote().getValore().setValore("Vuoto");
		categoria.inserisciValoriPredefiniti();
		
	}
	
	// Metodo che controlla se un utente � gi� iscritto ad un evento
	public Boolean giaIscritto(Utente utente) {
		Boolean iscritto= false;
		
		for(int i=0; i< elencoIscritti.size(); i++){
			if (utente.confrontaUtenteStringa(elencoIscritti.get(i).getUtente())){
				iscritto= true;
			}
		}
		
		
		return iscritto;
	}
	
	
	// Controlla che le date siano inserite in maniera coerente con il loro significato
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
	
	// Metodo che controlla se il numero di partecipanti di un evento ha raggiunto il limite e se � vero genere i messaggi
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


	// Metodo che controlla se si � superata la data di termine iscrizione o quella di svolgimento dell'evento
	public ArrayList<Messaggio> controlloData(){
		
		// Data odierna per effettuare il confronto
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		
		ArrayList<Messaggio> messaggiStato = new ArrayList<>();
		
		// Verifica se � stata passata la data conclusiva dell'evento (nel caso sia inserita)o la data dell'evento
		if(categoria.getDataFine().getValore().getInserito()){
			if( ((Date) categoria.getDataFine().getValore().getValore()).before(date)){
				if (getPostiLiberi()==0){
					stato= "Conclusa";
				}
			}
		}
		else{
			if( ((Date) categoria.getData().getValore().getValore()).before(date)){
				if (getPostiLiberi()==0){
					stato= "Conclusa";
				}
			}
		}
		
		// Controla se � stata superata la data di termine delle iscrizioni senza aver raggiunto il numero minimo di iscritti
		// Genera dei messaggi in caso affermativo
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
		
		
		return messaggiStato;
	}
	
	// Manda messaggi per eventi cancellati
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


	public boolean controlloDataEliminazione(){
		Boolean valido= true;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Date ultimaIscr = (Date) categoria.getDataRitiroIscrizione().getValore().getValore();

		if(date.before(ultimaIscr) || date.equals(ultimaIscr)){
			valido = true;
		}else {
			valido = false;
		}

		return valido;

	}
	
	// Metodo che ritorna il numero di posti liberi di un evento
	public int getPostiMinimiPartecipanti(){
			return categoria.getPartecipantiMin() - elencoIscritti.size();
	}

	public int getPostiLiberi(){
		
		return categoria.getPartecipantiMax() - elencoIscritti.size();
	}
	
	
	
	public int sceltaOpzioniGita() {
		int costo=categoria.sceltaOpzioni();
		return costo;
	}
	

	// Getters and Setters generati automaticamente
	public Categoria getCategoria() {
		return categoria;
	}

	

	public Boolean getvalidita() {
		return validita;
	}




	public Utente getCreatore() {
		return creatore;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public void setvalidita(Boolean validita) {
		this.validita = validita;
	}
	


	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}



	public ArrayList<Iscrizioni> getElencoIscritti() {
		return elencoIscritti;
	}



	public void setElencoIscritti(ArrayList<Iscrizioni> elencoIscritti) {
		this.elencoIscritti = elencoIscritti;
	}



	public String getStato() {
		return stato;
	}



	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
	
	
	

}
