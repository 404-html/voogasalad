/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import author.view.util.authoring_buttons.ButtonFactory;
import author.view.util.language_selection.ILanguageHolder;
import game_data.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.facades.ToolBarBuilder;

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
	private ILanguageHolder myLanguageHolder;
	
	LevelEditPage(ILevelEditWindowInternal iLevelEditInternal, ILanguageHolder aLanguageHolder) {
		myLanguageHolder = aLanguageHolder;
		this.iLevelEditInternal = iLevelEditInternal;
		this.levelEditBox = new LevelEditBox(aLanguageHolder);
		initToolBarBuilder();
		initPane();
	}
	
	LevelEditPage(Level aLevel, ILevelEditWindowInternal iLevelEditInternal, ILanguageHolder aLanguageHolder){
		this.iLevelEditInternal = iLevelEditInternal;
		this.levelEditBox = new LevelEditBox(aLevel, aLanguageHolder);
		initToolBarBuilder();
		initPane();
	}
	
	private void initToolBarBuilder(){
		this.toolBarBuilder = new ToolBarBuilder();
		this.toolBarBuilder.addBurst(new ButtonFactory().createButton("SaveAndClose", e -> saveAndClose(this.levelEditBox.getLevel()), myLanguageHolder).getButton());
		this.toolBarBuilder.addBurst(new ButtonFactory().createButton("Cancel", e -> cancel(), myLanguageHolder).getButton());
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
