package gameplayer.application_controller;

import java.io.File;
import author.view.pages.level_editor.windows.splash_screen.AuthoringSplashScreenFactory;
import author.view.pages.level_editor.windows.splash_screen.IAuthoringSplashScreen;
import game_data.Game;
import gameplayer.back_end.resources.FrontEndResources;
import gameplayer.back_end.stored_games.StoredGames;
import gameplayer.back_end.user_information.LevelManager;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import gameplayer.front_end.application_scene.SceneIdentifier;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.popup.PopUpFactory;
import gameplayer.front_end.popup.UserOptions;
import gameplayer.front_end.popup.ErrorAlert;
import gameplayer.front_end.popup.LevelSelectionPopUp;
import gameplayer.front_end.popup.PlayerOptionsPopUp;
import javafx.stage.Stage;

/**
 * Where the player part can interact with the game engine and get the appropriate data to be displayed
 * 
 * @author tedmarchildon, hannah
 *
 */

public class ApplicationController extends AbstractController {

	private StoredGames myStoredGames;
	private GamePlayController myGamePlay;
	private IDisplay myCurrentDisplay;

	public ApplicationController (Stage aStage) {
		super(aStage);
		setPlayerInformationController(new PlayerInformationController());
		myStoredGames = new StoredGames();
	}

	public void displayMainMenu(double aWidth, double aHeight) {
		myCurrentDisplay = getSceneFactory().create(SceneIdentifier.MAINMENU, aWidth, aHeight);
		resetStage(myCurrentDisplay);
		setMainMenuButtonHandlers((MainMenuScene) myCurrentDisplay);
	}

	private void setMainMenuButtonHandlers(INavigationDisplay mainMenu) {
		mainMenu.addButton(getButtonLabels().getString("Play"), e -> {
			displayGameChoice();
		}, ButtonDisplay.TEXT);
		mainMenu.addButton(getButtonLabels().getString("Author"), e -> {
			displayAuthoring();
		}, ButtonDisplay.TEXT);
		mainMenu.addButton(getButtonLabels().getString("Login"), e -> {
			try { 
				getPlayerInformationController().facebookLogin();
			} catch (Exception x) {
				showError(x);
			}
		}, ButtonDisplay.FACEBOOK);
	}

	private void showError(Exception x) {
		ErrorAlert ea = new ErrorAlert();
		ea.show(x);
	}

	private void displayAuthoring() {
		IAuthoringSplashScreen aSplashScreen = (new AuthoringSplashScreenFactory()).create();
		aSplashScreen.initializeWindow();
	}

	@SuppressWarnings("unchecked")
	private void createNavigationButtons(INavigationDisplay aMenu) {
		String[] names = {getButtonLabels().getString("MainMenu"), getButtonLabels().getString("Profile"), getButtonLabels().getString("HawaiianShirt")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		aMenu.addNavigationMenu(image, names, e -> {
			displayMainMenu(getStage().getWidth(), getStage().getHeight());
		}, e -> {
			displayUserScene();
		}, e-> {
			myCurrentDisplay.setBackground(getButtonLabels().getString("Shirt" + (int) Math.floor(Math.random() * 7)), getStage().getWidth(), getStage().getHeight());
		});
	}

	/**
	 * 
	 * @param aGamename is used to determine which set of highscores to get
	 */
	public void displayHighScoreScene(String aGamename) {
		IDisplay highScore = getSceneFactory().create(SceneIdentifier.HIGHSCORE, getStage().getWidth(), getStage().getHeight(), aGamename);
		createNavigationButtons((INavigationDisplay) highScore);
		resetStage(highScore);
		//setHighScoreHandlers((INavigationDisplay) highScore);
	}

	private void displayUserScene() {
		myCurrentDisplay = getSceneFactory().create(getPlayerInformationController().getUser(), getPlayerInformationController().getPictureUrl(), getStage().getWidth(), getStage().getHeight(), loadHighscores());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
	}

	private void displayGameChoice() {
		myCurrentDisplay = getSceneFactory().create(SceneIdentifier.GAMECHOICE, getStage().getWidth(), getStage().getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setGameChoiceButtonHandlers((INavigationDisplay) myCurrentDisplay, true, getButtonLabels().getString("Choose"));
	}

	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice, boolean showSecondGameChoice, String aLabel) {
		gameChoice.addNode(getGUIGenerator().createComboBox(aLabel, myStoredGames.getGames(), 
				myStoredGames.getIcons(), myStoredGames.getDescriptions(), (aChoice) -> {
					resetGame(myStoredGames.getGameFilePath(aChoice));
					if (showSecondGameChoice) setGameChoiceSecondRoundButtonHandlers(gameChoice, aChoice);
					try {
						getOptions();
						getLevel();
					} catch (Exception x) {
						//do nothing
					}
				}));
		gameChoice.addButton(getButtonLabels().getString("Load"), e -> {
			File chosenGame = new FileChoiceController().show(getStage());
			if (chosenGame != null) resetGame(chosenGame);
			if (chosenGame != null && showSecondGameChoice) setGameChoiceSecondRoundButtonHandlers(gameChoice, getButtonLabels().getString("Choose"));
			try {
				getOptions();
				getLevel();
			} catch (Exception x) {
				//do nothing
			}
		}, ButtonDisplay.TEXT); 
	}

	private void getOptions() {
		UserOptions uo = (UserOptions) getXMLHandler().load(myGamePlay.getGame().getName() + getPlayerInformationController().getUser() + "options");
		myGamePlay.setOptions(uo);
	}

	private void getLevel() {
		LevelManager lm = (LevelManager) getXMLHandler().load(myGamePlay.getGame().getName() + "level");
		myGamePlay.setLevel(lm.getLevel());
	}

	private void setGameChoiceSecondRoundButtonHandlers(INavigationDisplay gameChoice, String aChoice) {
		gameChoice.clear();
		setGameChoiceButtonHandlers(gameChoice, false, aChoice);
		HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("Options"), 0, 0, e -> {
			PlayerOptionsPopUp options = (PlayerOptionsPopUp) new PopUpFactory().buildPopUpDisplay();
			options.show();
			options.setOnClosed(k -> {
				try {
					UserOptions ud = new UserOptions(options.getColorChoice(), options.getKeyChoice());
					myGamePlay.setOptions(ud);
					getXMLHandler().save(ud, myGamePlay.getGame().getName() + getPlayerInformationController().getUser() + "options");
				} catch (Exception x) {
					showError(x);
				}
			});
		}, ButtonDisplay.TEXT));
		hbox.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("Levels"), 0, 0, e -> {
			LevelSelectionPopUp levelSelection = (LevelSelectionPopUp) new PopUpFactory().buildPopUpDisplay(myGamePlay.getGame().getLevels().size());
			levelSelection.setOnClosed(k -> {
				myGamePlay.setLevel(levelSelection.getSelectedLevel());
			});
			levelSelection.show();
		}, ButtonDisplay.TEXT));
		gameChoice.addNode(hbox);
		gameChoice.addButton("PLAY", e -> {
			myGamePlay.displayGame();
		}, ButtonDisplay.TEXT);
	}
	
	/**
	 *
	 * @param aTitle is the message title
	 * @param aMessage is the message for the post
	 */

	public void publishToFacebook(String aTitle, String aMessage) {
		getPlayerInformationController().publishToFaceBook(aTitle, aMessage);
	}

	private void resetGame(File chosenGame) {
		try {
			myGamePlay = new GamePlayController(getStage(), chosenGame, this, getPlayerInformationController());
		} catch (Exception x) {
			ErrorAlert ea = new ErrorAlert();
			ea.show(x);
		}
	}
}
