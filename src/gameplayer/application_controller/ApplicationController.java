package gameplayer.application_controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import author.controller.AuthorControllerFactory;
import author.controller.IAuthorControllerExternal;
import gameplayer.back_end.stored_games.StoredGames;
import gameplayer.front_end.application_scene.IDisplay;
import gameplayer.front_end.application_scene.INavigationDisplay;
import gameplayer.front_end.application_scene.MainMenuScene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	
	public ApplicationController (Stage aStage) {
		myStage = aStage;
		mySceneBuilder = new SceneFactory();
		myInformationController = new PlayerInformationController();
		myButtonLabels = PropertyResourceBundle.getBundle(FILE + BUTTONLABEL);
		myStoredGames = new StoredGames();
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
		String[] names = {myButtonLabels.getString("MainMenu"), myButtonLabels.getString("Profile")};
		ImageView image = getGUIGenerator().createImage("data/gui/clip_art_hawaiian_flower.png",30);
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
		//highScoreScene.addNode(getGUIGenerator().createLabel("" + myInformationController.getHighScoresForUser("hi"), 0, 0));
	}

	private void displayUserScene() {
		IDisplay userProfile = mySceneBuilder.create(myInformationController.getUser(), myStage.getWidth(), myStage.getHeight());
		resetStage(userProfile);
		createNavigationButtons((INavigationDisplay) userProfile);
		setUserProfileButtonHandlers((INavigationDisplay) userProfile);
	}
	
	private void setUserProfileButtonHandlers(INavigationDisplay userProfile) {
		userProfile.addButton(myButtonLabels.getString("Hi"), e -> {
			//do nothing
		}, ButtonDisplay.TEXT);
	}
	
	private void displayGameChoice() {
		IDisplay gameChoice = mySceneBuilder.create(SceneIdentifier.GAMECHOICE, myStage.getWidth(), myStage.getHeight());
		resetStage(gameChoice);
		createNavigationButtons((INavigationDisplay) gameChoice);
		setGameChoiceButtonHandlers((INavigationDisplay) gameChoice);
	}

	private void setGameChoiceButtonHandlers(INavigationDisplay gameChoice) {
		ComboBox<Pane> cBox = getGUIGenerator().createComboBox(getDisplayOfGames());
		cBox.setOnAction(e -> {
			String gameName = ((Label) cBox.getSelectionModel().getSelectedItem().getChildren().get(0)).getText();
			cBox.setPromptText(gameName);
			File gameFile = myStoredGames.getGameFilePath(gameName);
			myGamePlay = new GamePlayController(myStage, gameFile, this, 0);
			setGameChoiceSecondRoundButtonHandlers(gameChoice);
		});
		gameChoice.addNode(cBox);
		gameChoice.addButton(myButtonLabels.getString("Load"), e -> {
			File chosenGame = new FileController().show(myStage);
			myGamePlay = new GamePlayController(myStage, chosenGame, this, 0);
			myStoredGames.addGame(myGamePlay.getGame().getName(), chosenGame);
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
				for(HBox box : levelSelection.addOptions()) {
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

	private List<Pane> getDisplayOfGames() {
		List<Pane> aList = new ArrayList<Pane>();
		for (String game : myStoredGames.getGameNames()) {
			HBox box = new HBox();
			box.getChildren().add(getGUIGenerator().createLabel(game, 0,0));
			aList.add(box);
		}
		return aList;
	}
}
