package gameplayer.application_controller;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Set;
import game_data.Game;
import game_data.Sprite;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.back_end.keycode_handler.MovementHandler;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.XMLTranslator;

public class GamePlayController extends AbstractController {
	
	private EnginePlayerController myGameController;
	private UpdateGame myGameUpdater;
	private GameEngine myGameEngine;
	private File myGameFile;
	private AnimationLoop myAnimationLoop;
	private MovementHandler myKeyHandler;
	private GamePlayScene myGamePlayScene;
	private Set<KeyCode> myKeySet;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private Map<Sprite, ImageView> mySpriteMap;
	private ApplicationController myApplicationController;
	
	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController) {
		myStage = aStage;
		myGameFile = aFile;
		mySpriteMap = new HashMap<Sprite, ImageView>();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myApplicationController = aAppController;
		initializeKeySets(); 
		initializeEngineComponents(aFile);
		myGamePlayScene = new GamePlayScene(myKeyHandler, myGameController.getMyBackgroundImageFilePath(), aStage.getWidth(), aStage.getHeight());
	}

	private void initializeEngineComponents(File aFile) {
		myGameEngine = new GameEngine(aFile, 0);
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
	}

	private void initializeKeySets() {
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed= new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myKeyHandler = new MovementHandler();
	}
	
	public void displayGame() {
		initializeScene();
		setMenu();
		updateSprites();
		initializeAnimation();
		resetStage(myGamePlayScene);
	}

	private void initializeScene() {
		myGamePlayScene = new GamePlayScene(myKeyHandler, myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight());
		myGamePlayScene.setKeyHandlers(e -> handleKeyPress(e), e -> handleKeyRelease(e));
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			resetSprites(elapsedTime);
			updateScene();
		});
	}

	private void updateScene() {
		//the below line makes sure the keys released aren't stored in the set after they're released
		clearKeys();
		myKeyHandler.setXMovement(myGameController.getMyLevel().getMainPlayer().getMyXVelocity());
		myKeyHandler.setYMovement(myGameController.getMyLevel().getMainPlayer().getMyYVelocity());
		myGamePlayScene.moveScreen(myKeyHandler);
	}

	private void resetSprites(double elapsedTime) {
		myGamePlayScene.clearSprites();
		myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeysPressed, myKeysReleased, mySpriteMap);
		updateSprites();
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
		setImageProperties(aSprite, image);
		myGamePlayScene.addImageToView(mySpriteMap.get(aSprite));
	}

	private void setImageProperties(Sprite aSprite, ImageView image) {
		image.setFitWidth(aSprite.getMyWidth());
		image.setFitHeight(aSprite.getMyHeight());
		image.setX(aSprite.getMyLocation().getXLocation());
		image.setY(aSprite.getMyLocation().getYLocation());
	}

	private void clearKeys() {
		myKeysReleased.clear();
		//myKeysPressed.clear();
	}
	
	@SuppressWarnings("unchecked")
	private void setMenu() {
		String[] names = {myButtonLabels.getString("MainMenu")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myGamePlayScene.addMenu(image, names, e -> {
			myAnimationLoop.stop();
			myApplicationController.displayMainMenu();
		});
		String[] namesForGamePlay = {myButtonLabels.getString("Restart"), myButtonLabels.getString("Red"), myButtonLabels.getString("Save")};
		myGamePlayScene.addMenu(myButtonLabels.getString("GamePlay"), namesForGamePlay, e -> {
			handleRestart();
		}, e -> {
			myGamePlayScene.changeBackground(Color.RED);
		}, e -> {
			save();
		});
	}

	private void handleRestart() {
		myAnimationLoop.stop();
		GamePlayController gameControl = new GamePlayController(myStage, myGameFile, myApplicationController);
		gameControl.displayGame();
	}
	
	private void save() {
		Game currentGame = myGameController.getMyGame();
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(currentGame, "XMLGameFiles/", "MarioOnScreenSaved");
	}
	
	private void handleKeyPress(KeyCode aKey) {
		myKeysPressed.add(aKey);
        myKeySet.add(aKey);
	}
	
	private void handleKeyRelease(KeyCode key) {
		myKeysReleased.add(key);
		myKeysPressed.remove(key);
		myKeySet.remove(key);
	}

	public Game getGame() {
		return myGameController.getMyGame();
	}
	
	private void setWinningSceneHandlers(INavigationDisplay winScene){
		winScene.addNode(getGUIGenerator().createLabel(myButtonLabels.getString("YouWon"), 0, 0));
		winScene.addButton(myButtonLabels.getString("MainMenu"), e -> {
			myApplicationController.displayMainMenu();
		}, ButtonDisplay.TEXT);
		winScene.addButton(myButtonLabels.getString("PlayAgain"), e -> {
			handleRestart();
		}, ButtonDisplay.TEXT);
		winScene.addButton(myButtonLabels.getString("HighScores"), e -> {
			myApplicationController.displayHighScoreScene();
		}, ButtonDisplay.TEXT);
	}
	
	private void setLosingSceneHandlers(INavigationDisplay loseScene){
		loseScene.addNode(getGUIGenerator().createLabel(myButtonLabels.getString("YouLost"), 0, 0));
		loseScene.addButton(myButtonLabels.getString("MainMenu"), e -> {
			myApplicationController.displayMainMenu();
		}, ButtonDisplay.TEXT);
		loseScene.addButton(myButtonLabels.getString("PlayAgain"), e -> {
			handleRestart();
		}, ButtonDisplay.TEXT);
		loseScene.addButton(myButtonLabels.getString("HighScores"), e -> {
			myApplicationController.displayHighScoreScene();
		}, ButtonDisplay.TEXT);
	}
}