package author.model.game_observables.draggable_sprite;

import java.io.File;
import author.view.pages.sprite.SpriteEditWindow;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;

/**
 * This abstract class is the framework for making a sprite draggable
 * 
 * @author Jordan Frazier
 *
 */
public abstract class DraggableSprite {

	private HBox draggableItem;
	private ImageView myImageView;
	private Sprite mySprite;
	private InvalidationListener invalidationListener;

	// These define the size of the ghost image that follows the mouse when
	// dragging
	public static final int DRAG_IMAGE_WIDTH = 40;
	public static final int DRAG_IMAGE_HEIGHT = 40;

	public DraggableSprite(Sprite aSprite) {
		mySprite = aSprite;
		draggableItem = new HBox();
		myImageView = new ImageView(new Image((new File(aSprite.getMyImagePath()).toURI().toString())));
		addImageToContainer();
		setListener(aSprite);
		makeDraggable();
		openPreferences();
		setOnMouseHover();
	}

	private void addImageToContainer() {
		draggableItem.getChildren().add(myImageView);
		draggableItem.setPrefHeight(DRAG_IMAGE_HEIGHT);
		draggableItem.setPrefWidth(DRAG_IMAGE_WIDTH);
		myImageView.fitHeightProperty().bind(draggableItem.prefHeightProperty());
		myImageView.fitWidthProperty().bind(draggableItem.prefWidthProperty());
	}

	public void removeListener() {
		this.mySprite.removeListener(this.invalidationListener);
	}

	private void setListener(Sprite aSprite) {
		this.invalidationListener = initListener(aSprite);
		aSprite.addListener(this.invalidationListener);
	}

	protected InvalidationListener initListener(Sprite aSprite) {
		InvalidationListener invalidationListener = (sprite) -> {
			this.getImageView().setImage(new Image( (new File(aSprite.getMyImagePath()).toURI().toString())));
		};
		return invalidationListener;
	}

	protected void openPreferences() {
		draggableItem.setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				if (e.getClickCount() == 2) {
					new SpriteEditWindow(mySprite).openWindow();
				}
			}
		});
	}

	protected abstract void makeDraggable();
	
	private void setOnMouseHover() {
		draggableItem.setOnMouseEntered(e -> {
			 String style_inner = "-fx-border-color: red;"
		              + "-fx-border-width: 1;"
		              + "-fx-border-style: dotted;";
		      draggableItem.setStyle(style_inner);
		      displayNameOnHover();
		});
		draggableItem.setOnMouseExited(e -> {
			 String style_inner = "";
		      draggableItem.setStyle(style_inner);
		});
	}

	private void displayNameOnHover() {
		Tooltip tip = new Tooltip(mySprite.getName());
		Tooltip.install(draggableItem, tip);
	}
	
	public HBox getDraggableItem() {
		return draggableItem;
	}

	public Sprite getSprite() {
		return mySprite;
	}

	public ImageView getImageView() {
		return myImageView;
	}

	public void setImageView(ImageView imageView) {
		this.myImageView = imageView;
		makeDraggable();
	}
}
