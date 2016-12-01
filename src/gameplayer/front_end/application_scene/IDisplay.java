package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Every display must have an init method that returns a Scene
 *
 * 
 * @author tedmarchildon, hannah
 *
 */
public interface IDisplay {

	public final int BOX_INSETS = 20;
	
	public Scene init();
	
	public void addButton(String aLabel, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aType);
	
	public void addNavigationMenu(ImageView aImage, String[] aString, EventHandler<ActionEvent> ...aHandlers);
	
	public void addNode(Node aNode);
	
}
