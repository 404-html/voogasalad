/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;

import author.controller.IAuthorController;
import author.view.pages.level_editor.windows.ILevelEditorWindowInternal;
import game_data.Sprite;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class LevelWindowPaneFactory {
	@SuppressWarnings("unused")
	private ILevelEditorWindowInternal myLevelEditorWindowInternal;
	private LevelWindowPane levelWindowPane;
	private IAuthorController authorController;
	
	public LevelWindowPaneFactory(ILevelEditorWindowInternal levelEditorWindowInternal, IAuthorController authorController) {
		this.myLevelEditorWindowInternal = levelEditorWindowInternal;
		this.authorController = authorController;
	}
	
	public ILevelWindowPane create(){
		this.levelWindowPane = new LevelWindowPane();
		acceptDraggableSprites();
		
		this.levelWindowPane.getPane().setOnDragEntered(e -> {
			Dragboard dragboard = e.getDragboard();
			Sprite sprite = findSprite(dragboard.getString());
			this.levelWindowPane.updateGrid(sprite.getMyWidth(), sprite.getMyHeight());
		});
		this.levelWindowPane.getPane().setOnDragExited(e -> {
			this.levelWindowPane.removeGrid();
		});
		return this.levelWindowPane;
	}
	
	private void acceptDraggableSprites() {

		this.levelWindowPane.getPane().setOnDragDropped((DragEvent event) -> {
			if (checkGameHasLevel()) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					Sprite sprite = findSprite(db.getString());
					Sprite clone = sprite.clone();
					clone.getMyLocation().setLocation(levelWindowPane.adjustX((int) event.getX()), levelWindowPane.adjustY((int) event.getY()));
					this.authorController.getModel().getGame().getCurrentLevel().addNewSprite(clone);		
				}
				this.levelWindowPane.removeGrid();
				event.setDropCompleted(success);
				event.consume();
			}
		});
		
		this.levelWindowPane.getPane().setOnDragOver((DragEvent event) -> {
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
