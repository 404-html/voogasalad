package game_engine.actions;

import game_data.Sprite;
import states.LevelWon;
import states.State;

public class WinLevel implements Action {
	private Sprite mySprite;
	public WinLevel(Sprite aSprite) {
		mySprite=aSprite;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		for(State s: mySprite.getStates()){
			if(s instanceof LevelWon){
				((LevelWon)s).setHasWon(true);
			}
		}
	}

}
