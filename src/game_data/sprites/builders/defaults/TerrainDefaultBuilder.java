package game_data.sprites.builders.defaults;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.Impassable;
import game_data.sprites.Terrain;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.SpritePhysics;

public class TerrainDefaultBuilder implements SpriteDefaultBuilder {
	
	private Sprite terrain = new Terrain();
	
	private final Characteristic[] CHARACTERISTICS = new Characteristic[] {
			new Impassable(terrain)
	};

	private final State[] STATES = new State[] { 
			 new Physics(new SpritePhysics(0.0))
	};

	private static final String NAME = "Terrain_Name";

	@Override
	public Sprite build() {
//		Sprite player = new Terrain();
		for(Characteristic characteristic : CHARACTERISTICS) {
			terrain.addCharacteristic(characteristic);
		}
		for(State state : STATES) {
			terrain.addState(state);
		}
		terrain.setMyLocation(new Location(0, 0));
		terrain.setMyWidth(WIDTH);
		terrain.setMyHeight(HEIGHT);
		terrain.setMyImagePath(EmptyImage.INSTANCE.getFile().toString());
		terrain.setName(NAME);
		return terrain;
	}

}
