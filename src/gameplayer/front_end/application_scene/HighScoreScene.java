package gameplayer.front_end.application_scene;

import java.io.File;
import gameplayer.back_end.user_information.HighscoreManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.XMLTranslator;

public class HighScoreScene extends AbstractNavigationPlayerScene {

	private Pane myPane;
	private String myGamename;

	public HighScoreScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		initialize("");
	}

	public HighScoreScene(double aWidth, double aHeight, String aGamename){
		super(aWidth, aHeight);
		initialize(aGamename);
	}

	private void initialize(String aGamename) {
		myPane = new Pane();
		myPane.setId("glass");
		myGamename = aGamename;
	}

	public void setGame(String aGamename) {
		myGamename = aGamename;
	}

	@Override
	public Scene init() {
		XMLTranslator myTranslator = new XMLTranslator();
		HighscoreManager myScores = (HighscoreManager) myTranslator.loadFromFile(new File("XMLGameFiles/" + "highscores" + ".xml"));
		getRoot().setCenter(addNodes());
		addScores(myScores);
		return myScene;
	}

	private Pane addNodes() {
		myPane.getChildren().add(getOptions());
		myPane.setOpacity(0.5);
		getOptions().setAlignment(Pos.CENTER);
		return myPane;
	}

	private void addScores(HighscoreManager aManager) {
		VBox users = new VBox();
		VBox scores = new VBox();
		for (int i = 0; i < aManager.getGames().size(); i++) {
			if (aManager.getGames().get(i).getName().equals(myGamename)) {
				addScore(aManager.getUsers().get(i), aManager.getHighscores().get(i), users, scores);
			}
		}
		HBox box = new HBox();
		box.getChildren().add(users);
		box.getChildren().add(scores);
		getOptions().getChildren().add(box);
	}
	
	private void addScore(String aUser, double aScore, VBox aUsers, VBox aScores) {
		aScores.getChildren().add(new Label(String.valueOf(aScore)));
		aUsers.getChildren().add(new Label(aUser));
	}
}
