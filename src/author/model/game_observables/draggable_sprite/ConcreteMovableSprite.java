package author.model.game_observables.draggable_sprite;

import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ConcreteMovableSprite extends DraggableSprite {

	private double mouseX;
	private double mouseY;

	public ConcreteMovableSprite(Sprite aSprite) {
		super(aSprite);
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
			this.getImageView().setImage(new Image(aSprite.getMyImagePath()));
			this.getDraggableItem().setPrefWidth(aSprite.getMyWidth());
			this.getDraggableItem().setPrefHeight(aSprite.getMyHeight());
		};
		return invalidationListener;
	}

}
