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
public class BordersOutOfRangeException extends Exception {

	/**
	 * This is a constructor of BordersOutOfRangeException
	 * 
	 * @since 1.0
	 */
	public BordersOutOfRangeException() {
		super("Out of range");
	}

	/**
	 * This is a constructor o BordersOutOfRangeException
	 * 
	 * @param message The error message
	 * 
	 * @since 1.0
	 */
	public BordersOutOfRangeException(String message) {
		super(message);
	}

}