package Codice.Vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Codice.Modello.Categoria;
import MyLib.Utility;

public class InputOutput {

	final static String NOME="Nome categoria: ";
	final static String STATO="Stato: ";
	final String POSTILIBERI="Posti liberi: ";
	final static String DESCRIZIONE="Descrizione: ";
	final static String SCELTACATEGORIA="Quale categoria vuoi vedere in dettaglio?";
	final static String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
		
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

	public static void visualizzaCategorie(ArrayList<Categoria> categorie1){
		for(int i=0; i<categorie1.size();i++){
			System.out.println(i+1+")");
			System.out.println(NOME + categorie1.get(i).getNome());
			System.out.println(DESCRIZIONE + categorie1.get(i).getDescrizione()+"\n");
		}
	}

	public static int sceltaCategoria(int max) {
		int numCat = Utility.leggiIntero(1, max + 1, SCELTACATEGORIA);
		return numCat;
	}
	
	
	
}
