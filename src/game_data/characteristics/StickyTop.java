package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.actions.Action;
import game_engine.actions.HitTop;
import game_engine.actions.Stick;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "StickyTop")
public class StickyTop implements Characteristic{
	
	private Sprite mySprite;
	private Action myAction;
	
	@ParameterAnnotation(parameters={"Sprite"})
	public StickyTop(Sprite aSprite){
		mySprite = aSprite;
	}
	
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap){
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			myAction = new Stick(collidedSprite, mySprite);
			myAction.act();
		}
	}

	@Override
	public Characteristic copy() {
		return new StickyTop(mySprite);
	}

}
