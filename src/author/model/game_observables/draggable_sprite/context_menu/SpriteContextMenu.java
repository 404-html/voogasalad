package author.model.game_observables.draggable_sprite.context_menu;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class SpriteContextMenu implements ISpriteContextMenu {

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
		myMenu.getItems().add(new FunctionalMenuItemFactory().create("Delete", e -> {
			myAuthorController.getModel().getGame().getCurrentLevel().removeSprite(mySprite.getSprite());
		}).getItem());
		myMenu.getItems().add(new FunctionalMenuItemFactory().create("Copy", e -> {
			myAuthorController.getModel().getGame().getCurrentLevel().addNewSprite(mySprite.getSprite().clone());
		}).getItem());

	}

	@Override
	public ContextMenu getMenu() {
		return myMenu;
	}

}
