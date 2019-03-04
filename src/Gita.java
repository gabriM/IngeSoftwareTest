import java.util.ArrayList;

import MyLib.Utility;

public class Gita extends Categoria {
	private Campo costoPasti=new Campo("Costo pasti","Indica il costo se si vuole usufruire dei pasti forniti dall'organizzazione",true,INT);
	private Campo mezzoTrasporto=new Campo("Mezzo di trasporto","Indica il mezzo di trasporto con cui si raggiungerà il luogo di destinazione",true,STRING);
	private Campo costoTrasporto=new Campo("Costo del trasporto","Indica il prezzo da pagare se si vuole usufruire del trasporto fornito dall'organizazione",true,INT);
	private ArrayList<Campo> elencoCampi = new ArrayList<>();
	
	
	public Gita(){
		super("Gita in Città","Evento che prevede un viaggio in una città Italiana o Europea");
		creaArray();
	}
	
	
	public void creaArray(){
			
			elencoCampi.add(super.getTitolo());
			elencoCampi.add(super.getnPartecipanti());
			elencoCampi.add(super.getTolleranzaPartecipanti());
			elencoCampi.add(super.getTermineIscrizione());
			elencoCampi.add(super.getLuogo());
			elencoCampi.add(super.getData());
			elencoCampi.add(super.getOra());
			elencoCampi.add(super.getDurata());
			elencoCampi.add(super.getQuotaIndividuale());
			elencoCampi.add(super.getCompresoQuota());
			elencoCampi.add(super.getDataFine());
			elencoCampi.add(super.getDataRitiroIscrizione());
			elencoCampi.add(super.getOraFine());
			elencoCampi.add(super.getNote());
			elencoCampi.add(costoPasti);
			elencoCampi.add(mezzoTrasporto);
			elencoCampi.add(costoTrasporto);
		}
	public void visualizzaCampi(){
		super.visualizzaCampi();
		for (int i=0; i< elencoCampi.size(); i++){
			
			System.out.println(elencoCampi.get(i).visualizzaCampo());
		}
	}
	
	
	 public void inserisciCampi()throws Exception{
	    	super.inserisciCampi();
	    	for (int i=0; i< elencoCampi.size(); i++){
				elencoCampi.get(i).inserisciValore();
				
			}
		}

	
	 
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
	 
	public Campo getCostoPasti() {
		return costoPasti;
	}


	public Campo getMezzoTrasporto() {
		return mezzoTrasporto;
	}


	public Campo getCostoTrasporto() {
		return costoTrasporto;
	}


	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}


	public void setCostoPasti(Campo costoPasti) {
		this.costoPasti = costoPasti;
	}


	public void setMezzoTrasporto(Campo mezzoTrasporto) {
		this.mezzoTrasporto = mezzoTrasporto;
	}


	public void setCostoTrasporto(Campo costoTrasporto) {
		this.costoTrasporto = costoTrasporto;
	}


	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}
	    
	    
	    
	
	
}
