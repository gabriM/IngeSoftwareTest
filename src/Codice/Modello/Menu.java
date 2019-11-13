package Codice.Modello;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Codice.Vista.*;
import MyLib.ServizioFile;
import MyLib.*;
import Codice.Modello.*;

public class Menu {

    /*Costanti*/
    final String MSGBENVENUTO="Benvenuto nella social di gestione eventi";
    final String MSGLOGIN="Inserisci il tuo nome utente per effettuare il login";
    final String NOMEMENU="GESTIONE Eventi";
    final String NOMEMENUMSG="GESTIONE Messaggi";
    final String[] OPZIONIMSG={"Visualizza messaggi", "Elimina messaggi","Modifica dati personali"};
    final String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento","Pagina Utente", "Elimina Iscrizione evento", "Elimina evento","Invita persone ad evento","Genera evento standard per test"};
    final String NOME="Nome categoria: ";
    final String STATO="Stato: ";
    final String POSTILIBERI="Posti liberi: ";
    final String DESCRIZIONE="Descrizione: ";
    final String SCELTACATEGORIA="Quale categoria vuoi vedere in dettaglio?";
    final String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
    final String SCELTAISCEVENTO="A quale evento desideri iscriverti?";
    final String SCELTAINVITOEVENTO="A quale evento desideri invitare altri utenti?";
    final String SCELTAEVENTOPUBBLICAZIONE ="Quale evento vuoi pubblicare?";
    final String SCELTAMSG ="Quale messaggio vuoi eliminare?";
    final String NOMEEVENTO="Nome evento: ";
    final String VALIDITAPUBBLICAZIONE = "L'evento selezionato � valido, � stato pubblicato ed � visibile sulla bacheca.";
    final String NONVALIDITAPUBBLICAZIONE = "L'evento selezionato non � valido! Selezionare un altro evento. \n (Un Evento � valido solo se � stato assegnato un valore a tutti i campi obbligatori)";
    final String BACHECAVUOTA = "Non vi sono eventi validi pubblicati.";
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

    /*Attributi*/
    private String destinatario;
    private String testo;
    public ArrayList<Categoria> categorie;
    public ListaEventi bacheca;
    public ArrayList<Utente> elencoUtenti;
    public Partita partita;
    public Gita gita;
    public File filebacheca;
    public File fileutenti;

    public void startMenu() {

        categorie = new ArrayList<>();
        bacheca = new ListaEventi();
        elencoUtenti = new ArrayList<>();

        partita = new Partita();
        gita = new Gita();
        categorie.add(partita);
        categorie.add(gita);
    }

    public void salvaFile() {

        filebacheca = new File ("Bacheca.txt");
        fileutenti = new File ("Utenti.txt");

        /*Caricamento dati del programma da File*/
        if (ServizioFile.esistenzaFile(fileutenti) == 0) {
            ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
        } else
            //elencoUtenti=  (ArrayList<Utente>) ServizioFile.caricaSingoloOggetto(fileutenti);

            if (ServizioFile.esistenzaFile(filebacheca) == 0) {
                ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
            } else
        //bacheca= (ListaEventi) ServizioFile.caricaSingoloOggetto(filebacheca);

    }

/*
    public sceltaMenu(){

        GUI myMenu= new GUI(NOMEMENU,OPZIONI);
        int scelta;

        do{
            scelta=myMenu.scegli();

            /*Controlli su eventi in Bacheca e generazione di eventuali messaggi*/

