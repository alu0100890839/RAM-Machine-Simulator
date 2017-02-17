/**
 * Clase para la cinta de salida
 * Pablo Pastor Mart�n
 * 17/02/2017
 * alu0100890839@ull.edu.es
 */

import java.io.IOException;
import java.io.FileWriter;

/**
 * Clase para la cinta de salida
 * @author Pablo Pastor Mart�n
 * @version 1.0.0
 */
public class OutputTape {
	private FileWriter writer;
	
	/**
	 * Constructor que crea la cinta a partir de un archivo
	 * @param filename Cadena que contiene el nombre del archivo de la cinta
	 */
	public OutputTape(String filename) throws IOException{
		writer = new FileWriter(filename);
	}
	
	/**
	 * M�todo para escribir el siguiente caracter de la lista.
	 * @param c Car�cter a escribir
	 * @throws IOException
	 */
	public void write(char c) throws IOException{
		writer.write(c);
		writer.flush();
	}
}
