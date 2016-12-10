package gameplayer.application_controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import game_data.Game;
import game_data.Sprite;
import game_data.states.State;
import game_data.states.Visible;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.back_end.keycode_handler.MovementHandler;
import gameplayer.back_end.user_information.HighscoreManager;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.popup.UserOptions;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	private UserOptions myUserOptions;
	private HighscoreManager myHighscoreManager;
	private Map<Sprite, ImageView> mySpriteMap;
	private int myLevel;

	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, int aLevel) {
		this(aStage, aFile, aAppController, aLevel, null);
	}
	
	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, int aLevel, UserOptions aOptions) {
		myLevel = aLevel;
		myStage = aStage;
		myGameFile = aFile;
		myApplicationController = aAppController;
		mySpriteMap = new HashMap<Sprite, ImageView>();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		mySceneBuilder = new SceneFactory();
		myUserOptions = aOptions;
		initializeKeySets(myUserOptions);
		initializeEngineComponents(aFile);
		initializeScene(myUserOptions);
		updateSprites();
	}

	private void initializeEngineComponents(File aFile) {
		myGameEngine = new GameEngine(aFile, myLevel);
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
	}

	private void initializeKeySets(UserOptions aOptions) {
		if (aOptions != null) {
			myKeyCodeHandler = new KeyCodeHandler(aOptions.getMyKeyInput());
		} else {
			myKeyCodeHandler = new KeyCodeHandler("default");
		}
		myMovementHandler = new MovementHandler();
	}

	public void displayGame() {
		initializeScene(myUserOptions);
		setMenu();
		updateSprites();
		initializeAnimation();
		resetStage(myGamePlayScene);
	}

	private void initializeScene(UserOptions aOptions) {
		if (aOptions != null) {
			myGamePlayScene = new GamePlayScene(myMovementHandler, myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight(), aOptions.getMyFontColor());
		} else {
			myGamePlayScene = new GamePlayScene(myMovementHandler, myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight(), "black");
		}
		myGamePlayScene.setKeyHandlers(e -> myKeyCodeHandler.handleKeyPress(e), e -> myKeyCodeHandler.handleKeyRelease(e));
	}

	private void initializeAnimation() {
		myAnimationLoop = new AnimationLoop();
		myAnimationLoop.init( elapsedTime -> {
			//This is what gets called every update cycle
			resetSprites(elapsedTime);
			updateScene();
		});
	}

	private void updateScene() {
		//the below line makes sure the keys released aren't stored in the set after they're released
		myKeyCodeHandler.clearReleased();
		myMovementHandler.setXMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getXLocation(), myStage.getWidth());
		myMovementHandler.setYMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getYLocation(), myStage.getHeight());
		if (myGameController.getMyLevel().lostLevel()) setResultSceneHandlers(myGamePlayScene.createResultScene(), "YouLost");
		if (myGameController.getMyLevel().wonLevel()) setResultSceneHandlers(myGamePlayScene.createResultScene(), "YouWon");
		myGamePlayScene.moveScreen(myMovementHandler);
		setHealthLabel();
	}

	private void resetSprites(double elapsedTime) {
		myGamePlayScene.clearSprites();
		myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeyCodeHandler.getKeysPressed(), myKeyCodeHandler.getKeysReleased(), mySpriteMap, 
				myStage.getHeight(), myStage.getWidth(), myGamePlayScene.getAnimationScreenXPosition(), myGamePlayScene.getAnimationScreenYPosition());
		updateSprites();
	}

	private void updateSprites() {
		for (Sprite sprite : myGameController.getMyLevel().getMySpriteList()) {
			boolean mapped = false;
			for (State state : sprite.getStates()) {
				if (state instanceof Visible && ((Visible) state).isVisibile()) {
					getUpdatedSpriteMap(sprite);
					mapped = true;
				}
			}
			if (!mapped) {
				getUpdatedSpriteMap(sprite);
			}
		}
	}

	private void getUpdatedSpriteMap(Sprite aSprite) {
		ImageView image;
		if (mySpriteMap.containsKey(aSprite)) {
			image = mySpriteMap.get(aSprite);
		} else {
			image = new ImageView(aSprite.getMyImagePath());
			setImageProperties(aSprite, image);
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
		String[] namesForGamePlay = {myButtonLabels.getString("Restart"), myButtonLabels.getString("Red"), myButtonLabels.getString("Save")};
		myGamePlayScene.addMenu(myButtonLabels.getString("GamePlay"), namesForGamePlay, e -> {
			handleRestart();
		}, e -> {
			myGamePlayScene.changeBackground(Color.RED);
		}, e -> {
			save(getGame(), "");
		});
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
		GamePlayController gameControl = new GamePlayController(myStage, myGameFile, myApplicationController, myLevel, myUserOptions);
		gameControl.displayGame();
	}

	public void setLevel(int aLevel) {
		//TODO: Update the game engine with the new level
		myLevel = aLevel;
	}

	public Game getGame() {
		return myGameController.getMyGame();
	}

	private void setResultSceneHandlers(Pane loseScene, String aProperty) {
		saveHighscore();
		loseScene.getChildren().add(getGUIGenerator().createLabel(myButtonLabels.getString(aProperty), 0, 0));
		loseScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString(aProperty), 0,0, e -> {
			myApplicationController.displayMainMenu();
		}, ButtonDisplay.TEXT));
		loseScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("PlayAgain"),0,0, e -> {
			handleRestart();
		}, ButtonDisplay.TEXT));
		loseScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("HighScores"), 0,0, e -> {
			myApplicationController.displayHighScoreScene();
		}, ButtonDisplay.TEXT));
	}
	
	private void saveHighscore() {
		//TODO: Finish saving high scores
//		myHighscoreManager.setHighscore(, myGameController.getScore(), myGameController.getMyGame());
		save(myHighscoreManager, "highscores");
	}
	
	public void setOptions(UserOptions aOptions) {
		myUserOptions = aOptions;
		//TODO: update the scene
	}
}