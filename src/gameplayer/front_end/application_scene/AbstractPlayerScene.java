package gameplayer.front_end.application_scene;

import java.io.File;
import java.util.Observable;

import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Representation of generic functionality each scene might need
 * 
 * @author tedmarchildon, hannah
 *
 */

public abstract class AbstractPlayerScene  extends Observable implements IDisplay {
	
	private static final String STYLESHEET = "data/gui/style.css";
	private static final String BACKGROUND_IMAGE = "data/gui/hawaiian_shirt_background3.png";
	protected Scene myScene;
	protected BorderPane myRoot;
	protected VBox myOptions;
	protected IGUIGenerator myGUIGenerator;
	
	public AbstractPlayerScene(double aWidth, double aHeight) {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, aWidth, aHeight);
		File file = new File(STYLESHEET);
	    myScene.getStylesheets().add(file.toURI().toString());
	    myRoot.setId("pane");
	    Background backgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(BACKGROUND_IMAGE, aWidth, aHeight);
	    myGUIGenerator = new GUIGenerator();
	}
	
	public void addButton(String text, EventHandler<? super MouseEvent> handler, ButtonDisplay aButtonDisplayType) {
		myOptions.getChildren().add(myGUIGenerator.createButton(text, 0, 0, handler, aButtonDisplayType));
	}
	
	public void addNode(Node aNode){
		aNode.setLayoutX(0);
		aNode.setLayoutY(0);
		myOptions.getChildren().add(aNode);
	}
}
