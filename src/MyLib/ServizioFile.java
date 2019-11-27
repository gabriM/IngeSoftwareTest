package MyLib;
import java.io.*;
import java.util.ArrayList;

import Codice.Modello.ListaEventi;
import Codice.Modello.Utente;

public class ServizioFile{
	private final static String MSG_NO_FILE = "ATTENZIONE: NON TROVO IL FILE ";
	private final static String MSG_NO_LETTURA = "ATTENZIONE: PROBLEMI CON LA LETTURA DEL FILE ";
	private final static String MSG_NO_SCRITTURA = "ATTENZIONE: PROBLEMI CON LA SCRITTURA DEL FILE ";
	private final static String MSG_NO_CHIUSURA ="ATTENZIONE: PROBLEMI CON LA CHIUSURA DEL FILE ";


	public static int esistenzaFile(File f){
		if(f.exists())
			return 1;
		else
			return 0;
	}


	public static Object caricaSingoloOggetto (File f){
		Object letto = null;
		ObjectInputStream ingresso = null;
		
		try{
			ingresso = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			
			letto = ingresso.readObject();	
		}
		catch(FileNotFoundException excNotFound){
			System.out.println(MSG_NO_FILE + f.getName() );

		}
		catch(IOException excLettura){
			System.out.println(MSG_NO_LETTURA + f.getName() );
		}
		catch(ClassNotFoundException excLettura){
			System.out.println(MSG_NO_LETTURA + f.getName() );
		}
		finally{
			if(ingresso != null){
				try{
					ingresso.close();
				}
				catch(IOException excChiusura){
			 		System.out.println(MSG_NO_CHIUSURA + f.getName() );
				}
			}
		} // finally
		return letto;  
	} // metodo caricaSingoloOggetto
	
	public static void salvaSingoloOggetto(File f, Object daSalvare){
		ObjectOutputStream uscita = null;
		
		try{
			uscita = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			uscita.writeObject(daSalvare);	
		}
		catch(IOException excScrittura){
			System.out.println(MSG_NO_SCRITTURA + f.getName() );
		}
		 
  	    finally{
  	    	if(uscita != null){
  	    		try{
  	    			uscita.close();
				}
				catch(IOException excChiusura){
			 		System.out.println(MSG_NO_CHIUSURA + f.getName() );
				}
			}
		} // finally
	} // metodo salvaSingoloOggetto
	
	

	
	
	
	/**
	 * Crea un file nell'indirizzo passato
	 * @param path indirizzo nel quale creare il file
	 */
	public static void creaFile(String path) {
		  try {
			File file = new File(path);
		    if (file.exists())
		      System.out.println("Il file  esiste");
		    else if (file.createNewFile())
		      System.out.println("Il file e' stato creato");
		    else
		      System.out.println("Il file non puo' essere creato");
		  }
		  catch (IOException e) {
		    e.printStackTrace();
		  }
		}
	
	/**
	 * Legge un file che si trova all'indirizzo indicato 
	 * @param path indirizzo nel quale si trova il file da leggere
	 */
	public static void leggiFile(String path) {
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	  }
	
	/**
	 * Aggiunge il testo specificato all'indirizzo specificato dall'utente
	 * @param path indirizzo nel quale si trova il file al quale bisogna aggiungere il testo
	 * @param frase testo da aggiungere al file
	 */
	public static void scriviFile(String path, String frase) {
	    try {
			File file = new File(path);
	    	FileOutputStream fw = new FileOutputStream(file,true);
			PrintWriter pw= new PrintWriter(fw);
			pw.append(frase+"\n");
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	
	public static void eliminaFile(String path){
		File f = new File(path);

	    // Mi assicuro che il file esista
	    if (!f.exists())
	      throw new IllegalArgumentException("Il File o la Directory non esiste: " + path);

	    // Se � una cartella verifico che sia vuota
	    if (f.isDirectory()) {
	      String[] files = f.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException("La Directory non � vuota: " + path);
	    }

	    // Provo a cancellare
	    boolean success = f.delete();

	     // Se si � verificato un errore...
	    if (!success)
	      throw new IllegalArgumentException("Cancellazione fallita");
	  }
	

	

}

