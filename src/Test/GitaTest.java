package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Codice.*;
import Codice.Gita;

public class GitaTest {

	@Test
	public void testCreaArray() {
		Gita c= new Gita();
		assertEquals(17,c.getElencoCampi().size());
	}
	
	
	
}
