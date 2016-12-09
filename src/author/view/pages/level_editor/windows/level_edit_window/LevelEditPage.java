/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import author.view.util.authoring_buttons.ButtonFactory;
import author.view.util.facades.ToolBarBuilder;
import game_data.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class LevelEditPage {

	private ILevelEditWindowInternal iLevelEditInternal;
	private VBox container;
	private LevelEditBox levelEditBox;
	private ToolBarBuilder toolBarBuilder;
	private Level level;
	
	
	LevelEditPage(ILevelEditWindowInternal iLevelEditInternal) {
		this.iLevelEditInternal = iLevelEditInternal;
		this.levelEditBox = new LevelEditBox();
		initToolBarBuilder();
		initPane();
	}
	
	LevelEditPage(Level aLevel, ILevelEditWindowInternal iLevelEditInternal){
		this.iLevelEditInternal = iLevelEditInternal;
		this.levelEditBox = new LevelEditBox(aLevel);
		initToolBarBuilder();
		initPane();
	}
	
	private void initToolBarBuilder(){
		this.toolBarBuilder = new ToolBarBuilder();
		this.toolBarBuilder.addBurst(new ButtonFactory().createButton("Save and Close", e -> saveAndClose(this.levelEditBox.getLevel())).getButton());
		this.toolBarBuilder.addBurst(new ButtonFactory().createButton("Cancel", e -> cancel()).getButton());
	}
	
	private void initPane(){
		this.container = new VBox();
		this.container.getChildren().addAll(this.toolBarBuilder.getToolBar(), this.levelEditBox.getEditBox());
	}
	
	public Pane getPane(){
		return this.container;
	}
	
	private void saveAndClose(Level aLevel){
		if (aLevel != null && !this.levelEditBox.getTextField().getText().isEmpty()){
			this.level = aLevel;
			this.iLevelEditInternal.close();
		} else {
			displayEmptyLevelNameDialog();
		}
	}
	
	private void cancel(){
		this.iLevelEditInternal.close();
	}
	
	private void displayEmptyLevelNameDialog() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("VoogaSalad Game");
		alert.setHeaderText("Invalid Level Name");
		alert.setContentText("Name must contain a non-empty set of valid characters");
		alert.showAndWait();
	}
	
	public Level getLevel(){
		return this.level;
	}
	
	

}
