package gameplayer.front_end.popup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PlayerOptionsPopUp extends AbstractPopUp implements IPopUpDisplay {

	private String myCurrentKeyChoice;
	private String myCurrentColorChoice;

	public PlayerOptionsPopUp() {
		super();
		addOptions();
	}

	public void addOptions() {
		addOption(createKeyInputOption());
		addOption(createFontOption());
	}

	private Pane createKeyInputOption() {
		HBox keyOption = new HBox(20);
		keyOption.getChildren().add(new Label(getString("KeyInput")));
		final ToggleGroup keyboardGroup = new ToggleGroup();
		RadioButton defaultKey = createRadioButton(getString("Default"), keyboardGroup);
		RadioButton leftKeys = createRadioButton(getString("Left"), keyboardGroup);
		keyboardGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				myCurrentKeyChoice = newValue.getUserData().toString();
			}
		});
		keyOption.getChildren().addAll(defaultKey, leftKeys);
		return keyOption;
	}

	private Pane createFontOption() {
		HBox fontOption = new HBox(20);
		fontOption.getChildren().add(new Label(getString("HUD")));
		final ToggleGroup fontGroup = new ToggleGroup();
		RadioButton red = createRadioButton(getString("Red"), fontGroup);
		RadioButton green = createRadioButton(getString("Green"), fontGroup);
		RadioButton blue = createRadioButton(getString("Blue"), fontGroup);
		fontGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (fontGroup.getSelectedToggle() != null) {
					myCurrentColorChoice = newValue.getUserData().toString();
				}
			}
		});
		fontOption.getChildren().addAll(red, green, blue);
		return fontOption;
	}

	public String getKeyChoice() {
		return myCurrentKeyChoice == null ? "black": myCurrentKeyChoice;
	}

	public String getColorChoice() {
		return myCurrentColorChoice == null ? "black": myCurrentColorChoice;
	}
}