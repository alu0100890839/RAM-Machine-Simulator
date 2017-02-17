/**
 * Clase que trabaja con la cinta de entrada de la máquina RAM
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
public class InputTape {
	
	public static final int NO_CHAR = 65535;  	//Indica que el caracter no es útil
	
	
	private BufferedReader reader;				//Lector del archivo
	private ArrayList<Character> cinta;			//Array de caracteres de la cinta
	private int pointerIndex;
	
	
	/**
	 * Constructor que crea la cinta a partir de un archivo
	 * @param filename Cadena que contiene el nombre del archivo de la cinta
	 */
	public InputTape(String filename) throws IOException{
		cinta = new ArrayList<Character>();
		pointerIndex = 0;
		try{
			reader = new BufferedReader(new FileReader(filename));
			while(reader.ready()) {
				String line = reader.readLine();
				cinta.add(line.charAt(0));
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("Fichero de cinta de entrada no encontrado" + e.getMessage());
		}
	}
	
	/**
	 * Método para leer el siguiente caracter de la lista.
	 * @return carácter leído
	 * @throws IOException
	 */
	public char read() throws IOException{
		if(pointerIndex < cinta.size()) {
			char c = cinta.get(pointerIndex);
			pointerIndex++;
			return c;
		}
		else {
			return (char) -1;
		}
	}
	
	/**
	 * Imprime la cinta y la posicion del puntero
	 * @return La cinta impresa
	 */
	public String toString() {
		String salida = "| ";
		for(int i = 0; i < cinta.size(); i++) {
			salida += cinta.get(i) + " | ";
		}
		salida += "\n";
		for(int i = 0; i<(2 + pointerIndex*4); i++) {
			salida += " ";
		}
		salida += "^";
		return salida;
	}
}
