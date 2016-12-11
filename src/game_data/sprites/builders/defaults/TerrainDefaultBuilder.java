package game_data.sprites.builders.defaults;

import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.Impassable;
import game_data.sprites.Terrain;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Physics;
import game_data.states.State;

/**
 * Creates new default terrain when new terrain is created
 * @author Jordan Frazier
 *
 */
public class TerrainDefaultBuilder implements SpriteDefaultBuilder {
	
	private Sprite terrain = new Terrain();
	
	private final Characteristic[] CHARACTERISTICS = new Characteristic[] {
			new Impassable(terrain)
	};

	private final State[] STATES = new State[] { 
			 new Physics(0,0)
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
		terrain.setLocation(new Location(0, 0));
		terrain.setWidth(WIDTH);
		terrain.setHeight(HEIGHT);
		terrain.setImagePath(EmptyImage.INSTANCE.getFile().toString());
		terrain.setName(NAME);
		return terrain;
	}

}
