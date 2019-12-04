package Codice.Vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Codice.Modello.*;



import MyLib.Utility;

public class InputOutput {

	final static String NOME="Nome categoria: ";
	final static String STATO="Stato: ";
    final static String NOMEEVENTO="Nome evento: ";
	final static String DESCRIZIONE="Descrizione: ";
	final static String SCELTACATEGORIA="Quale categoria vuoi selezionare?";
	final static String SCELTAEVENTO = "Quale evento vuoi selezionare?";
    final static String MSGBENVENUTO="Benvenuto nella social di gestione eventi";
    final static String MSGLOGIN="Inserisci il tuo nome utente per effettuare il login";
    final static String NOMEMENU="GESTIONE Eventi";
    final static String NOMEMENUMSG="GESTIONE Messaggi";
    final static String[] OPZIONIMSG={"Visualizza messaggi", "Elimina messaggi","Modifica dati personali"};
    final static String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento","Pagina Utente", "Elimina Iscrizione evento", "Elimina evento","Invita persone ad evento","Genera evento standard per test"};
    final static String POSTILIBERI="Posti liberi: ";
    final static String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
    final static String SCELTAISCEVENTO="A quale evento desideri iscriverti?";
    final static String SCELTAINVITOEVENTO="A quale evento desideri invitare altri utenti?";
    final static String SCELTAEVENTOPUBBLICAZIONE ="Quale evento vuoi pubblicare?";
    final static String SCELTAMSG ="Quale messaggio vuoi eliminare?";
    final static String VALIDITAPUBBLICAZIONE = "L'evento selezionato � valido, � stato pubblicato ed � visibile sulla bacheca.";
    final static String NONVALIDITAPUBBLICAZIONE = "L'evento selezionato non � valido! Selezionare un altro evento. \n (Un Evento � valido solo se � stato assegnato un valore a tutti i campi obbligatori)";
    final static String BACHECAVUOTA = "Non vi sono eventi validi pubblicati.";
    final static String BACHECAEVENTIVUOTA = "Non vi sono eventi validi a cui ti � consentito iscriverti.";
    final static String EVENTIVUOTI = "Non ci sono eventi creati e non acora pubblicati in bacheca.";
    final static String EVENTIPUBBLICATIVUOTI = "Non hai ancora pubblicato eventi in bacheca.";
    final static String MESSAGGIVUOTI = "Non ci sono messaggi.";
    final static String MSGEVENTO="Evento creato con successo";
    final static String MSGPROBDATE="Le date non sono in ordine logico. DATE CANCELLATE";
    final static String SCELTAELIMISCRIZIONE= "A quale evento vuoi cancellare la tua iscrizione?";
    final static String ISCRIZIONIVUOTE= "Non sei Iscritto a nessun evento o � passata la data limite per il ritiro dell'iscrizione.";
    final static String CANCELLAZIONIVUOTE= "Non hai creato nessun evento o � passata la data limite per il ritiro dell'evento.";
    final static String SCELTAELIMINEVENTO= "Quale evento pubblicato vuoi cancellare?";
    final static String EVENTICREATIVUOTI= "Non hai creato nessun evento a cui poter invitare i tuoi amici.";
    final static String AMICIVUOTI= "Non ti � possibile invitare nessun utente";
    final static String SCELTAINVITO= "Quale utente vuoi invitare?";

	public static boolean richiestaInserimento(String nome){
		boolean inserimento= false;
		int ins = Utility.leggiIntero(0, 1, "Vuoi inserire " + nome + "? Digita 1 per SI e 0 pre NO");
		if (ins==1)
			inserimento=true;
		
		return inserimento;
	}
	public static String inserimentoStringa(String nome){
		String inserimento;
		inserimento=Utility.leggiLinea("inserisci " + nome);
		
		return inserimento;
	}
	public static int inserimentoInt(String nome){
		int inserimento;
		inserimento=Utility.leggiIntero(0, 9999999, "inserisci " + nome);
		
		return inserimento;
	}
	public static Date InserimentoData(String nome) throws Exception{
		String inserimento;
		inserimento=Utility.leggiData("inserisci " + nome + "(gg/mm/aaaa)");
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(inserimento);
		
		return data;
	}
	
