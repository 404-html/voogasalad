package game_data.sprites;

import java.util.HashMap;
import java.util.Map;

import game_data.Location;
import game_data.Sprite;
import game_engine.actions.Action;

public class Player extends Character {
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


}
