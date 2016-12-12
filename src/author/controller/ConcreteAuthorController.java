package author.controller;

import java.io.File;

import author.model.AuthorModelFactory;
import author.model.IAuthorModel;
import author.view.AuthorView;
import author.view.util.language_selection.ILanguageHolder;
import javafx.beans.InvalidationListener;
import javafx.scene.Scene;

import java.util.Collection;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * @author George Bernard
 * @author Addison Howenstine
 */
public class ConcreteAuthorController implements IAuthorController {

	private IAuthorModel authorModel;
	private AuthorView authorView;
	private ResourceBundle myLanguageResourceBundle;
	private ResourceBundle myPathResourceBundle;
	private Collection<InvalidationListener> invalidationListeners;


	/* (non-Javadoc)
	 * @see author.controller.IAuthorControllerExternal#getScene()
	 */
	public ConcreteAuthorController() {
		setLanguageResourceBundle("English");
		myPathResourceBundle = ResourceBundle.getBundle("author.resources/Paths");
		this.authorModel = new AuthorModelFactory().create((IAuthorController) this);
		this.authorView = new AuthorView((IAuthorController) this);
		this.invalidationListeners = new HashSet<>();
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

	private void setLanguageResourceBundle(String aLanguage) {
		try {
			myLanguageResourceBundle = ResourceBundle.getBundle("author.resources/" + aLanguage);
		} catch (final MissingResourceException e) {
			System.out.println("couldn't find language " + aLanguage);
		}
	}

	@Override
	public void addListener(InvalidationListener aListener) {
		if (this.invalidationListeners == null)
			this.invalidationListeners = new HashSet<>();
		this.invalidationListeners.add(aListener);
	}

	@Override
	public void removeListener(InvalidationListener aListener) {
		if (this.invalidationListeners == null)
			this.invalidationListeners = new HashSet<>();
		if (this.invalidationListeners.contains(aListener))
			this.invalidationListeners.remove(aListener);
	}

	@Override
	public void setLanguage(String aLanguage) {
		setLanguageResourceBundle(aLanguage);
		notifyListeners();
	}

	@Override
	public String getDisplayText(String aKey) {
		try {
			return myLanguageResourceBundle.getString(aKey);
		} catch (final MissingResourceException e) {
			System.out.println("KEY " + aKey + " not found!");
			return "KEY NOT FOUND";
		}
	}

	@Override
	public String getPathString(String aKey) {
		try {
			return myPathResourceBundle.getString(aKey);
		} catch (final MissingResourceException e) {
			System.out.println("PATH NAME " + aKey + " not found!");
			return "";
		}
	}

	private void notifyListeners() {
		this.invalidationListeners.forEach((listener) -> {
			if (listener != null ) {
				listener.invalidated(this);
			}
		});
	}
}
