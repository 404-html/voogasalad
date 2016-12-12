package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.WinLevel;
//import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */
@NameAnnotation(name = "Winnable")
public class Winnable implements Characteristic{
	
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters={"Sprite"})
	public Winnable(Sprite aSprite){
		mySprite = aSprite;
	}

	@Override
	public Characteristic copy(Sprite aSprite) {
		return new Winnable(aSprite);
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute win action
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			if(collidedSprite instanceof Player){
				//System.out.println("poop if winning");
				WinLevel winlevel=new WinLevel(collidedSprite);
				winlevel.act();
			}
		}
	}


}
