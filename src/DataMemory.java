/**
 * Memoria de datos
 * Pablo Pastor Mart�n
 * alu0100890839@ull.edu.es
 * 17/02/2017
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para la memoria de datos. Contiene enteros.
 * @author Pablo Pastor Mart�n
 * @version 1.0.0
 */
public class DataMemory {
	private HashMap<Integer, Integer> memory;
	
	/**
	 * Constructor por defecto
	 */
	public DataMemory() {
		memory = new HashMap<Integer, Integer>();
	}
	
	/**
	 * Obtiene el valor contenido en la posicion indicada
	 * @param index Posicion donde se encuentra el valor
	 * @return El valor
	 */
	public int get(int index) {
		if(memory.containsKey(index)) {
			return memory.get(index);
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Metodo para establecer los valores de las posiciones de la memoria
	 * @param index �ndice de la celda donde guardar el valor
	 * @param value valor a guardar
	 */
	public void set(int index, int value) {
		memory.put(index, value);
	}
	
	/**
	 * Metodo para resetear la memoria
	 */
	public void reset() {
		memory = new HashMap<Integer, Integer>();
	}
	
	/**
	 * M�todo para obtener la representaci�n en texto de la memoria
	 * @return La representaci�n en forma de cadena de la memoria
	 */
	public String toString() {
		String string = "DATA MEMORY:\n"+
						"INDEX     | VALUE    \n" + 
						"----------------------";
		for( Map.Entry<Integer, Integer> cell : memory.entrySet()) {
			string += "\n" + String.format("% 10d", cell.getKey()) + "|" + String.format("% 10d", cell.getValue()) ;
		}
		return string;
	}
}
