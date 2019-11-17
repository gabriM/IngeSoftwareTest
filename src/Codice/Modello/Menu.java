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
import Codice.Modello.Evento;

public class Menu {

    /*Costanti*/
    final static String NOMEMENU="GESTIONE Eventi";
    final static String NOMEMENUMSG="GESTIONE Messaggi";
    final static String[] OPZIONIMSG={"Visualizza messaggi", "Elimina messaggi","Modifica dati personali"};
    final static String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento","Pagina Utente", "Elimina Iscrizione evento", "Elimina evento","Invita persone ad evento","Genera evento standard per test"};


    /*Attributi*/
    public static ArrayList<CategoriaA> categorie;
    public static ListaEventi bacheca;
    public static ArrayList<Utente> elencoUtenti;
    public static Partita partita;
    public static Gita gita;
    public static File filebacheca;
    public static File fileutenti;

    public static void startMenu() {

        categorie = new ArrayList<>();
        bacheca = new ListaEventi();
        elencoUtenti = new ArrayList<>();

        partita = new Partita();
        gita = new Gita();
        categorie.add(partita);
        categorie.add(gita);
    }

    public static void salvaFile() {

        filebacheca = new File ("Bacheca.txt");
        fileutenti = new File ("Utenti.txt");

        /*Caricamento dati del programma da File*/
        if (ServizioFile.esistenzaFile(fileutenti) == 0) {
            ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
        } else
            elencoUtenti=  (ArrayList<Utente>) ServizioFile.caricaSingoloOggetto(fileutenti);

        if (ServizioFile.esistenzaFile(filebacheca) == 0) {
            ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
        }else
            bacheca= (ListaEventi) ServizioFile.caricaSingoloOggetto(filebacheca);

    }




