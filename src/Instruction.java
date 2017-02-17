/**
 * Clase que representa a las instrucciones
 * Pablo Pastor Martín
 * alu0100890839@ull.edu.es
 * 17/02/2017
 */

/**
 * Clase que representa las instrucciones
 * @author Pablo Pastor Martín
 * @version 1.0.0 
 */
public class Instruction {
	/**
	 * Tipos de instrucciones
	 */
	public enum Kind {LOAD,
		STORE,
		ADD,
		SUB,
		MUL,
		DIV,
		READ,
		WRITE,
		JUMP,
		JZERO,
		JGTZ,
		HALT
	};
	
	/**
	 * Tipos de direccionamientos
	 */
	public enum Addressing {constant,
		direct,
		indirect,
		tag,
		none
	};
	
	private Kind kind;
	private Addressing addressing;
	private String operand;
	
	/**
	 * Constructor a partir de un tipo de instrucción, un direccionamiento y un operando
	 * @param kind Tipo de la instrucción
	 * @param addressing Tipo de direccionamiento
	 * @param operand Operando
	 */
	public Instruction(Kind kind, Addressing addressing, String operand) {
		this.kind = kind;
		this.addressing = addressing;
		this.operand = operand;
	}
	
	/**
	 * Metodo que decide si una instrucción es válida o no
	 * @return Devuelve si la instrucción es válida o no
	 */
	public boolean isValid() {
		if(this.kind==Kind.STORE && addressing==Addressing.constant) {
			return false;
		}
		if(this.kind==Kind.READ && addressing==Addressing.constant) {
			return false;
		}
		return true;
	}
	
	/**
	 * Getter del tipo de instruccion
	 * @return El tipo
	 */
	public Kind getKind() {
		return kind;
	}
	
	/**
	 * Setter del tipo de instrucción
	 * @param kind Tipo de instrucción
	 */
	public void setKind(Kind kind) {
		this.kind = kind;
	}
	
	/**
	 * Getter del tipo de direccionamiento
	 * @return El tipo de direccionamiento
	 */
	public Addressing getAddressing() {
		return addressing;
	}
	
	/**
	 * Setter del tipo de direccionamiento
	 * @param adressing Tipo de direccionamiento
	 */
	public void setAddressing(Addressing adressing) {
		this.addressing = adressing;
	}

	/**
	 * Getter del operando
	 * @return El operando
	 */
	public String getOperand() {
		return operand;
	}

	/**
	 * Setter del operando
	 * @param operand El operando a poner
	 */
	public void setOperand(String operand) {
		this.operand = operand;
	}
}
