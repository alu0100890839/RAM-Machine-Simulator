/**
 * Pablo Pastor Martín
 * 17/02/2017
 * alu0100890839@ull.edu.es
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase que contiene la memoria de programa (Que a su vez contiene instrucciones) y mapea las etiquetas a su respectiva instruccion
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */

public class ProgramMemory {
	private ArrayList<Instruction> instructions;
	private HashMap<String, Integer> tagsPositions;
	
	/**
	 * Constructor a partir de un fichero con instrucciones
	 * @param filename Archivo con las instrucciones
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ProgramMemory(String filename) throws FileNotFoundException, IOException{
		instructions = new ArrayList<Instruction>();
		tagsPositions = new HashMap<String, Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		int instructionsAdded = 0;
		while(reader.ready()) {
			String line = removeBlankSpace(reader.readLine());
			if(!isComment(line) && !line.isEmpty()) {
				if(containsTag(line)) {
					tagsPositions.put(getTagName(line), instructionsAdded);
					line = removeTag(line);
				}
				if(!line.isEmpty()) {
					instructions.add(new Instruction(line));
					instructionsAdded++;
				}
			}
		}
		reader.close();
	}
	
	/**
	 * Metodo para obtener la instruccion en una posición dada
	 * @param index Número de la instrucción a devolver
	 * @return La instruccion buscada
	 */
	public Instruction getInstruction(int index) {
		return instructions.get(index);
	}
	
	/**
	 * Metodo para obtener el número de la instruccion a ejecutar al saltar con una etiqueta
	 * @param tagName Nombre de la etiqueta
	 * @return Índice de la instrucción a ejecutar
	 */
	public int getTagIndex(String name) {
		return tagsPositions.get(name);
	}
	
	/**
	 * Metodo para imprimir la memoria de programa
	 * @return La representación de la memoria en forma de texto
	 */
	public String toString() {
		String string = "PROGRAM MEMORY: \n";
		for(int i=0; i < instructions.size() ; i++) {
			string += instructions.get(i).toString();
		}
		return string;
	}
	
	private String removeBlankSpace(String line) {
		return line.trim();
	}
	
	private boolean isComment(String line) {
		line = removeBlankSpace(line);
		return line.charAt(0) == '#';
	}
	
	private boolean containsTag(String line) {
		return line.indexOf(':') != -1;
	}
	
	private String removeTag(String line) {
		return line.substring(line.indexOf(':')+1).trim();
	}
	
	private String getTagName(String line) {
		return line.substring(0, line.indexOf(':'));
	}
}
