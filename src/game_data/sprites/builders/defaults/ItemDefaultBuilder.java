package game_data.sprites.builders.defaults;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.Impassable;
import game_data.sprites.Item;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.SpritePhysics;

public class ItemDefaultBuilder implements SpriteDefaultBuilder {

	private Sprite item = new Item();
	
	private final Characteristic[] CHARACTERISTICS = new Characteristic[] {
			
	};

	private final State[] STATES = new State[] { 
			 new Physics(new SpritePhysics(0.0))
	};

	private static final String NAME = "Item_Name";

	@Override
	public Sprite build() {
//		Sprite player = new Terrain();
		for(Characteristic characteristic : CHARACTERISTICS) {
			item.addCharacteristic(characteristic);
		}
		for(State state : STATES) {
			item.addState(state);
		}
		item.setMyLocation(new Location(0, 0));
		item.setMyWidth(WIDTH);
		item.setMyHeight(HEIGHT);
		item.setMyImagePath(EmptyImage.INSTANCE.getFile().toString());
		item.setName(NAME);
		return item;
	}
 
}
