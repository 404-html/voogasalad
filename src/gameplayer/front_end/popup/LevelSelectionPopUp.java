package gameplayer.front_end.popup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LevelSelectionPopUp extends AbstractPopUp implements IPopUpDisplay {

	private int myNumberOfLevels;
	private int mySelectedLevel;

	public LevelSelectionPopUp(int aNumLevels) {
		super();
		myNumberOfLevels = aNumLevels;
		mySelectedLevel = 1;
		addOptions();
	}

	public void addOptions() {
		addOption(createLevelOptions());
	}
	
	public int getSelectedLevel() {
		return mySelectedLevel;
	}

	private Pane createLevelOptions() {
		HBox levelSelection = new HBox(10);
		ToggleGroup group = new ToggleGroup(); 
		int levelsPerLine = 5;
		if (myNumberOfLevels < 5) levelsPerLine = myNumberOfLevels;
		for (int j = 0; j < myNumberOfLevels; j=j+levelsPerLine) {
			for (int i = 0; i < levelsPerLine; i++) { 
				RadioButton button = createRadioButton("Level " + (j + i + 1), group);
				levelSelection.getChildren().add(button);
			}
		}
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				String selection = newValue.getUserData().toString().trim();
				mySelectedLevel = Integer.parseInt(String.valueOf(selection.charAt(selection.length() - 1)));
			}
		});
		return levelSelection;
	}
}