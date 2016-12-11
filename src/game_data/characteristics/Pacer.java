/*
 * Author: Alex and Austin
 * 
 * if toAct() is true run the following:
 * getSpeed()
 * 
 */

package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Terrain;
import game_engine.Side;
import game_engine.actions.Action;
import game_engine.actions.Pace;
//import javafx.geometry.Side;

@NameAnnotation(name = "Pacer")
public class Pacer implements Characteristic{

	private double myDistanceNeeded, myDistanceTraveled;
	private Sprite mySprite;
	private double previousNonZeroXVelocity, previousNonZeroYVelocity;

	@ParameterAnnotation(parameters = { "Distance Needed", "Sprite" })
	public Pacer(double distance, Sprite associatedSprite) {
		myDistanceTraveled = 0;
		myDistanceNeeded = distance;
		mySprite = associatedSprite;
		previousNonZeroXVelocity = 0;
		previousNonZeroYVelocity = 0;
	}
	
	@ViewableMethodOutput(description="Distance Needed", type=double.class)
	public double getDistanceNeeded() {
		return myDistanceNeeded;
	}
	
	private boolean goneFarEnough() {
		if(myDistanceTraveled >= myDistanceNeeded) {
			myDistanceTraveled = 0;
			return true;
		}
		return false;
	}
	
	private void updateMyDistanceTraveled() {
		myDistanceTraveled += getTimeStepDistance();
	}
	
	private void updateStoredVelocities() {
		previousNonZeroXVelocity = mySprite.getXVelocity() == 0 ? previousNonZeroXVelocity : mySprite.getXVelocity();
		previousNonZeroYVelocity = mySprite.getYVelocity() == 0 ? previousNonZeroYVelocity : mySprite.getYVelocity();
	}
	
	private void makeSureNonZeroVelocities() {
		if(mySprite.getXVelocity() == 0) {
			mySprite.setXVelocity(previousNonZeroXVelocity*-1);
			myDistanceTraveled = 0;
		}
		if(mySprite.getYVelocity() == 0) {
			mySprite.setYVelocity(previousNonZeroYVelocity*-1);
			myDistanceTraveled = 0;
		}
	}
	
	private double getTimeStepDistance() {
		return Math.sqrt(Math.pow(mySprite.getXVelocity(), 2) + Math.pow(mySprite.getYVelocity(),2))/60;
	}
	
	private boolean shouldChangeDirection(boolean collision) {
		return collision || goneFarEnough();
	}
	
	private boolean isCollisionOtherThanPlayer(Map<Sprite, Side> myCollisionMap){
		for(Sprite s: myCollisionMap.keySet()){
			if(s instanceof Terrain && myCollisionMap.get(s).isHorizontal()){
				myDistanceTraveled = 0; //if you run into something, reset distance traveled
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		updateMyDistanceTraveled();
		updateStoredVelocities();
		Action pace = new Pace(mySprite, shouldChangeDirection(isCollisionOtherThanPlayer(myCollisionMap)));
		pace.act();
		makeSureNonZeroVelocities();
	}
	
	@Override
	public Characteristic copy() {
		return new Pacer(myDistanceNeeded, mySprite);
	}
	
}
