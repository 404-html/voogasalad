package author.view.pages.sprite.editor.settings.view;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsViewBox {

	private Pane myPane;
	private Map<String, Object> myInfo;

	public SettingsViewBox(Object input) {
		myInfo = new TreeMap<>();
		myPane = new VBox();

		buildViewBox(input, myInfo);
	}

	public Pane getPane(){
		return myPane;
	}

	private void buildViewBox(Object input, Map<String, Object> infoMap){
		myPane.getChildren().addAll(new ToolBar(new Label(input.getClass().getSimpleName())) );

		infoMap.entrySet().forEach( e -> {
			Pane box = new HBox();
			box.getChildren().addAll(
					new Label( e.getKey().toString()),
					new Label( "  |  " + e.getValue().toString())
					);
			myPane.getChildren().add(box);
		} );		
	}

}