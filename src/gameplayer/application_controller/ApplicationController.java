package gameplayer.application_controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import gameplayer.back_end.facebook.FacebookInformation;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.MainMenuScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.application_scene.SceneIdentifier;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.popup.PlayerOptionsPopUp;
import gameplayer.front_end.popup.PopUpController;
import javafx.stage.Stage;

/**
 * Where the player part can interact with the game engine and get the appropriate data to be displayed
 * 
 * @author tedmarchildon, hannah
 *
 */

public class ApplicationController extends AbstractController {
	
	private static final String FILE = "gameplayerlabels.";
	private static final String BUTTONLABEL = "ButtonLabels"; 
	private SceneFactory mySceneBuilder;
	private PlayerInformationController myInformationController;
	private ResourceBundle myButtonLabels;
	private FacebookInformation myFacebookInformation;
	
	public ApplicationController (Stage aStage) {
		myStage = aStage;
		mySceneBuilder = new SceneFactory();
		myInformationController = new PlayerInformationController();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myFacebookInformation = new FacebookInformation();
	}
	
	public void startScene() throws FileNotFoundException {
		IDisplay mainMenu = mySceneBuilder.create(SceneIdentifier.MAINMENU, SCENE_SIZE, SCENE_SIZE);
		resetStage(mainMenu);
		setMainMenuButtonHandlers((MainMenuScene) mainMenu);
	}

	public void displayMainMenu() {
		MainMenuScene mainMenu = (MainMenuScene) mySceneBuilder.create(SceneIdentifier.MAINMENU, myStage.getWidth(), myStage.getHeight());
		resetStage(mainMenu);
		setMainMenuButtonHandlers(mainMenu);
	}

	private void setMainMenuButtonHandlers(INavigationDisplay mainMenu) {
		mainMenu.addButton(myButtonLabels.getString("Play"), e -> {
			displayGameChoice();
		}, ButtonDisplay.TEXT);
		mainMenu.addButton(myButtonLabels.getString("Author"), e -> {
			//TODO: implement authoring environment
		}, ButtonDisplay.TEXT);
		mainMenu.addButton("LOGIN TO FACEBOOK", e -> {
			myFacebookInformation.authenticatePlayer();
		}, ButtonDisplay.FACEBOOK);
	}
	
	@SuppressWarnings("unchecked")
	private void createNavigationButtons(INavigationDisplay aMenu) {
		String[] names = {"MAIN MENU", "PROFILE"};
		ImageView image = myGUIGenerator.createImage("data/gui/clip_art_hawaiian_flower.png",30);
		aMenu.addNavigationMenu(image, names, e -> {
			displayMainMenu();
		}, e -> {
			displayUserScene();
		});
	}
	
	public void displayHighScoreScene() {
		IDisplay highScore = mySceneBuilder.create(SceneIdentifier.HIGHSCORE, myStage.getWidth(), myStage.getHeight());
		resetStage(highScore);
		setHighScoreHandlers((INavigationDisplay) highScore);
	}
	
	private void setHighScoreHandlers(INavigationDisplay highScoreScene) {
		highScoreScene.addNode(myGUIGenerator.createLabel("" + myInformationController.getHighScoresForUser("hi"), 0, 0));
	}

	private void displayUserScene() {
		IDisplay userProfile = mySceneBuilder.create(SceneIdentifier.USERPROFILE, myStage.getWidth(), myStage.getHeight());
		resetStage(userProfile);
		setUserProfileButtonHandlers((INavigationDisplay) userProfile);
	}
	
	private void setUserProfileButtonHandlers(INavigationDisplay userProfile) {
		userProfile.addButton("HI!", e -> {
			//do nothing
		}, ButtonDisplay.TEXT);
	}
	
	private void displayGameChoice(){
		IDisplay gameChoice = mySceneBuilder.create(SceneIdentifier.GAMECHOICE, myStage.getWidth(), myStage.getHeight());
		resetStage(gameChoice);
		createNavigationButtons((INavigationDisplay) gameChoice);
		setGameChoiceButtonHandlers((INavigationDisplay) gameChoice);
	}

	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice) {
		//gameChoice.addNode(myGUIGenerator.createComboBox());
		gameChoice.addButton(myButtonLabels.getString("Load"), e -> {
			File chosenGame = new FileController().show(myStage);
			if (chosenGame != null) {
				GamePlayController gamePlay = new GamePlayController(myStage, chosenGame);
				gamePlay.displayGame();
			}
		}, ButtonDisplay.TEXT);
		gameChoice.addButton(myButtonLabels.getString("Options"), e -> {
			PopUpController popup = new PopUpController();
			PlayerOptionsPopUp options = new PlayerOptionsPopUp();
			for(HBox box : options.addOptions()){
				popup.addOption(box);
			}
			popup.show();
		}, ButtonDisplay.TEXT);
	}
}
