package game_engine.properties;

import game_data.Sprite;
import game_engine.properties.RandomMoveHandler.Orientation;

//THIS IS IN PROGRESS
public class RandomMoveConjointHandler {

	private Orientation myOrientation;
	private Sprite mySprite;
	private Sprite myConnectedSprite;
	private double myScreenWidth, myScreenHeight, myScreenXPosition, myScreenYPosition;
	private double myDistanceApart;
	
	public RandomMoveConjointHandler(Sprite aConnectedSprite, Orientation aOrientation, double aDistanceApart) {
		myConnectedSprite = aConnectedSprite;
		myOrientation = aOrientation;
		myDistanceApart = aDistanceApart;
	}
	
	public RandomMoveConjointHandler(RandomMoveConjointHandler aRandomMoveConjointHandler) {
		if(aRandomMoveConjointHandler != null) {
			myConnectedSprite = aRandomMoveConjointHandler.getConnectedSprite();
			myOrientation = aRandomMoveConjointHandler.getOrientation();
			myDistanceApart = aRandomMoveConjointHandler.getDistanceApart();
		}
	}
	
	public void move(Sprite aMySprite, double aMyScreenWidth, double aMyScreenHeight, 
			double aMyScreenXPosition, double aMyScreenYPosition) {
		
		mySprite = aMySprite;
		myScreenWidth = aMyScreenWidth;
		myScreenHeight = aMyScreenHeight;
		myScreenXPosition = aMyScreenXPosition*-1; //switch to different coordinates
		myScreenYPosition = aMyScreenYPosition*-1; //switch to different coordinates
		
		if(objectPassed()) {
			setSpritesNewLocation();
		}
		
	}
	
	private boolean objectPassed() {
		if(myOrientation.equals(Orientation.VERTICAL)) {
			return mySprite.getLocation().getYLocation() > myScreenYPosition+myScreenHeight;
		} else {
			return mySprite.getLocation().getXLocation()+mySprite.getWidth() < myScreenXPosition;
		}
	}
	
	private void setSpritesNewLocation() {
		
		double newXLoc, newYLoc;
		if(myOrientation.equals(Orientation.VERTICAL)) {
			newXLoc = Math.random()*(myScreenWidth-myDistanceApart) - mySprite.getWidth();
			newYLoc = mySprite.getLocation().getYLocation()-myScreenHeight - mySprite.getHeight();
		} else {
			newXLoc = mySprite.getLocation().getXLocation()+myScreenWidth + mySprite.getWidth();
			newYLoc = Math.random()*(myScreenHeight-myDistanceApart) - mySprite.getHeight();
		}
		mySprite.getLocation().setLocation(newXLoc, newYLoc);
		if(myOrientation.equals(Orientation.VERTICAL)) {
			newXLoc += myDistanceApart + mySprite.getWidth();
		} else {
			newYLoc += myDistanceApart + mySprite.getHeight();
		}
		myConnectedSprite.getLocation().setLocation(newXLoc,newYLoc);
		
	}
	
	public Orientation getOrientation() {
		return myOrientation;
	}
	
	public Sprite getConnectedSprite() {
		return myConnectedSprite;
	}

	public double getDistanceApart() {
		return myDistanceApart;
	}
	
}
