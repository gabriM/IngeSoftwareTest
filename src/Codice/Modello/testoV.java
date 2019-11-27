package Codice.Modello;

import java.io.Serializable;

import Codice.Vista.InputOutput;

public class testoV extends ValoreA implements Serializable{
	
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
