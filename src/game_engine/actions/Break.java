package game_engine.actions;

import game_data.Sprite;
import game_data.Level;
import game_data.states.Health;
import game_data.states.State;

public class Break implements Action {
	private Sprite myBrokenSprite;
	
	public Break(Sprite aBrokenSprite) {
		myBrokenSprite=aBrokenSprite;
	}

	@Override //SOOOOOO UGLY
	public void act() {
		
		boolean hasHealth = false;
		
		for(State myState:myBrokenSprite.getStates()){
			if (myState instanceof Health){
				hasHealth = true;
				((Health) myState).kill();
			}
		}
		
		if(!hasHealth) {
			Health newState = new Health(1);
			myBrokenSprite.addState(new Health(1));
			newState.kill();
		}
		
	}
}
