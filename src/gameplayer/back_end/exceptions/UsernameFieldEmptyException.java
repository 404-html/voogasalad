package gameplayer.back_end.exceptions;

public class UsernameFieldEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameFieldEmptyException(){
		super("Username may not be empty");
	}
}
