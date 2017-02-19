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
	public ProgramMemory(String filename) throws FileNotFoundException, IOException, RuntimeException{
		instructions = new ArrayList<Instruction>();
		tagsPositions = new HashMap<String, Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		int instructionsAdded = 0;
		while(reader.ready()) {
			String line = removeBlankSpace(reader.readLine());
			if( !line.isEmpty() && !isComment(line)) {
				if(containsTag(line)) {
					tagsPositions.put(getTagName(line), instructionsAdded);
					line = removeTag(line);
				}
				if(!line.isEmpty()) {
					try {
						instructions.add(new Instruction(line));
					}
					catch(WrongInstruction e) {
						System.out.println("Fallo en instrucción: " + e.whatsTheProblem());
						reader.close();
						throw new RuntimeException("No se puede ejecutar con instrucciones erróneas");
					}
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
	public Instruction getInstruction(int index) throws NoInstruction{
		if(instructions.size() == 0 ||index < 0 ||index >= instructions.size()) {
			throw new NoInstruction(index);
		}
		return instructions.get(index);
	}
	
	/**
	 * Metodo para obtener el número de la instruccion a ejecutar al saltar con una etiqueta
	 * @param name Nombre de la etiqueta
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
			string += instructions.get(i).toString()+"  ["+ i +"]"+"\n";
		}
		return string;
	}
	
	/**
	 * Metodo para resetear la memoria
	 */
	public void reset() {
		instructions = new ArrayList<Instruction>();
		tagsPositions = new HashMap<String, Integer>();
	}
	
	/**
	 * Elimina el espacio en blanco de una línea al final y principio
	 * @param line La línea
	 * @return La línea "limpiada"
	 */
	private String removeBlankSpace(String line) {
		return line.trim();
	}
	
	/**
	 * Determina si una línea es un comentario
	 * @param line La línea
	 * @return Bool que determina si es un comentario o no
	 */
	private boolean isComment(String line) {
		line = removeBlankSpace(line);
		return line.charAt(0) == '#';
	}
	
	/**
	 * Determina si la línea contiene una etiqueta
	 * @param line la línea
	 * @return TRUE si la contiene
	 */
	private boolean containsTag(String line) {
		return line.indexOf(':') != -1;
	}
	
	/**
	 * Quita el fragmento de la etiqueta de la línea
	 * @param line la línea
	 * @return La línea sin la parte de la etiqueta
	 */
	private String removeTag(String line) {
		return line.substring(line.indexOf(':')+1).trim();
	}
	
	/**
	 * Método para obtener el nombre de la etiqueta de la instrucción
	 * @param line La línea con la etiqueta 
	 * @return El nombre de la etiqueta
	 */
	private String getTagName(String line) {
		return line.substring(0, line.indexOf(':'));
	}
}
