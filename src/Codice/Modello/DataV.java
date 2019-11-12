package Codice.Modello;

import java.util.Date;

import Codice.Vista.InputOutput;

public class DataV extends ValoreA{
	public Date valore;
	
	public DataV(){
		inserito=false;
	}
	
	@Override
	public void setValore(Object valore) {
			this.valore=(Date) valore;
	}
	@Override
	public Object getValore() {
		
		return valore;
	}

	@Override
	public void inserisciValore(String nome) throws Exception {
		setValore(InputOutput.InserimentoData(nome));
		
	}
}
