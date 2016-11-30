package author.view.util.authoring_buttons;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LabelFactory {

	public LabelFactory() {

	}

	public FunctionalLabel createLabel(String name, EventHandler<MouseEvent> event) {
		return new FunctionalLabel(name, event);
	}
}
