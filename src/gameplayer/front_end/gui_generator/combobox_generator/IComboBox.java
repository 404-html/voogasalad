package gameplayer.front_end.gui_generator.combobox_generator;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public interface IComboBox {
	
	public ComboBox createComboBox(List<Pane> aListOfPanes);
	

}
