/**
 * 
 */
package author.view.util.edit_window;

import game_data.Game;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class GameEditWindowFactory {

	/**
	 * 
	 */
	public GameEditWindowFactory() {
		// Do Nothing
	}
	
	public IGameObjectEditWindowExternal<Game> create(){
		return new GameEditWindow();
	}

}
