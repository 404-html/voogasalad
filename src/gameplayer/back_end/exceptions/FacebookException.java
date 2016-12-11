package gameplayer.back_end.exceptions;

public class FacebookException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FacebookException(){
		super();
	}

	public FacebookException(String aMessage) {
		super(aMessage);
	}
}

