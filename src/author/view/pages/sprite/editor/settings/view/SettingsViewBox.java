package author.view.pages.sprite.editor.settings.view;

import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.InfoMap;

public class SettingsViewBox {

	private Pane myPane;
	private Map<String, Object> myInfo;

	public SettingsViewBox(Object input) {
		myInfo = new InfoMap(input).getInfoMap();
		myPane = new VBox();

		buildViewBox(input, myInfo);
	}

	public Pane getPane() {
		return myPane;
	}

	private void buildViewBox(Object input, Map<String, Object> infoMap) {
		myPane.getChildren().addAll(new ToolBar(new Label(input.getClass().getSimpleName())));

		infoMap.entrySet().forEach(e -> {
			Pane box = new HBox();
			box.getChildren().addAll(new Label(e.getKey().toString()), new Label("  |  " + e.getValue().toString()));
			myPane.getChildren().add(box);
		});
	}

}