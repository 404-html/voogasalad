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
	private SpritePhysics mySpritePhysics;
	
	public UpdateLocation(Sprite sprite, double timeElapsed) {
		myTimeElapsed=timeElapsed;
		mySprite=sprite;
		mySpritePhysics=null;
		for(State s: mySprite.getStates()){
			if(s instanceof Physics){
				mySpritePhysics = ((Physics) s).getPhysics();
			}
		}
	}
	public void updateSpriteParameters(){
		mySprite.setMyXVelocity(calculateNewXVelocity());
		mySprite.setMyYVelocity(calculateNewYVelocity());
		mySprite.setMyLocation(calculateNewLocation());
		mySprite.setMyXAcceleration(0);
		mySprite.setMyYAcceleration(0);
	}
	public double calculateNewXVelocity(){
		return mySprite.getMyXVelocity() + (mySpritePhysics.getHorizontalGravity() + mySprite.getMyXAcceleration())*myTimeElapsed;
	}
	public double calculateNewYVelocity(){
		return mySprite.getMyYVelocity() + (mySpritePhysics.getVerticalGravity() + mySprite.getMyYAcceleration())*myTimeElapsed;	
	}
	public Location calculateNewLocation(){
		Location myCurrentLocation = mySprite.getMyLocation();
		double curXLoc = myCurrentLocation.getXLocation();
		double curYLoc = myCurrentLocation.getYLocation();
		double myXLocation = curXLoc + calculateNewXVelocity()*myTimeElapsed;
		double myYLocation = curYLoc + calculateNewYVelocity()*myTimeElapsed;
		// update the location of the mySprite
//		Location myNewLocation = new Location(myXLocation, myYLocation, Math.asin(newXVelocity/newYVelocity));
		return new Location(myXLocation, myYLocation);
	}

}
