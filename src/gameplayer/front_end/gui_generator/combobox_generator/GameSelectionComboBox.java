package gameplayer.front_end.gui_generator.combobox_generator;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class GameSelectionComboBox implements IComboBox {
	
	private ComboBox<Pane> myBox;
	
	public ComboBox<Pane> createComboBox(List<Pane> aListOfPanes) {
		myBox = new ComboBox<Pane>();
		ObservableList<Pane> items = FXCollections.observableArrayList(aListOfPanes);
		myBox.setItems(items);
		myBox.setPromptText("CHOOSE GAME");
		myBox.setVisibleRowCount(4);
		return myBox;
	}


}
