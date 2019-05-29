package Test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	
	@Test
	public void testGiaIscrittoTrue()
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	Utente iscritto = new Utente("Rosso");
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	boolean isc = e.giaIscritto(iscritto);
	assertTrue(isc);
	}
	
	@Test
	public void testGiaIscrittoFalse()
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	Utente iscritto = new Utente("Rosso");
	Iscrizioni i1= new Iscrizioni("Giallo",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	boolean isc = e.giaIscritto(iscritto);
	assertFalse(isc);
	}
	
	@Test
	public void ControlloDateTrueAllInsert()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	Date dataFine=new Date("10/09/2019");
	Date dataRitiroIsc=new Date("10/06/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean controllo= e.controlloDate();
	assertTrue(controllo);
	}
	
	@Test
	public void ControlloDateTrueDFineNotInsert()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	Date dataRitiroIsc=new Date("10/06/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean controllo= e.controlloDate();
	assertTrue(controllo);
	}
	
	@Test
	public void ControlloDateTrueDRitiroNotInsert()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	Date dataFine=new Date("10/09/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	
	boolean controllo= e.controlloDate();
	assertTrue(controllo);
	}
	
	@Test
	public void ControlloDateFalseAllInsertDEv()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/06/2019");
	Date dataFine=new Date("10/09/2019");
	Date dataRitiroIsc=new Date("10/06/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean controllo= e.controlloDate();
	assertFalse(controllo);
	}
	@Test
	public void ControlloDateFalseAllInsertDRit()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	Date dataFine=new Date("10/09/2019");
	Date dataRitiroIsc=new Date("10/09/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean controllo= e.controlloDate();
	assertFalse(controllo);
	}
	@Test
	public void ControlloDateFalseNotAllInsertDEv()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/06/2019");
	Date dataRitiroIsc=new Date("10/06/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean controllo= e.controlloDate();
	assertFalse(controllo);
	}
	
	@Test
	public void ControlloDateFalseNotAllInsertDFine()
	{
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	Date dataFine=new Date("10/06/2019");
		
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	
	boolean controllo= e.controlloDate();
	assertFalse(controllo);
	}
	
	@Test
	public void testNPartecipantiFullDRitInsPassata() 
	{
	Date dataRitiroIsc=new Date("01/01/2019");
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	
	
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(2);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);;
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore("dataEv");
	e.getCategoria().getOra().getValore().setValore("10:10");
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	i1.setCosto(10);
	i2.setCosto(10);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i1);
	
	e.controlloNPartecipanti();
	
	String stato = e.getStato();
	assertEquals(stato,"Chiusa");
	}
	
	
	
	
	
	
}