	public static int[] inserimentoEta(String nome){
		int inserimento[];
		inserimento=Utility.leggiFasciaEta("Inserisci" + nome);
		
		
		return inserimento;
	}

	public static void visualizzaCategorie(ArrayList<CategoriaA> categorie1){
		stampaUscitaMenu();
		for(int i=0; i<categorie1.size();i++){
			System.out.println(i+1+")");
			System.out.println(NOME + categorie1.get(i).getNome());
			System.out.println(DESCRIZIONE + categorie1.get(i).getDescrizione()+"\n");
		}
	}

	public static int sceltaCategoria(int max) {
		int numCat = Utility.leggiIntero(1, max + 1, SCELTACATEGORIA);
		return numCat;
	}

	public static void visualizzaEventi(Utente utente){
            stampaUscitaMenu();
            for(int i=0; i<utente.getEventiUtente().size();i++){
                System.out.println(i+1 +")");
                if (utente.getEventiUtente().get(i).getCategoria().getTitolo().getValore().getInserito()){
                    System.out.println(NOMEEVENTO + utente.getEventiUtente().get(i).getCategoria().getTitolo().getValore().getValore());
                    System.out.println("Data Ritiro Iscrizione: " + utente.getEventiUtente().get(i).getCategoria().getDataRitiroIscrizione().getValore().getValore());
                }
                else {
                    System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
                }
                System.out.println(NOME + utente.getEventiUtente().get(i).getCategoria().getNome());
            }
    }

    public static int sceltaEvento(int max) {
        int numEvento = Utility.leggiIntero(0, max + 1, SCELTAEVENTO);
        return numEvento;
    }

    public static int sceltaInvitoEvento(ListaEventi bacheca) {
        int numInvitoEvento = Utility.leggiIntero(0, bacheca.getElencoEventi().size() + 1, SCELTAINVITOEVENTO);
        return numInvitoEvento;
    }

    public static int sceltaInserimento(){
        int num = Utility.leggiIntero(0,1, "Vuoi inserire completare l'evento? Digita 1 per SI e 0 per NO");
        return num;
    }

    public static int sceltaUtenteAmico(ArrayList<Utente> utentiInvitabili){
        int numAmico = Utility.leggiIntero(0, utentiInvitabili.size()+1, SCELTAINVITO);
        return numAmico;
    }

    public static int sceltaEliminaMessaggi(Utente utente){
        int numMsg=Utility.leggiIntero(0, utente.getMessaggiUtente().size(), SCELTAMSG);
        return numMsg;
    }

    public static int sceltaEliminaIscrizione(ListaEventi bacheca){
        int numEliminIscrizione = Utility.leggiIntero(0, bacheca.getElencoEventi().size() + 1, SCELTAELIMISCRIZIONE);
        return numEliminIscrizione;
    }

    public static int sceltaEliminaEvento(ListaEventi bacheca){
        int numEliminEventoPubblicato = Utility.leggiIntero(0, bacheca.getElencoEventi().size() + 1, SCELTAELIMINEVENTO);
        return numEliminEventoPubblicato;
    }
    
    public static int selezioneEvento(ArrayList<Evento> elencoEventi){
        int numEliminEventoPubblicato = Utility.leggiIntero(0,elencoEventi.size() + 1, SCELTAEVENTO);
        return numEliminEventoPubblicato;
    }
    
    public static void visualizzaEventiBacheca(ListaEventi bacheca){
            stampaUscitaMenu();
            for(int i=0; i<bacheca.getElencoEventi().size();i++){
                System.out.println(i+1 +")");
                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
                System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
                System.out.println(STATO + bacheca.getElencoEventi().get(i).getStato());
                System.out.println(POSTILIBERI + bacheca.getElencoEventi().get(i).getPostiLiberi());
            }

        
    }
    
    public static void visualizzaListaEventi(ArrayList<Evento> elencoEventi){
        stampaUscitaMenu();
        for(int i=0; i<elencoEventi.size();i++){
            System.out.println(i+1 +")");
            System.out.println(NOMEEVENTO + elencoEventi.get(i).getCategoria().getTitolo().getValore().getValore());
            System.out.println(NOME +elencoEventi.get(i).getCategoria().getNome());
            System.out.println(STATO + elencoEventi.get(i).getStato());
            System.out.println(POSTILIBERI + elencoEventi.get(i).getPostiLiberi());
        }

    
}
    
