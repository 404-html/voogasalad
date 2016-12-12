package game_engine.actions;

import game_data.Location;
import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class MoveLeft extends Move {

	public MoveLeft(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}
/*	@Override
	public Location changeCoordinates(){
		myNewLocation.setLocation(myOldLocation.getXLocation()-myVelocity, myOldLocation.getYLocation());
		//myNewLocation.setMyHeading(180);
		return myNewLocation;
	}*/
	public void setVelocity(){
		mySprite.setXVelocity(-myVelocity);
	}
	public void stop(){
		if(mySprite.getXVelocity()<0){
			mySprite.setXVelocity(0);
//			mySprite.setXVelocity(mySprite.getXVelocity()+myVelocity);
		}
	}
}