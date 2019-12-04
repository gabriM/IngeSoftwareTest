package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.Modello.*;

public class UtenteTest {

	@Test
	public void testConfrontaUtenteTrue() {
		Utente u1=new Utente("MaTteo");
		Utente u2=new Utente("matteo");
		assertTrue(u1.confrontaUtente(u2));
		
	}
	
	@Test
	public void testConfrontaUtenteFalse() {
		Utente u1=new Utente("Matteo");
		Utente u2=new Utente("Gabriele");
		assertFalse(u1.confrontaUtente(u2));
		
	}
	
	@Test
	public void testConfrontaUtenteStringaTrue() {
		Utente u1=new Utente("MaTteo");
		assertTrue(u1.confrontaUtenteStringa("matteo"));
		
	}
	
	@Test
	public void testConfrontaUtenteStringaFalse() {
		Utente u1=new Utente("MaTteo");
		assertFalse(u1.confrontaUtenteStringa("Gabriele"));
		
	}
	
	
	
}
