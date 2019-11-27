package Codice.Modello;

import java.io.Serializable;

import Codice.Vista.InputOutput;

public class etaV extends ValoreA implements Serializable{
	public int valore[];
	
	
	public etaV(){
		inserito=false;
	}
	
	
	@Override
	public void setValore(Object valore) {
			this.valore=(int[]) valore;
	}
	@Override
	public Object getValore() {
		
		return valore;
	}


	@Override
	public void inserisciValore(String nome) throws Exception {
		setValore(InputOutput.inserimentoEta(nome));
		
	}
}
