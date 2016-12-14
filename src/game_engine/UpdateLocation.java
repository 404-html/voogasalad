package game_engine;

import game_data.Location;
import game_data.Sprite;
import game_data.states.Physics;
import game_data.states.State;
/**
 * @author Austin, Katrina
 *
 */
public class UpdateLocation {
	private Sprite mySprite;
	private double myTimeElapsed;
	private Physics mySpritePhysics;
	
	public UpdateLocation(Sprite sprite, double timeElapsed) {
		myTimeElapsed=timeElapsed;
		mySprite=sprite;
		mySpritePhysics=null;
		for(State s: mySprite.getStates()){
			if(s instanceof Physics){
				mySpritePhysics = (Physics) s;
			}
		}
	}
	public void updateSpriteParameters(){
		mySprite.setXVelocity(calculateNewXVelocity());
		mySprite.setYVelocity(calculateNewYVelocity());
		mySprite.setLocation(calculateNewLocation());
		mySprite.setXAcceleration(0);
		mySprite.setYAcceleration(0);
	}
	public double calculateNewXVelocity(){
		if(mySpritePhysics != null)
			return mySprite.getXVelocity() + (mySpritePhysics.getHorizontalGravity() + mySprite.getXAcceleration())*myTimeElapsed;
		else
			return mySprite.getXVelocity();
	}
	public double calculateNewYVelocity(){
		if(mySpritePhysics != null)
			return mySprite.getYVelocity() + (mySpritePhysics.getVerticalGravity() + mySprite.getYAcceleration())*myTimeElapsed;	
		else 
			return mySprite.getYVelocity();
	}
	public Location calculateNewLocation(){
		Location myCurrentLocation = mySprite.getLocation();
		double curXLoc = myCurrentLocation.getXLocation();
		double curYLoc = myCurrentLocation.getYLocation();
		double myXLocation = curXLoc + calculateNewXVelocity()*myTimeElapsed;
		double myYLocation = curYLoc + calculateNewYVelocity()*myTimeElapsed;
		// update the location of the mySprite
//		Location myNewLocation = new Location(myXLocation, myYLocation, Math.asin(newXVelocity/newYVelocity));
		return new Location(myXLocation, myYLocation);
	}

}
