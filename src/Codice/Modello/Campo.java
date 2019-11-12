package Codice.Modello;

import java.util.*;

import Codice.Vista.InputOutput;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import MyLib.Utility;
/**
 * Classe che definisce un <i>Campo</i>.
 *
 * Un campo possiede le seguenti caratterisitiche:
 * nome;
 * descrizione;
 * obbligatorietà del campo;
 * valore del campo.
 *
 * @author Gabriele Manenti
 *
 * @version 4.0 1 Febbraio 2019
 *
 */
public class Campo implements Serializable{

	/*Attributi*/
	private String nome;
	private String descrizione;
	private Boolean obbligatorio;
	private ValoreA valore;


	/*Costruttori*/
	/**
	 * Un campo e' costituito da nome, descrizione, obbligatorietà e valore riguardante una caratteristica di un evento:
	 *
	 * @param _nome nome del campo
	 * @param _descrizione descrizione del campo
	 * @param _obbligatorio obbligatorietà del campo
	 * @param _valore tipologia del campo
	 *
	 * @author Gabriele Manenti
	 */
	public Campo(String _nome, String _descrizione, Boolean _obbligatorio, testoV testo){
		nome =_nome;
		descrizione= _descrizione;
		obbligatorio = _obbligatorio;
		valore=testo;

	}
	public Campo(String _nome, String _descrizione, Boolean _obbligatorio,interoV intero){
		nome =_nome;
		descrizione= _descrizione;
		obbligatorio = _obbligatorio;
		valore=intero;
	}
	
	public Campo(String _nome, String _descrizione, Boolean _obbligatorio,etaV eta){
		nome =_nome;
		descrizione= _descrizione;
		obbligatorio = _obbligatorio;
		valore=eta;
	}
	
	public Campo(String _nome, String _descrizione, Boolean _obbligatorio,DataV data){
		nome =_nome;
		descrizione= _descrizione;
		obbligatorio = _obbligatorio;
		valore=data;
	}

	/*Getters*/
	/**
	 * Ritorna il nome del Campo
	 * @return nome del campo
	 *
	 * @author Gabriele Manenti
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Ritorna la descrizione del Campo
	 * @return descrizione del campo
	 *
	 * @author Gabriele Manenti
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * Ritorna l'obbligatorietà del Campo
	 * @return se un campo è obbligatorio o meno
	 *
	 * @author Gabriele Manenti
	 */
	public Boolean getObbligatorio() {
		return obbligatorio;
	}
	/**
	 * Ritorna il valore del Campo
	 * @return valore del campo
	 *
	 * @author Gabriele Manenti
	 */
	public ValoreA getValore() {
		return valore;
	}

	/*Setters*/
	/**
	 * Metodo che imposta il nome del Campo.
	 *
	 * @param nome il nome del campo
	 *
	 * @author Gabriele Manenti
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Metodo che imposta la descrizione del Campo.
	 *
	 * @param descrizione la descrizione del campo
	 *
	 * @author Gabriele Manenti
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * Metodo che imposta l'obbligatorietà di un Campo.
	 *
	 * @param obbligatorio l'obbligatorietà del campo
	 *
	 * @author Gabriele Manenti
	 */
	public void setObbligatorio(Boolean obbligatorio) {
		this.obbligatorio = obbligatorio;
	}
	/**
	 * Metodo che imposta il valore di un Campo.
	 *
	 * @param valore il valore del campo
	 *
	 * @author Gabriele Manenti
	 */
	public void setValore(ValoreA valore) {
		this.valore = valore;
	}


	/*Metodi*/
	/**
	 * Ritorna una stringa che descrive il campo
	 *
	 * @return info le informazioni relative ad un campo
	 *
	 * @author Gabriele Manenti
	 */
	public String visualizzaCampo(){
		String info= new String("Nome: "+ nome +"\n" +"Descrizione: " + descrizione+ "\n"+ "Obbligatoria: " + obbligatorio +"\n");
		return info;
	}
	/**
	 * Assegna un valore al campo inserito
	 *
	 * @author Gabriele Manenti
	 */
	public void inserisciValore()throws Exception {

		
			if (!valore.getInserito()) {
				boolean inserimento = InputOutput.richiestaInserimento(nome);
				if (inserimento) {
					valore.inserisciValore(nome);
					valore.setInserito(true);	
				}

			}
		}

	



}

