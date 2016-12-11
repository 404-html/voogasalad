package gameplayer.application_controller;

import java.io.File;
import java.util.PropertyResourceBundle;
import author.view.pages.level_editor.windows.splash_screen.AuthoringSplashScreenFactory;
import author.view.pages.level_editor.windows.splash_screen.IAuthoringSplashScreen;
import gameplayer.back_end.resources.FrontEndResources;
import gameplayer.back_end.stored_games.StoredGames;
import gameplayer.back_end.user_information.LevelManager;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import gameplayer.front_end.application_scene.SceneFactory;
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

	private PlayerInformationController myInformationController;
	private StoredGames myStoredGames;
	private GamePlayController myGamePlay;
	private IDisplay myCurrentDisplay;

	public ApplicationController (Stage aStage) {
		myStage = aStage;
		mySceneBuilder = new SceneFactory();
		myInformationController = new PlayerInformationController();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myStage.setTitle(myButtonLabels.getString("Title"));
		myStoredGames = new StoredGames();
	}

	public void displayMainMenu(double aWidth, double aHeight) {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.MAINMENU, aWidth, aHeight);
		resetStage(myCurrentDisplay);
		setMainMenuButtonHandlers((MainMenuScene) myCurrentDisplay);
	}

	private void setMainMenuButtonHandlers(INavigationDisplay mainMenu) {
		mainMenu.addButton(myButtonLabels.getString("Play"), e -> {
			displayGameChoice();
		}, ButtonDisplay.TEXT);
		mainMenu.addButton(myButtonLabels.getString("Author"), e -> {
			displayAuthoring();
		}, ButtonDisplay.TEXT);
		mainMenu.addButton(myButtonLabels.getString("Login"), e -> {
			try { 
				myInformationController.facebookLogin();
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
		String[] names = {myButtonLabels.getString("MainMenu"), myButtonLabels.getString("Profile"), myButtonLabels.getString("HawaiianShirt")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		aMenu.addNavigationMenu(image, names, e -> {
			displayMainMenu(myStage.getWidth(), myStage.getHeight());
		}, e -> {
			displayUserScene();
		}, e-> {
			myCurrentDisplay.setBackground(myButtonLabels.getString("Shirt" + ((int) Math.floor(Math.random() * 6) + 1)), myStage.getWidth(), myStage.getHeight());
		});
	}

	public void displayHighScoreScene(String aGamename) {
		IDisplay highScore = mySceneBuilder.create(SceneIdentifier.HIGHSCORE, myStage.getWidth(), myStage.getHeight(), aGamename);
		resetStage(highScore);
		//setHighScoreHandlers((INavigationDisplay) highScore);
	}

	private void displayUserScene() {
		myCurrentDisplay = mySceneBuilder.create(myInformationController.getUser(), myInformationController.getPictureUrl(), myStage.getWidth(), myStage.getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
	}

	private void displayGameChoice() {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.GAMECHOICE, myStage.getWidth(), myStage.getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setGameChoiceButtonHandlers((INavigationDisplay) myCurrentDisplay, true);
	}


	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice, boolean showSecondGameChoice) {
		gameChoice.addNode(getGUIGenerator().createComboBox(myButtonLabels.getString("Choose"), myStoredGames.getGames(), 
				myStoredGames.getIcons(), myStoredGames.getDescriptions(), (aChoice) -> {
					displayGame(myStoredGames.getGameFilePath(aChoice));
					if (showSecondGameChoice) setGameChoiceSecondRoundButtonHandlers(gameChoice);
					try {
						getOptions();
						getLevel();
					} catch (Exception x) {
						//do nothing
					}

				}));
		gameChoice.addButton(myButtonLabels.getString("Load"), e -> {
			File chosenGame = new FileChoiceController().show(myStage);
			if (chosenGame != null) displayGame(chosenGame);
			if (chosenGame != null && showSecondGameChoice) setGameChoiceSecondRoundButtonHandlers(gameChoice);
			getOptions();
			getLevel();
		}, ButtonDisplay.TEXT); 
	}

	private void getOptions() {
		UserOptions uo = (UserOptions) load(myGamePlay.getGame().getName() + "options");
		myGamePlay.setOptions(uo);
	}

	private void getLevel() {
		LevelManager lm = (LevelManager) load(myGamePlay.getGame().getName() + "level");
		myGamePlay.setLevel(lm.getLevel());
	}

	private void setGameChoiceSecondRoundButtonHandlers(INavigationDisplay gameChoice) {
		gameChoice.clear();
		setGameChoiceButtonHandlers(gameChoice, false);
		HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Options"), 0, 0, e -> {
			PlayerOptionsPopUp options = (PlayerOptionsPopUp) new PopUpFactory().buildPopUpDisplay();
			options.show();
			options.setOnClosed(k -> {
				try {
					UserOptions ud = new UserOptions(options.getColorChoice(), options.getKeyChoice());
					myGamePlay.setOptions(ud);
					save(ud, myGamePlay.getGame().getName() + "options");
				} catch (Exception x) {
					showError(x);
				}
			});
		}, ButtonDisplay.TEXT));
		hbox.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Levels"), 0, 0, e -> {
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

	public void publishToFacebook(String aTitle, String aMessage) {
		myInformationController.publishToFaceBook(aTitle, aMessage);
	}

	private void displayGame(File chosenGame) {
		myGamePlay = new GamePlayController(myStage, chosenGame, this, myInformationController, 0);
	}
}
