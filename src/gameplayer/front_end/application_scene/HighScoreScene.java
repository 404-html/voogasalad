package gameplayer.front_end.application_scene;

import java.io.File;

import gameplayer.back_end.user_information.HighscoreManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import util.XMLTranslator;

public class HighScoreScene extends AbstractNavigationPlayerScene {
	
	private Pane myPane;
	
	public HighScoreScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		myPane = new Pane();
		myPane.setId("glass");
	} 

	@Override
	public Scene init() {
		XMLTranslator myTranslator = new XMLTranslator();
		HighscoreManager myScores = (HighscoreManager) myTranslator.loadFromFile(new File("XMLGameFiles/" + "highscores" + ".xml"));
		
		getRoot().setCenter(addNodes());
		return myScene;
	}

	private Pane addNodes() {
		myPane.getChildren().add(getOptions());
		myPane.setOpacity(0.5);
		getOptions().setAlignment(Pos.CENTER);
		return myPane;
	}
}
