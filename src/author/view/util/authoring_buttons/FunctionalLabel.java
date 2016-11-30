package author.view.util.authoring_buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FunctionalLabel {
	
	private Label label;
	
	public FunctionalLabel(String name, EventHandler<MouseEvent> event) {
		label = new Label(name);
		label.setOnMouseClicked(event);
	}
	
	public Label getLabel() {
		return label;
	}
}
