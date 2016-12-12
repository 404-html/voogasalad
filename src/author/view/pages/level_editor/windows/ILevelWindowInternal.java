/**
 * 
 */
package author.view.pages.level_editor.windows;

import author.model.game_observables.draggable_sprite.DraggableSprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SetProperty;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface ILevelWindowInternal extends ILevelEditorWindowInternal{

	public IntegerProperty getHorizontalPanes();
	
	public IntegerProperty getVerticalPanes();
	
	public SetProperty<DraggableSprite> getSelectedSprites();
	
	public DraggableSprite getSelectedSprite();
	
	public BooleanProperty getRandomProperty();

}
