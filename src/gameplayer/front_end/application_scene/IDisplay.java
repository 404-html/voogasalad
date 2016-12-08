package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
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
	
	public void addNode(Node aNode);
	
	public void setBackground(String aFilePath, double aWidth, double aHeight);
	
}
