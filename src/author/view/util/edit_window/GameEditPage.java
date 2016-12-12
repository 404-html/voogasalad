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
class GameEditPage extends GameObjectEditPage<Game>{

	ILanguageHolder myLanguageHolder;
	
	/**
	 * @param iGameObjectEditWindowInternal
	 */
	GameEditPage(IGameObjectEditWindowInternal iGameObjectEditWindowInternal, ILanguageHolder aLanguageHolder) {
		super(iGameObjectEditWindowInternal, aLanguageHolder);
		myLanguageHolder = aLanguageHolder;
	}

	/* (non-Javadoc)
	 * @see author.view.util.edit_window.GameObjectEditPage#getGameObjectBox()
	 */
	@Override
	protected GameObjectEditBox<Game> getGameObjectBox(ILanguageHolder aLanguageHolder) {
		return new GameEditBox(aLanguageHolder);
	}

}
