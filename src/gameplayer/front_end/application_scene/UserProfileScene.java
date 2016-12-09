package gameplayer.front_end.application_scene;

import gameplayer.back_end.Resources.FrontEndResources;
import gameplayer.back_end.facebook.FacebookInformation;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UserProfileScene extends AbstractNavigationPlayerScene {
	
	private FacebookInformation myUserInformation;
	private GUIGenerator myGUIGenerator; 
	private String myUserName;
	private String myPictureUrl = "gui/blank_profile_page.jpeg";
	private double myWidth;
	private double myHeight;
	private Pane myPane;

	public UserProfileScene(String aName, String aUrl, double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myGUIGenerator = new GUIGenerator();
		myUserName = aName;
		myWidth = aWidth;
		myHeight = aHeight;
		myPane = new Pane();
		myPane.setId("glass");
		if (aUrl != null) myPictureUrl = aUrl;
	}

	@Override
	public Scene init(){
		//getRoot().setCenter(addNodes());
		addTop();
		getRoot().setCenter(myPane);
		return myScene;
	}

	private void addTop() {
		HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource()); 
		hbox.setOpacity(1);
		ImageView profilePicture = new ImageView(new Image(myPictureUrl));
		profilePicture.setFitWidth(myWidth * .1);
		profilePicture.setPreserveRatio(true);
		//profilePicture.setFitHeight(myHeight * .1);
		hbox.getChildren().add(profilePicture);
		Label userName = myGUIGenerator.createLabel(myUserName, 0, 0);
		//userName.setId("heading");
		hbox.getChildren().add(userName);
		hbox.setAlignment(Pos.TOP_LEFT);
		//hbox.setLayoutX(myWidth * .1);
		//hbox.setLayoutY(myHeight * .1);
		myPane.getChildren().add(hbox);
	}
}
