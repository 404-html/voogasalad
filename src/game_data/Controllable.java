package game_data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import game_data.characteristics.Characteristic;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Terrain;
import game_engine.GameResources;
import game_engine.actions.Action;
import game_engine.actions.Move;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUpJump;
import javafx.geometry.Side;
import javafx.scene.input.KeyCode;

@CharacteristicAnnotation(name = "Controllable")
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
		isControllable=false;
	}
	public Controllable(Sprite aSprite, Map<KeyCode, Action> myKeyPressedMap) {
		this.mySprite=aSprite;
		this.myKeyPressedMap=myKeyPressedMap;
		isControllable=true;
		myKeyReleasedMap=new HashMap<KeyCode, Move>();
		
		generateMyKeyReleasedMap();
	}
	public boolean isControllable(){
		return isControllable;
	}
	private void generateMyKeyReleasedMap(){
		for(KeyCode key: myKeyPressedMap.keySet()){
			if(myKeyPressedMap.get(key) instanceof MoveRight){		
				System.out.println(myKeyPressedMap.get(key));
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
		runKeyCalls(myCollisionMap);
		runKeyReleased();
		// TODO Auto-generated method stub
	}
	private void runKeyCalls(Map<Sprite, Side> myCollisionMap) {
		for(KeyCode myKey: myKeysPressed){
			if(myKeyPressedMap.containsKey(myKey)){
				if((myKeyPressedMap.get(myKey) instanceof MoveUpJump)){
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
				if (myCollisionMap.get(s).equals(Side.BOTTOM));
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
	private void generateDefaultKeyPressedMap() {
		//System.out.println(GameResources.MOVE_RIGHT_SPEED.getDoubleResource());
		//System.out.println(myLevel.getMainPlayer()==null);
		myKeyPressedMap.put(KeyCode.RIGHT, new MoveRight(mySprite, GameResources.MOVE_RIGHT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.LEFT, new MoveLeft(mySprite, GameResources.MOVE_LEFT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.UP, new MoveUpJump(mySprite, GameResources.JUMP_SPEED.getDoubleResource()));
		//myKeyPressedMap.put(KeyCode.SPACE, new Launch(myLevel.getMainPlayer(), 10, 0));
	}

}
