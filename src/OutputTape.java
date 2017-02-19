/**
 * Clase para la cinta de salida
 * Pablo Pastor Martín
 * 17/02/2017
 * alu0100890839@ull.edu.es
 */

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

/**
 * Clase para la cinta de salida
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */
public class OutputTape {
	private FileWriter writer;
	private ArrayList<Integer> cinta;
	
	/**
	 * Constructor que crea la cinta a partir de un archivo
	 * @param filename Cadena que contiene el nombre del archivo de la cinta
	 */
	public OutputTape(String filename) throws IOException{
		writer = new FileWriter(filename);
		cinta = new ArrayList<Integer>();
	}
	
	/**
	 * Método para escribir el siguiente numero de la lista.
	 * @param i Número a escribir
	 * @throws IOException
	 */
	public void write(int i) throws IOException{
		cinta.add(i);
	}
	
	/**
	 * Cierra la cinta, para volcarla sobre el archivo
	 */
	public void close() throws IOException{
		for(int i = 0; i < cinta.size() - 1; i++) {
			writer.write(cinta.get(i) + "\n");
		}
		if(cinta.size()>0) {
			writer.write(cinta.get(cinta.size()-1)+"\n");
		}
		writer.close();
	}
	
	/**
	 * Imprime la cinta
	 * @return La cinta impresa
	 */
	public String toString() {
		String salida = "OUTPUT TAPE: \n| ";
		for(int i = 0; i < cinta.size(); i++) {
			salida += cinta.get(i) + " | ";
		}
		return salida;
	}
}
