/**
 * Clase que representa a las instrucciones
 * Pablo Pastor Mart�n
 * alu0100890839@ull.edu.es
 * 17/02/2017
 */

/**
 * Clase que representa las instrucciones
 * @author Pablo Pastor Mart�n
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
	 * Constructor a partir de un tipo de instrucci�n, un direccionamiento y un operando
	 * @param kind Tipo de la instrucci�n
	 * @param addressing Tipo de direccionamiento
	 * @param operand Operando
	 */
	public Instruction(Kind kind, Addressing addressing, String operand) {
		this.kind = kind;
		this.addressing = addressing;
		this.operand = operand;
	}
	
	/**
	 * Constructor a partir de una l�nea que la contiene
	 * @param line linea que contiene a la instruccion
	 */
	public Instruction(String line) {
		String[] tokens = line.split("\\s+");
		tokens[0] = tokens[0].toUpperCase();
		
		analyzeKind(tokens[0]);
		
		if(this.kind != null && this.kind!=Kind.HALT) {
			if(tokens.length == 2) {
				analyzeAddressing(tokens[1]);
			}
			else {
				this.kind = null;
			}
		}
	}
	
	private void analyzeKind(String line) {
		this.kind = null;
		for(Kind k : Kind.values()) {
			if(k.name().equals(line)) {
				this.kind = k;
			}
		}
	}
	
	private void analyzeAddressing(String line) {
		if(line.charAt(0) == '=') {
			this.addressing = Addressing.constant;
			this.operand = line.substring(1);
		}
		else if(line.charAt(0) == '*') {
			this.addressing = Addressing.indirect;
			this.operand = line.substring(1);
		}
		else if(isNumber(line)) {
			this.addressing = Addressing.direct;
			this.operand = line;
		}
		else {
			this.addressing = Addressing.tag;
			this.operand = line;
		}
	}
	
	private boolean isNumber(String line) {
		for(int i=0; i < line.length(); i++) {
			if(line.charAt(i)>'9' || line.charAt(i)<'0') {
				if(line.charAt(i)!='-') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Metodo que decide si una instrucci�n es v�lida o no
	 * @return Devuelve si la instrucci�n es v�lida o no
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
	 * Setter del tipo de instrucci�n
	 * @param kind Tipo de instrucci�n
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
	
	/**
	 * Metodo para obtener una impresion de la instruccion
	 * @return La representacion en texto de la instrucci�n
	 */
	public String toString() {
		if(this.kind==null) {
			return "NOT VALID INSTRUCTION";
		}
		else{
			String string = this.kind.name();
			if(this.addressing==Addressing.indirect) {
				string += " *"+this.operand;
			}
			else if(this.addressing==Addressing.constant) {
				string += " ="+this.operand;
			}
			else {
				string += " "+operand;
			}
			return string;
		}
	}
}
