package author.view.util.authoring_buttons;

import author.view.util.language_selection.ILanguageHolder;
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
	
	public FunctionalButton createButton(String aKey, EventHandler<ActionEvent> event, ILanguageHolder aLanguageHolder) {
		return new FunctionalButton(aKey, event, aLanguageHolder);
	}
	
	public FunctionalButton createButton(String aKey, EventHandler<ActionEvent> event) {
		return new FunctionalButton(aKey, event);
	}

}
