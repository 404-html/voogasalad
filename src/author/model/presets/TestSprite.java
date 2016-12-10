package author.model.presets;

import game_data.Location;
import game_data.Sprite;
import game_data.sprites.Player;

public enum TestSprite {
	MARIO(new Player(new Location(0, 0), 20, 20, 0, 0, "Mario", "author/images/MarioSMBW.png")),
	DUKE(new Player(new Location(10, 10), 20, 20, 0, 0, "Duke", "author/images/duke.gif"));
	
	Sprite mySprite;
	
	private TestSprite(Sprite aSprite){
		mySprite = aSprite;
	}
	
	public Sprite getSprite(){
		return mySprite;
	}
	
}
