package author.model.game_observables.draggable_sprite;

import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * This abstract class is the framework for making a sprite draggable
 * 
 * @author Jordan Frazier
 *
 */
public abstract class DraggableSprite {

	private ImageView myImageView;
	private Sprite mySprite;
	private InvalidationListener invalidationListener;

	// These define the size of the ghost image that follows the mouse when
	// dragging
	public static final int DRAG_IMAGE_WIDTH = 40;
	public static final int DRAG_IMAGE_HEIGHT = 40;

	public DraggableSprite(Sprite aSprite) {
		mySprite = aSprite;
		myImageView = new ImageView(new Image(aSprite.getMyImagePath()));
		myImageView.setFitHeight(DRAG_IMAGE_WIDTH);
		myImageView.setFitWidth(DRAG_IMAGE_HEIGHT);
		setListener(aSprite);
		makeDraggable();
		openPreferences();
	}
	
	public void removeListener(){
		this.mySprite.removeListener(this.invalidationListener);
	}
	
	private void setListener(Sprite aSprite){
		this.invalidationListener = initListener(aSprite);
		aSprite.addListener(this.invalidationListener);
	}
	
	protected InvalidationListener initListener(Sprite aSprite){
		InvalidationListener invalidationListener = (sprite) -> {
			this.getImageView().setImage(new Image(aSprite.getMyImagePath()));
		};
		return invalidationListener;
	}
	

	private void openPreferences() {
		myImageView.setOnMouseClicked(e -> {
			if(e.getButton().equals(MouseButton.PRIMARY)){
	            if(e.getClickCount() == 2){
	            	// TODO: George(vooga) - Open up the preferences editor
	            }
	        }
		});
	}

	/**
	 * https://docs.oracle.com/javase/8/javafx/events-tutorial/paper-doll.htm#CBHHBAJI
	 */
	protected void makeDraggable() {

		myImageView.setOnDragDetected((MouseEvent event) -> {
			mySprite.setId(this.getClass().getSimpleName() + System.currentTimeMillis());
			Dragboard db = myImageView.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			// Store the node ID in order to know what is dragged.
			content.putString(mySprite.getId());
			db.setContent(content);
			db.setDragView(new Image(mySprite.getMyImagePath(), DRAG_IMAGE_WIDTH, DRAG_IMAGE_HEIGHT, false, false));
			event.consume();
		});
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
