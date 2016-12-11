package game_data.sprites.builders.defaults;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.sprites.Player;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.states.Health;
import game_data.states.LevelWon;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.GameResources;
/**

 * Default player builder open to extension 
 * @author George Bernard, Jordan Frazier
 *
 */
public class PlayerDefaultBuilder implements SpriteDefaultBuilder {


	private final Characteristic[] CHARACTERISTICS = 
			new Characteristic[]{
					// Add Char here
	};
	
	private final State[] STATES = 
			new State[]{
				 new Physics(GameResources.DEFAULT_VERTICAL_GRAVITY.getDoubleResource(), GameResources.DEFAULT_HORIZONTAL_GRAVITY.getDoubleResource()),
				 new LevelWon(),
				 new Health(1)
			};
	
	private static final String NAME = "Player_Name";

	@Override
	public Sprite build() {
		Sprite player = new Player();
		for(Characteristic characteristic : CHARACTERISTICS) {
			player.addCharacteristic(characteristic);
		}
		for(State state : STATES) {
			player.addState(state);
		}
		player.setLocation(new Location(0, 0));
		player.setWidth(WIDTH);
		player.setHeight(HEIGHT);
		player.setImagePath(EmptyImage.INSTANCE.getFile().toString());
		player.setName(NAME);
		return player;
	}

}
