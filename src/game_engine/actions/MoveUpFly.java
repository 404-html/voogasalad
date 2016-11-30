package game_engine.actions;

import game_data.Location;
import game_data.Sprite;

public class MoveUpFly extends Move {

	public MoveUpFly(Sprite aSprite, double aVelocity) {
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