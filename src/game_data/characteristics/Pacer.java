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
import game_data.sprites.Player;
import game_engine.actions.Pace;
import javafx.geometry.Side;

@NameAnnotation(name = "Pacer")
public abstract class Pacer implements Characteristic{
	
	private double myDistance;
	private double originalXPosition;
	private double originalYPosition;
	private Sprite mySprite;

	public Pacer(double distance, Sprite associatedSprite) {
		myDistance = distance;
		mySprite = associatedSprite;
		originalXPosition = associatedSprite.getMyLocation().getXLocation();
		originalYPosition = associatedSprite.getMyLocation().getYLocation();
	}
	
	public Pacer(Sprite associatedSprite){
		myDistance = 0;
		mySprite = associatedSprite;
		originalXPosition = associatedSprite.getMyLocation().getXLocation();
		originalYPosition = associatedSprite.getMyLocation().getYLocation();
	}
	
	public double getDistance() {
		return myDistance;
	}

	public void setDistance(double myDistance) {
		this.myDistance = myDistance;
	}

	public double getOriginalXPosition() {
		return originalXPosition;
	}

	public void setOriginalXPosition(double originalXPosition) {
		this.originalXPosition = originalXPosition;
	}

	public double getOriginalYPosition() {
		return originalYPosition;
	}

	public void setOriginalYPosition(double originalYPosition) {
		this.originalYPosition = originalYPosition;
	}

	public Sprite getSprite() {
		return mySprite;
	}
	
	public abstract boolean changeDirection(boolean collision);
	
	public boolean collisionOtherThanPlayer(Map<Sprite, Side> myCollisionMap){
		int count = 0;
		for(Sprite s: myCollisionMap.keySet()){
			if(!(s instanceof Player)){
				count++;
			}
		}
		return count>0;
	}
	
}