            Messaggio.generaMessaggi(bacheca, elencoUtenti);
            ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);

            /*Eliminazione eventi Falliti, Chiusi, Conclusi*/

            Evento.eliminaEventoBacheca(bacheca);
            ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);

            switch(scelta)
            {
                case 0:
                    break;

                case 1:
                    /*Visualizza categorie*/
                    InputOutput.visualizzaCategorie(categorie);
                    categorie.get((InputOutput.sceltaCategoria(categorie.size()))-1).visualizzaCampi();

                    break;
                case 2:
                    /*Crea nuovo evento*/

                    elencoCategorieEvento(categorie);
                    /*

                    /*Elenco delle categorie a cui può appartenere l'evento*/

                    /*
                    for(int i=0; i<categorie.size();i++){
                        System.out.println(i+1+")");
                        System.out.println(NOME + categorie.get(i).getNome());
                        System.out.println(DESCRIZIONE + categorie.get(i).getDescrizione()+"\n");
                    }

                    int numCatEvento=Utility.leggiIntero(1, categorie.size()+1, SCELTACATEGORIAEVENTO);
                    */

                    /*Richiesta inserimento dettaglio eventi*/
                    switch(numCatEvento){

                        case 1:
                            Evento eventoP= new Evento(partita,elencoUtenti.get(numUtente));
                            eventoP.inserisciDettagliEvento();
                            Iscrizioni iscrizioneP=new Iscrizioni(elencoUtenti.get(numUtente).getNomeUtente(),eventoP);
                            eventoP.getElencoIscritti().add(iscrizioneP);
                            elencoUtenti.get(numUtente).getEventiUtente().add(eventoP);
                            break;
                        case 2:
                            Evento eventoG= new Evento(gita,elencoUtenti.get(numUtente));
                            eventoG.inserisciDettagliEvento();
                            Iscrizioni iscrizioneG=new Iscrizioni(elencoUtenti.get(numUtente).getNomeUtente(),eventoG);
                            eventoG.getElencoIscritti().add(iscrizioneG);
                            elencoUtenti.get(numUtente).getEventiUtente().add(eventoG);
                            break;
                    }

                    System.out.println(MSGEVENTO);


                    /*Salvataggio file*/
                    ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);



                    break;




                case 3:
                    /*Visualizza i miei eventi non pubblicati*/
                    if(elencoUtenti.get(numUtente).getEventiUtente().size()!=0){
                        for(int i=0; i<elencoUtenti.get(numUtente).getEventiUtente().size();i++){
                            System.out.println(i+1 +")");
                            if (elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getInserito()){
                                System.out.println(NOMEEVENTO + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getValore());
                                System.out.println("Data Ritiro Iscrizione: " + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getDataRitiroIscrizione().getValore().getValore());
                            }
                            else {
                                System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
                            }
                            System.out.println(NOME + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getNome());
                        }
                    }else {
                        System.out.println(EVENTIVUOTI);
                    }
                    break;
                case 4:
                    /*Pubblica eventi*/

                    /*Visualizza i propri eventi non ancora inseriti*/
                    if(elencoUtenti.get(numUtente).getEventiUtente().size()!=0){
                        System.out.println("0) Esci");
                        for(int i=0; i<elencoUtenti.get(numUtente).getEventiUtente().size();i++){
                            System.out.println(i +1 +")");
                            if (elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getInserito()){
                                System.out.println(NOMEEVENTO + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getValore() );
                            }
                            else {
                                System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
                            }
                            System.out.println(NOME + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getNome());
                        }

                        /*Scelta evento da pubblicare*/
                        int numEventoPubblicato=Utility.leggiIntero(0, elencoUtenti.get(numUtente).getEventiUtente().size(), SCELTAEVENTOPUBBLICAZIONE);


                        if(numEventoPubblicato!=0){

                            Evento eventop = elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1);


                            eventop.isValido();
                            /*Controllo validita evento*/
                            if(eventop.getvalidita() == true){
                                if(!eventop.getCategoria().getDataRitiroIscrizione().getValore().getInserito()){
                                    eventop.getCategoria().setDataRitiroIscrizione(eventop.getCategoria().getTermineIscrizione());
                                }
                                if(eventop.controlloDate()){
                                    System.out.println(VALIDITAPUBBLICAZIONE);

                                    /*Pubblicazione evento*/
                                    bacheca.getElencoEventi().add(eventop);
                                    elencoUtenti.get(numUtente).getEventiUtente().remove(numEventoPubblicato-1);

                                    /*Messaggi ad utenti con categoria dinteresse uguale a quella dell'evento*/
                                    String nomeCategoria=eventop.getCategoria().getNome();
                                    String nomeEventop;
                                    if (eventop.getCategoria().getTitolo().getValore().getInserito())
                                        nomeEventop=(String) eventop.getCategoria().getTitolo().getValore().getValore();
                                    else
                                        nomeEventop="Titolo non ancora inserito";

                                    for (int i=0; i<elencoUtenti.size(); i++){
                                        for(int j=0;j<elencoUtenti.get(i).getCategorieInteresse().size();j++){
                                            if(nomeCategoria.equalsIgnoreCase(elencoUtenti.get(i).getCategorieInteresse().get(j).getNome())){
                                                String testo="L'utente " + eventop.getCreatore().getNomeUtente() + " ha pubblicato in bacheca un evento della categoria "+ nomeCategoria + " dal nome " + nomeEventop;
                                                Messaggio msg=new Messaggio(elencoUtenti.get(i).getNomeUtente(),testo);
                                                elencoUtenti.get(i).getMessaggiUtente().add(msg);
                                            }
                                        }

                                    }

                                }
                                else{
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getData().getValore().removeValore();
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getDataFine().getValore().removeValore();
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getTermineIscrizione().getValore().removeValore();
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getDataRitiroIscrizione().getValore().removeValore();
                                    System.out.println(MSGPROBDATE);
                                }
                            }
                            else{

                                /*Evento non valido*/
                                System.out.println(NONVALIDITAPUBBLICAZIONE);

                                /*Possibilità di inserire altri dettagli all'evento*/
                                int inserimento= Utility.leggiIntero(0,1, "Vuoi inserire completare l'evento? Digita 1 per SI e 0 pre NO");
                                if (inserimento==1){
                                    eventop.inserisciDettagliEvento();
                                }
                            }
                        }


                    }
                    else {
                        System.out.println(EVENTIVUOTI);
                    }

                    ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                    ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
                    break;
                case 5:
                    /*Visualizza Bacheca*/

                    if(bacheca.getElencoEventi().size() != 0){
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
                    break;

                case 6:
                    /*Partecipa a evento*/

                    Boolean eventiValidi=false;

                    /*Controlla se esite almeno un evento a cui potersi iscrivere*/
                    for(int i=0; i< bacheca.getElencoEventi().size(); i++){
                        if(!bacheca.getElencoEventi().get(i).giaIscritto(elencoUtenti.get(numUtente))&& bacheca.getElencoEventi().get(i).getStato().equalsIgnoreCase("Aperta")&& bacheca.getElencoEventi().get(i).getPostiLiberi()>0)
                            eventiValidi=true;
                    }



                    if(eventiValidi){
                        /*Visualizzazione eventi presenti in bacheca*/

                        System.out.println("0) Esci");
                        for(int i=0; i<bacheca.getElencoEventi().size();i++){

                            if(!bacheca.getElencoEventi().get(i).giaIscritto(elencoUtenti.get(numUtente))&& bacheca.getElencoEventi().get(i).getStato().equalsIgnoreCase("Aperta")&& bacheca.getElencoEventi().get(i).getPostiLiberi()>0){
                                System.out.println(i+1 +")");
                                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
                                System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
                                System.out.println(POSTILIBERI + bacheca.getElencoEventi().get(i).getPostiLiberi());
                            }

                        }

                        /*Scelta eventi*/
                        int numIscEvento=Utility.leggiIntero(0, bacheca.getElencoEventi().size() +1, SCELTAISCEVENTO);

                        if (numIscEvento!=0){
                            Iscrizioni iscrizione=new Iscrizioni(elencoUtenti.get(numUtente).getNomeUtente(),bacheca.getElencoEventi().get(numIscEvento-1));
                            int costo=bacheca.getElencoEventi().get(numIscEvento-1).sceltaOpzioniGita();
                            iscrizione.setCosto(costo);
                            bacheca.getElencoEventi().get(numIscEvento-1).getElencoIscritti().add(iscrizione);
                            String nomeCreatore=bacheca.getElencoEventi().get(numIscEvento-1).getCreatore().getNomeUtente();
                            for(int i=0; i<elencoUtenti.size();i++){
                                if (elencoUtenti.get(i).getNomeUtente().equalsIgnoreCase(nomeCreatore)){
                                    elencoUtenti.get(i).getUtentiamici().add(elencoUtenti.get(numUtente));
                                }
                            }
                        }

                    }else{
                        System.out.println(BACHECAEVENTIVUOTA);
                    }



                    ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                    ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);


                    break;

                case 7:
                    /*Visualizza i miei messaggi*/
                    Menu Menumsg= new Menu(NOMEMENUMSG,OPZIONIMSG);
                    int sceltamsg;

                    do{
                        sceltamsg=Menumsg.scegli();

                        switch(sceltamsg)
                        {
                            case 0:
                                break;

                            case 1:
                                /*Visualzza i miei messaggi*/

                                if(elencoUtenti.get(numUtente).getMessaggiUtente().size()!=0){

                                    for(int i=0; i<elencoUtenti.get(numUtente).getMessaggiUtente().size();i++){
                                        System.out.println(i+1 +")");
                                        System.out.println(elencoUtenti.get(numUtente).getMessaggiUtente().get(i).getTesto());
                                    }
                                }else {
                                    System.out.println(MESSAGGIVUOTI);
                                }
                                break;

                            case 2:
                                /*Eliminazione messaggi*/

                                /*Visualizzazione dei miei messaggi*/
                                if(elencoUtenti.get(numUtente).getMessaggiUtente().size()!=0){
                                    System.out.println("0) Esci");
                                    for(int i=0; i<elencoUtenti.get(numUtente).getMessaggiUtente().size();i++){
                                        System.out.println(i+1 +")");
                                        System.out.println(elencoUtenti.get(numUtente).getMessaggiUtente().get(i).getTesto());
                                    }

                                    /*Scelta messaggio da eliminare*/
                                    int numMsg=Utility.leggiIntero(0, elencoUtenti.get(numUtente).getMessaggiUtente().size(), SCELTAMSG);


                                    if(numMsg!=0){
                                        elencoUtenti.get(numUtente).getMessaggiUtente().remove(numMsg-1);

                                        ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                                    }
                                }else {
                                    System.out.println(MESSAGGIVUOTI);
                                }
                                break;
                            case 3:
                                /*Modifica dati Personali*/
                                elencoUtenti.get(numUtente).inserisciDatiPersonali(categorie);

                                break;
                        }
                    }while(sceltamsg !=0);

                    break;

                case 8:

                    /*Elimina iscrizione a evento*/
                    boolean iscritto = false;

                    for (int i = 0; i < bacheca.getElencoEventi().size(); i++) {
                        if (bacheca.getElencoEventi().get(i).giaIscritto(elencoUtenti.get(numUtente)) && bacheca.getElencoEventi().get(i).controlloDataEliminazione()) {
                            iscritto = true;
                        }else{
                            iscritto = false;
                        }

                    }
                    if(iscritto) {

                        System.out.println("0) Esci");
                        for (int i = 0; i < bacheca.getElencoEventi().size(); i++) {
                            if (bacheca.getElencoEventi().get(i).giaIscritto(elencoUtenti.get(numUtente)) && bacheca.getElencoEventi().get(i).controlloDataEliminazione()) {
                                System.out.println(i + 1 + ")");
                                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
                                System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
                                System.out.println(POSTILIBERI + bacheca.getElencoEventi().get(i).getPostiLiberi());
                            }

                        }

                        /*Scelta eventi*/
                        int numEliminIscrizione = Utility.leggiIntero(0, bacheca.getElencoEventi().size() + 1, SCELTAELIMISCRIZIONE);

                        if (numEliminIscrizione != 0) {
                            for (int j=0; j<bacheca.getElencoEventi().get(numEliminIscrizione - 1).getElencoIscritti().size();j++){
                                if (elencoUtenti.get(numUtente).confrontaUtenteStringa(bacheca.getElencoEventi().get(numEliminIscrizione - 1).getElencoIscritti().get(j).utente)){
                                    bacheca.getElencoEventi().get(numEliminIscrizione - 1).getElencoIscritti().remove(j);
                                }
                            }

                        }

                    }else{
                        System.out.println(ISCRIZIONIVUOTE);
                    }

                    break;

                case 9:
                    /*Cancellazione Evento*/
                    Boolean eventiCancellabili=false;

                    for(int i=0; i<bacheca.getElencoEventi().size();i++){
                        if (elencoUtenti.get(numUtente).confrontaUtente(bacheca.getElencoEventi().get(i).getCreatore()) && bacheca.getElencoEventi().get(i).controlloDataEliminazione()){
                            eventiCancellabili=true;
                        }
                    }

                    if(eventiCancellabili){
                        System.out.println("0) Esci");
                        for(int i=0; i<bacheca.getElencoEventi().size();i++){
                            if (elencoUtenti.get(numUtente).confrontaUtente(bacheca.getElencoEventi().get(i).getCreatore()) && bacheca.getElencoEventi().get(i).controlloDataEliminazione()){
                                System.out.println(i+1 +")");
                                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
                            }
                        }

                        int numEliminEventoPubblicato = Utility.leggiIntero(0, bacheca.getElencoEventi().size() + 1, SCELTAELIMINEVENTO);

                        if (numEliminEventoPubblicato != 0) {
                            bacheca.getElencoEventi().get(numEliminEventoPubblicato -1).setStato("Annullato");
                        }
                    }
                    else{
                        System.out.println(CANCELLAZIONIVUOTE);
                    }

                    break;

                case 10:
                    /*invita persone ad evento*/

                    Boolean eventiPubblicati=false;

                    for(int i=0; i<bacheca.getElencoEventi().size();i++){
                        if (elencoUtenti.get(numUtente).confrontaUtente(bacheca.getElencoEventi().get(i).getCreatore())){
                            eventiPubblicati=true;
                        }
                    }

                    if(eventiPubblicati){
                        System.out.println("0) Esci");
                        for(int i=0; i<bacheca.getElencoEventi().size();i++){
                            if (elencoUtenti.get(numUtente).confrontaUtente(bacheca.getElencoEventi().get(i).getCreatore())){
                                System.out.println(i+1 +")");
                                System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
                            }
                        }

                        int numInvitoEvento = Utility.leggiIntero(0, bacheca.getElencoEventi().size() + 1, SCELTAINVITOEVENTO);

                        if (numInvitoEvento != 0) {
                            /*scelta amici da invitare*/
                            ArrayList<Utente> utentiInvitabili=new ArrayList<>();
                            ArrayList<Utente> utentiInvitati=new ArrayList<>();

                            for(int i=0; i<elencoUtenti.get(numUtente).getUtentiamici().size();i++){
                                boolean giaIscrittoEv=false;
                                for (int j=0; j<bacheca.getElencoEventi().get(numInvitoEvento-1).getElencoIscritti().size();j++){
                                    if(elencoUtenti.get(numUtente).getUtentiamici().get(i).confrontaUtenteStringa(bacheca.getElencoEventi().get(numInvitoEvento-1).getElencoIscritti().get(j).getUtente())){
                                        giaIscrittoEv=true;
                                    }
                                }
                                if(!giaIscrittoEv)
                                    utentiInvitabili.add(elencoUtenti.get(numUtente).getUtentiamici().get(i));
                            }

                            int numAmico=0;
                            if(utentiInvitabili.size()!=0){

                                do{
                                    System.out.println("0) Esci");
                                    for (int i=0; i<utentiInvitabili.size();i++){
                                        System.out.println(i+1+")");
                                        System.out.println("Nome Utente: " + utentiInvitabili.get(i).getNomeUtente());
                                    }

                                    numAmico=Utility.leggiIntero(0, utentiInvitabili.size()+1, SCELTAINVITO);
                                    if(numAmico!=0){
                                        utentiInvitati.add(utentiInvitabili.get(numAmico-1));
                                        utentiInvitabili.remove(numAmico-1);
                                    }

                                }while(utentiInvitabili.size()>0 && numAmico!=0);
                                /*Messaggi ad amici invitati*/

                                String nomeEventoi="";
                                if (bacheca.getElencoEventi().get(numInvitoEvento-1).getCategoria().getTitolo().getValore().getInserito())
                                    nomeEventoi=(String) bacheca.getElencoEventi().get(numInvitoEvento-1).getCategoria().getTitolo().getValore().getValore();
                                else
                                    nomeEventoi="Titolo non ancora inserito";


                                for (int i=0;i<utentiInvitati.size();i++){
                                    for(int j=0; j<elencoUtenti.size();j++){
                                        if(utentiInvitati.get(i).confrontaUtente(elencoUtenti.get(j))){
                                            String testo="L'utente " + elencoUtenti.get(numUtente).getNomeUtente() + " ti ha invitato a partecipare all'evento " +  nomeEventoi;
                                            Messaggio msg=new Messaggio(elencoUtenti.get(j).getNomeUtente(),testo);
                                            elencoUtenti.get(j).getMessaggiUtente().add(msg);
                                        }
                                    }
                                }



                            }
                            else{
                                System.out.println(AMICIVUOTI);
                            }

                        }
                    }
                    else{
                        System.out.println(EVENTICREATIVUOTI);
                    }

                    break;
                case 11:
                    /*Creazione evento con valori predefiniti (per fase di testing)*/

                    Evento eventopredef= new Evento(partita,elencoUtenti.get(numUtente));
                    eventopredef.inserisciValoriPredefinitiEvento();


                    Iscrizioni iscrizionepredef=new Iscrizioni(elencoUtenti.get(numUtente).getNomeUtente(),eventopredef);
                    eventopredef.getElencoIscritti().add(iscrizionepredef);
                    elencoUtenti.get(numUtente).getEventiUtente().add(eventopredef);
                    System.out.println(MSGEVENTO);


                    break;
            }
        }while(scelta!=0);

}

*/

}
}
