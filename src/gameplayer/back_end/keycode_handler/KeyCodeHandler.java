package gameplayer.back_end.keycode_handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	
	public void remove(KeyCode aKeyCode){
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}
	
	public void clearReleased() {
		myKeysPressed.clear();
		myKeysReleased.clear();
	}
	
	public void handleKeyPress(KeyCode aKeyCode){
		myKeysPressed.add(myKeyCodeTranslator.getCode(aKeyCode));
		myKeySet.add(myKeyCodeTranslator.getCode(aKeyCode));
		if (myKeyCodeTranslator.getCode(aKeyCode).equals(KeyCode.LEFT)) {
			for (int i = 0; i < myMainPlayerImage.size(); i++) {
				myMainPlayerImage.get(i).setRotate(180);
			}
		} else if (myKeyCodeTranslator.getCode(aKeyCode).equals(KeyCode.RIGHT)) {
			for (int i = 0; i < myMainPlayerImage.size(); i++) {
				myMainPlayerImage.get(i).setRotate(0);
			}
		}
	}
	
	public void handleKeyRelease(KeyCode aKeyCode) {
		myKeysReleased.add(myKeyCodeTranslator.getCode(aKeyCode));
		myKeySet.remove(myKeyCodeTranslator.getCode(aKeyCode));
		myKeysPressed.remove(myKeyCodeTranslator.getCode(aKeyCode));
	}
}
