package gameplayer.front_end.application_scene;

import java.io.File;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public abstract class AbstractNavigationPlayerScene extends AbstractPlayerScene implements INavigationDisplay {
	
	private static final String STYLESHEET = "data/gui/style.css";
	private MenuBar myNavigation;
	protected BorderPane myRoot;
	protected VBox myOptions;
	
	public AbstractNavigationPlayerScene(double aWidth, double aHeight) {
		initializeRoot();
		initializeScene(aWidth, aHeight);
		addNavigation();
	}
	
	@Override
	public void addButton(String aLabel, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aType) {
		myOptions.getChildren().add(myGUIGenerator.createButton(aLabel, 0, 0, aHandler, aType));
	}
	
	@Override
	public void addNode(Node aNode){
		aNode.setLayoutX(0);
		aNode.setLayoutY(0);
		myOptions.getChildren().add(aNode);
	}
	
	@Override
	public void addNavigationMenu(ImageView aImage, String[] aStringArray, @SuppressWarnings("unchecked") EventHandler<ActionEvent> ... aHandler) {
		myNavigation.getMenus().add(myGUIGenerator.createMenu(aImage, aStringArray, aHandler));
	}
	
	private void initializeRoot(){
		myRoot = new BorderPane();
		myRoot.setId("pane");
	}
	
	private void initializeScene(double aWidth, double aHeight){
		myScene = new Scene(myRoot, aWidth, aHeight);
		File file = new File(STYLESHEET);
	    myScene.getStylesheets().add(file.toURI().toString());
	}
	
	private void addNavigation() {
		myNavigation = new MenuBar();
		myRoot.setTop(myNavigation);
	}
}
