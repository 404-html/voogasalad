package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

/*
 * Author: alex
 */

public class StickyTopHorizontal extends StickyTop {
	
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters={"Sprite"})
	public StickyTopHorizontal(Sprite aSprite){
		super(aSprite,true);
	}
	
	@Override
	public Characteristic copy() {
		return new StickyTopHorizontal(mySprite);
	}

}
