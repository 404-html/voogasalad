package author.model.game_observables.draggable_sprite.context_menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

class ConcreteFunctionalMenuItem implements IFunctionalMenuItem {

	private MenuItem item;

	ConcreteFunctionalMenuItem(String label, EventHandler<ActionEvent> event) {
			item = new MenuItem(label);
			this.item.setOnAction(event);
		}

	public MenuItem getItem() {
		return item;
	}

}
