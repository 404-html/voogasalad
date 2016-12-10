package author.model.game_observables.draggable_sprite;

import game_data.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public interface ResizableSprite {

	HBox getDraggableItem();
	
	ImageView getImageView();
	
	Sprite getSprite();
	
}
