package gameplayer.front_end.gui_generator.combobox_generator;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class ComboBoxFactory {
	
	private IComboBox myCombo;

	public ComboBox createComboBox(List<Pane> aDisplayOfGames) {
		myCombo = new GameSelectionComboBox();
		return myCombo.createComboBox(aDisplayOfGames);
	}

}
