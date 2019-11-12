package Codice.Modello;

import Codice.Vista.InputOutput;

public class testoV extends ValoreA{
	
	public String valore;
	
	
	public testoV(){
		inserito=false;
	}
	
	
	
	@Override
	public void setValore(Object valore) {
			this.valore=(String) valore;
	}

	@Override
	public Object getValore() {
		
		return valore;
	}



	@Override
	public void inserisciValore(String nome) {
		setValore(InputOutput.inserimentoStringa(nome));
		
	}
	

}
