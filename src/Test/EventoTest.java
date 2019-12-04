package Test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.crypto.Data;

import Codice.Modello.*;
import org.junit.Test;

public class EventoTest {

	@Test
	public void testValiditaFalse(){
		Partita p = new Partita();
		Utente u = new Utente("Prova");
		Evento e = new Evento(p,u);
		e.isValido();
		boolean validita = e.getvalidita();
		assertFalse(validita);
	}

	@Test
	public void testValiditaTrueNeeded(){
		Partita p = new Partita();
		Utente u = new Utente("Prova");
		Evento e = new Evento(p,u);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date("10/10/2020");
		int[] eta=new int[]{10,20};
		e.getCategoria().getnPartecipanti().getValore().setValore(10);	
		e.getCategoria().getTermineIscrizione().getValore().setValore(date);
		e.getCategoria().getLuogo().getValore().setValore("Brescia");
		e.getCategoria().getData().getValore().setValore(date);
		e.getCategoria().getOra().getValore().setValore("10:10");
		e.getCategoria().getQuotaIndividuale().getValore().setValore(15);
		e.getCategoria().getGenere().getValore().setValore("M");
		e.getCategoria().getFasciaEta().getValore().setValore(eta);
		
		
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
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date("10/10/2020");
		e.getCategoria().getnPartecipanti().getValore().setValore(10);	
		e.getCategoria().getTermineIscrizione().getValore().setValore(date);
		e.getCategoria().getLuogo().getValore().setValore("Brescia");
		e.getCategoria().getData().getValore().setValore(date);
	
		
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
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	i1.setCosto(10);
	i2.setCosto(10);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	e.controlloNPartecipanti();
	
	String stato = e.getStato();
	assertEquals(stato,"Chiusa");
	}
	
	@Test
	public void testNPartecipantiFullDRitInsFutura() 
	{
	Date dataRitiroIsc=new Date("12/12/2019");
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	
	
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(2);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	i1.setCosto(10);
	i2.setCosto(10);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	e.controlloNPartecipanti();
	
	String stato = e.getStato();
	assertEquals(stato,"Aperta");
	}

	
	@Test
	public void testNPartecipantiNotFullDRitInsPassata() 
	{
	Date dataRitiroIsc=new Date("01/01/2019");
	Date termineIsc=new Date("10/07/2019");
	Date dataEV=new Date("10/08/2019");
	
	
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(3);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	i1.setCosto(10);
	i2.setCosto(10);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	e.controlloNPartecipanti();
	
	String stato = e.getStato();
	assertEquals(stato,"Aperta");
	}
	
	
	
	@Test
	public void testControlloDataDFineEvPassata() 
	{
	Date dataRitiroIsc=new Date("01/01/2019");
	Date termineIsc=new Date("02/02/2019");
	Date dataEV=new Date("03/03/2019");
	Date dataFine=new Date("04/04/2019");
	
	
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(3);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	
	e.controlloData();
	
	String stato = e.getStato();
	assertEquals(stato,"Conclusa");
	}
	
	@Test
	public void testControlloDataEvPass() 
	{
	Date dataRitiroIsc=new Date("01/01/2019");
	Date termineIsc=new Date("02/02/2019");
	Date dataEV=new Date("03/03/2019");

	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(3);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	
	e.controlloData();
	
	String stato = e.getStato();
	assertEquals(stato,"Conclusa");
	}
	
