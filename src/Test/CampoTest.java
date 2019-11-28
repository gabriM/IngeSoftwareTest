package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.Modello.*;

public class CampoTest {

	@Test
	public void testVisualizzaCampo() {
		testoV testo =new testoV();
		Campo c=new Campo("Prova", "Descrizione test",true,testo);
		String s="Nome: Prova"+"\n" +"Descrizione: Descrizione test" + "\n"+ "Obbligatoria: true"  +"\n";
		assertEquals(s,c.visualizzaCampo());
		
	}
	
	
	
	
	
	
	

}