    public static void loginMenu(){

        InputOutput.stampaBenvenuto();
        String utente= InputOutput.login();

        /*Controllo se utente già esistente*/
        int numUtente = Utente.controlloEsistenzaUtente(elencoUtenti, utente,categorie);

        ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
        Utente utenteAttivo= elencoUtenti.get(numUtente);

        GUI myMenu= new GUI(NOMEMENU,OPZIONI);
        int scelta;


        do{
            scelta=myMenu.scegli();

            /*Controlli su eventi in Bacheca e generazione di eventuali messaggi*/

            ArrayList<Messaggio> messaggiStato = new ArrayList<>(bacheca.controlloEventi());
            Messaggio.generaMessaggiStatoEvento(elencoUtenti, messaggiStato);

            ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);


            /*Eliminazione eventi Falliti, Chiusi, Conclusi*/
            bacheca.eliminaEventiStato();

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
                    InputOutput.visualizzaCategorie(categorie);
                    int numCatEvento = InputOutput.sceltaCategoria(categorie.size());

                    Evento eventoP=new Evento(categorie.get(numCatEvento-1),utenteAttivo);
                    Iscrizioni iscrizioneP=new Iscrizioni(utenteAttivo.getNomeUtente(),eventoP);

                    eventoP.creaEvento(iscrizioneP);
                    utenteAttivo.getEventiUtente().add(eventoP);

                    InputOutput.stampaMsgEvento();

                    /*Salvataggio file*/
                    ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                    break;

                case 3:
                    /*Visualizza i miei eventi non pubblicati*/
                    if(utenteAttivo.controlloEventiNonPubblicati()){
                        InputOutput.visualizzaEventi(elencoUtenti.get(numUtente));
                    }else {
                        InputOutput.stampaEventiVuoti();
                    }
                    break;
                case 4:
                    /*Pubblica eventi*/
                    /*Visualizza i propri eventi non ancora inseriti*/
                    if(utenteAttivo.controlloEventiNonPubblicati()){
                        InputOutput.visualizzaEventi(utenteAttivo);

                        /*Scelta evento da pubblicare*/
                        int numEventoPubblicato=InputOutput.sceltaEvento(utenteAttivo.getEventiUtente().size());
                        if(numEventoPubblicato!=0){
                            Evento eventop = utenteAttivo.getEventiUtente().get(numEventoPubblicato -1);

                            /*Controllo validita evento*/
                            eventop.getCategoria().setAutomaticoDataRitiroIscrizione();

                            if(eventop.isValido()){
                                if(eventop.controlloDate()){
                                    /*Pubblicazione evento*/
                                    bacheca.getElencoEventi().add(eventop);
                                    utenteAttivo.getEventiUtente().remove(numEventoPubblicato-1);

                                    /*Messaggi ad utenti con categoria di interesse uguale a quella dell'evento*/
                                    Messaggio.generaMessaggiCategoriaInteresse(elencoUtenti, eventop);

                                }
                                else{
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getData().getValore().removeValore();
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getDataFine().getValore().removeValore();
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getTermineIscrizione().getValore().removeValore();
                                    elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getDataRitiroIscrizione().getValore().removeValore();
                                    InputOutput.stampaMsgProbDate();
                                }
                            }
                            else{
                                /*Evento non valido*/
                                InputOutput.stampaEventiNonValidi();

                                /*Possibilità di inserire altri dettagli all'evento*/
                                int inserimento =InputOutput.sceltaInserimento();

                                if (inserimento==1){
                                    eventop.inserisciDettagliEvento();
                                }
                            }
                        }
                    }
                    else {
                        InputOutput.stampaEventiVuoti();
                    }

                    ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                    ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
                    break;
                case 5:
                    /*Visualizza Bacheca*/
                    if(bacheca.controlloEventiBacheca()){
                        InputOutput.visualizzaEventiBacheca(bacheca);
                    }else{
                        InputOutput.stampaBachecaVuota();
                    }
                    break;

                case 6:
                    /*Partecipa a evento*/
                    ListaEventi eventiValidi=new ListaEventi();
                    ArrayList<Evento> listaEventiValidi=new ArrayList<>();
                    listaEventiValidi=bacheca.eventiValidi(utenteAttivo);
                    eventiValidi.setElencoEventi(listaEventiValidi);

                    if(listaEventiValidi.size()!=0){
                        /*Visualizzazione eventi presenti in bacheca*/

                        InputOutput.visualizzaEventiBacheca(eventiValidi);
                        /*Scelta eventi*/
                        int numIscEvento=InputOutput.sceltaEvento(listaEventiValidi.size());

                        if (numIscEvento!=0){
                            Iscrizioni iscrizione=new Iscrizioni(elencoUtenti.get(numUtente).getNomeUtente(),bacheca.getElencoEventi().get(numIscEvento-1));
                            int costo= bacheca.getElencoEventi().get(numIscEvento-1).sceltaOpzioniGita();
                            iscrizione.setCosto(costo);
                            bacheca.getElencoEventi().get(numIscEvento-1).getElencoIscritti().add(iscrizione);
                            String nomeCreatore = bacheca.getElencoEventi().get(numIscEvento-1).getCreatore().getNomeUtente();

                            for(int i=0; i<elencoUtenti.size();i++){
                                if (elencoUtenti.get(i).getNomeUtente().equalsIgnoreCase(nomeCreatore)){
                                    elencoUtenti.get(i).getUtentiamici().add(elencoUtenti.get(numUtente));
                                }
                            }
                        }

                    }else{
                        InputOutput.stampaBachecaEventiValidiVuota();
                    }

                    ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                    ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);


                    break;

                case 7:
                    /*Visualizza i miei messaggi*/
                    GUI Menumsg= new GUI(NOMEMENUMSG,OPZIONIMSG);
                    int sceltamsg;

