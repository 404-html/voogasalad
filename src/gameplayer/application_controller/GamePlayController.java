package gameplayer.application_controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import game_data.Game;
import game_data.Sprite;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.back_end.keycode_handler.MovementHandler;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.application_scene.SceneIdentifier;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.XMLTranslator;

public class GamePlayController extends AbstractController {
	
	private EnginePlayerController myGameController;
	private UpdateGame myGameUpdater;
	private GameEngine myGameEngine;
	private AnimationLoop myAnimationLoop;
	private MovementHandler myMovementHandler;
	private GamePlayScene myGamePlayScene;
	private KeyCodeHandler myKeyCodeHandler;
	private ApplicationController myApplicationController;
	private File myGameFile;
	private Map<Sprite, ImageView> mySpriteMap;
	
	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, String aKeyInput) {
		myStage = aStage;
		myGameFile = aFile;
		mySpriteMap = new HashMap<Sprite, ImageView>();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myApplicationController = aAppController;
		mySceneBuilder = new SceneFactory();
		initializeKeySets(aKeyInput);
		initializeEngineComponents(aFile);
		initializeScene();
		updateSprites();
	}

	private void initializeEngineComponents(File aFile) {
		myGameEngine = new GameEngine(aFile, 0);
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
	}

	private void initializeKeySets(String aKeyInput) {
		myKeyCodeHandler = new KeyCodeHandler(aKeyInput);
		myMovementHandler = new MovementHandler();
	}
	
	public void displayGame() {
		initializeScene();
		setMenu();
		updateSprites();
		initializeAnimation();
		resetStage(myGamePlayScene);
	}

	private void initializeScene() {
		myGamePlayScene = new GamePlayScene(myMovementHandler, myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight(), myApplicationController.getUserDefaults().getFontColor("black"));
		myGamePlayScene.setKeyHandlers(e -> myKeyCodeHandler.handleKeyPress(e), e -> myKeyCodeHandler.handleKeyRelease(e));
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			//This is what gets called every time cycle
			resetSprites(elapsedTime);
			updateScene();
		});
	}

	private void updateScene() {
		//the below line makes sure the keys released aren't stored in the set after they're released
		myKeyCodeHandler.clearReleased();
		myMovementHandler.setXMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getXLocation(), myStage.getWidth());
		myMovementHandler.setYMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getYLocation(), myStage.getHeight());
		if (myGameController.getMyLevel().lostLevel()) createResultScene(myButtonLabels.getString("YouLost"));
		if (myGameController.getMyLevel().wonLevel()) createResultScene(myButtonLabels.getString("YouWon"));
		myGamePlayScene.moveScreen(myMovementHandler);
		setHealthLabel();
	}

	private void resetSprites(double elapsedTime) {
		myGamePlayScene.clearSprites();
		myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeyCodeHandler.getKeysPressed(), myKeyCodeHandler.getKeysReleased(), mySpriteMap);
		updateSprites();
	}
	
	private void updateSprites() {
		for (Sprite sprite : myGameController.getMyLevel().getMySpriteList()) {
			getUpdatedSpriteMap(sprite);
		}
	}
	
	private void getUpdatedSpriteMap(Sprite aSprite) {
		ImageView image;
		if (mySpriteMap.containsKey(aSprite)) {
			image = mySpriteMap.get(aSprite);
			setImageProperties(aSprite, image);
		} else {
			image = new ImageView(aSprite.getMyImagePath());
			setImageProperties(aSprite, image);
			mySpriteMap.put(aSprite, image);
		}
		setImageProperties(aSprite, image);
		myGamePlayScene.addImageToView(image);
	}

	private void setImageProperties(Sprite aSprite, ImageView image) {
		image.setFitWidth(aSprite.getMyWidth());
		image.setFitHeight(aSprite.getMyHeight());
		image.setX(aSprite.getMyLocation().getXLocation());
		image.setY(aSprite.getMyLocation().getYLocation());
	}
	
	private void setMenu() {
		setMainMenu();
		setDropDownMenu();
		setHealthLabel();
	}

	@SuppressWarnings("unchecked")
	private void setDropDownMenu() {
		String[] namesForGamePlay = {myButtonLabels.getString("Restart"), myButtonLabels.getString("Red"), myButtonLabels.getString("Save"), "lose", "win"};
		myGamePlayScene.addMenu(myButtonLabels.getString("GamePlay"), namesForGamePlay, e -> {
			handleRestart();
		}, e -> {
			myGamePlayScene.changeBackground(Color.RED);
		}, e -> {
			save();
		}, e -> {
			createResultScene(myButtonLabels.getString("YouLost"));
		}, e -> {
			createResultScene(myButtonLabels.getString("YouWon"));
		});
	}
	
	private void createResultScene(String aMessage) {
		myAnimationLoop.stop();
		IDisplay ls = mySceneBuilder.create(SceneIdentifier.RESULT, myStage.getWidth(), myStage.getHeight());
		setResultSceneHandlers((INavigationDisplay) ls, aMessage);
		resetStage(ls);
	}

	@SuppressWarnings("unchecked")
	private void setMainMenu() {
		String[] names = {myButtonLabels.getString("MainMenu")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myGamePlayScene.addMenu(image, names, e -> {
			myAnimationLoop.stop();
			myApplicationController.displayMainMenu();
		});
	}
	
	private void setHealthLabel() {
		myGamePlayScene.addLabel("Health: " + myGameController.getMySpriteHealthList().get(0));
	}
	
//	private void setScoreLabel() {
//		myGamePlayScene.addLabel("Score: " + myGameController.getMyLevel().getMainPlayer().getScore());
//	}

	private void handleRestart() {
		myAnimationLoop.stop();
		GamePlayController gameControl = new GamePlayController(myStage, myGameFile, myApplicationController, myApplicationController.getUserDefaults().getKeyInputColor("default"));
		gameControl.displayGame();
	}
	
	private void save() {
		Game currentGame = myGameController.getMyGame();
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(currentGame, "XMLGameFiles/", "MarioOnScreenSaved");
	}
	
	private void setResultSceneHandlers(INavigationDisplay winScene, String aMessage) {
		winScene.addNode(getGUIGenerator().createLabel(aMessage, 0, 0));
		setResultSceneButtons(winScene);
	}

	private void setResultSceneButtons(INavigationDisplay loseScene) {
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
	
	public Game getGame() {
		return myGameController.getMyGame();
	}
}