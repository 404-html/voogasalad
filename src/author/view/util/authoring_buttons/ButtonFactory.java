package author.view.util.authoring_buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * 
 * @author Jordan Frazier
 *
 */
public class ButtonFactory {
	
	public ButtonFactory() {
		
	}
	
	public FunctionalButton createButton(String name, EventHandler<ActionEvent> event) {
		return new FunctionalButton(name, event);
	}

}
