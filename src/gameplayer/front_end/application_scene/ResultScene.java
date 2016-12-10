package gameplayer.front_end.application_scene;
import java.io.File;

import gameplayer.front_end.background_display.BackgroundDisplayFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ResultScene extends AbstractPlayerScene {
	
	private BorderPane myPane;

	public ResultScene() {
		myPane = new BorderPane();
		myPane.setId(new File("gui/style.css").toURI().toString());
		myPane.getChildren().add(getOptions());
		myPane.setId("pane");
	}

	public Pane getPane() {
		return getOptions();
	}
	
	public void setBackground(String aFile, double aWidth, double aHeight) {
		Background background = new BackgroundDisplayFactory().buildBackgroundDisplay(aFile, aWidth, aHeight);
		myPane.setBackground(background);
	}
	
}
