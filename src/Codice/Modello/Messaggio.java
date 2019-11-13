package Codice.Modello;

import java.io.Serializable;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Codice.Vista.*;
import MyLib.ServizioFile;
import MyLib.Utility.*;

/**
 * Classe che definisce un <i>Messaggio</i>.
 *
 * Un messaggio contiene le seguenti caratteristiche:
 * destinatario;
 * testo del messaggio;
 *
 * @author Gabriele Manenti
 *
 * @version 4.0 1 Febbraio 2019
 *
 */
public class Messaggio implements Serializable{

	/*Attributi*/
	private String destinatario;
	private String testo;

	/*Costruttori*/
	/**
	 * Un messaggio e' costituito da destinatario e testo:
	 *
	 * @param _destinatario il destinatoario del messaggio
	 * @param _testo il testo del messaggio
	 *
	 * @author Gabriele Manenti
	 */
	public Messaggio(String _destinatario, String _testo){
			destinatario =_destinatario;
			testo= _testo;
			
		}

	/*Getters*/
	/**
	 * Ritorna il destrinatario del messaggio
	 * @return destrinatario del messaggio
	 *
	 * @author Gabriele Manenti
	 */
	public String getDestinatario() {
		return destinatario;
	}
	/**
	 * Ritorna il testo del messaggio
	 * @return il testo del messaggio
	 *
	 * @author Gabriele Manenti
	 */
	public String getTesto() {
		return testo;
	}

	/*Setters*/
	/**
	 * Metodo che imposta il destrinatario del messaggio
	 *
	 * @param destinatario il destrinatario del messaggio
	 *
	 * @author Gabriele Manenti
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	/**
	 * Metodo che imposta il testo del messaggio
	 *
	 * @param testo il testo del messaggio
	 *
	 * @author Gabriele Manenti
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	/*Metodi*/
	public void generaMessaggi(ListaEventi bacheca1, ArrayList<Utente> elencoUtenti1){
		ArrayList<Messaggio> messaggiStato = new ArrayList<>(bacheca1.controlloEventi());
		for(int i=0;i<messaggiStato.size();i++){
			for(int j=0; j<elencoUtenti1.size();j++){
				if(messaggiStato.get(i).getDestinatario().equalsIgnoreCase(elencoUtenti1.get(j).getNomeUtente())){
					elencoUtenti1.get(j).getMessaggiUtente().add(messaggiStato.get(i));
				}
			}
		}
	}
		
}
