package author.controller;

import java.io.File;

import author.model.AuthorModelFactory;
import author.model.IAuthorModel;
import author.view.AuthorView;
import javafx.scene.Scene;
import java.util.ResourceBundle;


/**
 * @author George Bernard
 * @author Addison Howenstine
 */
public class ConcreteAuthorController implements IAuthorController{

	private IAuthorModel authorModel;
	private AuthorView authorView;
	private ResourceBundle myLanguageResourceBundle;
	private ResourceBundle myPathResourceBundle;

	/* (non-Javadoc)
	 * @see author.controller.IAuthorControllerExternal#getScene()
	 */
	public ConcreteAuthorController() {
		this.authorModel = new AuthorModelFactory().create((IAuthorController) this);
		this.authorView = new AuthorView((IAuthorController) this);
		setLanguageResourceBundle("English");
		myLanguageResourceBundle = ResourceBundle.getBundle("author.resources/English");
	}
	
	@Override
	public Scene getScene() {
		return this.authorView.getScene();
	}

	/* (non-Javadoc)
	 * @see author.controller.IAuthorController#getModel()
	 */
	@Override
	public IAuthorModel getModel() {
		return this.authorModel;
	}

	/* (non-Javadoc)
	 * @see author.controller.IAuthorController#reinitializeView()
	 */
	@Override
	public void reinitializeView() {
		this.authorView.reinitializeView();
	}
	
	public void loadGame(File aFile){
		authorModel.loadGame(aFile);
	}

	@Override
	public void createNewGame(String aName) {
		authorModel.createNewGame(aName);
	}
	
	@Override
	public ResourceBundle getLaungaugeResourceBundle() {
		return myLanguageResourceBundle;
	}
	
	@Override
	public void setLanguageResourceBundle(String aLanguage) {
		myLanguageResourceBundle = ResourceBundle.getBundle("author.resources/" + aLanguage);
	}
	
	@Override
	public ResourceBundle getPathResourceBundle() {
		return myPathResourceBundle;
	}

}
