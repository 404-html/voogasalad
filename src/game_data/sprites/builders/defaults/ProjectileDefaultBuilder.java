package game_data.sprites.builders.defaults;

import java.io.File;

import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Breakable;
import game_data.characteristics.Characteristic;
import game_data.characteristics.Damager;
import game_data.characteristics.Impassable;
import game_data.sprites.Projectile;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Health;
import game_data.states.Physics;
import game_data.states.State;

/**
 * Creates a new default projectile when new projectile is created
 * @author Jordan Frazier
 *
 */
public class ProjectileDefaultBuilder implements SpriteDefaultBuilder {

	private Sprite projectile = new Projectile();

	private final Characteristic[] CHARACTERISTICS = 
			new Characteristic[]{
					new Breakable(true, true, true, true, 1, projectile),
					new Damager(true, true, true, true, 1, projectile)
	};
	
	private final State[] STATES = 
			new State[]{ 
				 new Physics(0, 0), 
				 new Health(1),
			};
	
	private static final String NAME = "Projectile_Name";

	@Override
	public Sprite buildDefault() {
		for(Characteristic characteristic : CHARACTERISTICS) {
			projectile.addCharacteristic(characteristic);
		}
		for(State state : STATES) {
			projectile.addState(state);
		}
		projectile.setLocation(new Location(0, 0));
		projectile.setWidth(10);
		projectile.setHeight(10);
		System.out.println("building the default");
		projectile.setImagePath("data/images/sprite_images/Bullet_Bill_SMW_SNES_Sprite_Right.png");
		projectile.setName(NAME);
		return projectile;
	}

}
