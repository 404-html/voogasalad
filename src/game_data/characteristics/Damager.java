package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.actions.Action;
import game_engine.actions.Break;
import game_engine.actions.Damage;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@NameAnnotation(name = "Damager")
public class Damager implements Characteristic{
	
	private boolean breakableNorth;
	private boolean breakableSouth;
	private boolean breakableEast;
	private boolean breakableWest;
	private int myDamageToGive;
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters = {"Breaks on Top", "Breaks on Bottom", "Breaks on Left", "Breaks on Right", "Amount of Damage", "Sprite"})
	public Damager(boolean north, boolean south, boolean east, boolean west, int damageToGive, Sprite aSprite){
		breakableNorth = north;
		breakableSouth = south;
		breakableEast = east;
		breakableWest = west;
		myDamageToGive = damageToGive;
		mySprite = aSprite;
	}
	
	@ViewableMethodOutput(description="Damage", type=int.class)
	public int getDamage(){
		return myDamageToGive;
	}

	@Override
	public Characteristic copy() {
		return new Damager(breakableNorth, breakableSouth, breakableEast, breakableWest, myDamageToGive, mySprite);
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			if(breaksAtDirection(myCollisionMap.get(collidedSprite)) && collidedSprite instanceof Player){
			Action myAction = new Damage(myDamageToGive, collidedSprite);
			myAction.act();
			}
		}
		
	}
	
	private boolean breaksAtDirection(Side aDirection) {
		if(aDirection == Side.TOP) {
			return breakableNorth;
		} else if(aDirection == Side.BOTTOM) {
			return breakableSouth;
		} else if(aDirection == Side.RIGHT) {
			return breakableEast;
		} else if(aDirection == Side.LEFT) {
			return breakableWest;
		} 
		return false;
	}
	
	@ViewableMethodOutput(description="Breaks on Top", type=boolean.class)
	public boolean breaksOnTop(){
		return breaksAtDirection(Side.TOP);
	}
	
	@ViewableMethodOutput(description="Breaks on Left", type=boolean.class)
	public boolean breaksOnLeft(){
		return breaksAtDirection(Side.LEFT);
	}
	
	@ViewableMethodOutput(description="Breaks on Bottom", type=boolean.class)
	public boolean breaksOnBottom(){
		return breaksAtDirection(Side.BOTTOM);
	}
	
	@ViewableMethodOutput(description="Breaks on Right", type=boolean.class)
	public boolean breaksOnRight(){
		return breaksAtDirection(Side.RIGHT);
	}

}
