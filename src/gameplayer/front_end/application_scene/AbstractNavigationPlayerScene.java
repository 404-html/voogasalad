package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public abstract class AbstractNavigationPlayerScene extends AbstractPlayerScene {
	
	private MenuBar myNavigation;
	
	public AbstractNavigationPlayerScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		addNavigation();
	}
	
	public void addNavigationMenu(ImageView aImage, String[] aString, EventHandler<ActionEvent> ... aHandler) {
		myNavigation.getMenus().add(myGUIGenerator.createMenu(aImage, aString, aHandler));
	}
	
	private void addNavigation() {
		myNavigation = new MenuBar();
		//myNavigation.setAlignment(Pos.CENTER_LEFT);
		myRoot.setTop(myNavigation);
	}

}
