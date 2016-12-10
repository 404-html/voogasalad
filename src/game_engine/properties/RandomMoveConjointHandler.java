package game_engine.properties;

import game_data.Sprite;

//THIS IS IN PROGRESS
public class RandomMoveConjointHandler {

	public enum Orientation {
		HORIZONTAL, VERTICAL;
	}
	private Orientation myOrientation;
	private Sprite mySprite;
	private Sprite myConnectedSprite;
	private double myScreenWidth, myScreenHeight, myScreenXPosition, myScreenYPosition;
	
	public RandomMoveConjointHandler(Sprite Connected, Orientation aOrientation, double aDistanceApart) {
		myOrientation = aOrientation;
	}
	
	public RandomMoveConjointHandler(RandomMoveConjointHandler aRandomMoveConjointHandler) {
		myOrientation = aRandomMoveConjointHandler.getMyOrientation();
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
			return mySprite.getMyLocation().getYLocation() > myScreenYPosition+myScreenHeight;
		} else {
			return mySprite.getMyLocation().getXLocation()+mySprite.getMyWidth() < myScreenXPosition;
		}
	}
	
	private void setSpritesNewLocation() {
		
		double newXLoc, newYLoc;
		if(myOrientation.equals(Orientation.VERTICAL)) {
			newXLoc = Math.random()*myScreenWidth;
			newYLoc = mySprite.getMyLocation().getYLocation()-myScreenHeight-mySprite.getMyHeight();
		} else {
			newXLoc = mySprite.getMyLocation().getXLocation()+myScreenWidth+mySprite.getMyWidth();
			newYLoc = Math.random()*myScreenHeight;
		}
		mySprite.getMyLocation().setLocation(newXLoc, newYLoc);
		
	}
	
	public Orientation getMyOrientation() {
		return myOrientation;
	}
	
}
