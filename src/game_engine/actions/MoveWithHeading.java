package game_engine.actions;

import game_data.Location;
import game_data.Sprite;
public class MoveWithHeading extends Move {
	
	public MoveWithHeading(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}

/*	@Override
	public Location changeCoordinates() {
		double heading = myOldLocation.getMyHeading();
		double radiansHeading = heading / 360 * (Math.PI*2);
		double xMovementDistance = Math.cos(radiansHeading)*myVelocity;
		double yMovementDistance = Math.sin(radiansHeading)*myVelocity;
		myNewLocation.setLocation(myOldLocation.getXLocation()+xMovementDistance, myOldLocation.getYLocation()+yMovementDistance);
		return myNewLocation;
	}*/
	//below lines aren't functional, just getting it to compile
	public void setVelocity(){
		mySprite.setYVelocity(-myVelocity);
	}
	public void stop(){
		return;
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new MoveWithHeading(aSprite, getVelocity());
	}
	
}
