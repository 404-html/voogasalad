package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.Bottom;
import game_engine.Left;
import game_engine.Right;
import game_engine.Side;
import game_engine.Top;
import game_engine.actions.Action;
import game_engine.actions.Break;
import game_engine.actions.Damage;
//import javafx.geometry.Side;

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
	
	@ParameterAnnotation(parameters = {"Damages on Top", "Damages on Bottom", "Damages on Left", "Damages on Right", "Amount of Damage", "Sprite"})
	public Damager(boolean north, boolean south, boolean east, boolean west, int damageToGive, Sprite aSprite){
		breakableNorth = north;
		breakableSouth = south;
		breakableEast = east;
		breakableWest = west;
		myDamageToGive = damageToGive;
		mySprite = aSprite;
	}
	
	@ViewableMethodOutput(description="Amount of Damage", type=int.class)
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
		if(aDirection instanceof Top) {
			return breakableNorth;
		} else if(aDirection instanceof Bottom) {
			return breakableSouth;
		} else if(aDirection instanceof Right) {
			return breakableEast;
		} else if(aDirection instanceof Left) {
			return breakableWest;
		} 
		return false;
	}
	
	@ViewableMethodOutput(description="Breaks on Top", type=boolean.class)
	public boolean breaksOnTop(){
		return breaksAtDirection(new Top());
	}
	
	@ViewableMethodOutput(description="Breaks on Left", type=boolean.class)
	public boolean breaksOnLeft(){
		return breaksAtDirection(new Left());
	}
	
	@ViewableMethodOutput(description="Breaks on Bottom", type=boolean.class)
	public boolean breaksOnBottom(){
		return breaksAtDirection(new Bottom());
	}
	
	@ViewableMethodOutput(description="Breaks on Right", type=boolean.class)
	public boolean breaksOnRight(){
		return breaksAtDirection(new Right());
	}

}
