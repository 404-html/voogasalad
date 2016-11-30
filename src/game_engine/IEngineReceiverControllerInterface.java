package game_engine;

import java.util.List;

import javafx.scene.input.KeyCode;
import sun.awt.SunHints.Key;

public interface IEngineReceiverControllerInterface {
	public List<KeyCode> getKeysBeingPressed();
	public String getXMLRoute();
	
}
