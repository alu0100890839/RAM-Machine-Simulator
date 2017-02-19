/**
 * Pablo Pastor Mart�n
 * alu0100890839@ull.edu.es
 * 19/02/2017
 */

/**
 * Clase para usar la excepciones de instrucciones err�neas
 * @author Pablo Pastor Mart�n
 * @version 1.0.0
 */
public class WrongInstruction extends Exception{
	Instruction wrong;
	
	/**
	 * Constructor a partir de la instruccion erronea
	 * @param wrong La instrucci�n err�nea
	 */
	public WrongInstruction(Instruction wrong) {
		this.wrong = wrong;
	}
	
	/**
	 * Obtiene la descripci�n del error
	 * @return Cadena con la descripcion
	 */
	public String whatsTheProblem() {
		if(wrong.getKind() == null) {
			return "La instruccion (" + wrong.getString() +") no es de un tipo conocido.";
		}
		else {
			switch(wrong.getKind()) {
			case STORE:
				return "La instrucci�n STORE no puede usar direccionamiento constante";
			case WRITE:
				return "La instrucci�n WRITE no puede usarse con el registro 0";
			case READ:
				if(wrong.getAddressing() == Instruction.Addressing.constant) {
					return "La instrucci�n READ no puede usar direccionamiento constante";
				}
				else {
					return "La instrucci�n READ no puede usarse con el registro 0";
				}
			default: 
				return "La instrucci�n " + wrong.toString() + "no tiene formato v�lido.";
			}
		}
	}
}
