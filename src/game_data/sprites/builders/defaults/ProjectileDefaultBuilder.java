package game_data.sprites.builders.defaults;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.sprites.Projectile;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Health;
import game_data.states.LevelWon;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.SpritePhysics;

public class ProjectileDefaultBuilder implements SpriteDefaultBuilder {

	private Sprite projectile = new Projectile();

	private final Characteristic[] CHARACTERISTICS = 
			new Characteristic[]{
					// Add Char here
	};
	
	private final State[] STATES = 
			new State[]{
				 new Physics(new SpritePhysics(0.0)),
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
		projectile.setMyLocation(new Location(0, 0));
		projectile.setMyWidth(WIDTH);
		projectile.setMyHeight(HEIGHT);
		projectile.setMyImagePath(EmptyImage.INSTANCE.getFile().toString());
		projectile.setName(NAME);
		return projectile;
	}

}
