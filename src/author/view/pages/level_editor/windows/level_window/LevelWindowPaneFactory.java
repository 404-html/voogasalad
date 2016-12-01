/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.level_editor.windows.ILevelEditorWindowInternal;
import game_data.Sprite;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class LevelWindowPaneFactory {
	@SuppressWarnings("unused")
	private ILevelEditorWindowInternal myLevelEditorWindowInternal;
	private Pane levelPane;
	private IAuthorController authorController;
	
	public LevelWindowPaneFactory(ILevelEditorWindowInternal levelEditorWindowInternal, IAuthorController authorController) {
		this.myLevelEditorWindowInternal = levelEditorWindowInternal;
		this.authorController = authorController;
	}
	
	public Pane create(){
		this.levelPane = new Pane();
		
		acceptDraggableSprites();
		
		this.levelPane.setOnDragEntered(e -> {
			//TODO: Set on drag entered
		});
		return this.levelPane;
	}
	
	private void acceptDraggableSprites() {

		levelPane.setOnDragDropped((DragEvent event) -> {
			if (checkGameHasLevel()) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					Sprite sprite = findSprite(db.getString());

					Sprite clone = sprite.clone();
					clone.getMyLocation().setLocation(event.getX(), event.getY());
					this.authorController.getModel().getGame().getCurrentLevel().addNewSprite(clone);
					DraggableSprite newSprite;
					try {
						newSprite = new ConcreteMovableSprite(clone);
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						throw new NullPointerException();
					}

					ImageView image = newSprite.getImageView();
					if (image != null) {
						image.setLayoutX(event.getX());
						image.setLayoutY(event.getY());
						success = true;
					}
					
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

		this.levelPane.setOnDragOver((DragEvent event) -> {
			if (event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
	}
	
	private boolean checkGameHasLevel() {
		if (this.authorController.getModel().getGame().getCurrentLevel() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Level");
			alert.setHeaderText("No Level created yet.");
			alert.setContentText("Select New -> New Level to create a new Level before dragging sprites");
			alert.showAndWait();
			return false;
		}
		return true;
	}
	
	
	private Sprite findSprite(String nodeId) {
		for (Sprite s : this.authorController.getModel().getGame().getPresets()) {
			if (nodeId.equals(s.getId())) {
				return s;
			}
		}
		return null;
	}
	
	

}
