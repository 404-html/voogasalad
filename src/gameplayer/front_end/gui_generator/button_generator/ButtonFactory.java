package gameplayer.front_end.gui_generator.button_generator;

import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonFactory {
	
	public Button buildButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aDisplayType) {
		IButton buttonClass = null;
		if (aDisplayType.equals(ButtonDisplay.CSS)) {
			buttonClass = new CSSButton();
		} else if (aDisplayType.equals(ButtonDisplay.TEXT)) {
			buttonClass = new TextButton();
		} else if (aDisplayType.equals(ButtonDisplay.IMAGE)) {
			buttonClass = new ImageButton();
		}
		return buttonClass.createButton(aMessage, aXPos, aYPos, aHandler);
	}

}
