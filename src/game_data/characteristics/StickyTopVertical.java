package game_data.characteristics;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

/*
 * Author: alex
 */

public class StickyTopVertical extends StickyTop {
	
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters={"Sprite"})
	public StickyTopVertical(Sprite aSprite){
		super(aSprite,false);
	}
	
	@Override
	public Characteristic copy() {
		return new StickyTopVertical(mySprite);
	}

}
