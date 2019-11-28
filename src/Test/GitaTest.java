package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.*;
import Codice.Modello.*;

public class GitaTest {

	@Test
	public void testCreaArray() {
		Gita c= new Gita();
		assertEquals(17,c.getElencoCampi().size());
	}
	
	@Test
	public void testSceltaOpzioni() {
		Gita c= new Gita();
		c.getQuotaIndividuale().getValore().setValore(10);
		assertEquals(10,c.sceltaOpzioni());
	}
	
	
}
