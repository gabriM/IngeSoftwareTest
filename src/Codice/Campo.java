package Codice;

import java.util.*;
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
	private Valore valore;


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
	public Campo(String _nome, String _descrizione, Boolean _obbligatorio, int _valore){
		nome =_nome;
		descrizione= _descrizione;
		obbligatorio = _obbligatorio;
		valore= new Valore(_valore);
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
	public Valore getValore() {
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
	public void setValore(Valore valore) {
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

			if (!valore.getInserito()) {
				int inserimento = Utility.leggiIntero(0, 1, "Vuoi inserire " + nome + "? Digita 1 per SI e 0 pre NO");
				if (inserimento == 0) {

				} else {
					switch (valore.getTipo()) {
						case 0:
							valore.setValore(Utility.leggiLinea("inserisci " + nome));
							break;
						case 1:
							valore.setValore(Utility.leggiIntero(0, 9999999, "inserisci " + nome));
							break;
						case 2:
							String data = Utility.leggiData("inserisci " + nome + "(gg/mm/aaaa)");
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
							valore.setValore(date);
							break;
						case 3:
							String ora = Utility.leggiOra("Inserisci " + nome + " (hh:mm)");
							valore.setValore(ora);
							break;
						case 4:
							ArrayList<Integer> eta = Utility.leggiFasciaEta("Inserisci" + nome);
							valore.setEta(eta);

							break;
					}
				}

			}
		}

	}



}

