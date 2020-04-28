package kasoulides.eleftheriou;

/**
 * This class represents a negative number exception.
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 */
public class NegativeNumberException extends Exception {

	/**
	 * This is a constructor for NegativeNumberException.
	 * 
	 * @since 1.0
	 */
	public NegativeNumberException() {
		super("Negative number");
	}

	/**
	 * This is a constructor for NegativeNumberException.
	 * 
	 * @param message The error message
	 * 
	 * @since 1.0
	 */
	public NegativeNumberException(String message) {
		super(message);
	}

}
