/**
 * 
 */
package author.view.util.undo;

import game_data.Level;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class RevertManagerFactory {

	/**
	 * 
	 */
	public RevertManagerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public IRevertManager create(Level aLevel){
		return new RevertManager(aLevel);
	}

}
