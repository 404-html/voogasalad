package gameplayer.back_end.keycode_handler;

import java.util.HashSet;
import java.util.Set;

import gameplayer.application_controller.KeyCodeTranslator;
import javafx.scene.input.KeyCode;

public class KeyCodeHandler {

	private Set<KeyCode> myKeySet;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private KeyCodeTranslator myKeyCodeTranslator;
	
	public KeyCodeHandler(String aInput) {
		myKeySet = new HashSet<KeyCode>();
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		myKeyCodeTranslator = new KeyCodeTranslator(aInput);
	}
	
	public void addToKeySet(KeyCode aKeyCode){
		myKeySet.add(myKeyCodeTranslator.getCode(aKeyCode));
	}
	
	public void addKeyPressed(KeyCode aKeyCode){
		myKeysPressed.add(myKeyCodeTranslator.getCode(aKeyCode));
	}
	
	public void addKeyReleased(KeyCode aKeyCode){
		myKeysReleased.add(myKeyCodeTranslator.getCode(aKeyCode));
	}
	
	public Set<KeyCode> getKeySet(){
		return myKeySet;
	}
	
	public Set<KeyCode> getKeysPressed(){
		return myKeysPressed;
	}
	
	public Set<KeyCode> getKeysReleased(){
		return myKeysReleased;
	}
	
	public void remove(KeyCode aKeyCode){
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}
	
	public void clearReleased() {
		myKeysReleased.clear();
	}
}
