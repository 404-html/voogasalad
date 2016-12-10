package gameplayer.front_end.gui_generator.button_generator;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class AbstractButton implements IButton {
	
	public Button createButton(String aMessage, int aXPos, int aYPos, 
		EventHandler<? super MouseEvent> aHandler){
		Button newButton = new Button();
		newButton.setOnMouseClicked(aHandler);
		newButton.setTranslateX(aXPos);
		newButton.setTranslateY(aYPos);
		return newButton;
	}

}
