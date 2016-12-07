package author.controller;

import java.io.File;

import author.model.AuthorModelFactory;
import author.model.IAuthorModel;
import author.view.AuthorView;
import javafx.scene.Scene;

/**
 * @author George Bernard
 * @author Addison Howenstine
 */
public class ConcreteAuthorController implements IAuthorController, IAuthorControllerExternal{

	private IAuthorModel authorModel;
	private AuthorView authorView;
	/* (non-Javadoc)
	 * @see author.controller.IAuthorControllerExternal#getScene()
	 */
	public ConcreteAuthorController() {
		this.authorModel = new AuthorModelFactory().create((IAuthorController) this);
		this.authorView = new AuthorView((IAuthorController) this);
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
		authorView.loadDefaultSprites();
	}

}
