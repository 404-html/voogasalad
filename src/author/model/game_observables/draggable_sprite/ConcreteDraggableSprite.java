package author.model.game_observables.draggable_sprite;

import game_data.Sprite;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;

/**
 * The concrete implementation that populates the @EntityWindow. 
 * Makes sprites draggable from the window and populates the dragboard and clipboard with the ID. 
 * @author Jordan Frazier
 *
 */
public class ConcreteDraggableSprite extends DraggableSprite {

	public ConcreteDraggableSprite(Sprite aSprite) {
		super(aSprite);
	}

	/**
	 * https://docs.oracle.com/javase/8/javafx/events-tutorial/paper-doll.htm#CBHHBAJI
	 */
	@Override
	protected void makeDraggable() {
		getDraggableItem().setOnDragDetected((MouseEvent event) -> {
			getSprite().setId(this.getClass().getSimpleName() + System.currentTimeMillis());
			Dragboard db = getImageView().startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			// Store the node ID in order to know what is dragged.
			content.putString(getSprite().getId());
			db.setContent(content);
			db.setDragView(new Text(getSprite().getName()).snapshot(null, null), event.getX(), event.getY());
			// db.setDragView(new Image(mySprite.getMyImagePath(),
			// DRAG_IMAGE_WIDTH, DRAG_IMAGE_HEIGHT, false, false));
			event.consume();
		});
	}

	@Override
	public void removePresetListener() {
		// TODO Auto-generated method stub
		
	}

}