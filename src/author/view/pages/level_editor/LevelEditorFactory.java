/**
 * 
 */
package author.view.pages.level_editor;

import author.controller.IAuthorController;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class LevelEditorFactory {

	/**
	 * Factory for creating Level Editor
	 */
	public LevelEditorFactory() {
		// Do Nothing
	}
	
	public ILevelEditorExternal create(IAuthorController aAuthorController){
		return new LevelEditor(aAuthorController);
	}

}
