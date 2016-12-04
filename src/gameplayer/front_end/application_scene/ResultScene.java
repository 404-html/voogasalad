package gameplayer.front_end.application_scene;
import javafx.scene.Scene;

public class ResultScene extends AbstractNavigationPlayerScene implements INavigationDisplay {

	public ResultScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
	}

	@Override
	public Scene init() {
		return myScene;
	}
}
