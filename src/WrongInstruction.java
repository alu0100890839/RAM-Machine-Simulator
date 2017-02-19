/**
 * Pablo Pastor Martín
 * alu0100890839@ull.edu.es
 * 19/02/2017
 */

/**
 * Clase para usar la excepciones de instrucciones erróneas
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */
public class WrongInstruction extends Exception{
	Instruction wrong;
	
	/**
	 * Constructor a partir de la instruccion erronea
	 * @param wrong La instrucción errónea
	 */
	public WrongInstruction(Instruction wrong) {
		this.wrong = wrong;
	}
	
	/**
	 * Obtiene la descripción del error
	 * @return Cadena con la descripcion
	 */
	public String whatsTheProblem() {
		if(wrong.getKind() == null) {
			return "La instruccion (" + wrong.getString() +") no es de un tipo conocido.";
		}
		else {
			switch(wrong.getKind()) {
			case STORE:
				return "La instrucción STORE no puede usar direccionamiento constante";
			case WRITE:
				return "La instrucción WRITE no puede usarse con el registro 0";
			case READ:
				if(wrong.getAddressing() == Instruction.Addressing.constant) {
					return "La instrucción READ no puede usar direccionamiento constante";
				}
				else {
					return "La instrucción READ no puede usarse con el registro 0";
				}
			default: 
				return "La instrucción " + wrong.toString() + "no tiene formato válido.";
			}
		}
	}
}
