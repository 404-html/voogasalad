package gameplayer.application_controller;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import game_data.Sprite;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.front_end.application_scene.AnimationScene;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import gameplayer.front_end.heads_up_display.HeadsUpDisplay;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
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
	private GUIGenerator myGUIGenerator;
	
	public GamePlayController(Stage aStage, File aFile) {
		myStage = aStage;
		myKeySet = new HashSet<KeyCode>();
		myStack = new StackPane();
		myGUIGenerator = new GUIGenerator();
		mySprites = new HashMap<Sprite, ImageView>();
		initializeEngine();
		myGameEngine = new GameEngine(aFile, 0);
		initializeAnimation();
		initializeScene();
	}
	
	public void displayGame() {
		initializeEngine();
		initializeScene();
		initializeGameScene();
		initializeAnimation();
		setMenu();
		resetStage();
	}

	private void initializeEngine() {
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
	}

	private void initializeScene() {
		myScene = new Scene(myStack, myStage.getWidth(), myStage.getHeight());
		myScene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
	}

	private void initializeGameScene() {
		myGamePlay = new AnimationScene(myScene, myStage.getWidth(), myStage.getHeight());
		//System.out.println(myGamePlay);
		myStack.getChildren().add(myGamePlay.init());
		myHeadsUpDisplay = new HeadsUpDisplay(myScene, myStage.getWidth(), myStage.getHeight());
		myStack.getChildren().add(myHeadsUpDisplay.init());
		setBackground(myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight());
		updateSprites();
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			deleteSprites();
			myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeySet, mySprites);
			myGamePlay.moveScreen(myKeySet);
			updateSprites();
		});
	}
	
	private void setBackground(String aBackgroundImageFilePath, double aWidth, double aHeight) {
		Background backgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aBackgroundImageFilePath, aWidth, aHeight);
		myStack.setBackground(backgroundDisplay);
	}

	private void deleteSprites() {
		myGamePlay.clear();
	}
	
	private void updateSprites() {
		for(Sprite sprite : myGameController.getMySpriteList()) {
			addSpriteToScene(sprite);
		}
	}
	
	private void addSpriteToScene(Sprite aSprite) {
		mySprites.put(aSprite, myGamePlay.addSpriteToScene(aSprite));
	}
	
	@SuppressWarnings("unchecked")
	private void setMenu() {
		String[] names = {"Main Menu"};
		ImageView image = myGUIGenerator.createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myHeadsUpDisplay.addMenu(image, names, e -> {
			ApplicationController appControl = new ApplicationController(myStage);
			appControl.displayMainMenu();
		});
		String[] namesForGamePlay = {"Restart", "Change to Red"};
		myHeadsUpDisplay.addMenu("GAME PLAY", namesForGamePlay, e -> {
			displayGame();
		}, e -> {
			myGamePlay.makeRed();
		});
	}
	
	private void resetStage() {
		myStage.close();
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private void handleKeyPress(KeyCode aKey) {
        myKeySet.add(aKey);
	}
	
	private void handleKeyRelease(KeyCode key) {
		myKeySet.remove(key);
	}
}