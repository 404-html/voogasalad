package gameplayer.front_end.application_scene;
import javafx.scene.Scene;

public class LosingScene extends AbstractNavigationPlayerScene implements INavigationDisplay {

	public LosingScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		
	}

	@Override
	public Scene init() {
		return myScene;
	}
}
