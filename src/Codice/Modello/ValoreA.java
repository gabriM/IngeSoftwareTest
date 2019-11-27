package Codice.Modello;

import java.io.Serializable;

public abstract class ValoreA implements Serializable{
	
	protected Boolean inserito;
	
	public abstract void setValore(Object valore);
	
	
	public abstract Object getValore();
	
	public abstract void inserisciValore(String nome) throws Exception;
	
	public Boolean getInserito() {
		return inserito;
	}

	public void setInserito(Boolean inserito) {
		this.inserito = inserito;
	}


	public void removeValore() {
		setInserito(false);
		
	}
}
