package gameplayer.front_end.application_scene;

import java.io.File;

import gameplayer.back_end.keycode_handler.MovementHandler;
import gameplayer.back_end.keycode_handler.XYMovementHandler;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.heads_up_display.HeadsUpDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GamePlayScene extends AbstractPlayerScene {
	
	private static final String STYLESHEET = "data/gui/style.css";

	private StackPane myStack;
	private AnimationPane myGamePlay;
	private HeadsUpDisplay myHeadsUpDisplay;
	private Background myBackgroundDisplay;
	private ResultScene myResultScene;
	private String myBackgroundFilePath;
	
	public GamePlayScene(String aBackgroundImageFilePath, double aWidth, double aHeight, String aFontColor) {
		myStack = new StackPane();
		myScene = new Scene(myStack, aWidth, aHeight);
		File file = new File(STYLESHEET);
	    myScene.getStylesheets().add(file.toURI().toString());
		myGamePlay = new AnimationPane();
		myHeadsUpDisplay = new HeadsUpDisplay(aWidth, aHeight, aFontColor);
		myBackgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aBackgroundImageFilePath, aWidth, aHeight);
		myResultScene = new ResultScene();
		initializeScene();
	}
	
	public Pane createResultScene() {
		myStack.getChildren().remove(myHeadsUpDisplay.init());
		myGamePlay.setOpacity(0.5);
		myStack.getChildren().add(myResultScene.getPane());
		return myResultScene.getPane();
	}
	
	public double getAnimationScreenXPosition() {
		return myGamePlay.getAnimationScreenXPosition();
	}
	
	
	public double getAnimationScreenYPosition() {
		return myGamePlay.getAnimationScreenYPosition();
	}
	
	@Override
	public Scene init() {
		return myScene;
	}

	public void moveScreen(XYMovementHandler aHandler) {
		myGamePlay.moveScreen(aHandler);
	}

	public void clearSprites() {
		myGamePlay.clear();
	}

	public void addImageToView(ImageView aImage) {
		myGamePlay.addImageToView(aImage);
	}
	
	public void changeBackground(Color aColor) {
		myBackgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aColor, myStack.getWidth(), myStack.getHeight());
		myStack.setBackground(myBackgroundDisplay);
	}

	public void addMenu(String string, String[] names, @SuppressWarnings("unchecked") EventHandler<ActionEvent>... eventHandler) {
		myHeadsUpDisplay.addMenu(string, names, eventHandler);
	}
	
	public void addMenu(ImageView aImage, String[] names, @SuppressWarnings("unchecked") EventHandler<ActionEvent>... eventHandler) {
		myHeadsUpDisplay.addMenu(aImage, names, eventHandler);
	}
	
	public void addLabel(String aText){
		myHeadsUpDisplay.addLabel(aText);
	}

	public void setKeyHandlers(KeyPressable aPressHandler, KeyPressable aReleaseHandler) {
		myScene.setOnKeyPressed(e -> aPressHandler.handleKeyInput(e.getCode()));
		myScene.setOnKeyReleased(e -> aReleaseHandler.handleKeyInput(e.getCode()));
	}
	
	private void initializeScene() {
		myStack.getChildren().add(myGamePlay.init());
		myStack.getChildren().add(myHeadsUpDisplay.init());
		myStack.setBackground(myBackgroundDisplay);
	}

	@Override
	public void setBackground(String aFilePath, double aWidth, double aHeight) {
		myBackgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aFilePath, myStack.getWidth(), myStack.getHeight());
		myStack.setBackground(myBackgroundDisplay);
	}
	

}
