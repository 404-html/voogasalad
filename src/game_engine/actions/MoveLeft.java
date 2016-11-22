package game_engine.actions;

import game_data.Location;
import game_data.Sprite;

public class MoveLeft extends Move {

	public MoveLeft(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}
	@Override
	public Location changeCoordinates(){
		myNewLocation.setLocation(myOldLocation.getXLocation()-myVelocity, myOldLocation.getYLocation());
		myNewLocation.setMyHeading(180);
		return myNewLocation;
	}
}