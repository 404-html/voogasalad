package gameplayer.application_scene;

import gameplayer.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Login Scene Initiation
 *
 * @author tedmarchildon, hannah
 *
 */
public class LoginScene extends AbstractPlayerScene {

	private VBox myOptions;
	private HBox myLoginOptions;
	private TextField myUserName;
	private TextField myPassword;
	
	public LoginScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
	}
	
	@Override
	public void addButton(String text, EventHandler<? super MouseEvent> handler, ButtonDisplay aButtonType){
		myLoginOptions.getChildren().add(myGUIGenerator.createButton(text, 0, 0, handler, aButtonType));
	}

	@Override
	public Scene init() {
		myRoot.setCenter(addNodes());
		return myScene;
	}
	
	private VBox addNodes() {
		myOptions = new VBox(BOX_INSETS);
		myLoginOptions = new HBox(BOX_INSETS);
		myUserName = myGUIGenerator.createTextField("Enter Username", 0, 0, 500);
		myPassword = myGUIGenerator.createTextField("Enter Password", 0, 0, 500);
		myOptions.getChildren().add(myUserName);
		myOptions.getChildren().add(myPassword);
		myLoginOptions.setAlignment(Pos.CENTER);
		myOptions.getChildren().add(myLoginOptions);
		myOptions.setAlignment(Pos.CENTER);
		return myOptions;
	}
	
	public String getUserName() {
		return myUserName.getText();
	}
	
	public String getPassword() {
		return myPassword.getText();
	}

	@Override
	public void addNavigationButton(String aText, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aType) {
		// do nothing
	}

}
