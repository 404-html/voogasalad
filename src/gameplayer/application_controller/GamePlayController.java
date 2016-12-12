package gameplayer.application_controller;
import java.io.File;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import game_data.Game;
import game_data.Sprite;
import game_data.states.Health;
import game_data.states.Score;
import game_data.states.State;
import game_data.states.Visible;
import game_engine.EnginePlayerController;
import game_engine.GameEngine;
import game_engine.UpdateGame;
import gameplayer.animation_loop.AnimationLoop;
import gameplayer.back_end.exceptions.GameNotFunctionalException;
import gameplayer.back_end.keycode_handler.KeyCodeHandler;
import gameplayer.back_end.user_information.HighscoreManager;
import gameplayer.back_end.user_information.LevelManager;
import gameplayer.front_end.application_scene.GamePlayScene;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.gui_generator.media.MediaController;
import gameplayer.front_end.popup.UserOptions;
import gameplayer.back_end.keycode_handler.MovementHandlerFactory;
import gameplayer.back_end.keycode_handler.XYMovementHandler;
import gameplayer.front_end.sprite_display.SpriteDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	private SpriteDisplay mySpriteDisplay;
	private MediaController myMusic;
	private Score myScore;
	private Health myHealth;

	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, PlayerInformationController aInfoController) throws Exception {
		super(aStage);
		myGameFile = aFile;
		myApplicationController = aAppController;
		myKeyCodeHandler = new KeyCodeHandler();
		setPlayerInformationController(aInfoController);
		initializeKeySets(myUserOptions);
		initializeEngineComponents(0);
	}

	public GamePlayController(Stage aStage, File aFile, ApplicationController aAppController, PlayerInformationController aPlayerController, UserOptions aOptions) throws Exception {
		this(aStage, aFile, aAppController, aPlayerController); 
		myUserOptions = aOptions;
		myKeyCodeHandler = new KeyCodeHandler(aOptions.getMyKeyInput());
	}

	String initializeEngineComponents(int aLevel) throws Exception {
		myGameEngine = new GameEngine(myGameFile, aLevel);
		myGameController = myGameEngine.getMyEnginePlayerController();
		myGameUpdater = new UpdateGame(myGameController.getMyGame());
		return myGameController.getMyGame().getName();
	}

	private void initializeKeySets(UserOptions aOptions) {
		if (aOptions != null) {
			myKeyCodeHandler = new KeyCodeHandler(aOptions.getMyKeyInput());
		} else {
			myKeyCodeHandler = new KeyCodeHandler("default");
		}
	}
	/**
	 * Displays the currently set up game
	 */
	public void displayGame() throws Exception {
		initializeScene(myUserOptions);
		setMenu();
		try {
			updateSprites();
		} catch (Exception e) {
			throw new GameNotFunctionalException();
		}
		myKeyCodeHandler.addMainPlayer(mySpriteDisplay.getMainPlayer());
		initializeAnimation();
		resetStage(myGamePlayScene);
		if (myGameController.getMyGame().getAudioFilePath() != null) myMusic = new MediaController(myGameController.getMyGame().getAudioFilePath());
	}

	private void initializeScene(UserOptions aOptions) {
		mySpriteDisplay = new SpriteDisplay();
		if (aOptions != null) {
			myGamePlayScene = new GamePlayScene(myGameController.getMyBackgroundImageFilePath(), getStage().getWidth(), getStage().getHeight(), aOptions.getMyFontColor());
		} else {
			myGamePlayScene = new GamePlayScene(myGameController.getMyBackgroundImageFilePath(), getStage().getWidth(), getStage().getHeight(), "#fdbe3b");
		}
		myGamePlayScene.setKeyHandlers(e -> myKeyCodeHandler.handleKeyPress(e, myGameController.getMyGame().getCurrentLevel().getMainPlayer().getControllable().getMyKeyPressedMap()), e -> myKeyCodeHandler.handleKeyRelease(e));
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
		XYMovementHandler movementHandler = new MovementHandlerFactory().buildMovementHandler(myGameController.getMyGame().getCurrentLevel().getMainPlayer().getLocation().getXLocation(), 
				getStage().getWidth(), myGameController.getMyGame().getCurrentLevel().getMainPlayer().getLocation().getYLocation(), getStage().getHeight(), 3);
		checkResult();
		myGamePlayScene.moveScreen(movementHandler);
		setLevelLabel();
		setHealthLabel();
		setScoreLabel();
	}

	private void checkResult() {
		if (myGameController.getMyGame().hasLost()) setResultScene(getButtonLabels().getString("YouLost"));
		if (myGameController.getMyGame().hasWon()) setResultScene(getButtonLabels().getString("YouWon"));
	}

	private void resetSprites(double elapsedTime) {
		myGamePlayScene.clearSprites();
		myGameUpdater.update(elapsedTime, myKeyCodeHandler.getKeysPressed(), myKeyCodeHandler.getKeysReleased(), mySpriteDisplay.getSpriteMap(), 
				getStage().getHeight(), getStage().getWidth(), myGamePlayScene.getAnimationScreenXPosition(), myGamePlayScene.getAnimationScreenYPosition());
		updateSprites();
	}

	private void updateSprites() {
		for (Sprite sprite : myGameController.getMyGame().getCurrentLevel().getMySpriteList()) {
			boolean mapped = false;
			for (State state : sprite.getStates()) {
				if (state instanceof Visible) {
					myGamePlayScene.addImageToView(mySpriteDisplay.getUpdatedSpriteMap(sprite), ((Visible) state).isVisible());
					mapped = true;
				}
			}
			if(!mapped){
				myGamePlayScene.addImageToView(mySpriteDisplay.getUpdatedSpriteMap(sprite), true);
			}
		}
	}

	private void setMenu() {
		setMainMenu();
		setDropDownMenu();
	}

	@SuppressWarnings("unchecked")
	private void setDropDownMenu() {
		String[] namesForGamePlay = {getButtonLabels().getString("Restart"), getButtonLabels().getString("Save"), "highscore"};
		myGamePlayScene.addMenu(getButtonLabels().getString("GamePlay"), namesForGamePlay, e -> {
			try {
				handleRestart();
			} catch (Exception e1) {
				showError(e1);
			}
		}, e -> {
			save();
		}, e -> {
			stopLoops();
			myApplicationController.displayHighScoreScene(myGameController.getMyGame().getName());
		});
	}

	@SuppressWarnings("unchecked")
	private void setMainMenu() {
		String[] names = {getButtonLabels().getString("MainMenu")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		myGamePlayScene.addMenu(image, names, e -> {
			stopLoops();
			myApplicationController.displayMainMenu(getStage().getWidth(), getStage().getHeight());
		});
	}

	private void setHealthLabel() {
		determineHealth();
		if (myHealth != null) { myGamePlayScene.addNode(getGUIGenerator().createLabel("Health: " + myHealth.getHealth(), 0, 0), 1);}
	}

	private void determineHealth() {
		for (State s : myGameController.getMyGame().getCurrentLevel().getMainPlayer().getStates()) {
			if (s instanceof Health) {
				myHealth = (Health) s; 
			}
		}
	}

	private void setScoreLabel() {
		determineScore();
		if (myScore != null) {
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			Double d = Double.valueOf(twoDForm.format(myScore.getMyScore()));
			myGamePlayScene.addNode(getGUIGenerator().createLabel("Score: " + d.doubleValue(), 0, 0), 2);
		}
	}

	private void determineScore() {
		for (State s : myGameController.getMyGame().getCurrentLevel().getMainPlayer().getStates()) {
			if (s instanceof Score) {
				myScore = (Score) s; 
			}
		}
	}

	private void setLevelLabel() {
		myGamePlayScene.addNode(getGUIGenerator().createLabel("Level: " + myGameController.getMyGame().getLevelNumber() + 1, 0, 0), 0);
	}

	private void handleRestart() throws Exception {
		stopLoops();
		if (myUserOptions != null) {
			GamePlayController gameControl = new GamePlayController(getStage(), myGameFile, 
					myApplicationController, getPlayerInformationController(), myUserOptions);
			gameControl.displayGame();
		} else {
			GamePlayController gameControl = new GamePlayController(getStage(), myGameFile, 
					myApplicationController, getPlayerInformationController());
			gameControl.displayGame();
		}
	}

	private void stopLoops() {
		myAnimationLoop.stop();
		myMusic.stopMusic();
	}

	private void save() {
		saveGame();
		saveHighscore();
	}

	private void saveGame() {
		LevelManager lm = new LevelManager(myGameController.getMyGame().getLevelNumber());
		getXMLHandler().save(lm, myGameController.getMyGame().getName() + "levels");
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
		resultScene.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("MainMenu"), 0,0, e -> {
			myApplicationController.displayMainMenu(getStage().getWidth(), getStage().getHeight());
		}, ButtonDisplay.TEXT));
		resultScene.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("PlayAgain"),0,0, e -> {
			try {
				handleRestart();
			} catch (Exception e1) {
				showError(e1);
			}
		}, ButtonDisplay.TEXT));
		resultScene.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("HighScores"), 0,0, e -> {
			myApplicationController.displayHighScoreScene(myGameController.getMyGame().getName());
		}, ButtonDisplay.TEXT));
		resultScene.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("Publish"), 0, 0, e -> {
			myApplicationController.publishToFacebook(MessageFormat.format(getButtonLabels().getString("MessageTitle"), 
					myGameController.getMyGame().getName()), 
					getButtonLabels().getString("PublishMessage"));
			MessageFormat.format(getButtonLabels().getString("PublishMessage"), 
					myGameController.getMyGame().getName());
		}, ButtonDisplay.TEXT));
	}

	private void saveHighscore() {
		if (myScore != null) {
			HighscoreManager hm = loadHighscores();
			System.out.println(getPlayerInformationController().getUser());
			hm.setHighscore(getPlayerInformationController().getUser(), myScore.getMyScore(), myGameController.getMyGame());
			getXMLHandler().save(hm, "highscores");
		}
	}
	/**
	 * @param aOptions is the key input and HUD font color for the user
	 */
	public void setOptions(UserOptions aOptions) {
		myUserOptions = aOptions;
		myKeyCodeHandler = new KeyCodeHandler(aOptions.getMyKeyInput());
		myGamePlayScene = new GamePlayScene(myGameController.getMyBackgroundImageFilePath(), getStage().getWidth(), getStage().getHeight(), aOptions.getMyFontColor());
	}
	/**
	 * @param aLevel is the level that the user chose from the options
	 * @throws Exception 
	 */
	public void setLevel(int aLevel) throws Exception {
		initializeEngineComponents(aLevel - 1);
	}

	public Game getGame() {
		return myGameController.getMyGame();
	}
}