package author.model.game_observables.draggable_sprite;

import java.io.File;

import author.view.pages.sprite.SpriteEditWindow;
import game_data.Location;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Line;

public class ConcreteMovableSprite extends DraggableSprite {

	private double mouseX;
	private double mouseY;
	private Sprite spritePreset;
	private InvalidationListener presetInvalidationListener;

	public ConcreteMovableSprite(Sprite aSpriteInstance, Sprite aSpritePreset) {
		super(aSpriteInstance);
		this.spritePreset = aSpritePreset;
		this.presetInvalidationListener = this.spritePreset == null ? null : initPresetListener(aSpriteInstance, this.spritePreset);
		styleSprite();
	}
	
	public void removePresetListener(){
		this.spritePreset.removeListener(presetInvalidationListener);
	}

	private void styleSprite() {
		getDraggableItem().setLayoutX(getSprite().getMyLocation().getXLocation());
		getDraggableItem().setLayoutY(getSprite().getMyLocation().getYLocation());
		getDraggableItem().setPrefWidth(getSprite().getMyWidth());
		getDraggableItem().setPrefHeight(getSprite().getMyHeight());
		getDraggableItem().setRotate(getSprite().getMyLocation().getMyHeading());
	}

	/**
	 * http://stackoverflow.com/questions/27080039/proper-way-to-move-a-javafx8-node-around
	 */
	@Override
	protected void makeDraggable() {

		super.getDraggableItem().setOnMouseDragOver(event -> {
			super.getDraggableItem();
			// Set boundaries to visible
			Line line = new Line(super.getImageView().getLayoutX(), super.getImageView().getLayoutY(),
					super.getImageView().getLayoutX() + super.getImageView().getFitWidth(),
					super.getImageView().getLayoutY() + super.getImageView().getFitHeight());

		});

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
			instanceSprite.setMyImagePath(spritePreset.getMyImagePath());
			instanceSprite.setMyWidth(spritePreset.getMyWidth());
			instanceSprite.setMyHeight(spritePreset.getMyHeight());
			instanceSprite.setMyLocation(new Location(instanceSprite.getMyLocation().getXLocation(), instanceSprite.getMyLocation().getYLocation(), spritePreset.getMyLocation().getMyHeading()));
			spritePreset.getCharacteristics()
					.forEach((characteristic) -> instanceSprite.addCharacteristic(characteristic));
		};
		spritePreset.addListener(invalidationListener);
		return invalidationListener;
	}

	private void onMouseReleased() {
		super.getDraggableItem().setOnMouseReleased(e -> {
			super.getSprite().getMyLocation().setLocation(super.getDraggableItem().getLayoutX(),
					super.getDraggableItem().getLayoutY());
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
		});
	}

	@Override
	protected InvalidationListener initListener(Sprite aSprite) {
		InvalidationListener invalidationListener = (sprite) -> {
			this.getImageView().setImage(new Image((new File(aSprite.getMyImagePath()).toURI().toString())));
			this.getDraggableItem().setPrefWidth(aSprite.getMyWidth());
			this.getDraggableItem().setPrefHeight(aSprite.getMyHeight());
			this.getDraggableItem().setRotate(aSprite.getMyLocation().getMyHeading());
		};
		return invalidationListener;
	}

}
