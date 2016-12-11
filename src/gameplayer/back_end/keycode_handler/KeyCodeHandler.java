package gameplayer.back_end.keycode_handler;

import java.util.HashSet;
import java.util.Set;

import game_data.Sprite;
import gameplayer.application_controller.KeyCodeTranslator;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class KeyCodeHandler {

	private Set<KeyCode> myKeySet;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private KeyCodeTranslator myKeyCodeTranslator;
	private Node myMainPlayerImage;
	
	public KeyCodeHandler(String aInput) {
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myKeyCodeTranslator = new KeyCodeTranslator(aInput);
	}
	
	public KeyCodeHandler(){
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myKeyCodeTranslator = new KeyCodeTranslator("Default");
	}
	
	public void addMainPlayer(Node node) {
		myMainPlayerImage = node;
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
	
	public void remove(KeyCode aKeyCode){
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}
	
	public void clearReleased() {
		myKeysReleased.clear();
	}
	
	public void handleKeyPress(KeyCode aKeyCode){
		myKeysPressed.add(myKeyCodeTranslator.getCode(aKeyCode));
		myKeySet.add(myKeyCodeTranslator.getCode(aKeyCode));
		if (aKeyCode.equals(KeyCode.LEFT)) {
		    myMainPlayerImage.setRotate(180);
		} else if (aKeyCode.equals(KeyCode.RIGHT)) {
			myMainPlayerImage.setRotate(0);
		}
	}
	
	public void handleKeyRelease(KeyCode aKeyCode) {
		myKeysReleased.add(myKeyCodeTranslator.getCode(aKeyCode));
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}
}
