package author.model.game_observables.draggable_sprite.context_menu;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.sprite.SpriteEditWindow;
import author.view.util.language_selection.ILanguageUser;
import game_data.Location;
import game_data.Sprite;
import game_data.sprites.Player;
import javafx.beans.Observable;
import javafx.geometry.Orientation;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Concrete implementation of the context menu that opens when a user right
 * clicks on a movable sprite. From here, you can copy / delete / etc sprites on
 * the level editor
 * 
 * @author Jordan Frazier
 *
 */
public class SpriteContextMenu implements ISpriteContextMenu, ILanguageUser {

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
		myMenu.getItems().add(makeStatusItem());
		
		myMenu.getItems().add(new FunctionalMenuItemFactory().create("Delete", e -> {
			myAuthorController.getModel().getGame().getCurrentLevel().removeSprite(mySprite.getSprite());
		}).getItem());
		
		myMenu.getItems().add(new FunctionalMenuItemFactory().create(myAuthorController.getDisplayText("Copy"), e -> {
			Sprite clone = mySprite.getSprite().clone();
			clone.setLocation(
					new Location(clone.getLocation().getXLocation() + 15, clone.getLocation().getYLocation() + 15));
			myAuthorController.getModel().getGame().getCurrentLevel().addNewSprite(clone);
		}).getItem());
		myMenu.getItems().add(new FunctionalMenuItemFactory().create(myAuthorController.getDisplayText("Edit"), e -> {
			mySprite.removePresetListener();
			new SpriteEditWindow(mySprite.getSprite()).openWindow();
		}).getItem());
		if(mySprite.getSprite() instanceof Player) {
			myMenu.getItems().add(new FunctionalMenuItemFactory().create(myAuthorController.getDisplayText("MakeMainPlayer"), e -> {
				myAuthorController.getModel().getGame().getCurrentLevel().setMainPlayer((Player)mySprite.getSprite());
			}).getItem());
		}

	}

	private MenuItem makeStatusItem(){
		CustomMenuItem statusItem = new CustomMenuItem();
		Sprite spr = mySprite.getSprite();
		Pane statusBox = new VBox();
		statusBox.getChildren().addAll(
				new Label(spr.getClass().getSimpleName() + ": " + spr.getName()),
				new Separator(Orientation.HORIZONTAL),
				new Label("Location: " + spr.getLocation()),
				new Label("Size: " + spr.getWidth()  + " x " + spr.getHeight() )
				);
		statusItem.setContent(statusBox);
		statusItem.setHideOnClick(false);
		
		return statusItem;
	}
	
	@Override
	public ContextMenu getMenu() {
		return myMenu;
	}

	@Override
	public void invalidated(Observable arg0) {
		myMenu.getItems().clear();
		createContextMenu();
	}

}
