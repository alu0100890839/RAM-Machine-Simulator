/**
 * Excepcion para cuando salimos fuera de la memoria de programa.
 * Pablo Pastor Mart�n
 * alu0100890839@ull.edu.es
 * 19/02/2017
 */

/**
 * Excepcion para cuando salimos fuera de la memoria de programa.
 * @author Pablo Pastor Mart�n
 * @version 1.0.0
 */
public class NoInstruction extends Exception {
	int index;
	
	/**
	 * Constructor a partir del �ndice inv�lido
	 * @param index �ndice donde se buscaba
	 */
	public NoInstruction(int index) {
		this.index = index;
	}
	
	/**
	 * Obtiene la descripci�n del error
	 * @return Cadena con la descripcion
	 */
	public String whatsTheProblem() {
		return "No hay instrucci�n en el �ndice " + index;
	}
}
