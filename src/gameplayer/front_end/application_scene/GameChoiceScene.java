package gameplayer.front_end.application_scene;

import javafx.scene.Scene;

/**
 * Concrete representation of the scene where users can choose which game to play
 * 
 * @author tedmarchildon, hannah
 *
 */
public class GameChoiceScene extends AbstractNavigationPlayerScene {

	public GameChoiceScene(double aWidth, double aHeight){
		super(aWidth, aHeight);
	}

	@Override
	public Scene init() {
		return myScene;
	}
}
