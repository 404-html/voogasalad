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
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.application_scene.SceneIdentifier;
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
		mySceneBuilder = new SceneFactory();
		initializeKeySets(); 
		initializeEngineComponents(aFile);
		myGamePlayScene = new GamePlayScene(myKeyHandler, myGameController.getMyBackgroundImageFilePath(), aStage.getWidth(), aStage.getHeight());
		updateSprites();
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
		myKeyHandler.setXMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getXLocation(), myStage.getWidth());
		myKeyHandler.setYMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getYLocation(), myStage.getHeight());
		if (myGameController.getMyLevel().lostLevel()) createLosingScene();
		if (myGameController.getMyLevel().wonLevel()) createWinningScene();
		myGamePlayScene.moveScreen(myKeyHandler);
	}

	private void resetSprites(double elapsedTime) {
		myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeysPressed, myKeysReleased, mySpriteMap);
		myGamePlayScene.clearSprites();
		updateSprites();
	}
	
	private void updateSprites() {
		// A sprite has been removed
		if (mySpriteMap.keySet().size() > myGameController.getMySpriteList().size()) {
			Set<Sprite> s = new HashSet<Sprite>(myGameController.getMySpriteList());
			mySpriteMap.keySet().retainAll(s);
		}
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
	
	private void setMenu() {
		setMainMenu();
		setDropDownMenu();
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
			createLosingScene();
		}, e -> {
			createWinningScene();
		});
	}
	
	private void createLosingScene() {
		myAnimationLoop.stop();
		IDisplay ls = mySceneBuilder.create(SceneIdentifier.RESULT, myStage.getWidth(), myStage.getHeight());
		setLosingSceneHandlers((INavigationDisplay) ls);
		resetStage(ls);
	}
	
	private void createWinningScene() {
		myAnimationLoop.stop();
		IDisplay ls = mySceneBuilder.create(SceneIdentifier.RESULT, myStage.getWidth(), myStage.getHeight());
		setWinningSceneHandlers((INavigationDisplay) ls);
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
	
	private void setWinningSceneHandlers(INavigationDisplay winScene) {
		winScene.addNode(getGUIGenerator().createLabel(myButtonLabels.getString("YouWon"), 0, 0));
		setResultSceneHandlers(winScene);
	}
	
	private void setLosingSceneHandlers(INavigationDisplay loseScene) {
		loseScene.addNode(getGUIGenerator().createLabel(myButtonLabels.getString("YouLost"), 0, 0));
		setResultSceneHandlers(loseScene);
	}

	private void setResultSceneHandlers(INavigationDisplay loseScene) {
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