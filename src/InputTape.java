/**
 * Clase que trabaja con la cinta de entrada de la máquina RAM
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class InputTape {
	private FileReader reader;
	public static final int NO_CHAR = 65535;
	
	/**
	 * Constructor que crea la cinta a partir de un archivo
	 * @param filename Cadena que contiene el nombre del archivo de la cinta
	 */
	public InputTape(String filename) {
		try{
			reader = new FileReader(filename);
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
		if(reader.ready()) {
			char c = ((char)reader.read());
			reader.skip(2);
			return c;
		}
		else {
			return (char) -1;
		}
	}
}
