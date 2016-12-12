/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;

import author.view.util.language_selection.ILanguageHolder;
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
	
//	public ILevelEditWindowExternal create(){
//		return new LevelEditWindow();
//	}
	
	public ILevelEditWindowExternal create(ILanguageHolder aLanguageHolder){
		return new LevelEditWindow(aLanguageHolder);
	}
	
	public ILevelEditWindowExternal create(Level aLevel, ILanguageHolder aLanguageHolder){
		return new LevelEditWindow(aLevel, aLanguageHolder);
	}

}
