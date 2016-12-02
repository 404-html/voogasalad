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
		super(aWidth, aHeight);
		initializeRoot();
		initializeScene(aWidth, aHeight);
		addNavigation();
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
	
	@Override
	public void addButton(String text, EventHandler<? super MouseEvent> handler, ButtonDisplay aButtonDisplayType) {
		myOptions.getChildren().add(myGUIGenerator.createButton(text, 0, 0, handler, aButtonDisplayType));
	}
	
	@Override
	public void addNode(Node aNode){
		aNode.setLayoutX(0);
		aNode.setLayoutY(0);
		myOptions.getChildren().add(aNode);
	}
	
	@Override
	public void addNavigationMenu(ImageView aImage, String[] aStringArray, EventHandler<ActionEvent> ... aHandler) {
		myNavigation.getMenus().add(myGUIGenerator.createMenu(aImage, aStringArray, aHandler));
	}
	
	private void addNavigation() {
		myNavigation = new MenuBar();
		//myNavigation.setAlignment(Pos.CENTER_LEFT);
		myRoot.setTop(myNavigation);
	}
}
