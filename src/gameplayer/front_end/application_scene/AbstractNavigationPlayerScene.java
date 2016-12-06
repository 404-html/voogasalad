package gameplayer.front_end.application_scene;

import java.io.File;

import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public abstract class AbstractNavigationPlayerScene extends AbstractPlayerScene implements INavigationDisplay {
	
	private static final String STYLESHEET = "data/gui/style.css";
	private MenuBar myNavigation;
	private BorderPane myRoot;
	private Background myBackground;
	
	public AbstractNavigationPlayerScene(double aWidth, double aHeight) {
		initializeRoot();
		initializeScene(aWidth, aHeight);
		addNavigation();
	}
	
	@Override
	public void addNavigationMenu(ImageView aImage, String[] aStringArray, @SuppressWarnings("unchecked") EventHandler<ActionEvent> ... aHandler) {
		myNavigation.getMenus().add(getGUIGenerator().createMenu(aImage, aStringArray, aHandler));
	}
	
	protected BorderPane getRoot() {
		return myRoot;
	}
	
	
	private void initializeRoot() {
		myRoot = new BorderPane();
		myRoot.setId("pane");
	}
	
	public void setBackground(String aFile, double aWidth, double aHeight) {
		myBackground = new BackgroundDisplayFactory().buildBackgroundDisplay(aFile, aWidth, aHeight);
		myRoot.setBackground(myBackground);
	}
	
	private void initializeScene(double aWidth, double aHeight) {
		myScene = new Scene(myRoot, aWidth, aHeight);
		File file = new File(STYLESHEET);
	    myScene.getStylesheets().add(file.toURI().toString());
	}
	
	private void addNavigation() {
		myNavigation = new MenuBar();
		myRoot.setTop(myNavigation);
		myRoot.setCenter(getOptions());
	}
}
