/**
 * 
 */
package author.view.util.edit_window;

import game_data.GameObject;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
abstract class GameObjectEditWindow<T extends GameObject> implements IGameObjectEditWindowInternal, IGameObjectEditWindowExternal<T>{

	private Stage stage;
	private static final String TITLE = "New Game";
	private GameObjectEditPage<T> gameObjectEditPage;
	
	GameObjectEditWindow(){
		this.gameObjectEditPage = getGameObjectLevelPage();
		initializeWindow();
	}
	
	protected abstract GameObjectEditPage<T> getGameObjectLevelPage();
	
	@Override
	public T getResult(){
		return this.gameObjectEditPage.getResult();
	}
	
	private void initializeWindow(){
		this.stage = new Stage();
		this.stage.setTitle(TITLE);
		this.stage.setScene(new Scene(this.gameObjectEditPage.getPane()));
		this.stage.setResizable(false);
		this.stage.showAndWait();
	}

	@Override
	public void close() {
		this.stage.close();
	}

}