package Codice.Modello;

import Codice.Vista.InputOutput;

public class interoV extends ValoreA{
	public int valore;
	
	public interoV(){
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
