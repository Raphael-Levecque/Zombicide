package zombicide.exception;

/**
 * Exception thrown when a zone does not exist
 */
public class NoSuchZoneException extends Exception {
	
	/**
	 * Constructor of NoSuchZoneException
	 * @param msg the message to display
	 */
	public NoSuchZoneException(String msg) {
		super(msg);
	}

}
