/**
 * 
 */
package author.view.util.edit_window;

import game_data.Game;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class GameEditWindow extends GameObjectEditWindow<Game>{

	GameEditWindow() {
		super();
	}

	/* (non-Javadoc)
	 * @see author.view.util.edit_window.GameObjectEditWindow#getGameObjectLevelPage()
	 */
	@Override
	protected GameObjectEditPage<Game> getGameObjectLevelPage() {
		return new GameEditPage((IGameObjectEditWindowInternal) this);
	}

}
