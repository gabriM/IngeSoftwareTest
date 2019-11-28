package Codice.Controller;

import java.util.ArrayList;

import Codice.Vista.Menu;
import Codice.Vista.InputOutput;
import Codice.Modello.*;
import MyLib.ServizioFile;

public class ControllerAreaPersonale {
	
	final static String NOMEMENUMSG="GESTIONE Messaggi";
	final static String[] OPZIONIMSG={"Visualizza messaggi", "Elimina messaggi","Modifica dati personali"};
	final static int ESCI=0;
	final static int VISUALIZZAMESSAGGI=1;
	final static int ELIMINAMESSAGGI=2;
	final static int MODIFICADATIPERSONALI=3;
	
	
	private Utente utente;
	private ArrayList<CategoriaA> categorie;
	
	public ControllerAreaPersonale(Utente u, ArrayList<CategoriaA> c){
		utente=u;
		categorie=c;
	}
	
	public Utente run(){
		Menu Menumsg= new Menu(NOMEMENUMSG,OPZIONIMSG);
        int sceltamsg;

        do{
            sceltamsg=Menumsg.scegli();

            switch(sceltamsg)
            {
                case ESCI:
                    break;

                case VISUALIZZAMESSAGGI:
                	visualizzaMessaggi();
                	break;

                case ELIMINAMESSAGGI:
                    eliminaMessaggi();
                    break;
                    
                case MODIFICADATIPERSONALI:
                	utente.inserisciDatiPersonali(categorie);

                    break;
            }
        }while(sceltamsg !=0);
		
		
		return utente;
	}

	private void eliminaMessaggi() {
        if(utente.controlloPresenzaMessaggi()){
            InputOutput.stampaUscitaMenu();
            InputOutput.visualizzaMessaggiUtente(utente);

            /*Scelta messaggio da eliminare*/
            int numMsg= InputOutput.sceltaEliminaMessaggi(utente);

            if(numMsg!=0){
            	utente.getMessaggiUtente().remove(numMsg-1);
            }
        }else {
            InputOutput.stampaMessaggiVuoti();
        }
		
	}

	private void visualizzaMessaggi() {
        if(utente.controlloPresenzaMessaggi()){

            InputOutput.visualizzaMessaggiUtente(utente);
        }else {
            InputOutput.stampaMessaggiVuoti();
        }       
	}
}
