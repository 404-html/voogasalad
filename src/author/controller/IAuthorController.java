/**
 * 
 */
package author.controller;

import java.util.ResourceBundle;

import author.model.IAuthorModel;
import author.view.util.language_selection.ILanguageHolder;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface IAuthorController extends ILanguageHolder, IAuthorControllerExternal{

	public IAuthorModel getModel();
	
	public void reinitializeView();
	
}
