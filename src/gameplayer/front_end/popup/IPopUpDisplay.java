package gameplayer.front_end.popup;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public interface IPopUpDisplay {

	public void show();
	
	public void setOnClosed(EventHandler<WindowEvent> aHandler);

}
