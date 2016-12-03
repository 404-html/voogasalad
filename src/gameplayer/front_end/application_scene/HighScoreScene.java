package gameplayer.front_end.application_scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HighScoreScene extends AbstractNavigationPlayerScene {
	
	private Pane myPane;

	public HighScoreScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myPane = new Pane();
	} 

	@Override
	public Scene init(){
		myRoot.setCenter(addNodes());
		return myScene;
	}

	private Pane addNodes() {
		myPane.getChildren().add(myOptions);
		myPane.setOpacity(0.5);
		myOptions = new VBox(BOX_INSETS);
		myOptions.setAlignment(Pos.CENTER);
		return myPane;
	}
}
