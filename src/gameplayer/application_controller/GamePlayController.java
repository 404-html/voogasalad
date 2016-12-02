package gameplayer.application_controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import game_data.Game;
import game_data.Sprite;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import util.XMLTranslator;

public class GamePlayController {
	
	private Stage myStage;
	private GamePlayScene myScene;
	private EnginePlayerController myGameController;
	private UpdateGame myGameUpdater;
	private GameEngine myGameEngine;
	private File myGameFile;
	private AnimationLoop myAnimationLoop;
	private Map<Sprite, ImageView> mySprites;
	private GUIGenerator myGUIGenerator;
	private KeyCodeHandler myKeyHandler;
	
	public GamePlayController(Stage aStage, File aFile) {
		myStage = aStage;
		myGUIGenerator = new GUIGenerator();
		mySprites = new HashMap<Sprite, ImageView>();
		myGameFile = aFile;
		myGameEngine = new GameEngine(aFile, 0);
		mySprites = new HashMap<Sprite, ImageView>();
		myGameFile = aFile;
		myKeyHandler = new KeyCodeHandler();
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
		myScene = new GamePlayScene(myStage.getWidth(), myStage.getHeight());
	}

	private void initializeGameScene() {
		setBackground(myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight());
		updateSprites();
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			deleteSprites();
			myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myScene.getKeysPressedSet(), myScene.getKeysReleasedSet(), mySprites);
			updateSprites();
			//the below line makes sure the keys released aren't stored in the set after they're released
			myScene.clearSets();
			myKeyHandler.setMovement(myGameController.getMyLevel().getMainPlayer().getMyXVelocity());
			myScene.moveScreen();
		});
	}
	
	private void setBackground(String aBackgroundImageFilePath, double aWidth, double aHeight) {
		Background backgroundDisplay = new BackgroundDisplayFactory().buildBackgroundDisplay(aBackgroundImageFilePath, aWidth, aHeight);
		myScene.setBackground(backgroundDisplay);
	}

	private void deleteSprites() {
		myScene.clear();
	}
	
	private void updateSprites() {
		for(Sprite sprite : myGameController.getMySpriteList()) {
			addSpriteToScene(sprite);
		}
	}
	
	private void addSpriteToScene(Sprite aSprite) {
		mySprites.put(aSprite, myScene.addSpriteToScene(aSprite));
	}
	
	@SuppressWarnings("unchecked")
	private void setMenu() {
		String[] names = {"Main Menu"};
		ImageView image = myGUIGenerator.createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myScene.addMenu(image, names, e -> {
			ApplicationController appControl = new ApplicationController(myStage);
			appControl.displayMainMenu();
		});
		String[] namesForGamePlay = {"Restart", "Save"};
		myScene.addMenu("GAME PLAY", namesForGamePlay, e -> {
			myAnimationLoop.stop();
			GamePlayController newGame = new GamePlayController(myStage, myGameFile);
			newGame.displayGame();
		}, e -> {
			save();
		});
	}
	
	private void resetStage() {
		myStage.close();
		myStage.setScene(myScene.init());
		myStage.show();
	}
	
	private void save() {
		Game currentGame = myGameController.getMyGame();
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(currentGame, "XMLGameFiles/", "MarioOnScreenSaved");
	}
}