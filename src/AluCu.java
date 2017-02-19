/**
 * Pablo Pastor Mart�n
 * alu0100890839@ull.edu.es
 * 18/02/2017
 */

import java.io.IOException;

/**
 * Clase que representa la unidad aritm�tica l�gica y la unidad de control de la m�quina
 * @author Pablo Pastor Mart�n
 * @version 1.0.0
 */
public class AluCu {
	private int ip;
	private InputTape input;
	private OutputTape output;
	private ProgramMemory program;
	private DataMemory data;
	private boolean debug;
	private int instructionsDone;
	private boolean stop;
	
	/**
	 * Constructor a partir del nombre de los ficheros
	 * @param programFile Fichero con las instrucciones
	 * @param inputTape Fichero con la cinta de entrada
	 * @param outputTape Fichero con la cinta de salida
	 * @throws IOException
	 */
	public AluCu(String programFile, String inputTape, String outputTape, boolean debug) throws IOException{
		input = new InputTape(inputTape);
		output = new OutputTape(outputTape);
		data = new DataMemory();
		program = new ProgramMemory(programFile);
		ip = 0;
		stop = false;
		this.debug = debug;
		instructionsDone=0;
	}
	
	/**
	 * Comienza la ejecuci�n del programa
	 */
	public void run() throws IOException{
		Instruction currentInstruction;
		while(!stop ) {
			try{
				currentInstruction = program.getInstruction(ip);
				if(currentInstruction.isValid()) {
					if(debug) {
						System.out.println("Instruction: " + program.getInstruction(ip));
						System.out.println("IP: " + ip);
					}
					runInstruction(currentInstruction);
					instructionsDone++;
					if(debug) {
						System.out.println(data);
						//System.out.println(program);
						System.out.println(input);
						System.out.println(output);
						System.out.println("Instrucciones realizadas: " + instructionsDone + "\n\n");
					}
					ip++;
				}
				else {
					stop = true;
				}
			}
			catch (NoInstruction e){
				System.out.println(e.whatsTheProblem());
				throw new RuntimeException("Acceso a instrucci�n inexistente");
			}
		}
		output.close();
	}
	
	/**
	 * Obtiene el operando que usa la instruccion
	 * @param instruction Instrucci�n a analizar
	 * @return El operando
	 */
	private int getNumericOperand(Instruction instruction) {
		switch(instruction.getAddressing()) {
			case constant:
				return Integer.parseInt(instruction.getOperand());
			case direct:
				return data.get(Integer.parseInt(instruction.getOperand()));
			case indirect:
				int newDirect = data.get(Integer.parseInt(instruction.getOperand()));
				return data.get(newDirect);
			case tag:
				return program.getTagIndex(instruction.getOperand());
			default:
				return -1;
		}
	}
	
	/**
	 * Obtiene el operando que usa la instruccion en caso de que sea de tipo registro
	 * @param instruction Instrucci�n a analizar
	 * @return El operando
	 */
	private int getRegisterOperand(Instruction instruction) {
		switch(instruction.getAddressing()) {
			case direct:
				return Integer.parseInt(instruction.getOperand());
			case indirect:
				return data.get(Integer.parseInt(instruction.getOperand()));
			default:
				return -1;
		}
	}
	
	/**
	 * Ejecuta la instrucci�n LOAD
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runLoad(Instruction instruction) {
		data.set(0, getNumericOperand(instruction));
	}
	
	/**
	 * Ejecuta la instrucci�n STORE
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runStore(Instruction instruction) {
		int index = getRegisterOperand(instruction);
		data.set(index, data.get(0));
	}
	
	/**
	 * Ejecuta la instrucci�n ADD
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runAdd(Instruction instruction) {
		int operand = getNumericOperand(instruction);
		data.set(0, data.get(0) + operand);
	}
	
	/**
	 * Ejecuta la instrucci�n SUB
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runSub(Instruction instruction) {
		int operand = getNumericOperand(instruction);
		data.set(0, data.get(0) - operand);
	}
	
	/**
	 * Ejecuta la instrucci�n MUL
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runMul(Instruction instruction) {
		int operand = getNumericOperand(instruction);
		data.set(0, data.get(0) * operand);
	}
	
	/**
	 * Ejecuta la instrucci�n DIV
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runDiv(Instruction instruction) {
		int operand = getNumericOperand(instruction);
		data.set(0, data.get(0) / operand);
	}
	
	/**
	 * Ejecuta la instrucci�n READ
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runRead(Instruction instruction) throws IOException{
		data.set(getRegisterOperand(instruction), input.read());
	}
	
	/**
	 * Ejecuta la instrucci�n WRITE
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runWrite(Instruction instruction) throws IOException{
		output.write(getNumericOperand(instruction));
	}
	
	/**
	 * Ejecuta la instrucci�n JUMP
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runJump(Instruction instruction) {
		ip = getNumericOperand(instruction)-1;
	}
	
	/**
	 * Ejecuta la instrucci�n JZERO
	 * @param instruction Instrucci�n a ejecutar
	 */
	private void runJzero(Instruction instruction) {
		if(data.get(0) == 0) {
			ip = getNumericOperand(instruction)-1;
		}
	}
	
	/**
	 * Ejecuta la instrucci�n JGTZ ejecutar
	 */
	private void runJgtz(Instruction instruction) {
		if(data.get(0) > 0) {
			ip = getNumericOperand(instruction)-1;
		}
	}
	
	/**
	 * Ejecuta una instrucci�n
	 * @param instruction Instrucci�n a ejecutar
	 * @throws IOException
	 */
	private void runInstruction(Instruction instruction) throws IOException{
		switch(instruction.getKind()) {
			case LOAD:
				runLoad(instruction);
				break;
			case HALT:
				stop = true;
				System.out.println("Fin");
				break;
			case STORE:
				runStore(instruction);
				break;
			case ADD:
				runAdd(instruction);
				break;
			case SUB:
				runSub(instruction);
				break;
			case MUL:
				runMul(instruction);
				break;
			case DIV:
				runDiv(instruction);
				break;
			case READ:
				runRead(instruction);
				break;
			case WRITE:
				runWrite(instruction);
				break;
			case JUMP:
				runJump(instruction);
				break;
			case JZERO:
				runJzero(instruction);
				break;
			case JGTZ:
				runJgtz(instruction);
				break;
		}
	}
}
