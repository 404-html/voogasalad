package gameplayer.front_end.popup;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class LevelSelectionPopUp extends AbstractOptions {

	private int myNumberOfLevels;
	private int mySelectedLevel;
	
	public LevelSelectionPopUp(int aNumLevels) {
		myOptions = new ArrayList<HBox>();
		myButtonLabels = PropertyResourceBundle.getBundle(RESOURCE_FILE + BUTTONLABEL);
		myNumberOfLevels = aNumLevels;
		mySelectedLevel = 0;
	}

	protected List<HBox> setOptions() {
		HBox levelSelection = new HBox(20);
		ToggleGroup group = new ToggleGroup(); 
		int levelsPerLine = 5;
		boolean selected = true;
		if (myNumberOfLevels < 5) levelsPerLine = myNumberOfLevels;
		for (int j = 0; j < myNumberOfLevels; j=j+levelsPerLine) {
			for (int i = 0; i < levelsPerLine; i++) { 
				RadioButton button = createRadioButton("Level " + (j + i + 1), group);
				button.setSelected(selected);
				selected = false;
				levelSelection.getChildren().add(button);
			}
		}
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				String selectedButton = group.selectedToggleProperty().getName(); 
				mySelectedLevel = (int) selectedButton.charAt(selectedButton.length() - 1);
				mySelectedLevel--;
			}
		});
		myOptions.add(levelSelection);
		return myOptions;
	}
	
	public int getSelectedLevel() {
		return mySelectedLevel;
	}
}