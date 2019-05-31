package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.Categoria;

public class CategoriaTest {

	@Test
	public void testCreaArray() {
		Categoria c= new Categoria("Prova","Prova pe test");
		c.creaArray();
		assertEquals(14,c.getElencoCampi().size());
	}
	
	@Test
	public void testGetPartecipantiMaxConTolleranza() {
		Categoria c= new Categoria("Prova","Prova pe test");
		c.getnPartecipanti().getValore().setValore(10);
		c.getTolleranzaPartecipanti().getValore().setValore(5);
		assertEquals(15,c.getPartecipantiMax());
	}
	
	@Test
	public void testGetPartecipantiMaxNoTolleranza() {
		Categoria c= new Categoria("Prova","Prova pe test");
		c.getnPartecipanti().getValore().setValore(10);
		assertEquals(10,c.getPartecipantiMax());
	}
	
	@Test
	public void testGetPartecipantiMinConTolleranza() {
		Categoria c= new Categoria("Prova","Prova pe test");
		c.getnPartecipanti().getValore().setValore(10);
		c.getTolleranzaPartecipanti().getValore().setValore(5);
		assertEquals(5,c.getPartecipantiMin());
	}
	
	@Test
	public void testGetPartecipantiMinNoTolleranza() {
		Categoria c= new Categoria("Prova","Prova pe test");
		c.getnPartecipanti().getValore().setValore(10);
		assertEquals(10,c.getPartecipantiMin());
	}
	
	@Test
	public void testSceltaOpzioni() {
		Categoria c= new Categoria("Prova","Prova pe test");
		c.getQuotaIndividuale().getValore().setValore(10);
		assertEquals(10,c.sceltaOpzioni());
	}
	
	
}
