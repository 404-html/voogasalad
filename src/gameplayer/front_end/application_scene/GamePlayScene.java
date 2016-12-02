package gameplayer.front_end.application_scene;

import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.heads_up_display.HeadsUpDisplay;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GamePlayScene {
	
	private StackPane myStack;
	private AnimationScene myGamePlay;
	private HeadsUpDisplay myHeadsUpDisplay;
	private Background myBackgroundDisplay;
	
	public GamePlayScene(KeyCodeHandler aKeyHandler, String aBackgroundImageFilePath, double aWidth, double aHeight) {
		myStack = new StackPane();
		myGamePlay = new AnimationScene(aKeyHandler, aWidth, aHeight);
		myHeadsUpDisplay = new HeadsUpDisplay(aWidth, aHeight);
		myBackgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aBackgroundImageFilePath, aWidth, aHeight);
		initializeScene();
	}
	
	public void initializeScene() {
		myStack.getChildren().add(myGamePlay.init());
		myStack.getChildren().add(myHeadsUpDisplay.init());
		myStack.setBackground(myBackgroundDisplay);
	}
	
	public void moveScreen() {
		myGamePlay.moveScreen();
	}

	public Parent getRoot() {
		return myStack;
	}
	
	public void clearSprites() {
		myGamePlay.clear();
	}
	
	public void addImageToView(ImageView aImage) {
		myGamePlay.addImageToView(aImage);
	}
	
	public HeadsUpDisplay getHeadsUpDisplay() {
		return myHeadsUpDisplay;
	}

	public void changeBackground(Color red) {
		myBackgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(red, myStack.getWidth(), myStack.getHeight());
		myStack.setBackground(myBackgroundDisplay);
	}

}
