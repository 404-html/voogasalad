package game_data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import game_data.characteristics.Characteristic;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Terrain;
import game_engine.Bottom;
import game_engine.GameResources;
import game_engine.Side;
import game_engine.Top;
import game_engine.actions.Action;

import game_engine.actions.Launch;
import game_engine.actions.LaunchWithLevel;
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
@NameAnnotation(name = "Controllable")
public class Controllable {
	private Sprite mySprite;
	private Map<KeyCode, Action> myKeyPressedMap;
	private Map<KeyCode, Move> myKeyReleasedMap;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private boolean isControllable;
	@ParameterAnnotation(parameters="Sprite")
	public Controllable(Sprite aSprite){
		this.mySprite=aSprite;
		myKeyReleasedMap=new HashMap<KeyCode, Move>();
		isControllable=false;
	}
	public Controllable(Sprite aSprite, Map<KeyCode, Action> myKeyPressedMap) {
		this.mySprite=aSprite;
		this.myKeyPressedMap=myKeyPressedMap;
		isControllable=true;
		myKeyReleasedMap=new HashMap<KeyCode, Move>();
	}
	
	public boolean isControllable(){
		return isControllable;
	}
	private void generateMyKeyReleasedMap(){
		for(KeyCode key: myKeyPressedMap.keySet()){
			if(myKeyPressedMap.get(key) instanceof MoveRight){		
				myKeyReleasedMap.put(key, (MoveRight) myKeyPressedMap.get(key));
			}
			else if(myKeyPressedMap.get(key) instanceof MoveLeft){
				myKeyReleasedMap.put(key, (MoveLeft) myKeyPressedMap.get(key));
			}
		}
	}
	public void sendCurrentKeys(Set<KeyCode>myKeysPressed, Set<KeyCode>myKeysReleased){
		this.myKeysPressed=myKeysPressed;
		this.myKeysReleased=myKeysReleased;
	}
	
	public void execute(Map<Sprite, Side> myCollisionMap) {
		generateMyKeyReleasedMap();
		runKeyCalls(myCollisionMap);
		runKeyReleased();
	}
	private void runKeyCalls(Map<Sprite, Side> myCollisionMap) {
		for(KeyCode myKey: myKeysPressed){
			if(myKeyPressedMap.containsKey(myKey)){
				if((myKeyPressedMap.get(myKey) instanceof MoveUpJump)){
//					System.out.println("LUCIA");
					if(isTerrainOnBottom(myCollisionMap)){
						myKeyPressedMap.get(myKey).act();
					}
				}
				else{
					myKeyPressedMap.get(myKey).act();
				}
			}
		}	
	}
	private boolean isTerrainOnBottom(Map<Sprite, Side> myCollisionMap){
		for(Sprite s: myCollisionMap.keySet()){
			if(s instanceof Terrain){
				//if (myCollisionMap.get(s).equals(Side.BOTTOM));
				if(myCollisionMap.get(s) instanceof Top)
				return true;
			}
		}		
		return false;
	}
	private void runKeyReleased(){
		for(KeyCode myKey: myKeysReleased){
			if(myKeyReleasedMap.containsKey(myKey)){
				myKeyReleasedMap.get(myKey).stop();
			}
		}	
	}
	public Characteristic copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<KeyCode, Action> getMyKeyPressedMap(){
		return myKeyPressedMap;
	}
	public void setMyKeyPressedMap(Map<KeyCode, Action> myKeyPressedMap) {
		this.myKeyPressedMap = myKeyPressedMap;
	}


}