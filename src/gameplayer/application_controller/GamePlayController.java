package gameplayer.application_controller;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import game_data.Location;
import game_data.Sprite;
import game_data.sprites.Player;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.front_end.application_scene.AnimationScene;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.heads_up_display.HeadsUpDisplay;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GamePlayController {
	
	private Stage myStage;
	private AnimationScene myGamePlay;
	private HeadsUpDisplay myHeadsUpDisplay;
	private StackPane myStack;
	private Scene myScene;
	private EnginePlayerController myGameController;
	private UpdateGame myGameUpdater;
	private GameEngine myGameEngine;
	private AnimationLoop myAnimationLoop;
	private Map<Sprite, ImageView> mySprites;
	private Set<KeyCode> myKeySet;
	private BackgroundDisplayFactory myBackground; 
	
	public GamePlayController(Stage aStage, File aFile) {
		myStage = aStage;
		myKeySet = new HashSet<KeyCode>();
		myStack = new StackPane();
		mySprites = new HashMap<Sprite, ImageView>();
		myBackground = new BackgroundDisplayFactory();
		initializeEngine(aFile, 0);
		initializeAnimation();
		initializeScene();
	}
	
	public void displayGame() {
		initializeGameScene();
		setButtonHandlers();
		resetStage();
	}

	private void initializeEngine(File aFile, int level) {
		myGameEngine = new GameEngine(aFile, level);
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
	}

	private void initializeScene() {
		myScene = new Scene(myStack, myStage.getWidth(), myStage.getHeight());
		myScene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			deleteSprites();
			myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeySet, mySprites);;
			updateSprites();
		});
	}

	private void initializeGameScene() {
		myGamePlay = new AnimationScene(myScene, myStage.getWidth(), myStage.getHeight());
		myStack.getChildren().add(myGamePlay.init());
		myHeadsUpDisplay = new HeadsUpDisplay(myScene, myStage.getWidth(), myStage.getHeight());
		myStack.getChildren().add(myHeadsUpDisplay.init());
		setBackground(myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight());
		updateSprites();
	}
	
	private void setBackground(String aBackgroundImageFilePath, double aWidth, double aHeight) {
		//System.out.println(aBackgroundImageFilePath);
		myGamePlay.setBackground(myBackground.buildBackgroundDisplay(aBackgroundImageFilePath, aWidth, aHeight));
		//myGamePlay.setBackground(aBackgroundImageFilePath, (int) aWidth, (int) aHeight);
	}

	private void deleteSprites() {
		//mySprites = new HashMap<Sprite, ImageView>();
		myGamePlay.clear();
	}
	
	private void updateSprites() {
		for(Sprite sprite : myGameController.getMySpriteList()) {
			addSpriteToScene(sprite);
		}
		//addSpriteToScene(new Player(new Location(227, 369, 0), 50, 50, "poop", "author/images/MarioSMBW.png"));
	}
	

	
	private void addSpriteToScene(Sprite aSprite) {
		mySprites.put(aSprite, myGamePlay.addSpriteToScene(aSprite));
	}
	
	private void setButtonHandlers() {
		myHeadsUpDisplay.addButton("Main Menu", e -> {
			ApplicationController appControl = new ApplicationController(myStage);
			appControl.displayMainMenu();
		}, ButtonDisplay.TEXT);
		myHeadsUpDisplay.addButton("Restart", e -> {
			displayGame();
		}, ButtonDisplay.TEXT);
		myHeadsUpDisplay.addButton("Change to Red", e -> {
			myGamePlay.makeRed();
		}, ButtonDisplay.TEXT);
	}
	
	private void resetStage() {
		myStage.close();
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private void handleKeyPress(KeyCode aKey) {
        myKeySet.add(aKey);
        System.out.println("new");
        for (KeyCode key : myKeySet) {
        	System.out.println(key);
        }
	}
	
	private void handleKeyRelease(KeyCode key) {
		//System.out.println(myKeySet);
		myKeySet.remove(key);
	}
}