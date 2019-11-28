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
	
	

}
