package author.view;

import java.io.File;

import author.controller.IAuthorController;
import author.view.pages.level_editor.ILevelEditorExternal;
import author.view.pages.level_editor.LevelEditorFactory;
import author.view.pages.menu.AuthorMenu;
import author.view.pages.sprite.page.SpritesPage;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.facades.TabPaneFacade;

/**
 * AuthorView handles the JavaFX GUI.
 * 
 * @author George Bernard, Cleveland Thompson, Addison Howenstine, Jordan
 *         Frazier
 */
public class AuthorView {

	private static final String STYLESHEET = "data/gui/author-style.css";
	Scene myScene;
	Pane myPane = new VBox();
	TabPaneFacade myTabPaneFacade;
	IAuthorController myAuthorController;

	private SpritesPage mySpritesPage;
	private ILevelEditorExternal myLevelEditor;

	// TODO move these to properties, as well as button names
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

	public AuthorView(IAuthorController authorController) {
		this.myAuthorController = authorController;
		myScene = new Scene(myPane, WIDTH, HEIGHT, Color.WHITE);
		myScene.getStylesheets().add(getStyleSheet());
		initializeView();
	}

	private void initializeView() {
		this.mySpritesPage = new SpritesPage(myAuthorController);
		this.myLevelEditor = new LevelEditorFactory().create(this.myAuthorController);

		myPane.getChildren().addAll(buildMenu(), buildTabPane());
	}

	public void reinitializeView() {
		this.myPane.getChildren().clear();
		initializeView();
	}

	/**
	 * Returns Toolbar built for primary AuthorScene
	 */
	private Node buildMenu() {
		AuthorMenu menu = new AuthorMenu(myAuthorController, myLevelEditor);
		return menu.getMenu();
	}

	/**
	 * Returns TabPane built for primary AuthorScene
	 */
	private Node buildTabPane() {
		myTabPaneFacade = new TabPaneFacade();
		myTabPaneFacade.getTabPane().prefWidthProperty().bind(myScene.widthProperty());
		myTabPaneFacade.getTabPane().prefHeightProperty().bind(myScene.heightProperty());
		myTabPaneFacade.getTabPane().setSide(Side.BOTTOM);

		myTabPaneFacade.addTab(mySpritesPage.toString(), mySpritesPage.getRegion());
		myTabPaneFacade.addTab(myLevelEditor.toString(), myLevelEditor.getPane());

		return myTabPaneFacade.getTabPane();
	}

	private String getStyleSheet() {
		File css = new File(STYLESHEET);
		return css.toURI().toString();
	}

	public Scene getScene() {
		return myScene;
	}

}
