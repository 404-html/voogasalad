package gameplayer.application_controller;

import java.io.File;
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
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
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
	private MovementHandler myMovementHandler;
	private GamePlayScene myGamePlayScene;
	private KeyCodeHandler myKeyCodeHandler;
	private ApplicationController myApplicationController;
	private File myGameFile;
	private SpriteDisplay mySpriteDisplay;
	private int myLevel;
	
	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, int aLevel, String aKeyInput) {
		myLevel = aLevel;
		myStage = aStage;
		myGameFile = aFile;
		myApplicationController = aAppController;
		mySpriteDisplay = new SpriteDisplay();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		mySceneBuilder = new SceneFactory();
		initializeKeySets(aKeyInput);
		initializeEngineComponents();
		initializeScene();
		updateSprites();
	}

	private void initializeEngineComponents() {
		myGameEngine = new GameEngine(myGameFile, myLevel);
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
		myKeyCodeHandler.addMainPlayer(mySpriteDisplay.get(myGameController.getMyLevel().getMainPlayer()));
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
			resetSprites(elapsedTime);
			updateScene();
		});
	}

	private void updateScene() {
		//the below line makes sure the keys released aren't stored in the set after they're released
		myKeyCodeHandler.clearReleased();
		myMovementHandler.setXMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getXLocation(), myStage.getWidth());
		myMovementHandler.setYMovement(myGameController.getMyLevel().getMainPlayer().getMyLocation().getYLocation(), myStage.getHeight());
		checkResult();
		myGamePlayScene.moveScreen(myMovementHandler);
		setHealthLabel();
	}
	
	private void checkResult() {
		if (myGameController.getMyLevel().lostLevel()) setResultScene(myButtonLabels.getString("YouLose"));
		if (myGameController.getMyLevel().wonLevel()) setResultScene(myButtonLabels.getString("YouWon"));
	}

	private void resetSprites(double elapsedTime) {
		myGamePlayScene.clearSprites();
		myGameUpdater.update(myGameController.getMyGame(), elapsedTime, myKeyCodeHandler.getKeysPressed(), myKeyCodeHandler.getKeysReleased(), mySpriteDisplay.getSpriteMap(), 
				myStage.getHeight(), myStage.getWidth(), myGamePlayScene.getAnimationScreenXPosition(), myGamePlayScene.getAnimationScreenYPosition());
		mySpriteDisplay.get(myGameController.getMyLevel().getMainPlayer());
		updateSprites();
	}
	
	private void updateSprites() {
		for (Sprite sprite : myGameController.getMyLevel().getMySpriteList()) {
			mySpriteDisplay.getUpdatedSpriteMap(sprite);
		}
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
			setResultScene(myButtonLabels.getString("YouLose")); 
		}, e -> {
			setResultScene(myButtonLabels.getString("YouWon"));
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


	private void handleRestart() {
		myAnimationLoop.stop();
		GamePlayController gameControl = new GamePlayController(myStage, myGameFile, myApplicationController, myLevel, myApplicationController.getUserDefaults().getKeyInputColor("default"));
		gameControl.displayGame();
	}
	
	private void save() {
		Game currentGame = myGameController.getMyGame();
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(currentGame, "XMLGameFiles/", "MarioOnScreenSaved");
	}
	
	public void setLevel(int aLevel) {
		myLevel = aLevel;
	}

	public Game getGame() {
		return myGameController.getMyGame();
	}
	
	private void setResultScene(String aLabel) {
		myAnimationLoop.stop();
		Pane winScene = myGamePlayScene.createResultScene();
		winScene.getChildren().add(getGUIGenerator().createLabel(aLabel, 0, 0));
		setResultSceneHandlers(winScene);
	}
	
	private void setResultSceneHandlers(Pane loseScene) {
		loseScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("MainMenu"), 0,0, e -> {
			myApplicationController.displayMainMenu();
		}, ButtonDisplay.TEXT));
		loseScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("PlayAgain"),0,0, e -> {
			handleRestart();
		}, ButtonDisplay.TEXT));
		loseScene.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("HighScores"), 0,0, e -> {
			myApplicationController.displayHighScoreScene();
		}, ButtonDisplay.TEXT));
	}
	
}