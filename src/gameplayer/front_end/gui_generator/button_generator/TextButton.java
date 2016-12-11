package gameplayer.front_end.gui_generator.button_generator;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class TextButton extends AbstractButton {

	public Button createButton(String aMessage, double aXPos, double aYPos, EventHandler<? super MouseEvent> aHandler) {
		Button button = super.createButton("", aXPos, aYPos, aHandler);
		button.setText(aMessage);
		return button;
	}
	
}
