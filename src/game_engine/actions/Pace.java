package game_engine.actions;

import game_data.Sprite;

public class Pace implements Action {
	//	private int direction;
	private Sprite mySprite;
	private String myType;
	private boolean myShouldChangeDirection;
	public Pace(Sprite aSprite, boolean aShouldChangeDirection, String type) {
		mySprite=aSprite;
		//direction=1;
		myType = type;
		myShouldChangeDirection=aShouldChangeDirection;
	}
	//this method/class needs to get updated based on moveWithHeading, which is also wrong because it needs to compile
	@Override
	public void act() {
		if(myShouldChangeDirection==true){
			if(myType.equals("HORIZONTAL")){
				mySprite.setMyXVelocity(-1*(mySprite.getMyXVelocity()));
			}
			else{
				mySprite.setMyYVelocity(-1*(mySprite.getMyYVelocity()));
			}
		}
		//MoveWithHeading moveWithHeading = new MoveWithHeading(mySprite, mySprite.getMyXVelocity());
		//moveWithHeading.act();
	}

}
