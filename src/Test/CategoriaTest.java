package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.Modello.*;

public class CategoriaTest {


	@Test
	public void testGetPartecipantiMaxConTolleranza() {
		CategoriaA c= new Partita();
		c.getnPartecipanti().getValore().setValore(10);
		c.getTolleranzaPartecipanti().getValore().setValore(5);
		assertEquals(15,c.getPartecipantiMax());
	}
	
	@Test
	public void testGetPartecipantiMaxNoTolleranza() {
		CategoriaA c= new Partita();
		c.getnPartecipanti().getValore().setValore(10);
		assertEquals(10,c.getPartecipantiMax());
	}
	
	@Test
	public void testGetPartecipantiMinConTolleranza() {
		CategoriaA c= new Partita();
		c.getnPartecipanti().getValore().setValore(10);
		c.getTolleranzaPartecipanti().getValore().setValore(5);
		assertEquals(5,c.getPartecipantiMin());
	}
	
	@Test
	public void testGetPartecipantiMinNoTolleranza() {
		CategoriaA c= new Partita();
		c.getnPartecipanti().getValore().setValore(10);
		assertEquals(10,c.getPartecipantiMin());
	}
	
	
	
}
