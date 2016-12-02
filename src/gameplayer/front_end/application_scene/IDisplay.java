package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

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

	//public void addNode(ComboBox<Node> createComboBox);

	//public void addButton(String string, EventHandler<? super MouseEvent>, ButtonDisplay text);
	
}
