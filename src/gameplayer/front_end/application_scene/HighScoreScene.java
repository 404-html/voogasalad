package gameplayer.front_end.application_scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HighScoreScene extends AbstractNavigationPlayerScene {

	public HighScoreScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
	} 

	@Override
	public Scene init(){
		myRoot.setCenter(addNodes());
		return myScene;
	}

	private VBox addNodes() {
		myOptions = new VBox(BOX_INSETS);
		myOptions.setAlignment(Pos.CENTER);
		return myOptions;
	}
}
