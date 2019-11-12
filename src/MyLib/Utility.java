package MyLib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class Utility 
{
	
	private final static String MSGERRORE="Il valore con cui evochi questa funzione non � accettabile";
	private final static String ERROREFORMATO="Il valore inserito non � del tipo richiesto";
	private final static String MSG_NO_FILE = "ATTENZIONE: NON TROVO IL FILE ";
	private final static String MSG_NO_LETTURA = "ATTENZIONE: PROBLEMI CON LA LETTURA DEL FILE ";
	private final static String MSG_NO_SCRITTURA = "ATTENZIONE: PROBLEMI CON LA SCRITTURA DEL FILE ";
	private final static String MSG_NO_CHIUSURA ="ATTENZIONE: PROBLEMI CON LA CHIUSURA DEL FILE ";
	
	
	static Scanner sc =new Scanner(System.in);

	public static String leggiStringa(String msg)
	{
		String nome;
		do{
			System.out.println(msg);
			nome = sc.next();
		}while(nome.length()==0);
		return nome;
	}
	
	public static int[] leggiFasciaEta(String msg)
	{
		int eta[] = new int[2];
		int eta1=Utility.leggiIntero(1, 110, "Inserisci estremo inferiore");
		int eta2=Utility.leggiIntero(eta1, 110, "Inserisci estremo superiore");
		eta[0]=eta1;
		eta[1]=eta2;
		
		return eta;
	}
	
	public static String leggiData(String msg)
	{
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Boolean valido=true;
		String nome;
		do{
			System.out.println(msg);
			nome = sc.next();
			valido = checkData(nome);
			
		}while(!valido);
		return nome;
	}
	
	public static String leggiOra(String msg)
	{
		Boolean valido=true;
		String nome;
		
		do{
			System.out.println(msg);
			nome = sc.next();
			valido = checkOra(nome);
			
			
		}while(!valido);
		return nome;
	}
		
		
	public static String leggiCodicefiscale(String msg)
	{
		String nome;
		do{
			System.out.println(msg);
			nome = sc.next();
		}while(nome.length()==0 && nome.length()<17);
		return nome;
	}
	
	public static String leggiLinea(String msg)
	{
		String nome;
		System.out.println(msg);
		do{
			nome = sc.nextLine();
		}while(nome.length()==0);
		return nome;
	}
	public static int leggiIntero(int min, int max, String msg)
	{
		int numero=min-1;
		boolean finito=false;
		do{
		System.out.println(msg);
		
		try
		{
		numero = sc.nextInt();
		finito = true;
		}
		catch (InputMismatchException e)
		{
		System.out.println(ERROREFORMATO);
		String daButtare = sc.next();
		}
		if(numero<min||numero>max)
			finito=false;
		
		}while(!finito);
		return numero;
	}
	
	
	public static int leggiVoto(String msg)
	{
		int numero=0;
		boolean finito=false;
		do{
		System.out.println(msg);
		
		try
		{
		numero = sc.nextInt();
		finito = true;
		}
		catch (InputMismatchException e)
		{
		System.out.println(ERROREFORMATO);
		String daButtare = sc.next();
		}
		if(numero<18||numero>110||(numero>30&&numero<67))
			finito=false;
		
		}while(!finito);
		return numero;
	}
	
	
	
	public static boolean assegnaBoolean(int n)
	{	
		boolean var;
		
		if (n==1)var=true;
		else var=false;
		return var;
	
	}
		
	
	public static int casuale(int nMax)
	{
		nMax++;
		Random estrattore=new Random();
		int numero;
		numero=estrattore.nextInt(nMax);
		numero=Math.abs(numero);
		return numero;
	}
	
	
	public static boolean confrontaCar(char [] nome,char [] corso)
	{
		boolean trovato=false;
		boolean errore=false;
		int j;
		int k;
		for(int i=0;i<(corso.length-nome.length+1);i++)
		{
			errore=false;
			k=0;
			for(j=i ;j<(nome.length+i);j++)
			{
				 		
					if(nome[k]!=corso[j])
						errore=true;
				k++;
				
			}
			if(errore==false)
				trovato=true;
			
		}
		
		return trovato;
	}
	
	public static boolean checkData(String dNascita){
		boolean controlla=true;	//Variabile per capire se bisngna controllare o meno la stringa, il controllo si fa solo se i caratteri nella stringa sono tutti numeri o /, in caso contrario la stringa non � una data accettabile
		for(int i=0; i<dNascita.length();i++){
			if(!MyCheck.checkNumero(dNascita.charAt(i)) && dNascita.charAt(i)!='/'){
				controlla=false;
			}
		}
		if(controlla){
			MyTime.DATE_FORMAT.setLenient(false);
			try {
				MyTime.DATE_FORMAT.parse(dNascita);
				return true;
			} 
			catch (ParseException e) {
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public static boolean checkOra(String ora){
		
		char[] fOra = ora.toCharArray();
		for(int i=0; i<ora.length();i++){
			if(!MyCheck.checkNumero(ora.charAt(i)) && ora.charAt(i)!=':'){
				return false;
			}
		}
		if(fOra.length != 5)
			return false;
		int car1= (int )fOra[0];
		int car2= (int )fOra[1];
		int car3= (int )fOra[3];
		int car4= (int )fOra[4];

		if( Character.getNumericValue(car1)< 0 && Character.getNumericValue(car1) > 2)
			return false;
		if(Character.getNumericValue(car1) == 2 && Character.getNumericValue(car2) > 4)
			return false;
		if(fOra[2] != ':' )
			return false;
		
		if(Character.getNumericValue(car3) > 5)
			return false;
		
		
		return true;
	}
	
	
	
}
