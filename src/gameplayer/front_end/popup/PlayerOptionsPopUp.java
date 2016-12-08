package gameplayer.front_end.popup;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class PlayerOptionsPopUp extends AbstractOptions {

	private String myCurrentKeyChoice;
	private String myCurrentColorChoice;
	
	public PlayerOptionsPopUp() {
		myOptions = new ArrayList<HBox>();
		myButtonLabels = PropertyResourceBundle.getBundle(RESOURCE_FILE + BUTTONLABEL);
	}
	
	protected List<HBox> setOptions(){
		createKeyInputOption();
		createFontOption();
		return myOptions;
	}

	private void createKeyInputOption() {
		HBox keyOption = new HBox(20);
		keyOption.getChildren().add(new Label(myButtonLabels.getString("KeyInput")));
		final ToggleGroup keyboardGroup = new ToggleGroup();
		RadioButton defaultKey = new RadioButton(myButtonLabels.getString("Default"));
		defaultKey.setToggleGroup(keyboardGroup);
		defaultKey.setSelected(true);
		defaultKey.setUserData("Default");
		RadioButton leftKeys = new RadioButton(myButtonLabels.getString("Left"));
		leftKeys.setToggleGroup(keyboardGroup);
		leftKeys.setSelected(false);
		leftKeys.setUserData("Left");
		keyboardGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				myCurrentKeyChoice = newValue.getUserData().toString();
			}
		});
		keyOption.getChildren().addAll(defaultKey, leftKeys);
		myOptions.add(keyOption);
	}

	private void createFontOption() {
		HBox fontOption = new HBox(20);
		fontOption.getChildren().add(new Label(myButtonLabels.getString("HUD")));
		final ToggleGroup fontGroup = new ToggleGroup();
		RadioButton red = createRadioButton(myButtonLabels.getString("Red"), fontGroup);
		RadioButton green = createRadioButton(myButtonLabels.getString("Green"), fontGroup);
		RadioButton blue = createRadioButton(myButtonLabels.getString("Blue"), fontGroup);
		fontGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (fontGroup.getSelectedToggle() != null) {
					myCurrentColorChoice = newValue.getUserData().toString();
				}
			}
		});
		fontOption.getChildren().addAll(red, green, blue);
		myOptions.add(fontOption);
	}
	
	public String getKeyChoice() {
		return myCurrentKeyChoice == null ? "black": myCurrentKeyChoice;
	}
	
	public String getColorChoice() {
		return myCurrentColorChoice == null ? "black": myCurrentColorChoice;
	}
}