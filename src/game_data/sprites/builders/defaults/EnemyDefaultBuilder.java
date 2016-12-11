package game_data.sprites.builders.defaults;

import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.sprites.Enemy;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Health;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.GameResources;

/**
 * Builds the default enemy when a new enemy is created
 * @author Jordan Frazier
 */
public class EnemyDefaultBuilder implements SpriteDefaultBuilder {

	private Sprite enemy = new Enemy();

	private final Characteristic[] CHARACTERISTICS = new Characteristic[] {

	};

	private final State[] STATES = new State[] { 
			new Physics(GameResources.DEFAULT_VERTICAL_GRAVITY.getDoubleResource(), GameResources.DEFAULT_HORIZONTAL_GRAVITY.getDoubleResource()),
			new Health(1)
	};

	private static final String NAME = "Enemy_Name";


	@Override
	public Sprite build() {
		for(Characteristic characteristic : CHARACTERISTICS) {
			enemy.addCharacteristic(characteristic);
		}
		for(State state : STATES) {
			enemy.addState(state);
		}
		enemy.setLocation(new Location(0, 0));
		enemy.setWidth(WIDTH);
		enemy.setHeight(HEIGHT);
		enemy.setImagePath(EmptyImage.INSTANCE.getFile().toString());
		enemy.setName(NAME);
		return enemy;
	}

}
