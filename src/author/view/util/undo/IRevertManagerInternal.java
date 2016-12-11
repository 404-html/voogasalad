/**
 * 
 */
package author.view.util.undo;

import game_data.Sprite;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface IRevertManagerInternal {

	public void addHistory(GameChangeEvent gameChangeEvent);
	
	public void addFuture(GameChangeEvent gameChangeEvent);

}
