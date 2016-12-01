package gameplayer.back_end.exceptions;

public class GameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameNotFoundException() {
		super();
	}
	
	public GameNotFoundException(String aMessage) {
		super(aMessage);
	}
	
}
