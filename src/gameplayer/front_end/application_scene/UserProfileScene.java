package gameplayer.front_end.application_scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class UserProfileScene extends AbstractNavigationPlayerScene {

	public UserProfileScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
	}

	@Override
	public Scene init(){
		myRoot.setCenter(addNodes());
		System.out.println(myScene);
		return myScene;
	}

	private VBox addNodes() {
		myOptions = new VBox(BOX_INSETS);
		myOptions.setAlignment(Pos.CENTER);
		//myOptions.bin
		return myOptions;
	}

}
