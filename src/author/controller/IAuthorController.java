/**
 * 
 */
package author.controller;

import java.util.ResourceBundle;

import author.model.IAuthorModel;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface IAuthorController extends IAuthorControllerExternal{

	public IAuthorModel getModel();
	
	public void reinitializeView();

	public ResourceBundle getLaungaugeResourceBundle();
	
	public ResourceBundle getPathResourceBundle();
	
	public void setLanguageResourceBundle(String aLanguage);
	
}
