package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.*;

public class CampoTest {

	@Test
	public void testVisualizzaCampo() {
		Campo c=new Campo("Prova", "Descrizione test",true,1);
		String s="Nome: Prova"+"\n" +"Descrizione: Descrizione test" + "\n"+ "Obbligatoria: true"  +"\n";
		assertEquals(s,c.visualizzaCampo());
		
	}
	
	
	
	
	
	
	

}
