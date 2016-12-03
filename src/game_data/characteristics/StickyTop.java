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
public abstract class StickyTop implements Characteristic{
	
	private Sprite mySprite;
	private Action myAction;
	private boolean horizontal;
	
	@ParameterAnnotation(parameters={"Sprite"})
	public StickyTop(Sprite aSprite, boolean hori){
		mySprite = aSprite;
		horizontal = hori;
	}
	
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap){
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			myAction = new Stick(collidedSprite, mySprite, horizontal);
			myAction.act();
		}
	}

}
