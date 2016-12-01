package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public abstract class AbstractNavigationPlayerScene extends AbstractPlayerScene {
	
	public AbstractNavigationPlayerScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		addNavigation();
	}
	
	public void addNavigationButton(String aText, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aType) {
		myNavigation.getChildren().add(myGUIGenerator.createButton(aText, 0, 0, aHandler, aType));
	}
	
	private void addNavigation() {
		myNavigation = new HBox(BOX_INSETS);
		myNavigation.setAlignment(Pos.CENTER_LEFT);
		myRoot.setTop(myNavigation);
	}

}
