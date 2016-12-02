package gameplayer.front_end.application_scene;

import java.util.HashSet;
import java.util.Set;
import game_data.Sprite;
import gameplayer.front_end.heads_up_display.HeadsUpDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;


public class GamePlayScene extends AbstractPlayerScene implements IDisplay {

	private AnimationPane myAnimation;
	private StackPane myStack;
	private HeadsUpDisplay myHeadsUpDisplay;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private Set<KeyCode> myKeySet;
	public GamePlayScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		initializeScene(aWidth, aHeight);
	}
	
	@Override
	public Scene init() {
		return myScene;
	}
	
	public void setBackground(Background aBackground) {
		myStack.setBackground(aBackground);
	}
	
	public ImageView addSpriteToScene(Sprite aSprite){
		return myAnimation.addSpriteToScene(aSprite);
	}
	
	public void clear(){
		myAnimation.clear();
	}
	
	public void moveScreen(){
		myAnimation.moveScreen(myKeySet);
	}
	
	public void addMenu(ImageView aImage, String[] aText, EventHandler<ActionEvent>... aHandler) {
		myHeadsUpDisplay.addMenu(aImage, aText, aHandler);
	}
	
	public void addMenu(String aMessage, String[] aText, EventHandler<ActionEvent>... aHandler) {
		myHeadsUpDisplay.addMenu(aMessage, aText, aHandler);
	}
	
	private void initializeScene(double aWidth, double aHeight) {
		myStack = new StackPane();
		myScene = new Scene(myStack, aWidth, aHeight);
		myAnimation = new AnimationPane(myScene, aWidth, aHeight);
		myStack.getChildren().add(myAnimation.init());
		myHeadsUpDisplay = new HeadsUpDisplay(myScene, aWidth, aHeight);
		myStack.getChildren().add(myHeadsUpDisplay.init());
		myScene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
	}
	
	private void handleKeyPress(KeyCode aKey) {
		myKeysPressed.add(aKey);
		myKeySet.add(aKey);
	}
	
	private void handleKeyRelease(KeyCode aKey) {
		myKeysReleased.add(aKey);
		myKeySet.remove(aKey);
	}
	
	public Set<KeyCode> getKeysPressedSet(){
		return myKeysPressed;
	}
	
	public Set<KeyCode> getKeysReleasedSet(){
		return myKeysReleased;
	}

	public void clearSets() {
		// TODO Auto-generated method stub
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
	}
}
