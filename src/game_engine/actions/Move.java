package game_engine.actions;

import game_data.Location;
import game_data.Sprite;
/**
 * can move sprites given the sprite and the instantaneous velocity of the sprite
 * Should we make it moveHorizontal and moveVertical and just input negative velocities when you want to move left and down?
 * idk 
 * 
 * @author Katrina Zhu
 */
public abstract class Move implements Action {
	protected Sprite mySprite;
	protected Location myOldLocation;
	protected Location myNewLocation;
	protected double myVelocity;
	public Move(Sprite aSprite, double aVelocity) {
		mySprite=aSprite;
		myVelocity=aVelocity;
		myOldLocation=mySprite.getLocation();
		myNewLocation = new Location(0, 0);
	}

	@Override
	public void act() {
		//mySprite.setLocation(changeCoordinates());
		setVelocity();
	}
	public abstract void stop();
	
	//public abstract Location changeCoordinates();
	public abstract void setVelocity();

	public void setMyVelocity(double newVel){
		myVelocity = newVel;
	}
	
	public double getVelocity(){
		return myVelocity;
	}
	
}
