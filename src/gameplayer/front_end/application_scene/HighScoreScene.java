package gameplayer.front_end.application_scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class HighScoreScene extends AbstractNavigationPlayerScene {
	
	private Pane myPane;

	public HighScoreScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myPane = new Pane();
		myPane.setId("glass");
	} 

	@Override
	public Scene init(){
		getRoot().setCenter(addNodes());
		return myScene;
	}

	private Pane addNodes() {
		myPane.getChildren().add(getOptions());
		myPane.setOpacity(0.5);
		getOptions().setAlignment(Pos.CENTER);
		return myPane;
	}
}
