package kasoulides.eleftheriou;

/**
 * This class represents a people overloading exception.
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 */
public class PeopleOverloadingException extends Exception {

	/**
	 * This is a constructor of PeopleOverloadingException.
	 * 
	 * @since 1.0
	 */
	public PeopleOverloadingException() {
		super("People overloading");
	}

	/**
	 * This is a constructor of PeopleOverloadingException.
	 * 
	 * @param message The error message
	 * 
	 * @since 1.0
	 */
	public PeopleOverloadingException(String message) {
		super(message);
	}

}
