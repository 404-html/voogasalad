package game_engine.actions;

import game_data.Sprite;
import game_data.states.*;


public class ScoreAdder extends PointsChanger implements Action {

	
	public ScoreAdder(int damage, Sprite damageTaker){
		super(damage, damageTaker);
	}
	
	@Override
	public void act() {
		for(State i : spriteAffected.getStates()){
			if (i instanceof Health){
				((Health) i).updateState(pointsToGive);
				if(((Health) i).getMyHealth()<=0){
					((Health) i).kill();
				}
			}
		}

		
	}

}
