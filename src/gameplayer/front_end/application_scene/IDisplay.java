package gameplayer.front_end.application_scene;

import javafx.scene.Scene;

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
	
}
