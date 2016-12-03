/**
 * 
 */
package author.view.util.edit_window;

import game_data.Game;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class GameEditPage extends GameObjectEditPage<Game>{


	/**
	 * @param iGameObjectEditWindowInternal
	 */
	GameEditPage(IGameObjectEditWindowInternal iGameObjectEditWindowInternal) {
		super(iGameObjectEditWindowInternal);
	}

	/* (non-Javadoc)
	 * @see author.view.util.edit_window.GameObjectEditPage#getGameObjectBox()
	 */
	@Override
	protected GameObjectEditBox<Game> getGameObjectBox() {
		return new GameEditBox();
	}

}
