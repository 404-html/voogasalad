package game_engine.actions;

import game_data.Sprite;
import game_data.states.LevelWon;
import game_data.states.State;

public class WinLevel implements Action {
	private Sprite mySprite;
	public WinLevel(Sprite aSprite) {
		mySprite=aSprite;
	}

	@Override
	public void act() {
		for(State s: mySprite.getStates()){
			if(s instanceof LevelWon){
				//System.out.println("pooooop");
				((LevelWon)s).setHasWon(true);
				//System.out.println(((LevelWon)s).isHasWon());
			}
		}
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new WinLevel(aSprite);
	}
	
}
