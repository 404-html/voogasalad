package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_data.sprites.Terrain;
import game_engine.actions.Pace;
//import javafx.geometry.Side;

/**
 * @author austingartside
 * alternative version of pacer where user does not to include bounds, but rather how far it can travel
 */

public class VerticalPacer extends Pacer{
	
	@ParameterAnnotation(parameters = {"Distance", "Sprite"})
	public VerticalPacer(double distance, Sprite associatedSprite){
		super(distance, associatedSprite);
	}
	
	public VerticalPacer(Sprite associatedSprite){
		super(associatedSprite);
	}
	
	@Override
	public boolean changeDirection(boolean collision){
		return atYBound() || collision;
	}
	
	private boolean atYBound(){
		double currentYLocation = getSprite().getLocation().getYLocation();
		return currentYLocation>getOriginalYPosition() || currentYLocation<(getOriginalYPosition()-getDistance());			
	}

	@Override
	public Characteristic copy() {
		return new VerticalPacer(getDistance(), getSprite().clone());
	}
	
	@Override
	public boolean collisionOtherThanPlayer(Map<Sprite, Side> myCollisionMap){
		int count = 0;
		for(Sprite s: myCollisionMap.keySet()){
			if(s instanceof Terrain && myCollisionMap.get(s).isVertical()){
				count++;
			}
		}
		return count>0;
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		Pace pace=new Pace(getSprite(), changeDirection(collisionOtherThanPlayer(myCollisionMap)));
		pace.act();
	}
}
