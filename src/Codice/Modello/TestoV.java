package Codice.Modello;

import java.io.Serializable;

import Codice.Vista.InputOutput;

public class TestoV extends ValoreA implements Serializable{
	
	public String valore;
	
	
	public TestoV(){
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
