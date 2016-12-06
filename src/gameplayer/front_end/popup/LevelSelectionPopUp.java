package gameplayer.front_end.popup;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class LevelSelectionPopUp {
	
	private static final String RESOURCE_FILE = "gameplayerlabels.";
	private static final String BUTTONLABEL = "ButtonLabels"; 

	private List<HBox> myOptions;
	private ResourceBundle myButtonLabels;
	private int myNumberOfLevels;
	
	public LevelSelectionPopUp(int aNumLevels) {
		myOptions = new ArrayList<HBox>();
		myButtonLabels = PropertyResourceBundle.getBundle(RESOURCE_FILE + BUTTONLABEL);
		myNumberOfLevels = aNumLevels;
	}
	
	public List<HBox> addOptions() {
		createLevelOptions();
		createPlayButton();
		return myOptions;
	}

	private void createLevelOptions() {
		HBox levelSelection = new HBox(10);
		ToggleGroup group = new ToggleGroup(); 
		int levelsPerLine = 5;
		boolean selected = true;
		if (myNumberOfLevels < 5) levelsPerLine = myNumberOfLevels;
		for (int j = 0; j < myNumberOfLevels; j=j+levelsPerLine) {
			for (int i = 0; i < levelsPerLine; i++) { 
				RadioButton button = new RadioButton("Level " + (j + i + 1));
				button.setSelected(selected);
				selected = false;
				button.setToggleGroup(group);
				levelSelection.getChildren().add(button);
			}
		}
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				
			}
		});
		myOptions.add(levelSelection);
	}

	private void createPlayButton() {
		HBox buttonBox = new HBox(10); 
		Button playButton = new Button("Play");
		buttonBox.getChildren().add(playButton);
		myOptions.add(buttonBox);
	}
}