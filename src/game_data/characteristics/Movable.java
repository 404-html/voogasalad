package game_data.characteristics;

import java.util.List;
import java.util.Map;

import game_data.Sprite;
import javafx.geometry.Side;

public class Movable implements Characteristic{

	Sprite mySprite;
	List<String> arguments;
	
	public Movable(Sprite aSprite){
		mySprite = aSprite;
		//arguments.add(e)
	}
	
//	public List<String> getArguments(){
//	}
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		
	}

	@Override
	public Characteristic copy() {
		return new Movable(mySprite.clone());
	}

}
