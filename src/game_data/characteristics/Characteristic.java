package game_data.characteristics;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

import game_data.Sprite;
import game_engine.Side;
////import javafx.geometry.Side;
import javafx.scene.input.KeyCode;

public interface Characteristic {
	
	
	/**
	 * Method implemented by all characteristics (i.e. breakable, movable etc.). What happens for each characteristic every
	 * time step. 
	 * 
	 * @author Austin Gartside
	 * @param myKeysReleased 
	 * @param myKeysPressed 
	 */
	//public boolean toAct();
	public void execute(Map<Sprite, Side> myCollisionMap);
	public Characteristic copy();

}