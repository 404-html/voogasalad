package game_engine.properties;

import game_data.Sprite;

public abstract class RandomMoveHandler {

	public enum Orientation {
		HORIZONTAL, VERTICAL, NULL;
	}
	private Orientation myOrientation;
	private Sprite mySprite;
	private double myScreenWidth, myScreenHeight, myScreenXPosition, myScreenYPosition;
	
	public RandomMoveHandler(Orientation aOrientation) {
		myOrientation = aOrientation;
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
		if(getOrientation().equals(Orientation.VERTICAL)) {
			return mySprite.getLocation().getYLocation() > myScreenYPosition+myScreenHeight;
		} else {
			return mySprite.getLocation().getXLocation()+mySprite.getWidth() < myScreenXPosition;
		}
	}
	
	protected abstract void setSpritesNewLocation();
	public abstract RandomMoveHandler copy();
	
	public Orientation getOrientation() {
		return myOrientation;
	}
	
	public Sprite getSprite() {
		return mySprite;
	}

	public double getScreenWidth() {
		return myScreenWidth;
	}

	public double getScreenHeight() {
		return myScreenHeight;
	}

	public double getScreenXPosition() {
		return myScreenXPosition;
	}

	public double getScreenYPosition() {
		return myScreenYPosition;
	}
	
}
