package gameplayer.application_controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PropertyResourceBundle;
import author.controller.AuthorControllerFactory;
import author.controller.IAuthorControllerExternal;
import gameplayer.back_end.stored_games.StoredGames;
import gameplayer.back_end.user_information.UserDefaults;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import gameplayer.front_end.application_scene.SceneFactory;
import gameplayer.front_end.application_scene.SceneIdentifier;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import gameplayer.front_end.popup.LevelSelectionPopUp;
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

	private PlayerInformationController myInformationController;
	private StoredGames myStoredGames;
	private GamePlayController myGamePlay;
	private IDisplay myCurrentDisplay;
	private UserDefaults myUserDefaults;
	
	//change to resource file / maybe make new CSS? 
	private String[] myShirtBackgrounds = {"data/gui/hawaiian_shirt_background1.jpeg", "data/gui/hawaiian_shirt_background2.jpeg", 
			"data/gui/hawaiian_shirt_background3.png", "data/gui/hawaiian_shirt_background4.jpeg", 
			"data/gui/hawaiian_shirt_background5.jpeg", "data/gui/hawaiian_shirt_background6.jpeg", 
			"data/gui/hawaiian_shirt_background7.jpeg"};
	
	public ApplicationController (Stage aStage) {
		myStage = aStage;
		mySceneBuilder = new SceneFactory();
		myInformationController = new PlayerInformationController();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myStoredGames = new StoredGames();
		myUserDefaults = new UserDefaults(this.getClass().toString());
	}

	public void startScene() throws FileNotFoundException {
		myCurrentDisplay = mySceneBuilder.create(SceneIdentifier.MAINMENU, SCENE_SIZE, SCENE_SIZE);
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
			IAuthorControllerExternal authorControllerExternal = new AuthorControllerFactory().create();
			myStage.setTitle("VOOGASalad");
			Scene scene = authorControllerExternal.getScene();
			myStage.setWidth(scene.getWidth());
			myStage.setHeight(scene.getHeight());
			myStage.setScene(scene);
		}, ButtonDisplay.TEXT);
		mainMenu.addButton("LOGIN TO FACEBOOK", e -> {
			myInformationController.facebookLogin();
		}, ButtonDisplay.FACEBOOK);
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
			myCurrentDisplay.setBackground(myShirtBackgrounds[(int) Math.floor(Math.random() * 7)], myStage.getWidth(), myStage.getHeight());
		});
	}

	public void displayHighScoreScene() {
		IDisplay highScore = mySceneBuilder.create(SceneIdentifier.HIGHSCORE, myStage.getWidth(), myStage.getHeight());
		resetStage(highScore);
		setHighScoreHandlers((INavigationDisplay) highScore);
	}

	private void setHighScoreHandlers(INavigationDisplay highScoreScene) {
		//highScoreScene.addNode(getGUIGenerator().createLabel("" + myInformationController.getHighScoresForUser("hi"), 0, 0));
	}

	private void displayUserScene() {
		myCurrentDisplay = mySceneBuilder.create(myInformationController.getUser(), myStage.getWidth(), myStage.getHeight());
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
//		ComboBox<Pane> cBox = getGUIGenerator().createComboBox(getDisplayOfGames());
//		cBox.setOnAction(e -> {
//			String gameName = ((Label) cBox.getSelectionModel().getSelectedItem().getChildren().get(0)).getText();
//			cBox.setPromptText(gameName);
//			File gameFile = myStoredGames.getGameFilePath(gameName);
//			displayGame(gameFile);
//			setGameChoiceSecondRoundButtonHandlers(gameChoice);
//		});
		gameChoice.addButton(myButtonLabels.getString("Load"), e -> {
			File chosenGame = new FileChoiceController().show(myStage);
			displayGame(chosenGame);
			//myStoredGames.addGame(myGamePlay.getGame().getName(), chosenGame);
			setGameChoiceSecondRoundButtonHandlers(gameChoice);
		}, ButtonDisplay.TEXT); 
	}
	
	private void setGameChoiceSecondRoundButtonHandlers(INavigationDisplay gameChoice) {
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(getGUIGenerator().createButton(myButtonLabels.getString("Options"), 0, 0, e -> {
			if (myGamePlay != null) {
				PopUpController popup = new PopUpController();
				PlayerOptionsPopUp options = new PlayerOptionsPopUp();
				for(HBox box : options.addOptions()){
					popup.addOption(box);
				}
				popup.show();
			}
		}, ButtonDisplay.TEXT));
		hbox.getChildren().add(getGUIGenerator().createButton("LEVELS", 0, 0, e -> {
			if (myGamePlay != null) {
				PopUpController popup = new PopUpController();
				LevelSelectionPopUp levelSelection = new LevelSelectionPopUp(myGamePlay.getGame().getLevels().size());
				for (HBox box : levelSelection.addOptions()) {
					popup.addOption(box);
				}
				popup.show();
				myGamePlay.setLevel(levelSelection.getSelectedLevel());
			}
		}, ButtonDisplay.TEXT));
		gameChoice.addNode(hbox);
		gameChoice.addButton("PLAY", e -> {
			if (myGamePlay != null) {
				myGamePlay.displayGame();
			}
		}, ButtonDisplay.TEXT);
	}
	
	private void displayGame(File chosenGame) {
		myGamePlay = new GamePlayController(myStage, chosenGame, this, 0, myUserDefaults.getKeyInputColor("default"));
	}
	
	public UserDefaults getUserDefaults(){
		return myUserDefaults;
	}
}
