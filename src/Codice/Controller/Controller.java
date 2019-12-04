package Codice.Controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import Codice.Modello.CategoriaA;
import Codice.Controller.ControllerAreaPersonale;
import Codice.Modello.Evento;
import Codice.Modello.Gita;
import Codice.Modello.Iscrizioni;
import Codice.Modello.ListaEventi;
import Codice.Modello.Messaggio;
import Codice.Modello.Partita;
import Codice.Modello.Utente;
import Codice.Vista.Menu;
import Codice.Vista.InputOutput;
import MyLib.ServizioFile;

public class Controller implements Serializable{
	
	 /*Costanti*/
	
	final static int ESCI=0;
	final static int VISUALIZZACATEGORIE=1;
	final static int CREANUOVOEVENTO=2;
	final static int VISUALIZZAEVENTINONPUBBLICATI=3;
	final static int PUBBLICAEVENTI=4;
	final static int VISUALIZZABACHECA=5;
	final static int PARTECIPAEVENTO=6;
	final static int PAGINAUTENTE=7;
	final static int ELIMINAISCRIZIONEEVENTO=8;
	final static int ELIMINAEVENTO=9;
	final static int INVITAAMICI=10;
    final static String NOMEMENU="GESTIONE Eventi";
    final static String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento","Pagina Utente", "Elimina Iscrizione evento", "Elimina evento","Invita persone ad evento"};

	
	// Attributi
	private ArrayList<CategoriaA> categorie;
	private ListaEventi bacheca;
	private ArrayList<Utente> elencoUtenti;
	private Partita partita;
	private Gita gita;
	private File filebacheca;
	private File fileutenti;
	private Utente utenteAttivo;
	
	public Controller(){
		categorie = new ArrayList<>();
        bacheca = new ListaEventi();
        elencoUtenti = new ArrayList<>();
        filebacheca = new File ("Bacheca.txt");
        fileutenti = new File ("Utenti.txt");

        partita = new Partita();
        gita = new Gita();
        categorie.add(partita);
        categorie.add(gita);
	}
	
	public void run() throws Exception{
		   
			caricaFile();
			login();
			
	        Menu myMenu= new Menu(NOMEMENU,OPZIONI);
	        int scelta;

	        do{
	            scelta=myMenu.scegli();  
	            /*Controlli su eventi in Bacheca e generazione di eventuali messaggi*/

	            ArrayList<Messaggio> messaggiStato = new ArrayList<>(bacheca.controlloEventi());
	            Messaggio.generaMessaggiStatoEvento(elencoUtenti, messaggiStato);

	            /*Eliminazione eventi Falliti, Chiusi, Conclusi*/
	            bacheca.eliminaEventiStato();
	            
	            salva();

	            switch(scelta)
	            {
	                case ESCI:
	                	elencoUtenti.add(utenteAttivo);
	                    break;

	                case VISUALIZZACATEGORIE:
	                	visualizzaCategorie();
	                    break;

	                case CREANUOVOEVENTO:
	                	creaNuovoEvento();
	                    break;

	                case VISUALIZZAEVENTINONPUBBLICATI:
	                	visualizzaEventiNonPubblicati();
	                    break;
	                    
	                case PUBBLICAEVENTI:
	                   pubblicaEventi();
	                    break;
	                    
	                case VISUALIZZABACHECA:
	                    visualizzaBacheca();
	                    break;

	                case PARTECIPAEVENTO:
	                	peartecipaEvento();
	                    break;

	                case PAGINAUTENTE:
	                	paginaUtente();
	                    break;

	                case ELIMINAISCRIZIONEEVENTO:
	                	eliminaIscrizioneEvento();
	                    break;

	                case ELIMINAEVENTO:
	                	eliminaEvento();
	                    break;

	                case INVITAAMICI:
	                	invitaAmici();
	                    break;
	            }
	            salva();
	        }while(scelta!=ESCI);
	}
	 

	private void paginaUtente() {
		ControllerAreaPersonale controllerAreaPersonale=new ControllerAreaPersonale(utenteAttivo,categorie);
		utenteAttivo=controllerAreaPersonale.run();
	}

