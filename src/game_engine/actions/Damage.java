package game_engine.actions;

import game_data.Sprite;
import game_data.states.*;


public class Damage extends PointsChanger implements Action {

	
	public Damage(int damage, Sprite damageTaker){
		super(damage, damageTaker);
	}
	
	@Override
	public void act() {
		//looop through all states, if it contains health check boolean 
		boolean hasHealth = false;
		State health = null;
		boolean isInvincible = false; 
		
		for(State i : spriteAffected.getStates()){
			if (i instanceof Health){
				hasHealth = true;
				health = i;
			}
				else if(i instanceof Vincibility){
					if(!((Vincibility) i).isVincibility())
						isInvincible = true;
				}
			
		}
		
		if(!isInvincible && hasHealth){
			((Health) health).updateState(pointsToGive);
			if(((Health) health).getMyHealth()<=0){
				((Health) health).kill();
			}
		}
		
	}

}
