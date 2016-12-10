package author.model.game_observables.draggable_sprite;

import java.io.File;

import author.model.game_observables.draggable_sprite.drag_resize.DragResizeMod;
import author.view.pages.sprite.SpriteEditWindow;
import game_data.Location;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
/**
 * Extension of DraggableSprite. This class defines the functionality of the sprites that are actually placed on 
 * the level editor. 
 * Allows users to click and drag sprites around the level editor, along with other functionality.
 * @author Jordan Frazier
 *
 */
public class ConcreteMovableSprite extends DraggableSprite implements ResizableSprite {

	private double mouseX;
	private double mouseY;
	private Sprite spritePreset;
	private InvalidationListener presetInvalidationListener;

	public ConcreteMovableSprite(Sprite aSpriteInstance, Sprite aSpritePreset) {
		super(aSpriteInstance);
		this.spritePreset = aSpritePreset;
		this.presetInvalidationListener = this.spritePreset == null ? null : initPresetListener(aSpriteInstance, this.spritePreset);
		styleSprite();
		//makeDraggable();
		DragResizeMod resizer = new DragResizeMod(this, this.getDraggableItem(), this.spritePreset, this.presetInvalidationListener,  null);
		resizer.makeResizable(this.getDraggableItem(), null);
		
	}
	
	public void removePresetListener(){
		if (this.spritePreset!=null){
			this.spritePreset.removeListener(presetInvalidationListener);
		}
	}

	private void styleSprite() {
		getDraggableItem().setLayoutX(getSprite().getLocation().getXLocation());
		getDraggableItem().setLayoutY(getSprite().getLocation().getYLocation());
		getDraggableItem().setPrefWidth(getSprite().getWidth());
		getDraggableItem().setPrefHeight(getSprite().getHeight());
		//getDraggableItem().setRotate(getSprite().getLocation().getMyHeading());
	}

	/**
	 * http://stackoverflow.com/questions/27080039/proper-way-to-move-a-javafx8-node-around
	 */
	@Override
	protected void makeDraggable() {
		onMousePressed();
		onMouseDragged();
		onMouseReleased();
	}
	
	@Override
	protected void openPreferences() {
		this.getDraggableItem().setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				if (e.getClickCount() == 2) {
					removePresetListener();
					this.getSprite().setPreset(null);
					new SpriteEditWindow(this.getSprite()).openWindow();
				}
			}
		});
	}

	private InvalidationListener initPresetListener(Sprite instanceSprite, Sprite spritePreset) {
		InvalidationListener invalidationListener = (sprite) -> {
			instanceSprite.setImagePath(spritePreset.getImagePath());
			instanceSprite.setWidth(spritePreset.getWidth());
			instanceSprite.setHeight(spritePreset.getHeight());
			instanceSprite.setLocation(new Location(instanceSprite.getLocation().getXLocation(), instanceSprite.getLocation().getYLocation()));
			spritePreset.getCharacteristics()
					.forEach((characteristic) -> instanceSprite.addCharacteristic(characteristic));
		};
		spritePreset.addListener(invalidationListener);
		return invalidationListener;
	}

	private void onMouseReleased() {
		super.getDraggableItem().setOnMouseReleased(e -> {
			super.getSprite().getLocation().setLocation(super.getDraggableItem().getLayoutX(),
					super.getDraggableItem().getLayoutY());
			super.getSprite().setName(super.getSprite().getName());
		});
	}

	private void onMouseDragged() {
		super.getDraggableItem().setOnMouseDragged(event -> {
			double deltaX = event.getSceneX() - mouseX;
			double deltaY = event.getSceneY() - mouseY;
			super.getDraggableItem().relocate(super.getDraggableItem().getLayoutX() + deltaX,
					super.getDraggableItem().getLayoutY() + deltaY);
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
		});
	}

	private void onMousePressed() {
		super.getDraggableItem().setOnMousePressed(event -> {
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			super.getDraggableItem().toFront();
		});
	}

	@Override
	protected InvalidationListener initListener(Sprite aSprite) {
		InvalidationListener invalidationListener = (sprite) -> {
			this.getImageView().setImage(new Image((new File(aSprite.getImagePath()).toURI().toString())));
			this.getDraggableItem().setPrefWidth(aSprite.getWidth());
			this.getDraggableItem().setPrefHeight(aSprite.getHeight());
			//this.getDraggableItem().setRotate(aSprite.getLocation().getMyHeading());
		};
		return invalidationListener;
	}

}
