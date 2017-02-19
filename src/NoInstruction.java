/**
 * Excepcion para cuando salimos fuera de la memoria de programa.
 * Pablo Pastor Martín
 * alu0100890839@ull.edu.es
 * 19/02/2017
 */

/**
 * Excepcion para cuando salimos fuera de la memoria de programa.
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */
public class NoInstruction extends Exception {
	int index;
	
	/**
	 * Constructor a partir del índice inválido
	 * @param index Índice donde se buscaba
	 */
	public NoInstruction(int index) {
		this.index = index;
	}
	
	/**
	 * Obtiene la descripción del error
	 * @return Cadena con la descripcion
	 */
	public String whatsTheProblem() {
		return "No hay instrucción en el índice " + index;
	}
}
