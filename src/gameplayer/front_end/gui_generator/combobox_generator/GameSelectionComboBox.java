package gameplayer.front_end.gui_generator.combobox_generator;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GameSelectionComboBox implements IComboBox {
	
	private ComboBox<Pane> myBox;
	private String myGame;
	
	public ComboBox<Pane> createComboBox(List<Pane> aListOfPanes) {
		myBox = new ComboBox<Pane>();
		ObservableList<Pane> items = FXCollections.observableArrayList(aListOfPanes);
		myBox.setItems(items);
		myBox.setPromptText("CHOOSE GAME");
		//box.setEditable(true); 
		//myBox.setOnAction(e -> {
		  //  Pane selectedPane = myBox.getSelectionModel().getSelectedItem();
		   // String name = ((Label) selectedPane.getChildren().get(0)).getText();
		   // myGame = name;
		//});
		myBox.setVisibleRowCount(4);
		return myBox;
	}


}
