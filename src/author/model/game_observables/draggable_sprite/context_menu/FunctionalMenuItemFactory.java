package author.model.game_observables.draggable_sprite.context_menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * Creates the @IFunctionalMenuItems for the @ISpriteContextMenu
 * @author Jordan Frazier
 *
 */
public class FunctionalMenuItemFactory {

	public FunctionalMenuItemFactory() {
		
	}
	
	public IFunctionalMenuItem create(String name, EventHandler<ActionEvent> event) {
		return new ConcreteFunctionalMenuItem(name, event);
	}
}
