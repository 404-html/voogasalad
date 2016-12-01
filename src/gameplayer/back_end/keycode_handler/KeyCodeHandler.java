package gameplayer.back_end.keycode_handler;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class KeyCodeHandler {
	
	private Map<KeyCode, Integer> myKeyCodeMap;
	
	public KeyCodeHandler() {
		myKeyCodeMap = new HashMap<KeyCode, Integer>();
		myKeyCodeMap.put(KeyCode.RIGHT, -10);
		myKeyCodeMap.put(KeyCode.LEFT, 10);
	}
	
	public int getMovement(KeyCode aKey) {
		if (myKeyCodeMap.containsKey(aKey)) return myKeyCodeMap.get(aKey);
		return 0;
	}

}
