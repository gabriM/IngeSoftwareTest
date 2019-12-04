package Codice.Modello;

import java.io.Serializable;

import Codice.Vista.InputOutput;

public class EtaV extends ValoreA implements Serializable{
	public int valore[];
	
	
	public EtaV(){
		inserito=false;
	}
	
	
	@Override
	public void setValore(Object valore) {
			this.valore=(int[]) valore;
			inserito=true;
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
