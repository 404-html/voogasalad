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
class GameEditBox extends GameObjectEditBox<Game>{

	GameEditBox(ILanguageHolder aLanguageHolder) {
		super(aLanguageHolder);
	}

	/* (non-Javadoc)
	 * @see author.view.util.edit_window.GameObjectEditBox#getResult()
	 */
	@Override
	public Game getResult() {
		try {
			return new Game(this.getName());
		}catch(Exception e){
			return null;
		}
	}



}
