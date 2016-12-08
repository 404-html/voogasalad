/**
 * 
 */
package author.view.util.undo;

import game_data.GameObject;
import game_data.Sprite;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class GameChangeEvent {

	private Sprite activeSprite;
	private Sprite restoreSprite;
	/**
	 * 
	 */
	public GameChangeEvent(Sprite activeSprite) {
		this.activeSprite = activeSprite;
		this.restoreSprite = activeSprite.clone();
	}

}
