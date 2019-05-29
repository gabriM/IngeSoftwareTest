package Test;

import static org.junit.Assert.*;
import Codice.*;
import org.junit.Test;

public class EventoTest {

	@Test
	public void testValiditaFalse()
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.isValido();
	boolean validita = e.getvalidita();
	assertFalse(validita);
	}
	
	@Test
	public void testValiditaTrueAll() throws Exception
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.inserisciValoriPredefinitiEvento();
	
	e.isValido();
	boolean validita = e.getvalidita();
	assertTrue(validita);
	}
	
	@Test
	public void testValiditaTrueNeeded() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(10);	
	e.getCategoria().getTermineIscrizione().getValore().setValore("10/10/2019");
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore("15/10/2019");
	e.getCategoria().getOra().getValore().setValore("10:10");
	e.getCategoria().getQuotaIndividuale().getValore().setValore(15);
	e.getCategoria().inserisciValoriPredefiniti();
	
	e.isValido();
	boolean validita = e.getvalidita();
	assertTrue(validita);
	}
	
	
	@Test
	public void testValiditaFalseNotAll() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(10);	
	e.getCategoria().getTermineIscrizione().getValore().setValore("10/10/2019");
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore("15/10/2019");

	
	e.isValido();
	boolean validita = e.getvalidita();
	assertFalse(validita);
	}
	
}
