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
class GameEditWindow extends GameObjectEditWindow<Game>{
	
	private ILanguageHolder myLanguageHolder;

	GameEditWindow(ILanguageHolder aLanguageHolder) {
		super(aLanguageHolder);
		myLanguageHolder = aLanguageHolder;
	}

	/* (non-Javadoc)
	 * @see author.view.util.edit_window.GameObjectEditWindow#getGameObjectLevelPage()
	 */
	@Override
	protected GameObjectEditPage<Game> getGameObjectLevelPage(ILanguageHolder aLanguageHolder) {
		return new GameEditPage((IGameObjectEditWindowInternal) this, aLanguageHolder);
	}

}
