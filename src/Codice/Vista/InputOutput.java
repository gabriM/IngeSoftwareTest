package Codice.Vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Codice.Modello.*;


import Codice.Modello.Categoria;
import MyLib.Utility;

public class InputOutput {

	final static String NOME="Nome categoria: ";
	final static String STATO="Stato: ";
    final static String NOMEEVENTO="Nome evento: ";
	final static String DESCRIZIONE="Descrizione: ";
	final static String SCELTACATEGORIA="Quale categoria vuoi selezionare?";
	final static String SCELTAEVENTO = "Quale evento vuoi selezionare?";
    final String MSGBENVENUTO="Benvenuto nella social di gestione eventi";
    final String MSGLOGIN="Inserisci il tuo nome utente per effettuare il login";
    final String NOMEMENU="GESTIONE Eventi";
    final String NOMEMENUMSG="GESTIONE Messaggi";
    final String[] OPZIONIMSG={"Visualizza messaggi", "Elimina messaggi","Modifica dati personali"};
    final String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento","Pagina Utente", "Elimina Iscrizione evento", "Elimina evento","Invita persone ad evento","Genera evento standard per test"};
    final static String POSTILIBERI="Posti liberi: ";
    final String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
    final String SCELTAISCEVENTO="A quale evento desideri iscriverti?";
    final String SCELTAINVITOEVENTO="A quale evento desideri invitare altri utenti?";
    final String SCELTAEVENTOPUBBLICAZIONE ="Quale evento vuoi pubblicare?";
    final String SCELTAMSG ="Quale messaggio vuoi eliminare?";
    final String VALIDITAPUBBLICAZIONE = "L'evento selezionato � valido, � stato pubblicato ed � visibile sulla bacheca.";
    final String NONVALIDITAPUBBLICAZIONE = "L'evento selezionato non � valido! Selezionare un altro evento. \n (Un Evento � valido solo se � stato assegnato un valore a tutti i campi obbligatori)";
    final static String BACHECAVUOTA = "Non vi sono eventi validi pubblicati.";
    final String BACHECAEVENTIVUOTA = "Non vi sono eventi validi a cui ti � consentito iscriverti.";
    final String EVENTIVUOTI = "Non ci sono eventi creati e non acora pubblicati in bacheca.";
    final String EVENTIPUBBLICATIVUOTI = "Non hai ancora pubblicato eventi in bacheca.";
    final String MESSAGGIVUOTI = "Non ci sono messaggi.";
    final String MSGEVENTO="Evento creato con successo";
    final String MSGPROBDATE="Le date non sono in ordine logico. DATE CANCELLATE";
    final String SCELTAELIMISCRIZIONE= "A quale evento vuoi cancellare la tua iscrizione?";
    final String ISCRIZIONIVUOTE= "Non sei Iscritto a nessun evento o � passata la data limite per il ritiro dell'iscrizione.";
    final String CANCELLAZIONIVUOTE= "Non hai creato nessun evento o � passata la data limite per il ritiro dell'evento.";
    final String SCELTAELIMINEVENTO= "Quale evento pubblicato vuoi cancellare?";
    final String EVENTICREATIVUOTI= "Non hai creato nessun evento a cui poter invitare i tuoi amici.";
    final String AMICIVUOTI= "Non ti � possibile invitare nessun utente";
    final String SCELTAINVITO= "Quale utente vuoi invitare?";
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

	public static void visualizzaCategorie(ArrayList<Categoria> categorie1){
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
        int numEvento = Utility.leggiIntero(1, max + 1, SCELTAEVENTO);
        return numEvento;
    }

    public static int sceltaInserimento(){
        int num = Utility.leggiIntero(0,1, "Vuoi inserire completare l'evento? Digita 1 per SI e 0 per NO");
        return num;
    }

    public static void visualizzaEventiBacheca(ListaEventi bacheca){

        if(bacheca.controlloEventiBacheca()){
            for(int i=0; i<bacheca.getElencoEventi().size();i++){
                System.out.println(i+1 +")");
                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
                System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
                System.out.println(STATO + bacheca.getElencoEventi().get(i).getStato());
                System.out.println(POSTILIBERI + bacheca.getElencoEventi().get(i).getPostiLiberi());
            }

        }else{
            System.out.println(BACHECAVUOTA);
        }
    }

}
