package gameplayer.front_end.application_scene;

import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import javafx.scene.Scene;

/**
 * Representation of generic functionality each scene might need
 * 
 * @author tedmarchildon, hannah
 *
 */

public abstract class AbstractPlayerScene implements IDisplay {
	
	protected Scene myScene;
	protected IGUIGenerator myGUIGenerator;
	
	public AbstractPlayerScene() {
	    myGUIGenerator = new GUIGenerator();
	}
}
