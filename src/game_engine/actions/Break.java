package game_engine.actions;

import game_data.Sprite;
import game_data.sprites.Projectile;
import game_data.Level;
import game_data.states.Health;
import game_data.states.State;


public class Break implements Action {
	private Sprite myBrokenSprite;
	
	public Break(Sprite aBrokenSprite) {
		myBrokenSprite=aBrokenSprite;
	}

	@Override
	public void act() {
		for(State myState:myBrokenSprite.getStates()){
			if (myState instanceof Health){
				((Health) myState).kill();

			}
		}
		
	}
	
	
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new Break(aSprite);
	}
}