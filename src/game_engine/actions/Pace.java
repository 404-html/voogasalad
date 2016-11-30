package game_engine.actions;

import game_data.Sprite;

public class Pace implements Action {
	private int direction;
	private Sprite mySprite;
	private boolean myShouldChangeDirection;
	public Pace(Sprite aSprite, boolean aShouldChangeDirection) {
		mySprite=aSprite;
		direction=1;
		myShouldChangeDirection=aShouldChangeDirection;
	}

	@Override
	public void act() {
		if(myShouldChangeDirection==true){
			direction=direction*(-1);
			mySprite.getMyLocation().setMyHeading(mySprite.getMyLocation().getMyHeading()+(180*direction));
		}
		MoveWithHeading moveWithHeading = new MoveWithHeading(mySprite, mySprite.getMyVelocity());
		moveWithHeading.act();
	}

}
