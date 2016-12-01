package gameplayer.back_end.exceptions;

public class UsernameNotFoundException extends Exception {

	/** The user likely needs to sign up if this exception is thrown
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameNotFoundException(){
		super();
	}
	
	public UsernameNotFoundException(String aMessage){
		super(aMessage);
	}
}
