package gameplayer.front_end.application_scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

public interface INavigationDisplay extends IDisplay {
	
	public void addNavigationMenu(ImageView aImage, String[] aString, @SuppressWarnings("unchecked") EventHandler<ActionEvent> ...aHandlers);

	public void clear();
	
}
