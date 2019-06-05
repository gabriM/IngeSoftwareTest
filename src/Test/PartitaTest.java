package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Codice.Modello.*;

public class PartitaTest {

	@Test
	public void testCreaArray() {
		Partita c= new Partita();
		assertEquals(16,c.getElencoCampi().size());
	}
	
	@Test
	public void testValoriPredefGenere() {
		Partita c= new Partita();
		c.inserisciValoriPredefiniti();
		
		assertEquals("Maschi",c.getGenere().getValore().getValore());
	}
	
	@Test
	public void testValoriPredefEta() {
		Partita c= new Partita();
		c.inserisciValoriPredefiniti();
		ArrayList<Integer> eta=new ArrayList<>();
		eta.add(16);
		eta.add(45);
		
		assertEquals(eta,c.getFasciaEta().getValore().getValore());
	}
}