	private void invitaAmici() {
		Evento evento=new Evento();
		evento=sceltaEventoCreato();
	
            if (evento!= null) {
                /*scelta amici da invitare*/
                ArrayList<Utente> utentiInvitabili=new ArrayList<>();
                ArrayList<Utente> utentiInvitati=new ArrayList<>();
                utentiInvitabili=evento.UtentiInvitabili(utenteAttivo);

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
                    generaMessaggi(evento,utentiInvitati);
                }
                else{
                    InputOutput.stampaAmiciVuoti();
                }
            }
       
	}

	private void generaMessaggi(Evento evento, ArrayList<Utente> utentiInvitati) {
		String nomeEventoi;
        if (evento.getCategoria().getTitolo().getValore().getInserito())
            nomeEventoi=(String)evento.getCategoria().getTitolo().getValore().getValore();
        else
            nomeEventoi="Titolo non ancora inserito";

        Messaggio.generaMessaggiInvitoUtente(utentiInvitati, elencoUtenti, utenteAttivo,nomeEventoi);
		
	}

	private void eliminaEvento() {
		Evento evento=new Evento();
		evento=sceltaEventoCreato();
            if (evento!= null) {
                bacheca.eliminaEvento(evento);
            }
	}

	private Evento sceltaEventoCreato() {
        ArrayList<Evento> listaEventiCreati=new ArrayList<>();
        listaEventiCreati=bacheca.eventiCreati(utenteAttivo);
		
		if(listaEventiCreati.size()>0){
            InputOutput.visualizzaListaEventi(listaEventiCreati);
            
            int numEv = InputOutput.selezioneEvento(listaEventiCreati);
            return listaEventiCreati.get(numEv);
		}
		else{
            InputOutput.stampaCancellazioniVuote();
			return null;
		}
	}

	private void eliminaIscrizioneEvento() {
        ArrayList<Evento> listaEventiIscritto=new ArrayList<>();
        listaEventiIscritto=bacheca.eventiIscritto(utenteAttivo);
        
		 if(listaEventiIscritto.size()>0) {

             InputOutput.visualizzaListaEventi(listaEventiIscritto);
             int numEliminIscrizione = InputOutput.selezioneEvento(listaEventiIscritto);
             if (numEliminIscrizione != 0) {
              listaEventiIscritto.get(numEliminIscrizione-1).eliminaIscrizione(utenteAttivo);
             }
         }else{
             InputOutput.stampaIscrVuote();
         }
	}

	private void peartecipaEvento() {
        ArrayList<Evento> listaEventiValidi=new ArrayList<>();
        listaEventiValidi=bacheca.eventiValidi(utenteAttivo);

        if(listaEventiValidi.size()!=0){
            InputOutput.visualizzaListaEventi(listaEventiValidi);
            int numIscEvento=InputOutput.sceltaEvento(listaEventiValidi.size());

            if (numIscEvento!=0){
                Iscrizioni iscrizione=new Iscrizioni(utenteAttivo.getNomeUtente(),listaEventiValidi.get(numIscEvento-1));
                
                int costo= listaEventiValidi.get(numIscEvento-1).sceltaOpzioniGita();
                iscrizione.setCosto(costo);
                bacheca.addIscrizione(listaEventiValidi.get(numIscEvento-1),iscrizione);
                
                aggiungiAdAmici(listaEventiValidi.get(numIscEvento-1).getCreatore().getNomeUtente());
            }
        }else{
            InputOutput.stampaBachecaEventiValidiVuota();
        }
	}

	private void aggiungiAdAmici(String nomeCreatore) {
		for(int i=0; i<elencoUtenti.size();i++){
            if (elencoUtenti.get(i).getNomeUtente().equalsIgnoreCase(nomeCreatore)){
                elencoUtenti.get(i).getUtentiamici().add(utenteAttivo);
            }
        }
	}

	private void visualizzaBacheca() {
        if(bacheca.controlloEventiBacheca()){
            InputOutput.visualizzaEventiBacheca(bacheca);
        }else{
            InputOutput.stampaBachecaVuota();
        }	
	}

	private void pubblicaEventi() throws Exception {
		/*Visualizza i propri eventi non ancora inseriti*/
        if(utenteAttivo.controlloEventiNonPubblicati()){
            InputOutput.visualizzaEventi(utenteAttivo);

            /*Scelta evento da pubblicare*/
            int numEventoPubblicato=InputOutput.sceltaEvento(utenteAttivo.getEventiUtente().size());
            if(numEventoPubblicato!=0){
                Evento eventop = utenteAttivo.getEventiUtente().get(numEventoPubblicato -1);

                /*Controllo validita evento*/
                if(eventop.isValido()){
                	eventop.getCategoria().setAutomaticoDataRitiroIscrizione();
                    if(eventop.controlloDate()){
                        /*Pubblicazione evento*/
                        bacheca.getElencoEventi().add(eventop);
                        utenteAttivo.getEventiUtente().remove(numEventoPubblicato-1);
                        /*Messaggi ad utenti con categoria di interesse uguale a quella dell'evento*/
                        Messaggio.generaMessaggiCategoriaInteresse(elencoUtenti, eventop);
                    }
                    else{
                    	utenteAttivo.removeDate(numEventoPubblicato -1);
                        InputOutput.stampaMsgProbDate();
                    }
                }
                else{
                    /*Evento non valido*/
                    InputOutput.stampaEventiNonValidi();
                    /*Possibilità di inserire altri dettagli all'evento*/
                    if (InputOutput.sceltaInserimento()==1){
                        eventop.inserisciDettagliEvento();
                    }
                }
            }
        }
        else {
            InputOutput.stampaEventiVuoti();
        }
	}

	private void visualizzaEventiNonPubblicati() {
		  if(utenteAttivo.controlloEventiNonPubblicati()){
              InputOutput.visualizzaEventi(utenteAttivo);
          }else {
              InputOutput.stampaEventiVuoti();
          }
	}

	private void salva() {
		ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
        ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
	}

	private void creaNuovoEvento() throws Exception {
		 InputOutput.visualizzaCategorie(categorie);
         int numCatEvento = InputOutput.sceltaCategoria(categorie.size());

         Evento eventoP=new Evento(categorie.get(numCatEvento-1),utenteAttivo);
         Iscrizioni iscrizioneP=new Iscrizioni(utenteAttivo.getNomeUtente(),eventoP);

         eventoP.creaEvento(iscrizioneP);
         utenteAttivo.getEventiUtente().add(eventoP);

         InputOutput.stampaMsgEvento();
	}

	private void visualizzaCategorie() {
		InputOutput.visualizzaCategorie(categorie);
        categorie.get((InputOutput.sceltaCategoria(categorie.size()))-1).visualizzaCampi();
	}

	public void login(){
		 	InputOutput.stampaBenvenuto();
	        String utente= InputOutput.login();
	        if(!controlloEsistenzaUtente(utente)){
	        	creaUtente(utente);
	        }
	}
	
	
	public boolean controlloEsistenzaUtente(String utente){
		Boolean esistente =false;
		int numUtente=0;
		for(int i=0; i<elencoUtenti.size();i++){
			if (elencoUtenti.get(i).getNomeUtente().equalsIgnoreCase(utente)){
				esistente =true;
				utenteAttivo=elencoUtenti.get(i);
				elencoUtenti.remove(i);
			}
		}
		return esistente;
	}
	
	public void creaUtente(String utente)
	{
		Utente nuovoUtente= new Utente(utente);
		elencoUtenti.add(nuovoUtente);
		utenteAttivo=nuovoUtente;
		utenteAttivo.inserisciDatiPersonali(categorie);
	}
	
		
	public void caricaFile() {
	        if (ServizioFile.esistenzaFile(fileutenti) == 0) {
	            ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
	        } else{
	            elencoUtenti=  (ArrayList<Utente>) ServizioFile.caricaSingoloOggetto(fileutenti);
	        }
	        if (ServizioFile.esistenzaFile(filebacheca) == 0) {
	            ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
	        }else{
	            bacheca= (ListaEventi) ServizioFile.caricaSingoloOggetto(filebacheca);
	        }
	}
}
