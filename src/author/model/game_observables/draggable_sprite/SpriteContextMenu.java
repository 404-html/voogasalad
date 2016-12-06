package author.model.game_observables.draggable_sprite;

import author.controller.IAuthorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class SpriteContextMenu {

	private DraggableSprite mySprite;
	private ContextMenu myMenu;
	private IAuthorController myAuthorController;

	public SpriteContextMenu(DraggableSprite sprite, IAuthorController authorController) {
		mySprite = sprite;
		myAuthorController = authorController;
		createContextMenu();
	}

	private void createContextMenu() {
		myMenu = new ContextMenu();
		myMenu.getItems().add(new FunctionalMenuItem("Delete", e -> {
			myAuthorController.getModel().getGame().getCurrentLevel().removeSprite(mySprite.getSprite());
		}).getMenu());
		myMenu.getItems().add(new FunctionalMenuItem("Copy", e -> {
			myAuthorController.getModel().getGame().getCurrentLevel().addNewSprite(mySprite.getSprite().clone());
		}).getMenu());

	}

	public ContextMenu getMenu() {
		return myMenu;
	}

	private class FunctionalMenuItem {

		private MenuItem item;

		FunctionalMenuItem(String label, EventHandler<ActionEvent> event) {
			item = new MenuItem(label);
			this.item.setOnAction(event);
		}

		protected MenuItem getMenu() {
			return item;
		}
	}

}
