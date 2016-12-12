package game_data.characteristics;

import java.util.List;
import game_engine.Side;
import game_engine.actions.Action;
import game_engine.actions.MovableTriggered;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
//import javafx.geometry.Side;
import game_data.sprites.Player;

@NameAnnotation(name="Moveable")
public class Movable implements Characteristic{

	  private Sprite mySprite;
		
		
		@ParameterAnnotation(parameters= {"Sprite"})
		public Movable(Sprite aSprite){
			mySprite = aSprite;
		}
		
		
		@Override
		public void execute(Map<Sprite, Side> myCollisionMap) {
			
			for(Sprite collidedSprite:myCollisionMap.keySet()){
				if(collidedSprite instanceof Player){
				Action myAction = new MovableTriggered(mySprite, collidedSprite, myCollisionMap.get(collidedSprite));
				myAction.act();}}
		}

		@Override
		public Characteristic copy() {
			return new Movable(mySprite);
		}

	}
