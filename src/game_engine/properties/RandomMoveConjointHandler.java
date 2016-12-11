package game_engine.properties;

import game_data.Sprite;

public class RandomMoveConjointHandler extends RandomMoveHandler {

	private Sprite myConnectedSprite;
	private double myDistanceApart;
	
	public RandomMoveConjointHandler(Sprite aConnectedSprite, Orientation aOrientation, double aDistanceApart) {
		super(aOrientation);
		myConnectedSprite = aConnectedSprite;
		myDistanceApart = aDistanceApart;
	}
	
	protected void setSpritesNewLocation() {
		
		double newXLoc, newYLoc;
		if(getOrientation().equals(Orientation.VERTICAL)) {
			newXLoc = Math.random()*( getScreenWidth() - getSprite().getWidth() );
			newYLoc = getSprite().getLocation().getYLocation() - getScreenHeight() - getSprite().getHeight();
		} else {
			newXLoc = getSprite().getLocation().getXLocation() + getScreenWidth() + getSprite().getWidth();
			newYLoc = Math.random()*( getScreenHeight() - getSprite().getHeight() );
		}
		getSprite().getLocation().setLocation(newXLoc, newYLoc);
		
		if(getOrientation().equals(Orientation.VERTICAL)) {
			newXLoc += myDistanceApart + getSprite().getWidth();
		} else {
			newYLoc += myDistanceApart + getSprite().getHeight();
		}
		myConnectedSprite.getLocation().setLocation(newXLoc,newYLoc);
		
	}
	
	public void setConnectedSprite(Sprite aSprite) {
		myConnectedSprite = aSprite;
	}
	
	public void setDistanceApart(double distance) {
		myDistanceApart = distance;
	}
	
	public Sprite getConnectedSprite() {
		return myConnectedSprite;
	}

	public double getDistanceApart() {
		return myDistanceApart;
	}
	
}