	@Test
	public void testControlloDataNIscSuff() 
	{
	Date dataRitiroIsc=new Date("01/01/2019");
	Date termineIsc=new Date("02/02/2019");
	Date dataEV=new Date("03/03/2020");
	Date dataFine=new Date("04/04/2020");
	
	
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(2);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	i1.setCosto(10);
	i2.setCosto(10);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	e.controlloData();
	
	String stato = e.getStato();
	assertEquals(stato,"Chiusa2");
	}
	
	
	@Test
	public void testControlloDataNIscNotSuff() 
	{
	Date dataRitiroIsc=new Date("01/01/2019");
	Date termineIsc=new Date("02/02/2019");
	Date dataEV=new Date("03/03/2020");
	Date dataFine=new Date("04/04/2020");
	
	
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	e.getCategoria().getnPartecipanti().getValore().setValore(4);	
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	e.getCategoria().getDataFine().getValore().setValore(dataFine);
	e.getCategoria().getTitolo().getValore().setValore("Titolo");
	e.getCategoria().getTermineIscrizione().getValore().setValore(termineIsc);
	e.getCategoria().getLuogo().getValore().setValore("Brescia");
	e.getCategoria().getData().getValore().setValore(dataEV);
	e.getCategoria().getOra().getValore().setValore("10:10");
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	i1.setCosto(10);
	i2.setCosto(10);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	e.controlloData();
	
	String stato = e.getStato();
	assertEquals(stato,"Fallita");
	}
	
	@Test
	public void testDataEliminazioneFalseDRitiroIns() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	Date dataRitiroIsc=new Date("01/01/2020");
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean validita = e.controlloDataEliminazione();
	assertTrue(validita);
	}
	
	@Test
	public void testDataEliminazioneTrueDRitiroIns() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	Date dataRitiroIsc=new Date("01/01/2019");
	e.getCategoria().getDataRitiroIscrizione().getValore().setValore(dataRitiroIsc);
	
	boolean validita = e.controlloDataEliminazione();
	assertFalse(validita);
	}
	
	
	@Test
	public void testDataEliminazioneTrueDRitiroNotIns() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	boolean validita = e.controlloDataEliminazione();
	assertFalse(validita);
	}
	
	@Test
	public void testPostiMinimiPartecipanti1ConTolleranza() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(5);
	e.getCategoria().getTolleranzaPartecipanti().getValore().setValore(2);
	
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	int posti= e.getPostiMinimiPartecipanti();
	assertEquals(posti , 1);
	}
	
	@Test
	public void testPostiMinimiPartecipanti3NoTolleranza() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(5);
	
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	int posti= e.getPostiMinimiPartecipanti();
	assertEquals(posti , 3);
	}
	
	@Test
	public void testPostiMinimiPartecipantiConTolleranzaSuff() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(1);
	e.getCategoria().getTolleranzaPartecipanti().getValore().setValore(2);
	
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	int posti= e.getPostiMinimiPartecipanti();
	assertEquals(posti , -3);
	}
	
	@Test
	public void testPostiMinimiPartecipantiNoTolleranzaSuff() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(1);
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	int posti= e.getPostiMinimiPartecipanti();
	assertEquals(posti , -1);
	}
	
	@Test
	public void testPostiLiberiConTolleranza() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(1);
	e.getCategoria().getTolleranzaPartecipanti().getValore().setValore(2);
	
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	int posti= e.getPostiLiberi();
	assertEquals(posti , 1);
	}
	
	@Test
	public void testPostiLiberiNoTolleranza() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(5);
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	
	int posti= e.getPostiLiberi();
	assertEquals(posti , 3);
	}
	
	@Test
	public void testPostiLiberiConTolleranza0() 
	{
	Partita p = new Partita();
	Utente u = new Utente("Prova");
	Evento e = new Evento(p,u);
	
	e.getCategoria().getnPartecipanti().getValore().setValore(1);
	e.getCategoria().getTolleranzaPartecipanti().getValore().setValore(2);
	
	
	Iscrizioni i1= new Iscrizioni("Rosso",e);
	Iscrizioni i2= new Iscrizioni("Verde",e);
	Iscrizioni i3= new Iscrizioni("Giallo",e);
	
	e.getElencoIscritti().add(i1);
	e.getElencoIscritti().add(i2);
	e.getElencoIscritti().add(i3);
	
	int posti= e.getPostiLiberi();
	assertEquals(posti , 0);
	}
	
	
}
