/**
 * 
 */
package author.view.util.edit_window;

import java.io.File;

import author.view.util.language_selection.ILanguageHolder;
import game_data.GameObject;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
abstract class GameObjectEditWindow<T extends GameObject> implements IGameObjectEditWindowInternal, IGameObjectEditWindowExternal<T>{

	private Stage stage;
	private GameObjectEditPage<T> gameObjectEditPage;
	ILanguageHolder myLanguageHolder;
	
	GameObjectEditWindow(ILanguageHolder aLanguageHolder){
		myLanguageHolder = aLanguageHolder;
		this.gameObjectEditPage = getGameObjectLevelPage(myLanguageHolder);
		initializeWindow();
	}
	
	protected abstract GameObjectEditPage<T> getGameObjectLevelPage(ILanguageHolder aLanguageHolder);
	
	@Override
	public T getResult(){
		return this.gameObjectEditPage.getResult();
	}
	
	private void initializeWindow(){
		this.stage = new Stage();
		this.stage.setTitle(myLanguageHolder.getDisplayText("NewGame"));
		this.stage.setScene(new Scene(this.gameObjectEditPage.getPane()));
		this.stage.getScene().getStylesheets().add(getStyleSheet());
		this.stage.setResizable(false);
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
