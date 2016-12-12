package game_data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import game_data.characteristics.Characteristic;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Item;
import game_data.sprites.Terrain;
import game_engine.Side;
import game_engine.Top;
import game_engine.actions.Action;
import game_engine.actions.Move;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUpJump;
////import javafx.geometry.Side;
import javafx.scene.input.KeyCode;

/**
 * @author Katrina
 *
 */
public class Controllable {
	private Sprite mySprite;
	private Map<KeyCode, Action> myKeyPressedMap;
	private Map<KeyCode, Move> myKeyReleasedMap;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private boolean isControllable;
//	private Map<KeyCode, Action> defaultKeyPressedMap;


	public Controllable() {
		isControllable = false;
		myKeyPressedMap = new HashMap<>();
		myKeyReleasedMap = new HashMap<>();
		myKeysReleased = new HashSet<>();
		myKeysPressed = new HashSet<>();
//		defaultKeyPressedMap = new HashMap<KeyCode,Action>();
	}

	public Controllable(Sprite aSprite, Map<KeyCode, Action> myKeyPressedMap) {
		this.mySprite = aSprite;
		isControllable = true;
//		defaultKeyPressedMap = new HashMap<KeyCode,Action>();
		myKeyReleasedMap = new HashMap<>();
		myKeysReleased = new HashSet<>();
		myKeysPressed = new HashSet<>();
		this.myKeyPressedMap = myKeyPressedMap;
//		defaultKeyPressedMap = null;
	}
	
	private Map<KeyCode, Action> makeCopy(Map<KeyCode, Action> aKeyPressedMap) {
		Map<KeyCode, Action> ans = new HashMap<KeyCode, Action>();
		for(KeyCode k : aKeyPressedMap.keySet()) {
			ans.put(k, aKeyPressedMap.get(k));
		}
		return ans;
	}
	
	public Controllable(Controllable that, Sprite aSprite){
		mySprite = aSprite;
		this.isControllable = that.isControllable;
		this.myKeyPressedMap = copyKeyPressedMap(that.myKeyPressedMap, aSprite);
		this.myKeyReleasedMap = that.myKeyReleasedMap;
		this.myKeysPressed = that.myKeysPressed;
		this.myKeysReleased = that.myKeysReleased;
	}

	public boolean isControllable() {
		return isControllable;
	}

	private Map<KeyCode, Action> copyKeyPressedMap(Map<KeyCode, Action> aOriginalMap, Sprite aSprite){
		Map<KeyCode, Action> map = new HashMap<>();
		
		aOriginalMap.entrySet().forEach( e -> {
			map.put(e.getKey(), e.getValue().copyWithNewSprite(aSprite));
		});
		
		return map;
	}
	
	private void generateMyKeyReleasedMap() {
		for (KeyCode key : myKeyPressedMap.keySet()) {
			if (myKeyPressedMap.get(key) instanceof MoveRight) {
				myKeyReleasedMap.put(key, (MoveRight) myKeyPressedMap.get(key));
			} else if (myKeyPressedMap.get(key) instanceof MoveLeft) {
				myKeyReleasedMap.put(key, (MoveLeft) myKeyPressedMap.get(key));
			}
		}
	}

	public void sendCurrentKeys(Set<KeyCode> myKeysPressed, Set<KeyCode> myKeysReleased) {
		this.myKeysPressed = myKeysPressed;
		this.myKeysReleased = myKeysReleased;
	}

	public void execute(Map<Sprite, Side> myCollisionMap) {
		generateMyKeyReleasedMap();
		runKeyCalls(myCollisionMap);
		runKeyReleased();
	}

	private void runKeyCalls(Map<Sprite, Side> myCollisionMap) {
		for (KeyCode myKey : myKeysPressed) {
			if (myKeyPressedMap.containsKey(myKey)) {
				if ((myKeyPressedMap.get(myKey) instanceof MoveUpJump)) {
					if (isTerrainOnBottom(myCollisionMap)) {
						myKeyPressedMap.get(myKey).act();
					}
				} else {
					myKeyPressedMap.get(myKey).act();
				}
			}
		}
	}

	private boolean isTerrainOnBottom(Map<Sprite, Side> myCollisionMap) {
		for (Sprite s : myCollisionMap.keySet()) {
			if (s instanceof Terrain || s instanceof Item) {
				// if (myCollisionMap.get(s).equals(Side.BOTTOM));
				
				
//				if (myCollisionMap.get(s) instanceof Top){
//					System.out.println("TOP");
					return true;
			}
		}
		return false;
	}

	private void runKeyReleased() {
		for (KeyCode myKey : myKeysReleased) {
			if (myKeyReleasedMap.containsKey(myKey)) {
				myKeyReleasedMap.get(myKey).stop();
			}
		}
	}

	public Map<KeyCode, Action> getMyKeyPressedMap() {
		return myKeyPressedMap;
	}

	public void setMyKeyPressedMap(Map<KeyCode, Action> myKeyPressedMap) {	
		this.myKeyPressedMap = myKeyPressedMap;
	}

//	public void resetMyKeyPressedMap() {
////		System.out.println("size of map before" + myKeyPressedMap.size());		
////		
//////		myKeyPressedMap = new HashMap<KeyCode, Action>(defaultKeyPressedMap);
////		HashMap<KeyCode, Action> ans;
////		for(KeyCode k : defaultKeyPressedMap.keySet()) {
//////			ans.put(new KeyCode(k), new )
////		}
////		System.out.println("size of map reset" + myKeyPressedMap.size());
//		myKeyPressedMap = null;
//
//	}

}