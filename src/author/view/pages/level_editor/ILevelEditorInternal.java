/**
 * 
 */
package author.view.pages.level_editor;

import author.view.pages.level_editor.windows.level_edit_window.ILevelEditorWindowExternal;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface ILevelEditorInternal {

	public ILevelEditorWindowExternal getMyLevelWindow();

	public ILevelEditorWindowExternal getMyEntityWindow();

	public ILevelEditorWindowExternal getMySelectionWindow();
	
}
