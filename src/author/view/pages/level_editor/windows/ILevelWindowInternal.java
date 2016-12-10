/**
 * 
 */
package author.view.pages.level_editor.windows;

import java.util.Set;

import author.model.game_observables.draggable_sprite.DraggableSprite;
import javafx.beans.property.IntegerProperty;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface ILevelWindowInternal extends ILevelEditorWindowInternal{

	public IntegerProperty getHorizontalPanes();
	
	public IntegerProperty getVerticalPanes();
	
	public Set<DraggableSprite> getSelectedSprites();
	
	public DraggableSprite getSelectedSprite();

}
