package gameplayer.application_controller;

import java.io.File;
import author.view.pages.level_editor.windows.splash_screen.AuthoringSplashScreenFactory;
import author.view.pages.level_editor.windows.splash_screen.IAuthoringSplashScreen;
import gameplayer.back_end.exceptions.GameNotFunctionalException;
import gameplayer.back_end.exceptions.VoogaFacebookException;
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

	private void displayAuthoring() {
		IAuthoringSplashScreen aSplashScreen = (new AuthoringSplashScreenFactory()).create();
		aSplashScreen.initializeWindow();
	}

	@SuppressWarnings("unchecked")
	private void createNavigationButtons(INavigationDisplay aMenu) {
		String[] names = {getButtonLabels().getString("MainMenu"), getButtonLabels().getString("Profile"), getButtonLabels().getString("HawaiianShirt")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		aMenu.addNavigationMenu(image, names, e -> {
			displayMainMenu(getStage().getScene().getWidth(), getStage().getScene().getHeight());
		}, e -> {
			displayUserScene();
		}, e-> {
			myCurrentDisplay.setBackground(getButtonLabels().getString("Shirt" + (int) Math.floor(Math.random() * 6)), getStage().getWidth(), getStage().getHeight());
		});
	}

	/**
	 * 
	 * @param aGamename is used to determine which set of highscores to get
	 */
	public void displayHighScoreScene(String aGamename) {
		IDisplay highScore = getSceneFactory().create(SceneIdentifier.HIGHSCORE, getStage().getScene().getWidth(), getStage().getScene().getHeight(), aGamename);
		createNavigationButtons((INavigationDisplay) highScore);
		resetStage(highScore);
		//setHighScoreHandlers((INavigationDisplay) highScore);
	}

	private void displayGameChoiceRoundTwo(String aGamename) {
		myCurrentDisplay = getSceneFactory().create(SceneIdentifier.GAMECHOICE, getStage().getScene().getWidth(), getStage().getScene().getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setGameChoiceButtonHandlers((INavigationDisplay) myCurrentDisplay, aGamename);
		setGameChoiceSecondRoundButtonHandlers((INavigationDisplay) myCurrentDisplay);
	}

	private void displayUserScene() {
		myCurrentDisplay = getSceneFactory().create(getPlayerInformationController().getUser(), getPlayerInformationController().getPictureUrl(), getStage().getScene().getWidth(), getStage().getScene().getHeight(), loadHighscores());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
	}

	private void displayGameChoice() {
		myCurrentDisplay = getSceneFactory().create(SceneIdentifier.GAMECHOICE, getStage().getScene().getWidth(), getStage().getScene().getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setGameChoiceButtonHandlers((INavigationDisplay) myCurrentDisplay, getButtonLabels().getString("Choose"));
	}

	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice, String aLabel) {
		gameChoice.addNode(getGUIGenerator().createComboBox(aLabel, myStoredGames.getGames(), 
				myStoredGames.getIcons(), myStoredGames.getDescriptions(), (aChoice) -> {
					try {
						resetGame(myStoredGames.getGameFilePath(aChoice));
						displayGameChoiceRoundTwo(myGamePlay.getGame().getName());
					} catch (Exception x) {
						showError(x);
						displayGameChoice();
					}
					getUserPreferences();
				}));
		gameChoice.addButton(getButtonLabels().getString("Load"), e -> {
			File chosenGame = new FileChoiceController().show(getStage());
			if (chosenGame != null) {
				try {
					resetGame(chosenGame);
					displayGameChoiceRoundTwo(myGamePlay.getGame().getName());
				} catch (Exception x) {
					showError(x);
				}
				getUserPreferences();
			}
		}, ButtonDisplay.TEXT); 
	}

	private void getUserPreferences() {
		try {
			getOptions();
			getLevel();
		} catch (Exception x) {
			//do nothing
		}
	}

	private void getOptions() {
		UserOptions uo = (UserOptions) getXMLHandler().load(myGamePlay.getGame().getName() + getPlayerInformationController().getUser() + "options");
		myGamePlay.setOptions(uo);
	}

	private void getLevel() throws Exception {
		LevelManager lm = (LevelManager) getXMLHandler().load(myGamePlay.getGame().getName() + "level");
		myGamePlay.setLevel(lm.getLevel());
	}

	private void setGameChoiceSecondRoundButtonHandlers(INavigationDisplay gameChoice) {
		HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("Options"), 0, 0, e -> {
			PlayerOptionsPopUp options = (PlayerOptionsPopUp) new PopUpFactory().buildPopUpDisplay();
			options.setOnClosed(k -> {
				try {
					UserOptions ud = new UserOptions(options.getColorChoice(), options.getKeyChoice());
					myGamePlay.setOptions(ud);
					getXMLHandler().save(ud, myGamePlay.getGame().getName() + getPlayerInformationController().getUser() + "options");
				} catch (Exception x) {
					showError(x);
				}
			});
			options.show();
		}, ButtonDisplay.TEXT));
		hbox.getChildren().add(getGUIGenerator().createButton(getButtonLabels().getString("Levels"), 0, 0, e -> {
			LevelSelectionPopUp levelSelection = (LevelSelectionPopUp) new PopUpFactory().buildPopUpDisplay(myGamePlay.getGame().getLevels().size());
			levelSelection.setOnClosed(k -> {
				try {
					myGamePlay.setLevel(levelSelection.getSelectedLevel());
				} catch (Exception e1) {
					showError(e1);
				}
			});
			levelSelection.show();
		}, ButtonDisplay.TEXT));
		gameChoice.addNode(hbox);
		gameChoice.addButton("PLAY", e -> {
			try {
				myGamePlay.displayGame();
			} catch (Exception e1) {
				showError(new GameNotFunctionalException("Your game had trouble loading, please try again"));
			}
		}, ButtonDisplay.TEXT);
	}

	/**
	 *
	 * @param aTitle is the message title
	 * @param aMessage is the message for the post
	 */

	public void publishToFacebook(String aTitle, String aMessage) throws VoogaFacebookException {
		getPlayerInformationController().publishToFaceBook(aTitle, aMessage);
	}

	private void resetGame(File chosenGame) throws Exception {
		myGamePlay = new GamePlayController(getStage(), chosenGame, this, getPlayerInformationController());
	}
}
