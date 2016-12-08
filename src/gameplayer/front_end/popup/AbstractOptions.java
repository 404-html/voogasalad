package gameplayer.front_end.popup;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public abstract class AbstractOptions {

	protected List<HBox> myOptions;
	protected ResourceBundle myButtonLabels;
	protected static final String RESOURCE_FILE = "gameplayerlabels.";
	protected static final String BUTTONLABEL = "ButtonLabels";
	
	public List<HBox> addOptions() {
		return setOptions();
	}

	protected abstract List<HBox> setOptions();
	
	protected RadioButton createRadioButton(String aProperty, ToggleGroup aFontGroup) {
		RadioButton radioButton = new RadioButton(aProperty);
		radioButton.setToggleGroup(aFontGroup);
		radioButton.setSelected(true);
		radioButton.setUserData(aProperty);
		return radioButton;
	}
}
