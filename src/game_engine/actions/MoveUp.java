package game_engine.actions;

import game_data.Location;
import game_data.Sprite;

public class MoveUp extends Move {

	public MoveUp(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}
/*	@Override
	public Location changeCoordinates(){
		myNewLocation.setLocation(myOldLocation.getXLocation(), myOldLocation.getYLocation()-myVelocity);
		return myNewLocation;
	}*/
	public void setVelocity(){
		mySprite.setMyYVelocity(-myVelocity);
	}

}