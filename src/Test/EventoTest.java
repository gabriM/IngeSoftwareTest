package Test;

import static org.junit.Assert.*;

import Codice.Utente;
import org.junit.Test;

public class EventoTest {

	@Test
	public void testValidita()
	{
	Partita p = new Categoria();
	Utente u = new Utente(Prova);
	Evento e = new Evento(p,u);

	boolean validita = e.isValido;
	assertFalse(validita);
	}

}
