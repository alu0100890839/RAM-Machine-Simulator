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
	public enum Kind {
		LOAD,
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
	public enum Addressing {
		constant,
		direct,
		indirect,
		tag,
		none
	};
	
	private Kind kind;
	private Addressing addressing;
	private String operand;
	private String instructionString;
	
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
		this.instructionString = this.toString();
	}
	
	/**
	 * Constructor a partir de una línea que la contiene
	 * @param line linea que contiene a la instruccion
	 */
	public Instruction(String line) throws WrongInstruction{
		this.instructionString = line;
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
		if(this.getKind() == null ||!this.isValid()) {
			throw new WrongInstruction(this);
		}
	}
	
	/**
	 * Devuelve la línea que originó la instrucción
	 * @return la línea que originó la instrucción
	 */
	public String getString() {
		return instructionString;
	}
	
	/**
	 * Determina el tipo de instrucción
	 * @param line La línea de la instrucción
	 */
	private void analyzeKind(String line) {
		this.kind = null;
		for(Kind k : Kind.values()) {
			if(k.name().equals(line)) {
				this.kind = k;
			}
		}
	}
	
	/**
	 * Analiza el direccionamiento usado por la instrucción
	 * @param line Línea que contiene la instrucción
	 */
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
	
	/**
	 * Determina si una cadena contiene un número entero
	 * @param line la cadena
	 * @return TRUE si es un número entero
	 */
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
		if(this.kind==Kind.READ && addressing==Addressing.direct && operand.equals("0")) {
			return false;
		}
		if(this.kind==Kind.WRITE && addressing==Addressing.direct && operand.equals("0")) {
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
	
	/**
	 * Metodo para obtener una impresion de la instruccion
	 * @return La representacion en texto de la instrucción
	 */
	public String toString() {
		if(this.kind==null) {
			return this.instructionString;
		}
		else{
			String string = this.kind.name();
			if(this.addressing==Addressing.indirect) {
				string += " *"+this.operand;
			}
			else if(this.addressing==Addressing.constant) {
				string += " ="+this.operand;
			}
			else if(this.operand!=null){
				string += " "+operand;
			}
			return string;
		}
	}	
}
