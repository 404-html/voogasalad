package author.model.game_observables.draggable_sprite;

import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class ConcreteMovableSprite extends DraggableSprite {

	private double mouseX;
	private double mouseY;

	public ConcreteMovableSprite(Sprite aSprite) {
		super(aSprite);
		styleSprite();
	}

	private void styleSprite() {
		getDraggableItem().setLayoutX(getSprite().getMyLocation().getXLocation());
		getDraggableItem().setLayoutY(getSprite().getMyLocation().getYLocation());
		getDraggableItem().setRotate(getSprite().getMyLocation().getMyHeading());
		getDraggableItem().setPrefWidth(getSprite().getMyWidth());
		getDraggableItem().setPrefHeight(getSprite().getMyHeight());
		// draggableSprite.getImageView().fitWidthProperty().bind(draggableSprite.getDraggableItem().prefWidthProperty());
		// draggableSprite.getImageView().fitHeightProperty().bind(draggableSprite.getDraggableItem().prefHeightProperty());
//		getImageView().setFitHeight(getDraggableItem().getPrefHeight());
//		getImageView().setFitWidth(getDraggableItem().getPrefWidth());
		getImageView().setRotate(getSprite().getMyLocation().getMyHeading());
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
