package game_engine.actions;

import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.Damager;
import game_data.characteristics.ICharactericticAction;
import game_data.characteristics.InvincibilityPowerUpper;
import game_data.states.*;
import game_engine.GameResources;
import game_engine.UpdateStates;


public class AddHealth extends PointsChanger implements Action {

	int myDamage;
	
	public AddHealth(int damage, Sprite damageTaker){
		super(damage, damageTaker);
		myDamage = damage;
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

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new AddHealth(myDamage, aSprite);
	}

}
