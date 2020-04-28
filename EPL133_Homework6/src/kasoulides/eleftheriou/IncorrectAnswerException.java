package kasoulides.eleftheriou;

/**
 * This class represents an incorrect answer exception.
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 */
public class IncorrectAnswerException extends Exception {

	/**
	 * This is a constructor of IncorrectAnswerException.
	 * 
	 * @since 1.0
	 */
	public IncorrectAnswerException() {
		super("Incorrect answer");
	}

	/**
	 * This is a constructor of IncorrectAnswerException.
	 * 
	 * @param message The error message
	 * 
	 * @since 1.0
	 */
	public IncorrectAnswerException(String message) {
		super(message);
	}

}
