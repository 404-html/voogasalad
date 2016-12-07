/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Level;
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
		if (aLevel != null){
			this.level = aLevel;
			this.iLevelEditInternal.close();
		}
	}
	
	private void cancel(){
		this.iLevelEditInternal.close();
	}
	public Level getLevel(){
		return this.level;
	}

}
