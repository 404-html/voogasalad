package game_data.sprites.builders.defaults;

import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.sprites.Projectile;
import game_data.sprites.builders.SpriteDefaultBuilder;
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
					// Add Char here
	};
	
	private final State[] STATES = 
			new State[]{ 
				 new Physics(0, 0),
			};
	
	private static final String NAME = "Projectile_Name";

	@Override
	public Sprite build() {
		for(Characteristic characteristic : CHARACTERISTICS) {
			projectile.addCharacteristic(characteristic);
		}
		for(State state : STATES) {
			projectile.addState(state);
		}
		projectile.setLocation(new Location(0, 0));
		projectile.setWidth(WIDTH);
		projectile.setHeight(HEIGHT);
		projectile.setImagePath(EmptyImage.INSTANCE.getFile().toString());
		projectile.setName(NAME);
		return projectile;
	}

}
