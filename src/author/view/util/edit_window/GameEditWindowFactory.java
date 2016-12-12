/**
 * 
 */
package author.view.util.edit_window;

import author.view.util.language_selection.ILanguageHolder;
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
	
	public IGameObjectEditWindowExternal<Game> create(ILanguageHolder aLanguageHolder){
		return new GameEditWindow(aLanguageHolder);
	}

}
