package Codice.Vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

public class InputOutput {
		
	public static boolean richiestaInserimento(String nome){
		boolean inserimento= false;
		int ins = Utility.leggiIntero(0, 1, "Vuoi inserire " + nome + "? Digita 1 per SI e 0 pre NO");
		if (ins==1)
			inserimento=true;
		
		return inserimento;
	}
	public static String inserimentoStringa(String nome){
		String inserimento;
		inserimento=Utility.leggiLinea("inserisci " + nome);
		
		return inserimento;
	}
	public static int inserimentoInt(String nome){
		int inserimento;
		inserimento=Utility.leggiIntero(0, 9999999, "inserisci " + nome);
		
		return inserimento;
	}
	public static Date InserimentoData(String nome) throws Exception{
		String inserimento;
		inserimento=Utility.leggiData("inserisci " + nome + "(gg/mm/aaaa)");
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(inserimento);
		
		return data;
	}
	
	public static int[] inserimentoEta(String nome){
		int inserimento[];
		inserimento=Utility.leggiFasciaEta("Inserisci" + nome);
		
		
		return inserimento;
	}
	
	
	
	
}
