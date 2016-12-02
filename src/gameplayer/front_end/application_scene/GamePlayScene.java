package gameplayer.front_end.application_scene;

import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.heads_up_display.HeadsUpDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GamePlayScene extends AbstractPlayerScene implements IDisplay {

	private StackPane myStack;
	private AnimationPane myGamePlay;
	private HeadsUpDisplay myHeadsUpDisplay;
	private Background myBackgroundDisplay;

	public GamePlayScene(KeyCodeHandler aKeyHandler, String aBackgroundImageFilePath, double aWidth, double aHeight) {
		myStack = new StackPane();
		myScene = new Scene(myStack, aWidth, aHeight);
		myGamePlay = new AnimationPane(aKeyHandler, aWidth, aHeight);
		myHeadsUpDisplay = new HeadsUpDisplay(aWidth, aHeight);
		myBackgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aBackgroundImageFilePath, aWidth, aHeight);
		initializeScene();
	}
	
	@Override
	public Scene init(){
		return myScene;
	}

	private void initializeScene() {
		myStack.getChildren().add(myGamePlay.init());
		myStack.getChildren().add(myHeadsUpDisplay.init());
		myStack.setBackground(myBackgroundDisplay);
	}

	public void moveScreen() {
		myGamePlay.moveScreen();
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

	public void addMenu(String string, String[] names, EventHandler<ActionEvent>... eventHandler) {
		myHeadsUpDisplay.addMenu(string, names, eventHandler);
	}
	
	public void addMenu(ImageView aImage, String[] names, EventHandler<ActionEvent>... eventHandler) {
		myHeadsUpDisplay.addMenu(aImage, names, eventHandler);
	}

	public void setOnKeyPressed(KeyPressable a) {
		myScene.setOnKeyPressed(e -> a.handleKeyInput(e.getCode()));
	}

	public void setOnKeyReleased(KeyPressable a) {
		myScene.setOnKeyReleased(e -> a.handleKeyInput(e.getCode()));
	}

	public void setBackground(Background backgroundDisplay) {
		myStack.setBackground(backgroundDisplay);
	}
}
