package author.model.game_observables.draggable_sprite.context_menu;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.sprite.SpriteEditWindow;
import game_data.Location;
import game_data.Sprite;
import javafx.scene.control.ContextMenu;

/**
 * Concrete implementation of the context menu that opens when a user right
 * clicks on a movable sprite. From here, you can copy / delete / etc sprites on
 * the level editor
 * 
 * @author Jordan Frazier
 *
 */
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
			Sprite clone = mySprite.getSprite().clone();
			clone.setLocation(
					new Location(clone.getLocation().getXLocation() + 15, clone.getLocation().getYLocation() + 15));
			myAuthorController.getModel().getGame().getCurrentLevel().addNewSprite(clone);
		}).getItem());
		myMenu.getItems().add(new FunctionalMenuItemFactory().create("Edit", e -> {
			mySprite.removePresetListener();
			new SpriteEditWindow(mySprite.getSprite()).openWindow();
		}).getItem());

	}

	@Override
	public ContextMenu getMenu() {
		return myMenu;
	}

}
