package gameplayer.application_controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PropertyResourceBundle;
import author.controller.AuthorControllerFactory;
import author.controller.IAuthorControllerExternal;
import gameplayer.back_end.Resources.FrontEndResources;
import gameplayer.back_end.stored_games.StoredGames;
import gameplayer.back_end.user_information.UserDefaults;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.application_scene.SceneIdentifier;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.popup.LevelSelectionPopUp;
import gameplayer.front_end.popup.PlayerOptionsPopUp;
import gameplayer.front_end.popup.PopUpFactory;
import gameplayer.front_end.popup.AbstractPopUp;
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
	private UserDefaults myUserDefaults;
	
	public ApplicationController (Stage aStage) {
		myStage = aStage;
		mySceneBuilder = new SceneFactory();
		myInformationController = new PlayerInformationController();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myStoredGames = new StoredGames();
		myUserDefaults = new UserDefaults(this.getClass().toString());
	}

	public void startScene() throws FileNotFoundException {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.MAINMENU, FrontEndResources.SCENE_SIZE.getDoubleResource(),
				FrontEndResources.SCENE_SIZE.getDoubleResource());
		resetStage(myCurrentDisplay);
		setMainMenuButtonHandlers((MainMenuScene) myCurrentDisplay);
	}

	public void displayMainMenu() {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.MAINMENU, myStage.getWidth(), myStage.getHeight());
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
		mainMenu.addButton("LOGIN TO FACEBOOK", e -> {
			myInformationController.facebookLogin();
		}, ButtonDisplay.FACEBOOK);
	}
	
	private void displayAuthoring() {
		IAuthorControllerExternal authorControllerExternal = new AuthorControllerFactory().create();
		Scene scene = authorControllerExternal.getScene();
		myStage.setWidth(scene.getWidth());
		myStage.setHeight(scene.getHeight());
		myStage.setScene(scene);
	}

	@SuppressWarnings("unchecked")
	private void createNavigationButtons(INavigationDisplay aMenu) {
		String[] names = {myButtonLabels.getString("MainMenu"), myButtonLabels.getString("Profile"), "New HawaiianShirt"};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
		aMenu.addNavigationMenu(image, names, e -> {
			displayMainMenu();
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
		userProfile.addButton(myButtonLabels.getString("Hi"), e -> {
			//do nothing
		}, ButtonDisplay.TEXT);
	}

	private void displayGameChoice() {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.GAMECHOICE, myStage.getWidth(), myStage.getHeight());
		resetStage(myCurrentDisplay);
		createNavigationButtons((INavigationDisplay) myCurrentDisplay);
		setGameChoiceButtonHandlers((INavigationDisplay) myCurrentDisplay);
	}

	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice) {
		gameChoice.addNode(getGUIGenerator().createComboBox(myStoredGames.getGames(), myStoredGames.getIcons(), (aChoice) -> {
			displayGame(myStoredGames.getGameFilePath(aChoice));
			setGameChoiceSecondRoundButtonHandlers(gameChoice);
		}));
		gameChoice.addButton(myButtonLabels.getString("Load"), e -> {
			File chosenGame = new FileChoiceController().show(myStage);
			displayGame(chosenGame);
			setGameChoiceSecondRoundButtonHandlers(gameChoice);
		}, ButtonDisplay.TEXT); 
	}
	
	private void setGameChoiceSecondRoundButtonHandlers(INavigationDisplay gameChoice) {
		HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Options"), 0, 0, e -> {
			IPopUpDisplay options = new PopUpFactory().buildPopUpDisplay();
			options.show();
		}, ButtonDisplay.TEXT));
		hbox.getChildren().add(getGUIGenerator().createButton("LEVELS", 0, 0, e -> {
			IPopUpDisplay levelSelection = new PopUpFactory().buildPopUpDisplay(myGamePlay.getGame().getLevels().size());
			levelSelection.show();
		}, ButtonDisplay.TEXT));
		gameChoice.addNode(hbox);
		gameChoice.addButton("PLAY", e -> {
			myGamePlay.displayGame();
		}, ButtonDisplay.TEXT);
	}
	
	private void displayGame(File chosenGame) {
		myGamePlay = new GamePlayController(myStage, chosenGame, this, 0, myUserDefaults.getKeyInputColor("default"));
	}
	
	public UserDefaults getUserDefaults(){
		return myUserDefaults;
	}
}
