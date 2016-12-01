package gameplayer.back_end.keycode_handler;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class KeyCodeHandler {
	
	private Map<KeyCode, Double> myKeyCodeMap;
	
	public KeyCodeHandler() {
		myKeyCodeMap = new HashMap<KeyCode, Double>();
		myKeyCodeMap.put(KeyCode.RIGHT, -3.3);
		myKeyCodeMap.put(KeyCode.LEFT, 3.3);
	}
	
	public double getMovement(KeyCode aKey) {
		if (myKeyCodeMap.containsKey(aKey)) return myKeyCodeMap.get(aKey);
		return 0;
	}

}
