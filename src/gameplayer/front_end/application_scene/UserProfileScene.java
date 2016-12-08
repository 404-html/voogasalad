package gameplayer.front_end.application_scene;

import gameplayer.back_end.facebook.FacebookInformation;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
		HBox hbox = new HBox(BOX_INSETS); 
		ImageView profilePicture = new ImageView(new Image(myPictureUrl));
		profilePicture.setFitWidth(myWidth * .1);
		profilePicture.setPreserveRatio(true);
		//profilePicture.setFitHeight(myHeight * .1);
		hbox.getChildren().add(profilePicture);
		hbox.getChildren().add(myGUIGenerator.createLabel(myUserName, 0, 0));
		hbox.setAlignment(Pos.TOP_LEFT);
		//hbox.setLayoutX(myWidth * .1);
		//hbox.setLayoutY(myHeight * .1);
		myPane.getChildren().add(hbox);
	}
}
