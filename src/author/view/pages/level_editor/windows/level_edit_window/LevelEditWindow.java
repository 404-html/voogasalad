/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import java.io.File;

import author.view.util.language_selection.ILanguageHolder;
import game_data.Level;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class LevelEditWindow implements ILevelEditWindowInternal, ILevelEditWindowExternal{
	private Stage stage;
	private LevelEditPage levelEditPage;
	private ILanguageHolder myLanguageHolder;
	
	LevelEditWindow(ILanguageHolder aLanguageHolder){
		myLanguageHolder = aLanguageHolder;
		this.levelEditPage = new LevelEditPage((ILevelEditWindowInternal) this, myLanguageHolder);
		initializeWindow();
	}
	
	LevelEditWindow(Level aLevel, ILanguageHolder aLanguageHolder){
		this.levelEditPage = new LevelEditPage(aLevel, (ILevelEditWindowInternal) this, aLanguageHolder);
		initializeWindow();
	}
	
	@Override
	public Level getLevel(){
		return this.levelEditPage.getLevel();
	}
	
	private void initializeWindow(){
		this.stage = new Stage();
		this.stage.setTitle(myLanguageHolder.getDisplayText("NewLevel"));
		this.stage.setScene(new Scene(this.levelEditPage.getPane()));
		this.stage.getScene().getStylesheets().add(getStyleSheet());
		this.stage.setResizable(false);
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.showAndWait();
	}

	@Override
	public void close() {
		this.stage.close();
	}
	
	private String getStyleSheet(){
		File css = new File(myLanguageHolder.getPathString("AuthorCSSStyle"));
		return css.toURI().toString();
	}
	
}
