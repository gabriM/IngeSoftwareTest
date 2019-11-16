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
	public static void generaMessaggi(ListaEventi bacheca1, ArrayList<Utente> elencoUtenti1){
		ArrayList<Messaggio> messaggiStato = new ArrayList<>(bacheca1.controlloEventi());
		for(int i=0;i<messaggiStato.size();i++){
			for(int j=0; j<elencoUtenti1.size();j++){
				if(messaggiStato.get(i).getDestinatario().equalsIgnoreCase(elencoUtenti1.get(j).getNomeUtente())){
					elencoUtenti1.get(j).getMessaggiUtente().add(messaggiStato.get(i));
				}
			}
		}
	}

	public static void generaMessaggiCategoriaInteresse(ArrayList<Utente> elencoUtenti, Evento eventop){
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

	public static void generaMessaggiInvitoUtente(ArrayList<Utente> utentiInvitati, ArrayList<Utente> elencoUtenti, Utente utente, String nomeEventoi){
		for (int i=0;i<utentiInvitati.size();i++){
			for(int j=0; j<elencoUtenti.size();j++){
				if(utentiInvitati.get(i).confrontaUtente(elencoUtenti.get(j))){
					String testo="L'utente " + utente.getNomeUtente() + " ti ha invitato a partecipare all'evento " +  nomeEventoi;
					Messaggio msg=new Messaggio(elencoUtenti.get(j).getNomeUtente(),testo);
					elencoUtenti.get(j).getMessaggiUtente().add(msg);
				}
			}
		}
	}

	public static void generaMessaggiStatoEvento(ArrayList<Utente> elencoUtenti, ArrayList<Messaggio> messaggiStato){
		for(int i=0;i<messaggiStato.size();i++){
			for(int j=0; j<elencoUtenti.size();j++){
				if(messaggiStato.get(i).getDestinatario().equalsIgnoreCase(elencoUtenti.get(j).getNomeUtente())){
					elencoUtenti.get(j).getMessaggiUtente().add(messaggiStato.get(i));
				}
			}
		}
	}

}
