package game_data.sprites.builders.defaults;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.sprites.Item;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.SpritePhysics;

/**
 * Creates a new default item when a new item is created
 * @author Jordan Frazier
 *
 */
public class ItemDefaultBuilder implements SpriteDefaultBuilder {

	private Sprite item = new Item();
	
	private final Characteristic[] CHARACTERISTICS = new Characteristic[] {
			
	};

	private final State[] STATES = new State[] { 
			 new Physics(0, 0) 
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
		
		item.setLocation(new Location(0, 0));
		item.setWidth(WIDTH);
		item.setHeight(HEIGHT);
		item.setImagePath(EmptyImage.INSTANCE.getFile().toString());
		item.setName(NAME);
		return item;
	}
 
}
