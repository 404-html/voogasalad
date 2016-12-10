package gameplayer.application_controller;

import java.io.File;
import java.util.PropertyResourceBundle;

import author.view.pages.level_editor.windows.splash_screen.AuthoringSplashScreenFactory;
import author.view.pages.level_editor.windows.splash_screen.IAuthoringSplashScreen;
import gameplayer.back_end.resources.FrontEndResources;
import gameplayer.back_end.stored_games.StoredGames;
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
import gameplayer.front_end.popup.IPopUpDisplay;
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
			myInformationController.facebookLogin();
		}, ButtonDisplay.FACEBOOK);
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
			myCurrentDisplay.setBackground(myButtonLabels.getString("Shirt" + (int) Math.floor(Math.random() * 7)), myStage.getWidth(), myStage.getHeight());
		});
	}

	public void displayHighScoreScene() {
		IDisplay highScore = mySceneBuilder.create(SceneIdentifier.HIGHSCORE, myStage.getWidth(), myStage.getHeight());
		resetStage(highScore);
		//setHighScoreHandlers((INavigationDisplay) highScore);
	}

	private void displayUserScene() {
		myCurrentDisplay = mySceneBuilder.create(myInformationController.getUser(), myInformationController.getPictureUrl(), myStage.getWidth(), myStage.getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setUserProfileButtonHandlers((INavigationDisplay) myCurrentDisplay);
	}

	private void setUserProfileButtonHandlers(INavigationDisplay userProfile) {
		//do nothing
	}

	private void displayGameChoice() {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.GAMECHOICE, myStage.getWidth(), myStage.getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setGameChoiceButtonHandlers((INavigationDisplay) myCurrentDisplay, true);
	}


	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice, boolean showSecondGameChoice) {
		gameChoice.addNode(getGUIGenerator().createComboBox(myStoredGames.getGames(), myStoredGames.getIcons(), myStoredGames.getDescriptions(), (aChoice) -> {
			displayGame(myStoredGames.getGameFilePath(aChoice));
			if (showSecondGameChoice) setGameChoiceSecondRoundButtonHandlers(gameChoice);
		}));
		gameChoice.addButton(myButtonLabels.getString("Load"), e -> {
			File chosenGame = new FileChoiceController().show(myStage);
			if (chosenGame != null) displayGame(chosenGame);
			if (showSecondGameChoice) setGameChoiceSecondRoundButtonHandlers(gameChoice);
		}, ButtonDisplay.TEXT); 
	}

	private void setGameChoiceSecondRoundButtonHandlers(INavigationDisplay gameChoice) {
		gameChoice.clear();
		setGameChoiceButtonHandlers(gameChoice, false);
		HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Options"), 0, 0, e -> {
			IPopUpDisplay options = new PopUpFactory().buildPopUpDisplay();
			options.show();
		}, ButtonDisplay.TEXT));
		hbox.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Levels"), 0, 0, e -> {
			IPopUpDisplay levelSelection = new PopUpFactory().buildPopUpDisplay(myGamePlay.getGame().getLevels().size());
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
		myGamePlay = new GamePlayController(myStage, chosenGame, this, 0);
	}
}
