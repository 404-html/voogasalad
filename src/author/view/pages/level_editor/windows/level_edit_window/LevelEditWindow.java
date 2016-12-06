/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import java.io.File;

import game_data.Level;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class LevelEditWindow implements ILevelEditWindowInternal, ILevelEditWindowExternal{
	private Stage stage;
	private static final String TITLE = "New Level";
	private LevelEditPage levelEditPage;
	private static final String STYLESHEET = "data/gui/author-style.css";

	
	LevelEditWindow(){
		this.levelEditPage = new LevelEditPage((ILevelEditWindowInternal) this);
		initializeWindow();
	}
	
	LevelEditWindow(Level aLevel){
		this.levelEditPage = new LevelEditPage(aLevel, (ILevelEditWindowInternal) this);
		initializeWindow();
	}
	
	@Override
	public Level getLevel(){
		return this.levelEditPage.getLevel();
	}
	
	private void initializeWindow(){
		this.stage = new Stage();
		this.stage.setTitle(TITLE);
		this.stage.setScene(new Scene(this.levelEditPage.getPane()));
		this.stage.getScene().getStylesheets().add(this.getClass().getResource(STYLESHEET).toExternalForm());
		this.stage.setResizable(false);
		this.stage.showAndWait();
	}

	@Override
	public void close() {
		this.stage.close();
	}
	
	private String getStyleSheet(){
		File css = new File(STYLESHEET);
		return css.toURI().toString();
	}
	
}
