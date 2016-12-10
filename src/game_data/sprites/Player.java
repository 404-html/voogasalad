package game_data.sprites;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import game_data.Level;
import game_data.LevelSetter;
import game_data.Location;
import game_data.Sprite;
import game_engine.actions.Action;
import game_engine.actions.Launch;
import game_engine.actions.LaunchProxy;

public class Player extends Character implements LevelSetter {
	public Player(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath) {
		super(aLocation, aWidth, aHeight, aName, aImagePath);
	}
	
	public Player(Player aPlayer){
		super(aPlayer);
	}

	@Override
	public Sprite clone() {
		return new Player(this);
	}

	@Override
	public void setLevel(Level aLevel) {
		Collection<Action> playerActions = this.getControllable().getMyKeyPressedMap().values();
		for(Action action: playerActions){
			if(action instanceof LaunchProxy){
				((LaunchProxy) action).setLevel(aLevel);
			}
		}		
	}


}
