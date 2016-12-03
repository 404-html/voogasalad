/**
 * 
 */
package author.view.util.edit_window;

import game_data.Game;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class GameEditBox extends GameObjectEditBox<Game>{

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
