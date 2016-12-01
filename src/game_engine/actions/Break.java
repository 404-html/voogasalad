package game_engine.actions;

import game_data.Sprite;
import states.Health;
import states.State;
import game_data.Level;

public class Break implements Action {
	private Sprite myBrokenSprite;
	private Sprite myPlayerSprite;
	private Level myLevel;
	
	public Break(Sprite aBrokenSprite, Sprite aPlayerSprite) {
		myBrokenSprite=aBrokenSprite;
		myPlayerSprite=aPlayerSprite;
	}

	@Override
	public void act() {
		//remove the bouncing characteristic and make a "solid" thing
	//	myPlayerSprite.setMyVelocity(-myPlayerSprite.getMyVelocity());		
	//	myLevel.removeSprite(myBrokenSprite);
		for(State myState:myBrokenSprite.getStates()){
			if (myState instanceof Health){
				((Health) myState).kill();
			}
		}
	}
}
