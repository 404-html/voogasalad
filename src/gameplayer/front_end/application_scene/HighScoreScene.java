package gameplayer.front_end.application_scene;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import gameplayer.back_end.resources.FrontEndResources;
import gameplayer.back_end.user_information.HighscoreManager;
import gameplayer.front_end.background_display.BackgroundDisplayFactory;
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
	private List<Double> myScores;
	private HighscoreManager myHighscoreManager;
	private Set<String> myAddedUsers;
	
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
		myScores = new ArrayList<Double>();
		myPane.setId("glass");
		myGamename = aGamename;
		myAddedUsers = new HashSet<String>();
	}
	
	public void setGame(String aGamename) {
		myGamename = aGamename;
	}

	@Override
	public Scene init() {
		XMLTranslator myTranslator = new XMLTranslator();
		myHighscoreManager = (HighscoreManager) myTranslator.loadFromFile(
				new File("XMLGameFiles/" + "highscores" + ".xml"));
		getRoot().setCenter(addNodes());
		addScores(myHighscoreManager);
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
		HBox box = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource() * 3);
		VBox users = new VBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		VBox scores = new VBox(FrontEndResources.BOX_INSETS.getDoubleResource());
		for (int i = 0; i < aManager.getGames().size(); i++) {
			if (aManager.getGames().get(i).getName().equals(myGamename)) {
				addScore(aManager.getUsers().get(i), aManager.getHighscores().get(i), users, scores);
			}
		}
		getSortedList(users, scores);
		users.setFillWidth(true);
		scores.setFillWidth(true);
		scores.setMaxWidth(200);
		users.setMaxWidth(2000);
		box.setMaxWidth(5000);
		box.setMinWidth(1000);
		box.getChildren().add(users);
		box.getChildren().add(scores);
		box.setAlignment(Pos.TOP_CENTER);
		getOptions().getChildren().add(box);
	}

	private void addScore(String aUser, double aScore, VBox aUsers, VBox aScores) {
		myScores.add(aScore);
	}
	
	private void getSortedList(VBox aUsers, VBox aScores) {
		int i = 0;
		Collections.sort(myScores, Collections.reverseOrder());
		for (Double score : myScores) {
			String user = getUserWithScore(score, i);
			if (!user.isEmpty()) {
				aUsers.getChildren().add(getGUIGenerator().createLabel(user, 0, 0));
				DecimalFormat twoDForm = new DecimalFormat("#.##");
				Double d = Double.valueOf(twoDForm.format(score));
				aScores.getChildren().add(getGUIGenerator().createLabel(String.valueOf(d), 0, 0));
			}
			if (i == 8) {
				break;
			}
			i++;
		}
	}
	
	private String getUserWithScore(double aScore, int aDex) {
		for (int i = 0; i < myHighscoreManager.getHighscores().size(); i++) {
			if (myHighscoreManager.getHighscores().get(i) == aScore) {
				if (!myAddedUsers.contains(myHighscoreManager.getUsers().get(i))) {
					myAddedUsers.add(myHighscoreManager.getUsers().get(i));
					return myHighscoreManager.getUsers().get(i);
				}
				if (aDex < 8) {
					return myHighscoreManager.getUsers().get(i);
				}
			}
		}
		return "";
	}
}
