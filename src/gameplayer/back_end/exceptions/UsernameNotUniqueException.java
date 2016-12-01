package gameplayer.back_end.exceptions;

public class UsernameNotUniqueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameNotUniqueException(){
		super();
	}
	
	public UsernameNotUniqueException(String aMessage){
		super(aMessage);
	}
}
