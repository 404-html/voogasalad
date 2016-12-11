package gameplayer.application_controller;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class KeyCodeTranslator {

	private Map<KeyCode, KeyCode> myMap;
	
	public KeyCodeTranslator(String aKeyInput){
		myMap = new HashMap<KeyCode, KeyCode>();
		if (aKeyInput.toLowerCase().equals("left")) {
			myMap.put(KeyCode.A, KeyCode.LEFT);
			myMap.put(KeyCode.W, KeyCode.UP);
			myMap.put(KeyCode.S, KeyCode.DOWN);
			myMap.put(KeyCode.D, KeyCode.RIGHT);
		} else {
			myMap.put(KeyCode.LEFT, KeyCode.LEFT);
			myMap.put(KeyCode.UP, KeyCode.UP);
			myMap.put(KeyCode.DOWN, KeyCode.DOWN);
			myMap.put(KeyCode.RIGHT, KeyCode.RIGHT);
		}
	}
	
	public KeyCode getCode(KeyCode aKeyCode) {
		return myMap.containsKey(aKeyCode) || myMap.values().contains(aKeyCode) ? myMap.get(aKeyCode) : aKeyCode;
	}
}
