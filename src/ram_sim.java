/**
 * Main de la máqina
 * Pablo Pastor Martín
 * alu0100890839@ull.edu.es
 * 18/02/2017
 */
import java.io.IOException;
/**
 * Clase que contiene el main de la máquina
 * @author Pablo Pastor Martín
 * @version 1.0.0
 */
public class ram_sim {
	/**
	 * Main
	 */
	public static void main(String args[]) throws IOException{
		if(args.length < 3 || args.length > 5) {
			System.out.println("Comprueba que la invocación sea correcta");
		}
		else {
			AluCu machine;
			if(args.length==5 && args[4].equals("1")){
				machine = new AluCu(args[0], args[1], args[2], true);
			}
			else {
				machine = new AluCu(args[0], args[1], args[2], false);
			}
			machine.run();
		}
	}
}
