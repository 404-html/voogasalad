package game_engine.actions;

import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.SpeedPowerUpper;
import game_data.states.State;
import game_data.states.Vincibility;
import game_engine.GameResources;

public class Invincibility implements Action {

	private Sprite myPlayerSprite;

	public Invincibility(Sprite playerSprite) {
		myPlayerSprite = playerSprite;
	}
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new Invincibility(aSprite);
	}
	
	@Override
	public void act() {
		boolean hasState = false;
		for(State state:myPlayerSprite.getStates()){
//			System.out.println("vincibility changes");
			if(state instanceof Vincibility){
				((Vincibility) state).setVincibility(false);
				hasState = true;
			}
		}
		if(!hasState){
			myPlayerSprite.getStates().add(new Vincibility(false));
		}
	}

}
