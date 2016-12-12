/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;

import author.controller.IAuthorController;
import author.view.pages.level_editor.windows.ILevelEditorWindowInternal;
import author.view.pages.level_editor.windows.ILevelWindowInternal;
import game_data.LevelSetter;
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
	private LevelWindowPane levelWindowPane;
	private IAuthorController authorController;
	private ILevelWindowInternal levelWindowInternal;
	
	public LevelWindowPaneFactory(ILevelWindowInternal levelWindowInternal, IAuthorController authorController) {
		this.levelWindowInternal = levelWindowInternal;
		this.authorController = authorController;
	}
	
	public ILevelWindowPane create(){
		this.levelWindowPane = new LevelWindowPane();
		this.levelWindowPane.getPane().setFocusTraversable(true);
		acceptDraggableSprites();
		acceptCopiedSprites();
		manageGridDisplay();
		
		return this.levelWindowPane;
	}
	
	private void manageGridDisplay(){
		this.levelWindowPane.getPane().setOnDragEntered(e -> {
			Dragboard dragboard = e.getDragboard();
			Sprite sprite = findSprite(dragboard.getString());
			this.levelWindowPane.updateGrid(sprite.getWidth(), sprite.getHeight());
		});
		this.levelWindowPane.getPane().setOnMouseExited(e -> {
			this.levelWindowPane.removeGrid();
		});
		
	}
	
	private void acceptCopiedSprites(){

		this.levelWindowPane.getPane().setOnMouseClicked((event) ->{
			this.levelWindowPane.getPane().requestFocus();
			if (this.levelWindowInternal.getSelectedSprite() != null && event.isControlDown()){
				addSprite(this.levelWindowInternal.getSelectedSprite().getSprite(), event.getX(), event.getY());
			}
		});
		this.levelWindowPane.getPane().setOnMouseEntered((event)->{
			this.levelWindowPane.getPane().requestFocus();
		});
		this.levelWindowPane.getPane().setOnKeyReleased((event) -> {
			if (!event.isControlDown()){
				this.levelWindowPane.removeGrid();
			}
		});
	}
	
	private void acceptDraggableSprites() {

		this.levelWindowPane.getPane().setOnDragDropped((DragEvent event) -> {
			if (checkGameHasLevel()) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					Sprite sprite = findSprite(db.getString());
					addSprite(sprite, event.getX(), event.getY());		
				}
				this.levelWindowPane.removeGrid();
				event.setDropCompleted(success);
			}
		});
		
		this.levelWindowPane.getPane().setOnDragOver((DragEvent event) -> {
			if (event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
		});
	}
	
	private void addSprite(Sprite aSprite, double xPos, double yPos){
		addSprite(aSprite, (int) xPos, (int) yPos);
	}
	
	private void addSprite(Sprite aSprite, int xPos, int yPos){
		Sprite clone = aSprite.clone();
		if(clone instanceof LevelSetter) 
			((LevelSetter) clone).setLevel(
					this.authorController.getModel().getGame().getCurrentLevel());
			
		clone.getLocation().setLocation(levelWindowPane.adjustX(xPos), levelWindowPane.adjustY(yPos));
		this.authorController.getModel().getGame().getCurrentLevel().addNewSprite(clone);
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
