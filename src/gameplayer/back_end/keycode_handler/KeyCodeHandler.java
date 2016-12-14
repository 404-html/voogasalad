package gameplayer.back_end.keycode_handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import game_engine.actions.Action;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import gameplayer.application_controller.KeyCodeTranslator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class KeyCodeHandler {

	private Set<KeyCode> myKeySet;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private KeyCodeTranslator myKeyCodeTranslator;
	private List<ImageView> myMainPlayerImage;

	public KeyCodeHandler(String aInput) {
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myMainPlayerImage = new ArrayList<ImageView>();
		myKeyCodeTranslator = new KeyCodeTranslator(aInput);
	}

	public KeyCodeHandler() {
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myKeyCodeTranslator = new KeyCodeTranslator("Default");
	}

	public void addMainPlayer(List<ImageView> aImageList) {
		myMainPlayerImage = aImageList;
	}

	public Set<KeyCode> getKeySet() {
		return myKeySet;
	}

	public Set<KeyCode> getKeysPressed() {
		return myKeysPressed;
	}

	public Set<KeyCode> getKeysReleased() {
		return myKeysReleased;
	}

	public void remove(KeyCode aKeyCode) {
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}

	public void clearReleased() {
		myKeysReleased.clear();
	}

	public void handleKeyPress(KeyCode aKeyCode, Map<KeyCode, Action> aKeyMap) {
		myKeysPressed.add(myKeyCodeTranslator.getCode(aKeyCode));
		myKeySet.add(myKeyCodeTranslator.getCode(aKeyCode));
		
		KeyCode leftKey = null;
		KeyCode rightKey = null;
		
		for(Entry<KeyCode, Action> e : aKeyMap.entrySet()) {
			if(leftKey == null && e.getValue() instanceof MoveLeft)
				leftKey = e.getKey();
			if(rightKey == null && e.getValue() instanceof MoveRight)
				rightKey = e.getKey();
		}
				
		if (aKeyCode == leftKey || myKeyCodeTranslator.getCode(aKeyCode).equals(KeyCode.LEFT)) {
			for (int i = 0; i < myMainPlayerImage.size(); i++) {
				myMainPlayerImage.get(i).setRotate(180);
			}
		} else if (aKeyCode == rightKey || myKeyCodeTranslator.getCode(aKeyCode).equals(KeyCode.RIGHT)) {
			for (int i = 0; i < myMainPlayerImage.size(); i++) {
				myMainPlayerImage.get(i).setRotate(0);
			}
		}
	}
	
	public boolean checkNoKeysPressed() {
		return myKeySet.isEmpty();
	}

	public void handleKeyRelease(KeyCode aKeyCode) {
		myKeysReleased.add(myKeyCodeTranslator.getCode(aKeyCode));
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}
}