                    do{
                        sceltamsg=Menumsg.scegli();

                        switch(sceltamsg)
                        {
                            case 0:
                                break;

                            case 1:
                                /*Visualzza i miei messaggi*/

                                if(elencoUtenti.get(numUtente).controlloPresenzaMessaggi()){

                                    InputOutput.visualizzaMessaggiUtente(elencoUtenti.get(numUtente));
                                }else {
                                    InputOutput.stampaMessaggiVuoti();
                                }
                                break;

                            case 2:
                                /*Eliminazione messaggi*/

                                /*Visualizzazione dei miei messaggi*/
                                if(elencoUtenti.get(numUtente).controlloPresenzaMessaggi()){
                                    InputOutput.stampaUscitaMenu();
                                    InputOutput.visualizzaMessaggiUtente(elencoUtenti.get(numUtente));

                                    /*Scelta messaggio da eliminare*/
                                    int numMsg= InputOutput.sceltaEliminaMessaggi(elencoUtenti.get(numUtente));

                                    if(numMsg!=0){
                                        elencoUtenti.get(numUtente).getMessaggiUtente().remove(numMsg-1);

                                        ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
                                    }
                                }else {
                                    InputOutput.stampaMessaggiVuoti();
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

                    if(bacheca.controlloIscrizione(elencoUtenti.get(numUtente))) {

                        InputOutput.visualizzaEventiBacheca(bacheca);

                        /*Scelta eventi*/
                        int numEliminIscrizione = InputOutput.sceltaEliminaIscrizione(bacheca);

                        if (numEliminIscrizione != 0) {
                            for (int j=0; j<bacheca.getElencoEventi().get(numEliminIscrizione - 1).getElencoIscritti().size();j++){
                                if (elencoUtenti.get(numUtente).confrontaUtenteStringa(bacheca.getElencoEventi().get(numEliminIscrizione - 1).getElencoIscritti().get(j).utente)){
                                    bacheca.getElencoEventi().get(numEliminIscrizione - 1).getElencoIscritti().remove(j);
                                }
                            }

                        }

                    }else{
                        InputOutput.stampaIscrVuote();
                    }

                    break;

                case 9:
                    /*Cancellazione Evento*/

                    if(bacheca.controlloEliminazioneEventi(elencoUtenti.get(numUtente))){
                        InputOutput.visualizzaEventiUtente(bacheca, elencoUtenti.get(numUtente));

                        int numEliminEventoPubblicato = InputOutput.sceltaEliminaEvento(bacheca);

                        if (numEliminEventoPubblicato != 0) {
                            bacheca.getElencoEventi().get(numEliminEventoPubblicato -1).setStato("Annullato");
                        }
                    }
                    else{
                        InputOutput.stampaCancellazioniVuote();
                    }

                    break;

                case 10:
                    /*invita persone ad evento*/

                    if(bacheca.controlloEventiPubblicati(elencoUtenti.get(numUtente))){
                        InputOutput.visualizzaEventiUtente(bacheca, elencoUtenti.get(numUtente));

                        int numInvitoEvento = InputOutput.sceltaInvitoEvento(bacheca);

                        if (numInvitoEvento != 0) {
                            /*scelta amici da invitare*/
                            ArrayList<Utente> utentiInvitabili=new ArrayList<>();
                            ArrayList<Utente> utentiInvitati=new ArrayList<>();

                            bacheca.aggiungiUtentiInvitabili(elencoUtenti.get(numUtente), utentiInvitabili, numInvitoEvento);

                            int numAmico=0;
                            if(utentiInvitabili.size()!=0){

                                do{
                                    InputOutput.visualizzaUtentiInvitabili(utentiInvitabili);

                                    numAmico= InputOutput.sceltaUtenteAmico(utentiInvitabili);
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


                                Messaggio.generaMessaggiInvitoUtente(utentiInvitati, elencoUtenti, elencoUtenti.get(numUtente),nomeEventoi);
                            }
                            else{
                                InputOutput.stampaAmiciVuoti();
                            }

                        }
                    }
                    else{
                        InputOutput.stampaEventiCreatiVuoti();
                    }

                    break;
                case 11:
                    break;
            }
        }while(scelta!=0);

    }




}
