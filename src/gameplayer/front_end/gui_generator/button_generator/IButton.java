package gameplayer.front_end.gui_generator.button_generator;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public interface IButton {
	
	public Button createButton(String aMessage, double aXPos, double aYPos, EventHandler<? super MouseEvent> aHandler);
	
}
