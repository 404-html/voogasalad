/**
 * 
 */
package author.controller;

import java.io.File;

/**
 * @author Cleveland Thompson V (ct168)
 * @author Addison Howenstine (arh55)
 *
 */
public class AuthorControllerFactory {

	/**
	 * 
	 */
	public AuthorControllerFactory() {
		// Empty On Purpose
	}
	
	public IAuthorControllerExternal create(){
		return new ConcreteAuthorController();
	}
	
	public IAuthorControllerExternal create(File aFile){
		ConcreteAuthorController cac = new ConcreteAuthorController();
		cac.loadGame(aFile);
		return cac;
	}

}
