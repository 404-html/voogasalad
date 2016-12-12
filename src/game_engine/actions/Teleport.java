package game_engine.actions;

import game_data.Sprite;

public class Teleport implements Action {

	private Sprite mySprite;
	private double myXLocation, myYLocation;
	
	public Teleport(Sprite aSprite, double xLoc, double yLoc) {
		mySprite = aSprite;
		myXLocation = xLoc;
		myYLocation = yLoc;
	}
	
	@Override
	public void act() {
		mySprite.getLocation().setLocation(myXLocation, myYLocation);
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new Teleport(aSprite, myXLocation, myXLocation);
	}
	
}
