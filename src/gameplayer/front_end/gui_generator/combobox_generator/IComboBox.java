package gameplayer.front_end.gui_generator.combobox_generator;

import java.util.List;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public interface IComboBox {
	
	public ComboBox<Pane> createComboBox(List<Pane> aListOfPanes);
	

}
