package game_engine.actions;

import game_data.Sprite;

import states.*;


public class Damage implements Action {
	
	private int damageToGive;
	private Sprite myDamageTaker;
	
	public Damage(int damage, Sprite damageTaker){
		damageToGive = damage;
		myDamageTaker = damageTaker;
	}
	
	@Override
	public void act() {
		for(State i : myDamageTaker.getStates()){
			if (i instanceof Health){
				((Health) i).updateState(damageToGive);
				if(((Health) i).getMyHealth()<=0){
					((Health) i).kill();
				}
			}
		}
		
	}

}
