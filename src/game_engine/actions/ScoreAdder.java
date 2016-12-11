package game_engine.actions;

import game_data.Sprite;
import game_data.states.*;


public class ScoreAdder extends PointsChanger implements Action {

	
	public ScoreAdder(double score, Sprite damageTaker){
		super(score, damageTaker);
	}
	

	@Override
	public void act() {
		for(State i : spriteAffected.getStates()){
			if (i instanceof Score){
				//System.out.println(pointsToGive);
				((Score) i).updateState(pointsToGive);
//				if(((Health) i).getMyHealth()<=0){
//					((Health) i).kill();
//				}
			}
		}

		
	}

}
