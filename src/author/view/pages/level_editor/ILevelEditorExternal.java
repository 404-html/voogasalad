/**
 * 
 */
package author.view.pages.level_editor;

import game_data.Level;
import javafx.scene.layout.Pane;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface ILevelEditorExternal {
	public Level createLevel();
	
	public Pane getPane();
}
