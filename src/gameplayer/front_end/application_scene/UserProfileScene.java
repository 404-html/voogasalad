package gameplayer.front_end.application_scene;

import gameplayer.back_end.facebook.FacebookInformation;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class UserProfileScene extends AbstractNavigationPlayerScene {
	
	private FacebookInformation myUserInformation;
	private GUIGenerator myGUIGenerator; 

	public UserProfileScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myUserInformation = new FacebookInformation();
		myGUIGenerator = new GUIGenerator();
	}

	@Override
	public Scene init(){
		getRoot().setCenter(addNodes());
		System.out.println(myScene);
		return myScene;
	}

	private VBox addNodes() {
		getOptions().getChildren().add(myGUIGenerator.createLabel(myUserInformation.getUserName(), 0, 0));
		getOptions().setAlignment(Pos.CENTER);
		//myOptions.bin
		return getOptions();
	}
}
