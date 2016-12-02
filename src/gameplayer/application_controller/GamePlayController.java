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
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.gui_generator.GUIGenerator;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GamePlayController {
	
	private Stage myStage;
	private Scene myScene;
	private EnginePlayerController myGameController;
	private UpdateGame myGameUpdater;
	private GameEngine myGameEngine;
	private File myGameFile;
	private AnimationLoop myAnimationLoop;
	private GUIGenerator myGUIGenerator;
	private Set<KeyCode> myKeySet;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private KeyCodeHandler myKeyHandler;
	private GamePlayScene myGamePlayScene;
	private Map<Sprite, ImageView> mySpriteMap;
	
	public GamePlayController(Stage aStage, File aFile) {
		myStage = aStage;
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed= new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myGUIGenerator = new GUIGenerator();
		mySpriteMap = new HashMap<Sprite, ImageView>(); 
		myGameEngine = new GameEngine(aFile, 0);
		myGameFile = aFile;
		myKeyHandler = new KeyCodeHandler();
		myKeysReleased = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
		myGamePlayScene = new GamePlayScene(myKeyHandler, myGameController.getMyBackgroundImageFilePath(), aStage.getWidth(), aStage.getHeight());
	}
	
	public void displayGame() {
		initializeScene();
		updateSprites();
		initializeAnimation();
		setMenu();
		resetStage();
	}

	private void initializeScene() {
		myScene = new Scene(myGamePlayScene.getRoot(), myStage.getWidth(), myStage.getHeight());
		myScene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			myGamePlayScene.clearSprites();
			myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeysPressed, myKeysReleased, mySpriteMap);
			updateSprites();
			//the below line makes sure the keys released aren't stored in the set after they're released
			clearKeys();
			myKeyHandler.setMovement(myGameController.getMyLevel().getMainPlayer().getMyXVelocity());
			myGamePlayScene.moveScreen();
		});
	}

	private void clearKeys() {
		myKeysReleased.clear();
		myKeysPressed.clear();
	}
	
	private void updateSprites() {
		for (Sprite sprite : myGameController.getMySpriteList()) {
			getUpdatedSpriteMap(sprite);
		}
	}
	
	private void getUpdatedSpriteMap(Sprite aSprite) {
		ImageView image;
		if (mySpriteMap.containsKey(aSprite)) {
			image = mySpriteMap.get(aSprite);
		} else {
			image = new ImageView(aSprite.getMyImagePath());
			mySpriteMap.put(aSprite, image);
		}
		image.setFitWidth(aSprite.getMyWidth());
		image.setFitHeight(aSprite.getMyHeight());
		image.setX(aSprite.getMyLocation().getXLocation());
		image.setY(aSprite.getMyLocation().getYLocation());
		myGamePlayScene.addImageToView(mySpriteMap.get(aSprite));
	}
	
	@SuppressWarnings("unchecked")
	private void setMenu() {
		String[] names = {"Main Menu"};
		ImageView image = myGUIGenerator.createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myGamePlayScene.getHeadsUpDisplay().addMenu(image, names, e -> {
			myAnimationLoop.stop();
			ApplicationController appControl = new ApplicationController(myStage);
			appControl.displayMainMenu();
		});
		String[] namesForGamePlay = {"Restart", "Change to Red"};
		myGamePlayScene.getHeadsUpDisplay().addMenu("GAME PLAY", namesForGamePlay, e -> {
			myAnimationLoop.stop();
			GamePlayController gameControl = new GamePlayController(myStage, myGameFile);
			gameControl.displayGame();
		}, e -> {
			myGamePlayScene.changeBackground(Color.RED);
		});
	}
	
	private void resetStage() {
		myStage.close();
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private void handleKeyPress(KeyCode aKey) {
		if(!myKeySet.contains(aKey)){
			myKeysPressed.add(aKey);
		}
        myKeySet.add(aKey);
	}
	
	private void handleKeyRelease(KeyCode key) {
		myKeysReleased.add(key);
		myKeySet.remove(key);
	}
	
	
}