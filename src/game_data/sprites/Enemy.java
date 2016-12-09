package game_data.sprites;

import game_data.Location;
import game_data.Sprite;

public class Enemy extends Character {

	public Enemy(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath) {
		super(aLocation, aWidth, aHeight, aName, aImagePath);
	}
	
	public Enemy(Enemy aEnemy){
		super(aEnemy);
	}
	
	public Enemy() {
		super();
	}

	@Override
	public Sprite clone() {
		return new Enemy(this);
	}

}
