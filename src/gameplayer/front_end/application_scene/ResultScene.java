package gameplayer.front_end.application_scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ResultScene extends AbstractPlayerScene {
	
	private BorderPane myPane;

	public ResultScene() {
		myPane = new BorderPane();
		myPane.getChildren().add(getOptions());
	}

	public Pane getPane() {
		return getOptions();
	}
	
}
