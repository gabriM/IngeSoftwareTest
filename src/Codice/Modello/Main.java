package Codice.Modello;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Codice.Vista.*;
import MyLib.ServizioFile;
import MyLib.Utility;
// Prova gabri nuova
/**
 * Classe che definisce il Main del programma.
 *
 * @author Gabriele Manenti, Matteo Gusmini
 *
 * @version 5.0 1 Febbraio 2019
 *
 */
public class Main {
	/**
	 * Metodo Main del programma
	 *
	 */
	public static void main(String[] args) throws Exception{

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


		/*Creazione file per il salvataggio dei dati*/
		File filebacheca = new File ("Bacheca.txt");
		File fileutenti = new File ("Utenti.txt");
				//File fileutentiP = new File("UtentiP.txt");



		/*Creazione degli oggetti principali per l'esecuzione del programma*/
		ArrayList<CategoriaA> categorie=new ArrayList<>();
		ListaEventi bacheca = new ListaEventi();
		ArrayList<Utente> elencoUtenti=new ArrayList<>();


		/*Creazione delle categorie di cui possono essere i vari eventi*/
		Partita partita= new Partita();
		Gita gita=new Gita();
		categorie.add(partita);
		categorie.add(gita);



		/*Caricamento dati del programma da File*/
		if(ServizioFile.esistenzaFile(fileutenti) == 0) {
			ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
		}else
			//elencoUtenti=  (ArrayList<Utente>) ServizioFile.caricaSingoloOggetto(fileutenti);

		if(ServizioFile.esistenzaFile(filebacheca) == 0) {
			ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
		}else
			//bacheca= (ListaEventi) ServizioFile.caricaSingoloOggetto(filebacheca);


		/*Messaggio di benvenuto e richiesta nome per login*/
		InputOutput.stampaBenvenuto();
		String utente= InputOutput.login();

		/*Controllo se utente già esistente*/
		int numUtente = Utente.controlloEsistenzaUtente(elencoUtenti, utente,categorie);

		ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
		Utente utenteAttivo= elencoUtenti.get(numUtente);

		/*Menu Scelta Opzioni*/
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