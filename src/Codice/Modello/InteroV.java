package Codice.Modello;

import java.io.Serializable;

import Codice.Vista.InputOutput;

public class InteroV extends ValoreA implements Serializable{
	public int valore;
	
	public InteroV(){
		inserito=false;
	}
	
	
	@Override
	public void setValore(Object valore) {
			this.valore=(int) valore;
	}
	@Override
	public Object getValore() {
		
		return valore;
	}


	@Override
	public void inserisciValore(String nome) {
		setValore(InputOutput.inserimentoInt(nome));
		
	}
}
