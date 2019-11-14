package Codice.Modello;

import java.util.ArrayList;

import MyLib.Utility;

/**
 * La classe Gita estende la classe Categoria per poter creare oggetti Gita.
 *
 * In particolare definisce:
 *
 * costo dei pasti, il costo se si vuole usufruire dei pasti forniti dall'organizzazione
 * mezzo di trasporto, indica il mezzo di trasporto con cui si raggiungerà il luogo di destinazione
 * costo del trasporto, indica il prezzo da pagare se si vuole usufruire del trasporto fornito dall'organizazione
 * elencoCampi, l'elenco dei campi della categoria.
 *
 *
 * @author Gabriele Manenti
 *
 * @version 5.0 1 Febbraio 2019
 */
public class Gita extends CategoriaA {
	private Campo costoPasti=new Campo("Costo pasti","Indica il costo se si vuole usufruire dei pasti forniti dall'organizzazione",true,new interoV());
	private Campo mezzoTrasporto=new Campo("Mezzo di trasporto","Indica il mezzo di trasporto con cui si raggiungerà il luogo di destinazione",true,new testoV());
	private Campo costoTrasporto=new Campo("Costo del trasporto","Indica il prezzo da pagare se si vuole usufruire del trasporto fornito dall'organizazione",true,new interoV());
	private ArrayList<Campo> elencoCampi = new ArrayList<>();

	/*Costruttori*/
	/**
	 * Costruttore di default.
	 * Una Gita e' costituita da nome e descizione.
	 *
	 * @author Gabriele Manenti
	 */
	public Gita(){
		super("Gita in Citt�","Evento che prevede un viaggio in una citt� Italiana o Europea");
		creaArrayCampi();
	}

	/*Getters*/
	/**
	 * Ritorna il costo dei pasti
	 * @return il costo dei pasti
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getCostoPasti() {

		return costoPasti;
	}
	/**
	 * Ritorna il mezzo di Trasporto
	 * @return il mezzo di Trasporto
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getMezzoTrasporto() {

		return mezzoTrasporto;
	}
	/**
	 * Ritorna il costo del Trasporto
	 * @return il cost del Trasporto
	 *
	 * @author Gabriele Manenti
	 */
	public Campo getCostoTrasporto() {

		return costoTrasporto;
	}
	/**
	 * Ritorna un ArrayList dei campi dell'evento
	 * @return ArrayList dei campi dell'evento
	 *
	 * @author Gabriele Manenti
	 */
	public ArrayList<Campo> getElencoCampi()
	{
		return elencoCampi;
	}

	/*Setters*/
	/**
	 * Permette di cambiare il costo dei pasti
	 * @param costoPasti il costo dei pasti
	 *
	 * @author Gabriele Manenti
	 */
	public void setCostoPasti(Campo costoPasti) {

		this.costoPasti = costoPasti;
	}
	/**
	 * Permette di cambiare il mezzo di Trasporto
	 * @param mezzoTrasporto il mezzo di Trasporto
	 *
	 * @author Gabriele Manenti
	 */
	public void setMezzoTrasporto(Campo mezzoTrasporto) {

		this.mezzoTrasporto = mezzoTrasporto;
	}
	/**
	 * Permette di cambiare il costo del Trasporto
	 * @param costoTrasporto il costo del Trasporto
	 *
	 * @author Gabriele Manenti
	 */
	public void setCostoTrasporto(Campo costoTrasporto) {

		this.costoTrasporto = costoTrasporto;
	}
	/**
	 * Permette di cambiare l'elenco di Campi
	 * @param elencoCampi il nuovo elenco
	 *
	 * @author Gabriele Manenti
	 */
	public void setElencoCampi(ArrayList<Campo> elencoCampi)
	{
		this.elencoCampi = elencoCampi;
	}

	/*Metodi*/

	/**
	 * Ritorna una stringa che descrive tutti i campi della categoria Partita
	 *
	 * @return tutti i campi della categoria partita
	 *
	 * @author Gabriele Manenti
	 */
	public void visualizzaCampi(){
		
		for (int i=0; i< elencoCampi.size(); i++){
			
			elencoCampi.get(i).visualizzaCampo();
		}
	}
	/**
	 * Assegna i valori ai campi di partita
	 *
	 * @throws Exception in caso di arraylist vuoto
	 *
	 * @author Gabriele Manenti
	 */
	 public void inserisciCampi()throws Exception{
	    	for (int i=0; i< elencoCampi.size(); i++){
				elencoCampi.get(i).inserisciValore();
				
			}
		}
	/**
	 * Permette di poter scegliere di che servizi usufruire durante l'evento, come i pasti e il trasporto
 	 * @return costo l'ammontare del totale da dover pagare in funzione dei servizi scelti
	 *
	 * @author Gabriele Manenti
	 */
	 public int sceltaOpzioni() {
			int costo=(int) getQuotaIndividuale().getValore().getValore();
			int inserimento1= Utility.leggiIntero(0,1, "Vuoi usufruire dei pasti forniti dall'organizzazione? (Costo "+ (int) costoPasti.getValore().getValore() +" Euro) Digita 1 per SI e 0 pre NO");
			if(inserimento1==0){
			
			}
			else{
				costo=costo + (int) costoPasti.getValore().getValore();
			}
			
			
			int inserimento2= Utility.leggiIntero(0,1, "Vuoi usufruire del trasporto fornito dall'organizzazione? (Costo "+ (int) costoTrasporto.getValore().getValore() +" Euro) Digita 1 per SI e 0 pre NO");
			if(inserimento2==0){
				
			}
			else{
				costo=costo + (int) costoTrasporto.getValore().getValore();
			}
			return costo;
		}

	@Override
	public void creaArrayCampi() {
		elencoCampi.add(getTitolo());
		elencoCampi.add(getnPartecipanti());
		elencoCampi.add(getTolleranzaPartecipanti());
		elencoCampi.add(getTermineIscrizione());
		elencoCampi.add(getLuogo());
		elencoCampi.add(getData());
		elencoCampi.add(getOra());
		elencoCampi.add(getDurata());
		elencoCampi.add(getQuotaIndividuale());
		elencoCampi.add(getCompresoQuota());
		elencoCampi.add(getDataFine());
		elencoCampi.add(getDataRitiroIscrizione());
		elencoCampi.add(getOraFine());
		elencoCampi.add(getNote());
		
		
		elencoCampi.add(costoPasti);
		elencoCampi.add(mezzoTrasporto);
		elencoCampi.add(costoTrasporto);
		
	} 

}
