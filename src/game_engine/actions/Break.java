package game_engine.actions;

import game_data.Sprite;
import game_data.Level;
import game_data.states.Health;
import game_data.states.State;

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
		myLevel.removeSprite(myBrokenSprite);
		System.out.println("what even does instance of health mean");
/*		for(State myState:myBrokenSprite.getStates()){
			if (myState instanceof Health){
				System.out.println("what even does instance of health mean");
				((Health) myState).kill();
			}
		}*/
	}
}
