/**
 * 
 */
package author.controller;

import author.model.IAuthorModel;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface IAuthorController extends IAuthorControllerExternal{

	public IAuthorModel getModel();
	
	public void reinitializeView();
	
}
