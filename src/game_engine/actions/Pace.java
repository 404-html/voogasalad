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
	//this method/class needs to get updated based on moveWithHeading, which is also wrong because it needs to compile
	@Override
	public void act() {
		if(myShouldChangeDirection==true){
			direction=direction*(-1);
		}
		
		MoveWithHeading moveWithHeading = new MoveWithHeading(mySprite, mySprite.getMyXVelocity());
		moveWithHeading.act();
	}

}
