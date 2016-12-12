package game_engine.actions;

//import javafx.geometry.Side;
import game_data.Sprite;

public class Shoot implements Action {
	
	private Sprite myPlayerSprite;
	

	public Shoot(Sprite player) {
		myPlayerSprite = player;
	}

	@Override
	public void act() {
		
		
	}
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new Shoot(aSprite);
	}
	
	
}

