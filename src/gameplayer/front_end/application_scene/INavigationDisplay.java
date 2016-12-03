package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public interface INavigationDisplay extends IDisplay {
	
	public void addButton(String aLabel, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aType);
	
	public void addNavigationMenu(ImageView aImage, String[] aString, @SuppressWarnings("unchecked") EventHandler<ActionEvent> ...aHandlers);
	
	public void addNode(Node aNode);
}
