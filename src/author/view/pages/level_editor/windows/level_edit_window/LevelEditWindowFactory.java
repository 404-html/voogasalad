/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import game_data.Level;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class LevelEditWindowFactory {

	/**
	 * 
	 */
	public LevelEditWindowFactory() {
		// Do Nothing
	}
	
	public ILevelEditWindowExternal create(){
		return new LevelEditWindow();
	}
	
	public ILevelEditWindowExternal create(Level aLevel){
		return new LevelEditWindow(aLevel);
	}

}
