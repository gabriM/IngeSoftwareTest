import java.io.Serializable;

public class Messaggio implements Serializable{
	//Attributi
		private String destinatario;
		private String testo;
		
		
		//Costruttori
		public Messaggio(String _destinatario, String _testo){
			destinatario =_destinatario;
			testo= _testo;
			
		}

		
		
		
		// Getters and Setters generati automaticamente
		public String getDestinatario() {
			return destinatario;
		}


		public String getTesto() {
			return testo;
		}


		public void setDestinatario(String destinatario) {
			this.destinatario = destinatario;
		}


		public void setTesto(String testo) {
			this.testo = testo;
		}
		
		
		
		
		
}