    public static void stampaCampo(String nome, String descrizione, Boolean obbligatorio){
    	String info= new String("Nome: "+ nome +"\n" +"Descrizione: " + descrizione+ "\n"+ "Obbligatoria: " + obbligatorio +"\n");
    	System.out.println(info);
    }

    public static void visualizzaMessaggiUtente(Utente utente){
        for(int i=0; i<utente.getMessaggiUtente().size();i++){
            System.out.println(i+1 +")");
            System.out.println(utente.getMessaggiUtente().get(i).getTesto());
        }
    }
    public static void visualizzaEventiUtente(ListaEventi bacheca, Utente utente){
        stampaUscitaMenu();
        for(int i=0; i<bacheca.getElencoEventi().size();i++){
            if (utente.confrontaUtente(bacheca.getElencoEventi().get(i).getCreatore()) && bacheca.getElencoEventi().get(i).controlloDataEliminazione()){
                System.out.println(i+1 +")");
                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
            }
        }

    }
    public static void stampaIscrVuote(){
        System.out.println(ISCRIZIONIVUOTE);
    }


    public static void stampaMsgEvento(){
        System.out.println(MSGEVENTO);
    }

    public static void stampaAmiciVuoti(){
        System.out.println(AMICIVUOTI);
    }


    public static void stampaEventiCreatiVuoti(){
        System.out.println(EVENTICREATIVUOTI);
    }


    public static void stampaBenvenuto(){
        System.out.println(MSGBENVENUTO);
    }

    public static void stampaEventiVuoti(){
        System.out.println(EVENTIVUOTI);
    }

    public static void stampaEventiNonValidi(){
        System.out.println(NONVALIDITAPUBBLICAZIONE);
    }


    public static void stampaMsgProbDate(){
        System.out.println(MSGPROBDATE);
    }

    public static void stampaBachecaVuota(){
        System.out.println(BACHECAVUOTA);
    }

    public static void stampaBachecaEventiValidiVuota(){
        System.out.println(BACHECAEVENTIVUOTA);
    }

    public static void stampaMessaggiVuoti(){
        System.out.println(MESSAGGIVUOTI);
    }
    public static String login(){
        String utente = Utility.leggiStringa(MSGLOGIN);
        return utente;
    }

    public static void stampaUscitaMenu(){
        System.out.println("0) Esci");
    }

    public static void stampaCancellazioniVuote(){
        System.out.println(CANCELLAZIONIVUOTE);
    }
    public static void  visualizzaUtentiInvitabili(ArrayList <Utente> utentiInvitabili){
        stampaUscitaMenu();
        for (int i=0; i<utentiInvitabili.size();i++){
            System.out.println(i+1+")");
            System.out.println("Nome Utente: " + utentiInvitabili.get(i).getNomeUtente());
        }
    }
	public static int sceltaOpzioniGita(int costo, int pasti, int trasporti) {
		int inserimento1= Utility.leggiIntero(0,1, "Vuoi usufruire dei pasti forniti dall'organizzazione? (Costo "+ pasti +" Euro) Digita 1 per SI e 0 pre NO");
		if(inserimento1==0){
		
		}
		else{
			costo=costo + pasti;
		}
		
		
		int inserimento2= Utility.leggiIntero(0,1, "Vuoi usufruire del trasporto fornito dall'organizzazione? (Costo "+ trasporti +" Euro) Digita 1 per SI e 0 pre NO");
		if(inserimento2==0){
			
		}
		else{
			costo=costo + trasporti;
		}
		return 0;
	}
	public static boolean richiestaInserimentoCategoriePreferite() {
		int inserimento= Utility.leggiIntero(0,1, "Vuoi modificare l'elenco delle tue categorie preferite? Digita 1 per SI e 0 pre NO");
		if (inserimento==1)
			return true;
		else 
			return false;
	}
	public static boolean richiestaInserimentoFasciaEta() {
		int inserimento= Utility.leggiIntero(0,1, "Vuoi inserire la tua fascia di età? Digita 1 per SI e 0 per NO");
		if (inserimento==1)
			return true;
		else 
			return false;
	}


}
