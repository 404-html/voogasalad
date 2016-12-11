package gameplayer.application_controller;

import java.io.File;
import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import game_data.Game;
import game_data.Sprite;
import game_data.states.Score;
import game_data.states.State;
import game_data.states.Visible;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.back_end.user_information.HighscoreManager;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.gui_generator.media.MediaController;
import gameplayer.front_end.popup.UserOptions;
import gameplayer.back_end.keycode_handler.MovementHandlerFactory;
import gameplayer.back_end.keycode_handler.XYMovementHandler;
import gameplayer.front_end.sprite_display.SpriteDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.XMLTranslator;

public class GamePlayController extends AbstractController {

	private EnginePlayerController myGameController;
	private UpdateGame myGameUpdater;
	private GameEngine myGameEngine;
	private AnimationLoop myAnimationLoop;
	private GamePlayScene myGamePlayScene;
	private KeyCodeHandler myKeyCodeHandler;
	private ApplicationController myApplicationController;
	private File myGameFile;
	private UserOptions myUserOptions;
	private HighscoreManager myHighscoreManager;
	private SpriteDisplay mySpriteDisplay;
	private MediaController myMusic;
	private Score myScore;
	private PlayerInformationController myPlayerInformation;
	
	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, 
			PlayerInformationController aInfoController,int aLevel) {
		myStage = aStage;
		myGameFile = aFile;
		myApplicationController = aAppController;
		myPlayerInformation = aInfoController;
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		mySceneBuilder = new SceneFactory();
		mySpriteDisplay = new SpriteDisplay();
		myKeyCodeHandler = new KeyCodeHandler();
		initializeKeySets(myUserOptions);
		initializeEngineComponents(aLevel);
		initializeScene(myUserOptions);
		updateSprites();
	}
	
	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, 
			PlayerInformationController aPlayerController, int aLevel, UserOptions aOptions) {
		this(aStage, aFile, aAppController, aPlayerController, aLevel); 
		myUserOptions = aOptions;
		myKeyCodeHandler = new KeyCodeHandler(aOptions.getMyKeyInput());
	}

	private void initializeEngineComponents(int aLevel) {
		myGameEngine = new GameEngine(myGameFile, aLevel);
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame();
	}

	private void initializeKeySets(UserOptions aOptions) {
		if (aOptions != null) {
			myKeyCodeHandler = new KeyCodeHandler(aOptions.getMyKeyInput());
		} else {
			myKeyCodeHandler = new KeyCodeHandler("default");
		}
		//getGUIGenerator().createMediaPlayer("");
	}

	public void displayGame() {
		initializeScene(myUserOptions);
		setMenu();
		updateSprites();
		myKeyCodeHandler.addMainPlayer(mySpriteDisplay.get(myGameController.getMyLevel().getMainPlayer()));
		initializeAnimation();
		resetStage(myGamePlayScene);
		if (myGameController.getMyGame().getAudioFilePath() != null) myMusic = new MediaController(myGameController.getMyGame().getAudioFilePath());
	}

	private void initializeScene(UserOptions aOptions) {
		if (aOptions != null) {
			myGamePlayScene = new GamePlayScene(myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight(), aOptions.getMyFontColor());
		} else {
			myGamePlayScene = new GamePlayScene(myGameController.getMyBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight(), "black");
		}
		myGamePlayScene.setKeyHandlers(e -> myKeyCodeHandler.handleKeyPress(e), e -> myKeyCodeHandler.handleKeyRelease(e));
		
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
		myKeyCodeHandler.clearReleased();
		XYMovementHandler movementHandler = new MovementHandlerFactory().buildMovementHandler(myGameController.getMyLevel().getMainPlayer().getLocation().getXLocation(), 
				myStage.getWidth(), myGameController.getMyLevel().getMainPlayer().getLocation().getYLocation(), myStage.getHeight(), 3);
		checkResult();
		myGamePlayScene.moveScreen(movementHandler);
		setHealthLabel();
		setScoreLabel();
	}
	
	private void checkResult() {
		if (myGameController.getMyLevel().lostLevel()) setResultScene(myButtonLabels.getString("YouLost"));
		if (myGameController.getMyLevel().wonLevel()) setResultScene(myButtonLabels.getString("YouWon"));
	}

	private void resetSprites(double elapsedTime) {
		myGamePlayScene.clearSprites();
		myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeyCodeHandler.getKeysPressed(), myKeyCodeHandler.getKeysReleased(), mySpriteDisplay.getSpriteMap(), 
				myStage.getHeight(), myStage.getWidth(), myGamePlayScene.getAnimationScreenXPosition(), myGamePlayScene.getAnimationScreenYPosition());
		//mySpriteDisplay.get(myGameController.getMyLevel().getMainPlayer());
		//checkBackground();
		updateSprites();
	}
	
	private void checkBackground() {
		myGamePlayScene.setBackground(myGameController.getMyLevel().getBackgroundImageFilePath(), myStage.getWidth(), myStage.getHeight());
	}

	private void updateSprites() {
		for (Sprite sprite : myGameController.getMyLevel().getMySpriteList()) {
			boolean mapped = false;
			for (State state : sprite.getStates()) {
				if (state instanceof Visible && ((Visible) state).isVisibile()) {
					myGamePlayScene.addImageToView(mySpriteDisplay.getUpdatedSpriteMap(sprite));
					mapped = true;
				}
			}
			if (!mapped) {
				myGamePlayScene.addImageToView(mySpriteDisplay.getUpdatedSpriteMap(sprite));
			}
		}
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
			myMusic.stopMusic();
		}, e -> {
			myGamePlayScene.changeBackground(Color.RED);
		}, e -> {
			save();
		});
	}

	@SuppressWarnings("unchecked")
	private void setMainMenu() {
		String[] names = {myButtonLabels.getString("MainMenu")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myGamePlayScene.addMenu(image, names, e -> {
			myAnimationLoop.stop();
			myApplicationController.displayMainMenu(myStage.getWidth(), myStage.getHeight());
			myMusic.stopMusic();
		});
	}

	private void setHealthLabel() {
		if (myGameController.getMySpriteHealthList() != null && 
				myGameController.getMySpriteHealthList().size() >= 1) 
			myGamePlayScene.addLabel("Health: " + myGameController.getMySpriteHealthList().get(0));
	}

	private void setScoreLabel() {
		for (State s : myGameController.getMyLevel().getMainPlayer().getStates()) {
			if (s instanceof Score) {
				myScore = (Score) s; 
			}
		}
		if (myScore != null) myGamePlayScene.addLabel("Score: " + myScore.getMyScore());
	}

	private void handleRestart() {
		myAnimationLoop.stop();
		GamePlayController gameControl = new GamePlayController(myStage, myGameFile, 
				myApplicationController, myPlayerInformation, 0);
		gameControl.displayGame();
	}
	
	private void save() {
		Game currentGame = myGameController.getMyGame();
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(currentGame, "XMLGameFiles/", "MarioOnScreenSaved");
	}

	public Game getGame() {
		return myGameController.getMyGame();
	}
	
	private void setResultScene(String aLabel) {
		myAnimationLoop.stop();
		Pane winScene = myGamePlayScene.createResultScene();
		winScene.getChildren().add(getGUIGenerator().createLabel(aLabel, 0, 0));
		setResultSceneHandlers(winScene);
		myMusic.stopMusic();
	}
	
	private void setResultSceneHandlers(Pane resultScene) {
		saveHighscore();
		resultScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("MainMenu"), 0,0, e -> {
			myApplicationController.displayMainMenu(myStage.getWidth(), myStage.getHeight());
		}, ButtonDisplay.TEXT));
		resultScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("PlayAgain"),0,0, e -> {
			handleRestart();
		}, ButtonDisplay.TEXT));
		resultScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("HighScores"), 0,0, e -> {
			myApplicationController.displayHighScoreScene();
		}, ButtonDisplay.TEXT));
		resultScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Publish"), 0, 0, e -> {
			myApplicationController.publishToFacebook(MessageFormat.format(myButtonLabels.getString("MessageTitle"), 
					myGameController.getMyGame().getName()), 
					myButtonLabels.getString("PublishMessage"));
		}, ButtonDisplay.TEXT));
	}
	
	private void saveHighscore() {
		if (myScore != null) {
			myHighscoreManager.setHighscore(myPlayerInformation.getUser(), myScore.getMyScore(), myGameController.getMyGame());
			save(myHighscoreManager, "highscores");
		}
	}
	
	public void setOptions(UserOptions aOptions) {
		myUserOptions = aOptions;
		//TODO: update the scene
	}
}