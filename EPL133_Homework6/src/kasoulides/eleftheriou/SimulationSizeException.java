package kasoulides.eleftheriou;

/**
 * This class represents a simulation size exception.
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 */
public class SimulationSizeException extends Exception {

	/**
	 * This is a constructor of SimulationSizeException.
	 * 
	 * @since 1.0
	 */
	public SimulationSizeException() {
		super("Simulation size error");
	}

	/**
	 * This is a constructor of SimulationSizeException.
	 * 
	 * @param message The error message
	 * 
	 * @since 1.0
	 */
	public SimulationSizeException(String message) {
		super(message);
	}

}
