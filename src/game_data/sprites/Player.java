package game_data.sprites;

import java.util.Collection;

import game_data.Level;
import game_data.LevelSetter;
import game_data.Location;
import game_data.Sprite;
import game_engine.actions.Action;

public class Player extends Character implements LevelSetter {
	public Player(Location aLocation, int aWidth, int aHeight, double xVelocity, double yVelocity, String aName, String aImagePath) {
		super(aLocation, aWidth, aHeight, xVelocity, yVelocity, aName, aImagePath);
	}
	public Player() {
		super();
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
			if(action instanceof LevelSetter){
				((LevelSetter) action).setLevel(aLevel);
			}
		}		
	}


}
