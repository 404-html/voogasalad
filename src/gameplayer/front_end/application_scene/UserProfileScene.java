package gameplayer.front_end.application_scene;

import gameplayer.back_end.facebook.FacebookInformation;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.VBox;

public class UserProfileScene extends AbstractNavigationPlayerScene {
	
	private FacebookInformation myUserInformation;
	private GUIGenerator myGUIGenerator; 
	private String myUserName;
	private String myPictureUrl;

	public UserProfileScene(String aName, String aUrl, double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myGUIGenerator = new GUIGenerator();
		myUserName = aName;
		myPictureUrl = aUrl;
	}

	@Override
	public Scene init(){
		//getRoot().setCenter(addNodes());
		getRoot().setCenter(addTop());
		System.out.println(myScene);
		return myScene;
	}

	private VBox addTop() {
		getOptions().getChildren().add(myGUIGenerator.createLabel(myUserName, 0, 0));
		getOptions().setAlignment(Pos.TOP_LEFT);
        
        ImageView profilePicture = new ImageView(new Image(myPictureUrl));
         
        getOptions().getChildren().add(profilePicture);
		//myOptions.bin
		return getOptions();
	}
}
