package gameplayer.front_end.application_scene;

import java.io.File;
import gameplayer.back_end.resources.FrontEndResources;
import gameplayer.back_end.user_information.HighscoreManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.XMLTranslator;

public class HighScoreScene extends AbstractNavigationPlayerScene {

	private String myGamename;
	private BorderPane myPane;
	
	public HighScoreScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		initialize("");
	}

	public HighScoreScene(double aWidth, double aHeight, String aGamename){
		super(aWidth, aHeight);
		initialize(aGamename);
	}

	private void initialize(String aGamename) {
		myPane = new BorderPane();
		myPane.setId("glass");
		myGamename = aGamename;
	}

	public void setGame(String aGamename) {
		myGamename = aGamename;
	}

	@Override
	public Scene init() {
		XMLTranslator myTranslator = new XMLTranslator();
		HighscoreManager myScores = (HighscoreManager) myTranslator.loadFromFile(
				new File("XMLGameFiles/" + myGamename + "-highscores" + ".xml"));
		getRoot().setCenter(addNodes());
		addScores(myScores);
		return myScene;
	}

	private Pane addNodes() {
		myPane.setTop(addHeading()); 
		myPane.setCenter(getOptions());
		getRoot().setCenter(myPane);
		return myPane;
	}
	
	private Pane addHeading() {
		VBox v = new VBox();
		Label label = getGUIGenerator().createLabel(myGamename + " HIGHSCORES", 0, 0);
		label.setAlignment(Pos.CENTER);
		v.getChildren().add(label);
		v.setAlignment(Pos.CENTER);
		return v;
	}
	
	private void addScores(HighscoreManager aManager) {
		HBox box = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource() * 2);
		VBox users = new VBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		VBox scores = new VBox();
		for (int i = 0; i < aManager.getGames().size(); i++) {
			if (aManager.getGames().get(i).getName().equals(myGamename)) {
				addScore(aManager.getUsers().get(i), aManager.getHighscores().get(i), users, scores);
			}
		}
		box.getChildren().add(users);
		box.getChildren().add(scores);
		box.setAlignment(Pos.TOP_CENTER);
		getOptions().getChildren().add(box);
	}
	
	private void addScore(String aUser, double aScore, VBox aUsers, VBox aScores) {
		aScores.getChildren().add(new Label(String.valueOf(aScore)));
		aUsers.getChildren().add(new Label(aUser));
	}
}
