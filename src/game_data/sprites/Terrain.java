package game_data.sprites;

import game_data.Location;
import game_data.Sprite;

public class Terrain extends Sprite {

	public Terrain(Location aLocation, int aWidth, int aHeight, double xVelocity, double yVelocity, String aName, String aImagePath) {
		super(aLocation, aWidth, aHeight, xVelocity, yVelocity, aName, aImagePath);
	}

	public Terrain(Terrain aTerrain) {
		super(aTerrain);
	}

	public Terrain() {
		super();
	}

	@Override
	public Sprite clone() {
		return new Terrain(this);
	}

}
