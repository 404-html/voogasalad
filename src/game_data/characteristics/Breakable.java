/*
 * Authors: Alex + Austin
 * 
 */

package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_data.sprites.Projectile;
import game_engine.Bottom;
import game_engine.Left;
import game_engine.Right;
import game_engine.Side;
import game_engine.Top;
import game_engine.actions.Action;
import game_engine.actions.Break;
//import javafx.geometry.Side;

@NameAnnotation(name = "Breakable")
public class Breakable implements Characteristic{

	private boolean breakableNorth;
	private boolean breakableSouth;
	private boolean breakableEast;
	private boolean breakableWest;
	private int myDurability;
	private Action myAction;
	private Sprite mySprite;
	private int timeSinceHit;
	
	@ParameterAnnotation(parameters = {"Breaks on Top", "Breaks on Bottom", "Breaks on Left", "Breaks on Right", "Durability", "Sprite"})
	public Breakable(boolean north, boolean south, boolean east, boolean west, int durability, Sprite aSprite){
		breakableNorth = north;
		breakableSouth = south;
		breakableEast = east;
		breakableWest = west;
		myDurability = durability;
		mySprite = aSprite;
		timeSinceHit = 0;
	}
	
	public void setDurability(int durability){
		myDurability = durability;
	}
	
	@ViewableMethodOutput(description = "Durability", type=int.class)
	public int getDurability() {
		return myDurability;
	}
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap){
		//System.out.println("durability" + myDurability);
		if(inUnbreakablePeriod()) {
			return;
		}
		
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			if(breaksAtDirection(myCollisionMap.get(collidedSprite)) && validPairing(collidedSprite)){
//				System.out.println("SIDE HIT: "+myCollisionMap.get(collidedSprite));
				System.out.println("fuckking fuck");
				timeSinceHit = 0;
				if(isBroken()) {
					System.out.println("is breaking");
					myAction = new Break(mySprite);
					myAction.act();
				}
			}
		}
		
	}
	private boolean validPairing(Sprite collidedSprite){
		return (mySprite instanceof Projectile || collidedSprite instanceof Player);
	}
	
	private boolean inUnbreakablePeriod() {
		timeSinceHit++;
		return timeSinceHit < 20;
	}
	
	private boolean isBroken() {
		myDurability--;
		return myDurability<=0;
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

	@Override
	public Characteristic copy() {
		return new Breakable(breakableNorth, breakableSouth, breakableEast, breakableWest, myDurability, mySprite);
	}

	@ViewableMethodOutput(description="Breaks on Top", type=boolean.class)
	public boolean breaksOnTop(){
		//return breaksAtDirection(Side.TOP);
		return breaksAtDirection(new Top());
	}
	
	@ViewableMethodOutput(description="Breaks on Left", type=boolean.class)
	public boolean breaksOnLeft(){
		//return breaksAtDirection(Side.LEFT);
		return breaksAtDirection(new Left());
	}
	
	@ViewableMethodOutput(description="Breaks on Bottom", type=boolean.class)
	public boolean breaksOnBottom(){
		//return breaksAtDirection(Side.BOTTOM);
		return breaksAtDirection(new Bottom());
	}
	
	@ViewableMethodOutput(description="Breaks on Right", type=boolean.class)
	public boolean breaksOnRight(){
		//return breaksAtDirection(Side.RIGHT);
		return breaksAtDirection(new Right());
	}
}