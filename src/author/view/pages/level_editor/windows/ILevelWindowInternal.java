/**
 * 
 */
package author.view.pages.level_editor.windows;

import java.util.Set;

import game_data.Level;
import javafx.beans.property.IntegerProperty;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface ILevelWindowInternal extends ILevelEditorWindowInternal{

	public IntegerProperty getHorizontalPanes();
	
	public IntegerProperty getVerticalPanes();
	
	public Set<Level> getSelectedSprites();
}
