package team5.hw6;

/**
 * This class represents an out of range exception.
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 */
public class OutOfRangeException extends Exception {

	/**
	 * This is a constructor of OutOfRangeException
	 * 
	 * @since 1.0
	 */
	public OutOfRangeException() {
		super("Out of range");
	}

	/**
	 * This is a constructor o OutOfRangeException
	 * 
	 * @param message The error message
	 * 
	 * @since 1.0
	 */
	public OutOfRangeException(String message) {
		super(message);
	}

}