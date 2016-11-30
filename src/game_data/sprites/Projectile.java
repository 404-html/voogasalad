package game_data.sprites;

import game_data.Location;
import game_data.Sprite;

/**
 * @author austingartside
 *
 */
public class Projectile extends Sprite{
	
	public Projectile(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath) {
		super(aLocation, aWidth, aHeight, aName, aImagePath);
	}
	
	public Projectile(Projectile aProjectile){
		super(aProjectile);
	}

	@Override
	public Sprite clone() {
		return new Projectile(this);
	}

}