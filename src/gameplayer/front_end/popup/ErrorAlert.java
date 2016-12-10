package gameplayer.front_end.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorAlert {

	
	public void show(Exception e) {
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText(e.getMessage());
        a.setResizable(true);
        a.showAndWait();
	}

	public void show() {
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Error");
        a.setResizable(true);
        a.showAndWait();
	}
}
