package Codice.Modello;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Codice.Vista.*;
import MyLib.ServizioFile;
import MyLib.Utility;
// Prova gabri nuova
/**
 * Classe che definisce il Main del programma.
 *
 * @author Gabriele Manenti, Matteo Gusmini
 *
 * @version 5.0 1 Febbraio 2019
 *
 */
public class Main {
	/**
	 * Metodo Main del programma
	 *
	 */
	public static void main(String[] args) throws Exception{
		Menu.startMenu();
		Menu.salvaFile();
		Menu.loginMenu();
	}


	
}